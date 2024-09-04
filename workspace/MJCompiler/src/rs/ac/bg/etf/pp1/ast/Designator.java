// generated with ast extension for cup
// version 0.8
// 6/8/2024 0:51:15


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private IdentOrExprList IdentOrExprList;

    public Designator (String I1, IdentOrExprList IdentOrExprList) {
        this.I1=I1;
        this.IdentOrExprList=IdentOrExprList;
        if(IdentOrExprList!=null) IdentOrExprList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public IdentOrExprList getIdentOrExprList() {
        return IdentOrExprList;
    }

    public void setIdentOrExprList(IdentOrExprList IdentOrExprList) {
        this.IdentOrExprList=IdentOrExprList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentOrExprList!=null) IdentOrExprList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentOrExprList!=null) IdentOrExprList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentOrExprList!=null) IdentOrExprList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Designator(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(IdentOrExprList!=null)
            buffer.append(IdentOrExprList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
