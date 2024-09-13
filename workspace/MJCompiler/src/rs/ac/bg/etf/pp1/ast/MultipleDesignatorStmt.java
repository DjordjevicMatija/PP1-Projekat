// generated with ast extension for cup
// version 0.8
// 13/8/2024 13:30:46


package rs.ac.bg.etf.pp1.ast;

public class MultipleDesignatorStmt extends DesignatorStatementElem {

    private DesignatorStatementElem DesignatorStatementElem;
    private DesignatorStatement DesignatorStatement;

    public MultipleDesignatorStmt (DesignatorStatementElem DesignatorStatementElem, DesignatorStatement DesignatorStatement) {
        this.DesignatorStatementElem=DesignatorStatementElem;
        if(DesignatorStatementElem!=null) DesignatorStatementElem.setParent(this);
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
    }

    public DesignatorStatementElem getDesignatorStatementElem() {
        return DesignatorStatementElem;
    }

    public void setDesignatorStatementElem(DesignatorStatementElem DesignatorStatementElem) {
        this.DesignatorStatementElem=DesignatorStatementElem;
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementElem!=null) DesignatorStatementElem.accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementElem!=null) DesignatorStatementElem.traverseTopDown(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementElem!=null) DesignatorStatementElem.traverseBottomUp(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleDesignatorStmt(\n");

        if(DesignatorStatementElem!=null)
            buffer.append(DesignatorStatementElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleDesignatorStmt]");
        return buffer.toString();
    }
}
