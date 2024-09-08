// generated with ast extension for cup
// version 0.8
// 8/8/2024 14:38:34


package rs.ac.bg.etf.pp1.ast;

public class VoidMethodDecl extends MethodDecl {

    private Method Method;

    public VoidMethodDecl (Method Method) {
        this.Method=Method;
        if(Method!=null) Method.setParent(this);
    }

    public Method getMethod() {
        return Method;
    }

    public void setMethod(Method Method) {
        this.Method=Method;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Method!=null) Method.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Method!=null) Method.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Method!=null) Method.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VoidMethodDecl(\n");

        if(Method!=null)
            buffer.append(Method.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VoidMethodDecl]");
        return buffer.toString();
    }
}
