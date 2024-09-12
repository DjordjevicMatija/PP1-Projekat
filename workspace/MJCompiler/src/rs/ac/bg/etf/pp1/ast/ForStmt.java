// generated with ast extension for cup
// version 0.8
// 12/8/2024 2:10:44


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends MatchedStatement {

    private DesignatorStmtList DesignatorStmtList;
    private OptCondFact OptCondFact;
    private DesignatorStmtList DesignatorStmtList1;
    private MatchedStatement MatchedStatement;

    public ForStmt (DesignatorStmtList DesignatorStmtList, OptCondFact OptCondFact, DesignatorStmtList DesignatorStmtList1, MatchedStatement MatchedStatement) {
        this.DesignatorStmtList=DesignatorStmtList;
        if(DesignatorStmtList!=null) DesignatorStmtList.setParent(this);
        this.OptCondFact=OptCondFact;
        if(OptCondFact!=null) OptCondFact.setParent(this);
        this.DesignatorStmtList1=DesignatorStmtList1;
        if(DesignatorStmtList1!=null) DesignatorStmtList1.setParent(this);
        this.MatchedStatement=MatchedStatement;
        if(MatchedStatement!=null) MatchedStatement.setParent(this);
    }

    public DesignatorStmtList getDesignatorStmtList() {
        return DesignatorStmtList;
    }

    public void setDesignatorStmtList(DesignatorStmtList DesignatorStmtList) {
        this.DesignatorStmtList=DesignatorStmtList;
    }

    public OptCondFact getOptCondFact() {
        return OptCondFact;
    }

    public void setOptCondFact(OptCondFact OptCondFact) {
        this.OptCondFact=OptCondFact;
    }

    public DesignatorStmtList getDesignatorStmtList1() {
        return DesignatorStmtList1;
    }

    public void setDesignatorStmtList1(DesignatorStmtList DesignatorStmtList1) {
        this.DesignatorStmtList1=DesignatorStmtList1;
    }

    public MatchedStatement getMatchedStatement() {
        return MatchedStatement;
    }

    public void setMatchedStatement(MatchedStatement MatchedStatement) {
        this.MatchedStatement=MatchedStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStmtList!=null) DesignatorStmtList.accept(visitor);
        if(OptCondFact!=null) OptCondFact.accept(visitor);
        if(DesignatorStmtList1!=null) DesignatorStmtList1.accept(visitor);
        if(MatchedStatement!=null) MatchedStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStmtList!=null) DesignatorStmtList.traverseTopDown(visitor);
        if(OptCondFact!=null) OptCondFact.traverseTopDown(visitor);
        if(DesignatorStmtList1!=null) DesignatorStmtList1.traverseTopDown(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStmtList!=null) DesignatorStmtList.traverseBottomUp(visitor);
        if(OptCondFact!=null) OptCondFact.traverseBottomUp(visitor);
        if(DesignatorStmtList1!=null) DesignatorStmtList1.traverseBottomUp(visitor);
        if(MatchedStatement!=null) MatchedStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmt(\n");

        if(DesignatorStmtList!=null)
            buffer.append(DesignatorStmtList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptCondFact!=null)
            buffer.append(OptCondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtList1!=null)
            buffer.append(DesignatorStmtList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MatchedStatement!=null)
            buffer.append(MatchedStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmt]");
        return buffer.toString();
    }
}
