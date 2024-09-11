// generated with ast extension for cup
// version 0.8
// 11/8/2024 22:16:26


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatements extends DesignatorStmtList {

    private DesignatorStmtList DesignatorStmtList;
    private DesignatorStatementElem DesignatorStatementElem;

    public DesignatorStatements (DesignatorStmtList DesignatorStmtList, DesignatorStatementElem DesignatorStatementElem) {
        this.DesignatorStmtList=DesignatorStmtList;
        if(DesignatorStmtList!=null) DesignatorStmtList.setParent(this);
        this.DesignatorStatementElem=DesignatorStatementElem;
        if(DesignatorStatementElem!=null) DesignatorStatementElem.setParent(this);
    }

    public DesignatorStmtList getDesignatorStmtList() {
        return DesignatorStmtList;
    }

    public void setDesignatorStmtList(DesignatorStmtList DesignatorStmtList) {
        this.DesignatorStmtList=DesignatorStmtList;
    }

    public DesignatorStatementElem getDesignatorStatementElem() {
        return DesignatorStatementElem;
    }

    public void setDesignatorStatementElem(DesignatorStatementElem DesignatorStatementElem) {
        this.DesignatorStatementElem=DesignatorStatementElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStmtList!=null) DesignatorStmtList.accept(visitor);
        if(DesignatorStatementElem!=null) DesignatorStatementElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStmtList!=null) DesignatorStmtList.traverseTopDown(visitor);
        if(DesignatorStatementElem!=null) DesignatorStatementElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStmtList!=null) DesignatorStmtList.traverseBottomUp(visitor);
        if(DesignatorStatementElem!=null) DesignatorStatementElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatements(\n");

        if(DesignatorStmtList!=null)
            buffer.append(DesignatorStmtList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatementElem!=null)
            buffer.append(DesignatorStatementElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatements]");
        return buffer.toString();
    }
}
