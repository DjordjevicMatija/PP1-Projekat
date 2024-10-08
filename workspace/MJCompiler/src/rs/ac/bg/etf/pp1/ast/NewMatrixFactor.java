// generated with ast extension for cup
// version 0.8
// 15/8/2024 21:36:9


package rs.ac.bg.etf.pp1.ast;

public class NewMatrixFactor extends Factor {

    private Type Type;
    private MatrixFactor MatrixFactor;
    private Expr Expr;

    public NewMatrixFactor (Type Type, MatrixFactor MatrixFactor, Expr Expr) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.MatrixFactor=MatrixFactor;
        if(MatrixFactor!=null) MatrixFactor.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public MatrixFactor getMatrixFactor() {
        return MatrixFactor;
    }

    public void setMatrixFactor(MatrixFactor MatrixFactor) {
        this.MatrixFactor=MatrixFactor;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(MatrixFactor!=null) MatrixFactor.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(MatrixFactor!=null) MatrixFactor.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(MatrixFactor!=null) MatrixFactor.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewMatrixFactor(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatrixFactor!=null)
            buffer.append(MatrixFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NewMatrixFactor]");
        return buffer.toString();
    }
}
