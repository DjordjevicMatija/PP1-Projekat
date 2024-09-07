// generated with ast extension for cup
// version 0.8
// 7/8/2024 12:57:53


package rs.ac.bg.etf.pp1.ast;

public class IdentsOrExpresions extends IdentOrExprList {

    private IdentOrExprList IdentOrExprList;
    private IdentOrExpr IdentOrExpr;

    public IdentsOrExpresions (IdentOrExprList IdentOrExprList, IdentOrExpr IdentOrExpr) {
        this.IdentOrExprList=IdentOrExprList;
        if(IdentOrExprList!=null) IdentOrExprList.setParent(this);
        this.IdentOrExpr=IdentOrExpr;
        if(IdentOrExpr!=null) IdentOrExpr.setParent(this);
    }

    public IdentOrExprList getIdentOrExprList() {
        return IdentOrExprList;
    }

    public void setIdentOrExprList(IdentOrExprList IdentOrExprList) {
        this.IdentOrExprList=IdentOrExprList;
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
        if(IdentOrExprList!=null) IdentOrExprList.accept(visitor);
        if(IdentOrExpr!=null) IdentOrExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IdentOrExprList!=null) IdentOrExprList.traverseTopDown(visitor);
        if(IdentOrExpr!=null) IdentOrExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IdentOrExprList!=null) IdentOrExprList.traverseBottomUp(visitor);
        if(IdentOrExpr!=null) IdentOrExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IdentsOrExpresions(\n");

        if(IdentOrExprList!=null)
            buffer.append(IdentOrExprList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IdentOrExpr!=null)
            buffer.append(IdentOrExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IdentsOrExpresions]");
        return buffer.toString();
    }
}
