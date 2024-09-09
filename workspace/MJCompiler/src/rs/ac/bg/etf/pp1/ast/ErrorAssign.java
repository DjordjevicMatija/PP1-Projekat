// generated with ast extension for cup
// version 0.8
// 9/8/2024 23:56:54


package rs.ac.bg.etf.pp1.ast;

public class ErrorAssign extends MatchedStatement {

    public ErrorAssign () {
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
        buffer.append("ErrorAssign(\n");

        buffer.append(tab);
        buffer.append(") [ErrorAssign]");
        return buffer.toString();
    }
}
