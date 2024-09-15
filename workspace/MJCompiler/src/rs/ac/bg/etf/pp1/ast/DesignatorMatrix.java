// generated with ast extension for cup
// version 0.8
// 15/8/2024 17:44:53


package rs.ac.bg.etf.pp1.ast;

public class DesignatorMatrix extends Designator {

    private DesignatorName DesignatorName;
    private Expr Expr;
    private MatrixAload MatrixAload;
    private Expr Expr1;

    public DesignatorMatrix (DesignatorName DesignatorName, Expr Expr, MatrixAload MatrixAload, Expr Expr1) {
        this.DesignatorName=DesignatorName;
        if(DesignatorName!=null) DesignatorName.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.MatrixAload=MatrixAload;
        if(MatrixAload!=null) MatrixAload.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
    }

    public DesignatorName getDesignatorName() {
        return DesignatorName;
    }

    public void setDesignatorName(DesignatorName DesignatorName) {
        this.DesignatorName=DesignatorName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public MatrixAload getMatrixAload() {
        return MatrixAload;
    }

    public void setMatrixAload(MatrixAload MatrixAload) {
        this.MatrixAload=MatrixAload;
    }

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(MatrixAload!=null) MatrixAload.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorName!=null) DesignatorName.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(MatrixAload!=null) MatrixAload.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(MatrixAload!=null) MatrixAload.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorMatrix(\n");

        if(DesignatorName!=null)
            buffer.append(DesignatorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatrixAload!=null)
            buffer.append(MatrixAload.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorMatrix]");
        return buffer.toString();
    }
}
