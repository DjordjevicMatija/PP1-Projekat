// generated with ast extension for cup
// version 0.8
// 11/8/2024 10:55:16


package rs.ac.bg.etf.pp1.ast;

public class Method implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodName MethodName;
    private MethodPars MethodPars;
    private LocalVarDeclList LocalVarDeclList;
    private StatementList StatementList;

    public Method (MethodName MethodName, MethodPars MethodPars, LocalVarDeclList LocalVarDeclList, StatementList StatementList) {
        this.MethodName=MethodName;
        if(MethodName!=null) MethodName.setParent(this);
        this.MethodPars=MethodPars;
        if(MethodPars!=null) MethodPars.setParent(this);
        this.LocalVarDeclList=LocalVarDeclList;
        if(LocalVarDeclList!=null) LocalVarDeclList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodName getMethodName() {
        return MethodName;
    }

    public void setMethodName(MethodName MethodName) {
        this.MethodName=MethodName;
    }

    public MethodPars getMethodPars() {
        return MethodPars;
    }

    public void setMethodPars(MethodPars MethodPars) {
        this.MethodPars=MethodPars;
    }

    public LocalVarDeclList getLocalVarDeclList() {
        return LocalVarDeclList;
    }

    public void setLocalVarDeclList(LocalVarDeclList LocalVarDeclList) {
        this.LocalVarDeclList=LocalVarDeclList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
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
        if(MethodName!=null) MethodName.accept(visitor);
        if(MethodPars!=null) MethodPars.accept(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodName!=null) MethodName.traverseTopDown(visitor);
        if(MethodPars!=null) MethodPars.traverseTopDown(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodName!=null) MethodName.traverseBottomUp(visitor);
        if(MethodPars!=null) MethodPars.traverseBottomUp(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Method(\n");

        if(MethodName!=null)
            buffer.append(MethodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodPars!=null)
            buffer.append(MethodPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LocalVarDeclList!=null)
            buffer.append(LocalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Method]");
        return buffer.toString();
    }
}
