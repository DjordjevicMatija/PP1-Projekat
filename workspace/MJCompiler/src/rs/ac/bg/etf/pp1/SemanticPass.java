package rs.ac.bg.etf.pp1;

import org.apache.log4j.*;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

    boolean errorDetected = false;
    Logger log = Logger.getLogger(getClass());

    public static final Struct boolType = new Struct(Struct.Bool);
    
    Struct currentType = null;
    boolean returnFound = false;

    public void visit(ProgName progName){
        progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
        Tab.openScope();
    }

    public void visit(Program program){
        Tab.chainLocalSymbols(program.getProgName().obj);
        Tab.closeScope();
    }

    public void visit(ConstNumber constant){
        if(currentType == Tab.intType){
            constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
        }
    }

    public void visit(ConstChar constant){
        if(currentType == Tab.charType){
            constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
        }
    }

    public void visit(ConstBool constant){
        if(currentType == boolType){
            constant.obj = Tab.insert(Obj.Con, constant.getConstName(), currentType);
        }
    }

    public void visit(Type type){
        Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			log.error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
			currentType = Tab.noType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				currentType = typeNode.getType();
			} 
			else {
				log.error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", null);
				currentType = Tab.noType;
			}
		}  
    }

    public void visit(VarElement var){
        var.obj = Tab.insert(Obj.Var, var.getVarName(), currentType);
    }

    public void visit(VarArray var){
        var.obj = Tab.insert(Obj.Var, var.getVarName(), new Struct(Struct.Array, currentType));
    }

    public void visit(VarMatrix var){
        var.obj = Tab.insert(Obj.Var, var.getVarName(), new Struct(Struct.Array, new Struct(Struct.Array, currentType)));
    }

    public void visit(MethodTypeName methodName){
        methodName.obj = Tab.insert(Obj.Meth, methodName.getMethodName(), methodName.getType().struct);
        Tab.openScope();
    }

    public void visit(MethodVoidName methodName){
        methodName.obj = Tab.insert(Obj.Meth, methodName.getMethodName(), Tab.noType);
        Tab.openScope();
    }

    public void visit(Method method){ 
        if (!returnFound && method.getMethodName().obj.getType() != Tab.noType) {
			log.error("Semanticka greska na liniji " + method.getLine() + ": funcija " + method.getMethodName().obj.getName() + " nema return iskaz!", null);
		}

        Tab.chainLocalSymbols(method.getMethodName().obj);
        Tab.closeScope();
        returnFound = false;
    }

    public void visit(FormParamElement formParam){
        formParam.obj = Tab.insert(Obj.Var, formParam.getParamName(), formParam.getType().struct);
    }

    public void visit(FormParamArray formParam){
        formParam.obj = Tab.insert(Obj.Var, formParam.getParamName(), new Struct(Struct.Array, formParam.getType().struct));
    }
}
 