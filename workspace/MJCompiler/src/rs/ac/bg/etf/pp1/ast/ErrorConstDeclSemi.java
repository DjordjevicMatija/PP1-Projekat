// generated with ast extension for cup
// version 0.8
// 13/8/2024 13:30:46


package rs.ac.bg.etf.pp1.ast;

public class ErrorConstDeclSemi extends ConstDecl {

    public ErrorConstDeclSemi () {
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
        buffer.append("ErrorConstDeclSemi(\n");

        buffer.append(tab);
        buffer.append(") [ErrorConstDeclSemi]");
        return buffer.toString();
    }
}
