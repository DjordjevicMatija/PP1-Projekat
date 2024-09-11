package rs.ac.bg.etf.pp1;

import org.apache.log4j.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

    public static final Struct boolType = new Struct(Struct.Bool);

    boolean errorDetected = false;
    Struct currentType = null;
    boolean returnFound = false;
    boolean invalidMethodName = false;
    boolean mainExists = false;

    Logger log = Logger.getLogger(getClass());

    public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder("Semanticka greska: ");
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

    private boolean checkMain(Method method){
        return (
            method.getMethodPars() instanceof NoMethodParameters
            && method.getMethodName() instanceof MethodVoidName
            && "main".equals(((MethodVoidName)method.getMethodName()).getMethodName())
        );
    }

    public void visit(ProgName progName){
        progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
        Tab.openScope();
    }

    public void visit(Program program){
        Tab.chainLocalSymbols(program.getProgName().obj);
        Tab.closeScope();
        if(!mainExists){
            report_error("Ne postoji metoda void main()", program);
        }
    }

    public void visit(ConstNumber constant){
        if(currentType == Tab.intType){
            Obj obj = Tab.currentScope.findSymbol(constant.getConstName());
            if(obj == null)
                constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
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
            if(obj == null)
                constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
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
            if(obj == null)
                constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
            else{
                report_error("Simbol " + constant.getConstName() + " je vec deklarisan", constant);
            }
        }
        else{
            report_error("Tip konstante " + constant.getConstName() + " nije ekvivalentan tipu proslednjenog podatka", constant);
        }
    }

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

    public void visit(MethodTypeName methodName){
        Obj obj = Tab.currentScope.findSymbol(methodName.getMethodName());
        if(obj == null){
            methodName.obj = Tab.insert(Obj.Meth, methodName.getMethodName(), methodName.getType().struct);
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
            invalidMethodName = false;
        }
        else{
            report_error("Simbol " + methodName.getMethodName() + " je vec deklarisan", methodName);
            invalidMethodName = true;
        }
        Tab.openScope();
    }

    public void visit(Method method){ 
        if(invalidMethodName){
            Tab.closeScope();
            returnFound = false;
            return;
        }

        if(checkMain(method)){
            mainExists = true;
        }

        if (!returnFound && method.getMethodName().obj.getType() != Tab.noType) {
            report_error("Funcija " + method.getMethodName().obj.getName() + " nema return iskaz", method);
		}

        Tab.chainLocalSymbols(method.getMethodName().obj);
        Tab.closeScope();
        returnFound = false;
    }

    public void visit(FormParamElement formParam){
        Obj obj = Tab.currentScope.findSymbol(formParam.getParamName());
        if(obj == null){
            formParam.obj = Tab.insert(Obj.Var, formParam.getParamName(), formParam.getType().struct);
        }
        else{
            report_error("Simbol " + formParam.getParamName() + " je vec deklarisan", formParam);
        }
    }

    public void visit(FormParamArray formParam){
        Obj obj = Tab.currentScope.findSymbol(formParam.getParamName());
        if(obj == null){
            formParam.obj = Tab.insert(Obj.Var, formParam.getParamName(), new Struct(Struct.Array, formParam.getType().struct));
        }
        else{
            report_error("Simbol " + formParam.getParamName() + " je vec deklarisan", formParam);
        }
    }

    public void visit(DesignAssign designAssign){

    }

    public void visit(DesingActPars desingActPars){
        
    }

    public void visit(DesignInc designInc){
        
    }

    public void visit(DesignDec designDec){
        
    }
}
 