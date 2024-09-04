// generated with ast extension for cup
// version 0.8
// 6/8/2024 0:51:15


package rs.ac.bg.etf.pp1.ast;

public class SingleIdentOrExpr extends IdentOrExprList {

    private IdentOrExpr IdentOrExpr;

    public SingleIdentOrExpr (IdentOrExpr IdentOrExpr) {
        this.IdentOrExpr=IdentOrExpr;
        if(IdentOrExpr!=null) IdentOrExpr.setParent(this);
    }

    public IdentOrExpr getIdentOrExpr() {
        return IdentOrExpr;
    }

    public void setIdentOrExpr(IdentOrExpr IdentOrExpr) {
        this.IdentOrExpr=IdentOrExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IdentOrExpr!=null) IdentOrExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentOrExpr!=null) IdentOrExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentOrExpr!=null) IdentOrExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleIdentOrExpr(\n");

        if(IdentOrExpr!=null)
            buffer.append(IdentOrExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleIdentOrExpr]");
        return buffer.toString();
    }
}
