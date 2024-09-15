package rs.ac.bg.etf.pp1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import org.apache.log4j.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

    public static final Struct boolType = new Struct(Struct.Bool);

    boolean errorDetected = false;

    Struct currentType = null;
    Obj currentMethod = null;

    boolean returnFound = false;
    boolean invalidMethodName = false;
    boolean mainExists = false;
    ArrayList<Struct> localParams = new ArrayList<>();

    boolean forStatementExists = false;
    // moze samo counter umesto steka
    Deque<ForStmt> forStatements = new ArrayDeque<>();

    boolean matrixError = false;

    int nVars;

    Logger log = Logger.getLogger(getClass());

    // LOGS

    public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder("\nSemanticka greska: ");
        msg.append(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}

    // PROGRAM

    public void visit(ProgName progName){
        progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
        Tab.openScope();
    }

    public void visit(Program program){
        nVars = Tab.currentScope.getnVars();
        Tab.chainLocalSymbols(program.getProgName().obj);
        Tab.closeScope();
        if(!mainExists){
            report_error("Ne postoji metoda void main()", program);
        }
    }

    // CONST

    public void visit(ConstNumber constant){
        if(currentType == Tab.intType){
            Obj obj = Tab.currentScope.findSymbol(constant.getConstName());
            if(obj == null){
                constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
            }
            else{
                report_error("Simbol " + constant.getConstName() + " je vec deklarisan", constant);
            }
        }
        else{
            report_error("Tip konstante " + constant.getConstName() + " nije ekvivalentan tipu proslednjenog podatka", constant);
        }
    }

    public void visit(ConstChar constant){
        if(currentType == Tab.charType){
            Obj obj = Tab.currentScope.findSymbol(constant.getConstName());
            if(obj == null){
                constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
            }
            else{
                report_error("Simbol " + constant.getConstName() + " je vec deklarisan", constant);
            }
        }
        else{
            report_error("Tip konstante " + constant.getConstName() + " nije ekvivalentan tipu proslednjenog podatka", constant);
        }
    }

    public void visit(ConstBool constant){
        if(currentType == boolType){
            Obj obj = Tab.currentScope.findSymbol(constant.getConstName());
            if(obj == null){
                constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
            }
            else{
                report_error("Simbol " + constant.getConstName() + " je vec deklarisan", constant);
            }
        }
        else{
            report_error("Tip konstante " + constant.getConstName() + " nije ekvivalentan tipu proslednjenog podatka", constant);
        }
    }

    // TYPE

    public void visit(Type type){
        Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
            report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", type);
			currentType = Tab.noType;
            type.struct = currentType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				currentType = typeNode.getType();
                type.struct = currentType;
			} 
			else {
                report_error("Ime " + type.getTypeName() + " ne predstavlja tip", type);
				currentType = Tab.noType;
                type.struct = currentType;
			}
		}  
    }

    // VAR

    public void visit(VarElement var){
        Obj obj = Tab.currentScope.findSymbol(var.getVarName());
        if(obj == null){
            var.obj = Tab.insert(Obj.Var, var.getVarName(), currentType);
        }
        else{
            report_error("Simbol " + var.getVarName() + " je vec deklarisan", var);
        }
    }

    public void visit(VarArray var){
        Obj obj = Tab.currentScope.findSymbol(var.getVarName());
        if(obj == null){
            var.obj = Tab.insert(Obj.Var, var.getVarName(), new Struct(Struct.Array, currentType));
        }
        else{
            report_error("Simbol " + var.getVarName() + " je vec deklarisan", var);
        }
    }

    public void visit(VarMatrix var){
        Obj obj = Tab.currentScope.findSymbol(var.getVarName());
        if(obj == null){
            var.obj = Tab.insert(Obj.Var, var.getVarName(), new Struct(Struct.Array, new Struct(Struct.Array, currentType)));
        }
        else{
            report_error("Simbol " + var.getVarName() + " je vec deklarisan", var);
        }
    }

    // METHOD

    public static boolean checkMain(Method method){
        return (
            method.getMethodPars() instanceof NoMethodParameters
            && method.getMethodName() instanceof MethodVoidName
            && "main".equalsIgnoreCase(((MethodVoidName)method.getMethodName()).getMethodName())
        );
    }

    public void visit(MethodTypeName methodName){
        Obj obj = Tab.currentScope.findSymbol(methodName.getMethodName());
        if(obj == null){
            methodName.obj = Tab.insert(Obj.Meth, methodName.getMethodName(), methodName.getType().struct);
            currentMethod = methodName.obj;
            invalidMethodName = false;
        }
        else{
            report_error("Simbol " + methodName.getMethodName() + " je vec deklarisan", methodName);
            invalidMethodName = true;
        }
        Tab.openScope();
    }

    public void visit(MethodVoidName methodName){
        Obj obj = Tab.currentScope.findSymbol(methodName.getMethodName());
        if(obj == null){
            methodName.obj = Tab.insert(Obj.Meth, methodName.getMethodName(), Tab.noType);
            currentMethod = methodName.obj;
            invalidMethodName = false;
        }
        else{
            report_error("Simbol " + methodName.getMethodName() + " je vec deklarisan", methodName);
            invalidMethodName = true;
        }
        Tab.openScope();
    }

    public void visit(Method method){ 
        if(!invalidMethodName){
            if(checkMain(method)){
                mainExists = true;
            }
    
            if (!returnFound && method.getMethodName().obj.getType() != Tab.noType) {
                report_error("Funcija " + method.getMethodName().obj.getName() + " nema return iskaz", method);
            }
        }

        Tab.chainLocalSymbols(method.getMethodName().obj);
        Tab.closeScope();
        returnFound = false;
        currentMethod = null;
    }

    // FORM PARS

    public void visit(FormParamElement formParam){
        Obj obj = Tab.currentScope.findSymbol(formParam.getParamName());
        if(obj == null){
            formParam.obj = Tab.insert(Obj.Var, formParam.getParamName(), formParam.getType().struct);
            currentMethod.setLevel(currentMethod.getLevel() + 1);
        }
        else{
            report_error("Simbol " + formParam.getParamName() + " je vec deklarisan", formParam);
        }
    }

    public void visit(FormParamArray formParam){
        Obj obj = Tab.currentScope.findSymbol(formParam.getParamName());
        if(obj == null){
            formParam.obj = Tab.insert(Obj.Var, formParam.getParamName(), new Struct(Struct.Array, formParam.getType().struct));
            currentMethod.setLevel(currentMethod.getLevel() + 1);
        }
        else{
            report_error("Simbol " + formParam.getParamName() + " je vec deklarisan", formParam);
        }
    }

    // DESIGNATOR

    public void visit(DesignatorElement designatorElement){
        Obj obj = Tab.find(designatorElement.getDesignName());
        if(obj == Tab.noObj){
            report_error("Simbol " + designatorElement.getDesignName() + " nije deklarisan", designatorElement);
        }
        report_info("Detektovan simbol " + designatorElement.getDesignName(), designatorElement);
        designatorElement.obj = obj;
    }

    public void visit(DesignatorName name){
        Obj obj = Tab.find(name.getDesignName());

        if(obj == Tab.noObj){
            name.obj = Tab.noObj;
            report_error("Simbol " + name.getDesignName() + " nije deklarisan", name);
        }
        else{
            name.obj = obj;
        }
    }

    public void visit(DesignatorArray designatorArray){
        Obj obj = Tab.find(designatorArray.getDesignatorName().getDesignName());
        Struct returnType;
        if(obj == Tab.noObj){
            report_error("Simbol " + designatorArray.getDesignatorName().getDesignName() + " nije deklarisan", designatorArray);
        }
        report_info("Detektovan simbol " + designatorArray.getDesignatorName().getDesignName(), designatorArray);

        if(obj.getType().getKind() != Struct.Array){
            report_error("Simbol " + designatorArray.getDesignatorName().getDesignName() + " nije deklarisan kao niz", designatorArray);
            returnType = Tab.noType;
        }
        else{
            returnType = obj.getType().getElemType();
        }

        if(designatorArray.getExpr().obj.getType() != Tab.intType){
            report_error("Na referencirani element niza mora ukazivati int", designatorArray);
        }
        designatorArray.obj = new Obj(Obj.Elem, "", returnType);
    }

    public void visit(DesignatorMatrix designatorMatrix){        
        Obj obj = Tab.find(designatorMatrix.getDesignatorName().getDesignName());
        Struct returnType;
        if(obj == Tab.noObj){
            report_error("Simbol " + designatorMatrix.getDesignatorName().getDesignName() + " nije deklarisan", designatorMatrix);
        }
        report_info("Detektovan simbol " + designatorMatrix.getDesignatorName().getDesignName(), designatorMatrix);


        if(obj.getType().getKind() != Struct.Array || obj.getType().getElemType().getKind() != Struct.Array){
            report_error("Simbol " + designatorMatrix.getDesignatorName().getDesignName() + " nije deklarisan kao matrica", designatorMatrix);
            returnType = Tab.noType;
        }
        else{
            returnType = obj.getType().getElemType().getElemType();
        }

        if(designatorMatrix.getExpr().obj.getType() != Tab.intType || designatorMatrix.getExpr1().obj.getType() != Tab.intType){
            report_error("Na referencirani element matrice mora ukazivati int", designatorMatrix);
        }
        designatorMatrix.obj = new Obj(Obj.Elem, "", returnType);
    }

    // ACT PARS

    public void visit(SingleActParam param){
        localParams.add(param.getExpr().obj.getType());
    }

    public void visit(ActParamList param){
        localParams.add(param.getExpr().obj.getType());
    }

    // DESIGNATOR STMT

    public void visit(DesignAssign designAssign){
        if(!designAssign.getExpr().obj.getType().assignableTo(designAssign.getDesignator().obj.getType())){
            report_error("Nekompatabilni tipovi pri dodeli vrednosti", designAssign);
        }
    }

    public void visit(DesingFunc desingFunc){
        Obj func = desingFunc.getDesignator().obj;
        if(func.getKind() == Obj.Meth){

            if(localParams.size() != func.getLevel()){
                report_error("Neodgovarajuci broj parametara za metodu " + func.getName(), desingFunc);
            }
            else{
                ArrayList<Obj> localSymbols = new ArrayList<>(func.getLocalSymbols());

                for(int i = 0; i < localParams.size(); i++){
                    if(localParams.get(i) != localSymbols.get(i).getType()){
                        report_error("Neodgovarajuci tip parametara za metodu " + func.getName(), desingFunc);
                        break;
                    }
                }
            }
        }
        else{
            report_error("Simbol " + func.getName() + " nije deklarisan kao metoda", desingFunc);
        }
        localParams.clear();
    }

    public void visit(DesignInc designInc){
        Struct type = designInc.getDesignator().obj.getType();
        if(
            type != Tab.intType
            || (type.getKind() == Struct.Array && type.getElemType() != Tab.intType)
            || (type.getKind() == Struct.Array && type.getElemType().getKind() == Struct.Array && type.getElemType().getElemType() != Tab.intType)
        ){
            report_error("Promenljiva, element niza ili element matrice mora biti tipa int za operaciju inc", designInc);
        }
    }

    public void visit(DesignDec designDec){
        Struct type = designDec.getDesignator().obj.getType();
        if(
            type != Tab.intType
            || (type.getKind() == Struct.Array && type.getElemType() != Tab.intType)
            || (type.getKind() == Struct.Array && type.getElemType().getKind() == Struct.Array && type.getElemType().getElemType() != Tab.intType)
        ){
            report_error("Promenljiva, element niza ili element matrice mora biti tipa int za operaciju dec", designDec);
        }
    }

    // FACTOR

    public void visit(DesignFactor factorVar){
        factorVar.struct = factorVar.getDesignator().obj.getType();
    }

    public void visit(DesingFuncFactor desingFunc){
        Obj func = desingFunc.getDesignator().obj;
        if(func.getKind() == Obj.Meth){
            desingFunc.struct = func.getType();

            if(func.getType() == Tab.noType){
                report_error("Void metoda ne moze da se koristi unutar izraza", desingFunc);
                return;
            }

            if(localParams.size() != func.getLevel()){
                report_error("Neodgovarajuci broj parametara za metodu " + func.getName(), desingFunc);
            }
            else{
                ArrayList<Obj> localSymbols = new ArrayList<>(func.getLocalSymbols());

                for(int i = 0; i < localParams.size(); i++){
                    if(localParams.get(i) != localSymbols.get(i).getType()){
                        report_error("Neodgovarajuci tip parametara za metodu " + func.getName(), desingFunc);
                        break;
                    }
                }
            }
        }
        else{
            report_error("Simbol " + func.getName() + " nije deklarisan kao metoda", desingFunc);
            desingFunc.struct = Tab.noType;
        }
        localParams.clear();
    }

    public void visit(FactorNumber factorConst){
        factorConst.struct = Tab.intType;
    }

    public void visit(FactorChar factorConst){
        factorConst.struct = Tab.charType;
    }

    public void visit(FactorBool factorConst){
        factorConst.struct = boolType;
    }

    public void visit(NewArrayFactor factorArray){
        if(factorArray.getExpr().obj.getType() != Tab.intType){
            report_error("Na referencirani element niza mora ukazivati int", factorArray);
            factorArray.struct = Tab.nullType;
        }
        else{
            factorArray.struct = new Struct(Struct.Array, currentType);
        }
    }

    public void visit(MatrixFactor matrixFactor){
        if(matrixFactor.getExpr().obj.getType() != Tab.intType){
            report_error("Na referencirani element matrice mora ukazivati int", matrixFactor);
            matrixError = true;
        }
        else{
            matrixError = false;
        }
    }

    public void visit(NewMatrixFactor factorMatrix){
        if(factorMatrix.getExpr().obj.getType() != Tab.intType || matrixError){
            report_error("Na referencirani element matrice mora ukazivati int", factorMatrix);
            factorMatrix.struct = Tab.nullType;
        }
        else{
            factorMatrix.struct = new Struct(Struct.Array, new Struct(Struct.Array, currentType));
        }
    }

    public void visit(FactorExpr factorExpr){
        factorExpr.struct = factorExpr.getExpr().obj.getType();
    }

    // FACTOR LIST

    public void visit(SingleFactor term){
        term.struct = term.getFactor().struct;
    }

    public void visit(Factors factors){
        if(factors.getFactorList().struct != Tab.intType || factors.getFactor().struct != Tab.intType){
            report_error("Operandi operacije mnozenja moraju biti tipa int", factors);
            factors.struct = Tab.noType;
        }
        else{
            factors.struct = Tab.intType;
        }
    }

    // TERM

    public void visit(Term term){
        term.struct = term.getFactorList().struct;
    }

    // TERM LIST

    public void visit(SingleTerm term){
        term.struct = term.getTerm().struct;
    }

    public void visit(TerminalList termList){
        // Struct tl = termList.getTermList().struct;
        // Struct t = termList.getTerm().struct;
        // if(tl.compatibleWith(t) && t == Tab.intType)
        if(termList.getTermList().struct != Tab.intType || termList.getTerm().struct != Tab.intType){
            report_error("Operandi operacije sabiranja moraju biti tipa int", termList);
            termList.struct = Tab.noType;
        }
        else{
            termList.struct = Tab.intType;
        }
    }

    public void visit(NegativeTermList expr){
        if(expr.getTerm().struct != Tab.intType){
            report_error("Izraz mora biti tipa int", expr);
            expr.struct = Tab.noType;
        }
        else{
            expr.struct = Tab.intType;
        }
    }

    // EXPR

    public void visit(PositiveTermList expr){
        expr.obj = new Obj(Obj.Con, null, expr.getTermList().struct);
    }

    // STATEMENTS

    // UNMATCHED

    public void visit(UnmatchedIf unmatchedIf){
        if(unmatchedIf.getCondition().struct != boolType){
            report_error("Uslov if izraza mora biti tipa bool", unmatchedIf);
        }
    }

    public void visit(UnmatchedIfElse unmatchedIfElse){
        if(unmatchedIfElse.getCondition().struct != boolType){
            report_error("Uslov if izraza mora biti tipa bool", unmatchedIfElse);
        }
    }
    
    // MATCHED

    public void visit(MatchedIf matchedIf){
        if(matchedIf.getCondition().struct != boolType){
            report_error("Uslov if izraza mora biti tipa bool", matchedIf);
        }
    }

    public void visit(BreakStmt breakStmt){
        if(!forStatementExists){
            report_error("Break naredba mora da se nalazi unutar for petlje", breakStmt);
        }
    }

    public void visit(ContinueStmt continueStmt){
        if(!forStatementExists){
            report_error("Continue naredba mora da se nalazi unutar for petlje", continueStmt);
        }
    }

    public void visit(ReturnNoExpr returnStmt){
        if(currentMethod == null){
            report_error("Return naredba mora da se nalazi unutar metode", returnStmt);
            return;
        }

        returnFound = true;
        if(!currentMethod.getType().compatibleWith(Tab.noType)){
            report_error("Tip izraza u return naredbi se ne slaze sa povratnom vrednosti metode " + currentMethod.getName(), returnStmt);
        }
    }

    public void visit(ReturnExpr returnStmt){
        if(currentMethod == null){
            report_error("Return naredba mora da se nalazi unutar metode", returnStmt);
            return;
        }

        returnFound = true;
        if(!currentMethod.getType().compatibleWith(returnStmt.getExpr().obj.getType())){
            report_error("Tip izraza u return naredbi se ne slaze sa povratnom vrednosti metode " + currentMethod.getName(), returnStmt);
        }
    }

    public void visit(ReadStmt readStmt){
        Struct type = readStmt.getDesignator().obj.getType();
        if(
            type != Tab.intType
            && type != Tab.charType
            && type != boolType
        ){
            report_error("Metoda read moze da se zove samo za tipove int, char ili bool", readStmt);
        }
    }

    public void visit(PrintExpr printStmt){
        Struct type = printStmt.getExpr().obj.getType();
        if(
            type != Tab.intType
            && type != Tab.charType
            && type != boolType
        ){
            report_error("Metoda print moze da se zove samo za tipove int, char ili bool", printStmt);
        }
    }

    public void visit(PrintExprNumber printStmt){
        Struct type = printStmt.getExpr().obj.getType();
        if(
            type != Tab.intType
            && type != Tab.charType
            && type != boolType
        ){
            report_error("Metoda print moze da se zove samo za tipove int, char ili bool", printStmt);
        }
    }

    public void visit(ForLoop forLoop){
        forStatementExists = true;
        if(forLoop.getParent() instanceof ForStmt){
            forStatements.push((ForStmt)forLoop.getParent());
        }
    }

    public void visit(ForStmt forStmt){
        forStatements.pop();
        if(forStatements.isEmpty()){
            forStatementExists = false;
        }
    }

    // COND FACT

    public void visit(SingleCondition cond){
        cond.struct = cond.getExpr().obj.getType();
    }

    public void visit(ConditionList condList){
        Struct e = condList.getExpr().obj.getType();
        Struct e1 = condList.getExpr1().obj.getType();
        if(e1.compatibleWith(e)){
            if(
                e.isRefType()
                && (condList.getRelop() instanceof RelopEquals || condList.getRelop() instanceof RelopNotEquals)
            ){
                condList.struct = boolType;
            }
            else if (!e.isRefType()){
                condList.struct = boolType;
            }
            else{
                report_error("Tipovi nisu kompatabilni", condList);
                condList.struct = Tab.noType;
            }
        }
        else{
            report_error("Tipovi nisu kompatabilni", condList);
            condList.struct = Tab.noType;
        }
    }

    // COND TERM

    public void visit(SingleConditionFact cond){
        cond.struct = cond.getCondFact().struct;
    }

    public void visit(ConditionFactList cond){
        cond.struct = cond.getCondFact().struct;
    }

    // CONDITION

    public void visit(SingleConditionTerm cond){
        cond.struct = cond.getCondTerm().struct;
    }

    public void visit(ConditionTermList cond){
        cond.struct = cond.getCondTerm().struct;
    }

    // SUCCESSFUL PASS
    public boolean success(){
        return !errorDetected;
    }
}
 