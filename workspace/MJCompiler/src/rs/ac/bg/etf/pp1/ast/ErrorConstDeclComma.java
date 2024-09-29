// generated with ast extension for cup
// version 0.8
// 15/8/2024 21:36:9


package rs.ac.bg.etf.pp1.ast;

public class ErrorConstDeclComma extends ConstList {

    public ErrorConstDeclComma () {
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
        buffer.append("ErrorConstDeclComma(\n");

        buffer.append(tab);
        buffer.append(") [ErrorConstDeclComma]");
        return buffer.toString();
    }
}
