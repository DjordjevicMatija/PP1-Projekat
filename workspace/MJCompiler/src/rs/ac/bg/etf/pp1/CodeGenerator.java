package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor{

    private int mainPc;

    public int getMainPc(){
        return mainPc;
    }

    int matrixStart;
    int matrixEnd;

    // CONST

    public void visit(ConstNumber constant){
        constant.obj.setAdr(constant.getN1());
    }

    public void visit(ConstChar constant){
        constant.obj.setAdr(constant.getC1());
    }

    public void visit(ConstBool constant){
        constant.obj.setAdr(constant.getB1() ? 1 : 0);
    }

    // METHOD

    public void visit(MethodTypeName methodName){
        methodName.obj.setAdr(Code.pc);

        SyntaxNode methodNode = methodName.getParent();

        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);

        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(fpCnt.getCount() + varCnt.getCount());
    }

    public void visit(MethodVoidName methodName){
        methodName.obj.setAdr(Code.pc);

        SyntaxNode methodNode = methodName.getParent();

        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);

        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(fpCnt.getCount() + varCnt.getCount());
    }

    public void visit(Method method){
        if(SemanticPass.checkMain(method)){
            mainPc = method.getMethodName().obj.getAdr();
        }

        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    // FACTOR

    public void visit(DesignFactor factorVar){
        Code.load(factorVar.getDesignator().obj);
    }

    public void visit(DesingFuncFactor desingFunc){
        Obj functionObj = desingFunc.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset); 
    }

    public void visit(FactorNumber constant){
		Code.load(new Obj(Obj.Con, "$", Tab.intType, constant.getN1(), 0));
    }

    public void visit(FactorChar constant){
		Code.load(new Obj(Obj.Con, "$", Tab.charType, constant.getC1(), 0));
    }

    public void visit(FactorBool constant){
		Code.load(new Obj(Obj.Con, "$", SemanticPass.boolType, constant.getB1() ? 1 : 0, 0));
    }

    public void visit(NewArrayFactor factorArray){
        Code.put(Code.newarray);
        if(factorArray.struct == Tab.charType){
            Code.put(0);
        }
        else{
            Code.put(1);
        }
    }

    public void visit(MatrixFactor matrixFactor){
        // pravimo novi niz
        Code.put(Code.dup);
        Code.put(Code.newarray);
        Code.put(1);

        // priprema za petlju
        Code.put(Code.dup2);
        Code.loadConst(0);
        matrixStart = Code.pc;
        Code.put(Code.dup_x2);
    }

    public void visit(NewMatrixFactor factorMatrix){
        // pravimo niz nizova
        Code.put(Code.newarray);
        if(factorMatrix.struct == Tab.charType){
            Code.put(0);
        }
        else{
            Code.put(1);
        }

        // provera uslova
        Code.put(Code.astore);      // 2 adr 0 2
        Code.put(Code.dup_x2);      // 2 2 adr 0 2
        Code.put(Code.dup2);        // 2 2 adr 0 2 0 2
        Code.put(Code.pop);         // 2 2 adr 0 2 0 
        Code.loadConst(1);        // 2 2 adr 0 2 0 1
        Code.put(Code.add);         // 2 2 adr 0 2 1 
        Code.put(Code.dup2);        // 2 2 adr 0 2 1 2 1
        Code.put(Code.pop);         // 2 2 adr 0 2 1 2
        Code.putFalseJump(Code.ne, 0); // 2 2 adr 0 2 
        matrixEnd = Code.pc - 2;
        Code.put(Code.pop);         // 2 2 adr 0 
        Code.loadConst(1);        // 2 2 addr 0 1
        Code.put(Code.add);         // 2 2 adr 1
        Code.put(Code.dup_x1);      // 2 2 1 adr 1
        Code.put(Code.pop);         // 2 2 1 adr
        Code.put(Code.dup_x2);      // 2 adr 2 1 adr
        Code.put(Code.dup_x1);      // 2 adr 2 adr 1 adr
        Code.put(Code.pop);         // 2 adr 2 adr 1
        Code.putJump(matrixStart);
        Code.fixup(matrixEnd);
        Code.put(Code.pop);         // 2 2 adr 0 
        Code.put(Code.pop); // 2 2 adr
        Code.put(Code.dup_x2); // adr 2 2 adr
        Code.put(Code.pop); // adr 2 2
        Code.put(Code.pop); // adr 2
        Code.put(Code.pop); // adr
    }

    // FACTOR LIST

    public void visit(Factors factors){
        if(factors.getMulop() instanceof MulopMul){
            Code.put(Code.mul);
        }
        else if(factors.getMulop() instanceof MulopDiv){
            Code.put(Code.div);
        }
        else{
            Code.put(Code.rem);
        }
    }

    // TERM LIST

    public void visit(TerminalList termList){
        if(termList.getAddop() instanceof AddopPlus){
            Code.put(Code.add);
        }
        else{
            Code.put(Code.sub);
        }
    }

    // EXPR

    public void visit(NegativeTermList expr){
        Code.put(Code.neg);
    }

    // STATEMENTS

    // UNMATCHED

    public void visit(UnmatchedIf unmatchedIf){

    }

    public void visit(UnmatchedIfElse unmatchedIfElse){

    }
    
    // MATCHED

    public void visit(MatchedIf matchedIf){

    }

    public void visit(BreakStmt breakStmt){

    }

    public void visit(ContinueStmt continueStmt){

    }

    public void visit(ReturnNoExpr returnStmt){
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    public void visit(ReturnExpr returnStmt){
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    public void visit(ReadStmt readStmt){
        if(readStmt.getDesignator().obj.getType() == Tab.charType){
            Code.put(Code.bread);
        }
        else{
            Code.put(Code.read);
        }
        Code.store(readStmt.getDesignator().obj);
    }

    public void visit(PrintExpr printStmt){
        Code.loadConst(0);
        if(printStmt.getExpr().obj.getType() == Tab.charType){
            Code.put(Code.bprint);
        }
        else{
            Code.put((Code.print));
        }
    }

    public void visit(PrintExprNumber printStmt){
        Code.loadConst(printStmt.getN2());
        if(printStmt.getExpr().obj.getType() == Tab.charType){
            Code.put(Code.bprint);
        }
        else{
            Code.put((Code.print));
        }
    }

    public void visit(ForLoop forLoop){

    }

    public void visit(ForStmt forStmt){

    }

    // DESIGNATOR STMT

    public void visit(DesignAssign designAssign){
        Code.store(designAssign.getDesignator().obj);
    }

    public void visit(DesingFunc desingFunc){
        Obj functionObj = desingFunc.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
        
        if(desingFunc.getDesignator().obj.getType() != Tab.noType){
            Code.put(Code.pop);
        }
    }

    public void visit(DesignInc designInc){
        Code.load(designInc.getDesignator().obj);
        Code.put(Code.const_1);
        Code.put(Code.add);
        Code.store(designInc.getDesignator().obj);
    }

    public void visit(DesignDec designDec){
        Code.load(designDec.getDesignator().obj);
        Code.put(Code.const_1);
        Code.put(Code.sub);
        Code.store(designDec.getDesignator().obj);
    }

    // DESIGNATOR

    public void visit(DesignatorName name){
        Code.load(name.obj);
    }

    public void visit(MatrixAload mat){
        Code.put(Code.aload);
    }

}
