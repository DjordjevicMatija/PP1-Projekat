// generated with ast extension for cup
// version 0.8
// 8/8/2024 14:38:34


package rs.ac.bg.etf.pp1.ast;

public class ErrorVarDeclComma extends VarList {

    public ErrorVarDeclComma () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorVarDeclComma(\n");

        buffer.append(tab);
        buffer.append(") [ErrorVarDeclComma]");
        return buffer.toString();
    }
}
