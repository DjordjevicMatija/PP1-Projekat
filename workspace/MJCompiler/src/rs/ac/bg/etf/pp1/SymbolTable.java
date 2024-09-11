package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class SymbolTable extends Tab {

    public static void init() {
        Tab.init();
        Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", SemanticPass.boolType));
    }

    public static void dump(SymbolTableVisitor stv) {
		System.out.println("=====================SYMBOL TABLE DUMP=========================");
		if (stv == null)
			stv = new SymbolTableVisitorExt();
		for (Scope s = currentScope; s != null; s = s.getOuter()) {
			s.accept(stv);
		}
		System.out.println(stv.getOutput());
	}
	
	/** Stampa sadrzaj tabele simbola. */
	public static void dump() {
		dump(null);
	}
}