// generated with ast extension for cup
// version 0.8
// 11/8/2024 10:55:16


package rs.ac.bg.etf.pp1.ast;

public class RelopNotEquals extends Relop {

    public RelopNotEquals () {
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
        buffer.append("RelopNotEquals(\n");

        buffer.append(tab);
        buffer.append(") [RelopNotEquals]");
        return buffer.toString();
    }
}
