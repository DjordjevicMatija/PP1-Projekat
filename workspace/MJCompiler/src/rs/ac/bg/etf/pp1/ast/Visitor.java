// generated with ast extension for cup
// version 0.8
// 11/8/2024 10:55:16


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(OptCondFact OptCondFact);
    public void visit(MatchedStatement MatchedStatement);
    public void visit(Relop Relop);
    public void visit(TermList TermList);
    public void visit(DesignatorElemList DesignatorElemList);
    public void visit(Var Var);
    public void visit(StatementList StatementList);
    public void visit(FactorList FactorList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(VarList VarList);
    public void visit(ConstList ConstList);
    public void visit(DeclList DeclList);
    public void visit(MethodName MethodName);
    public void visit(Condition Condition);
    public void visit(MethodPars MethodPars);
    public void visit(DesignatorStmtList DesignatorStmtList);
    public void visit(Expr Expr);
    public void visit(ActPars ActPars);
    public void visit(DesignatorList DesignatorList);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Const Const);
    public void visit(UnmatchedStatement UnmatchedStatement);
    public void visit(DesignatorElems DesignatorElems);
    public void visit(Decl Decl);
    public void visit(OptDesignator OptDesignator);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(IdentOrExpr IdentOrExpr);
    public void visit(IdentOrExprList IdentOrExprList);
    public void visit(ConstDecl ConstDecl);
    public void visit(OptActPars OptActPars);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(DesignatorStatementElem DesignatorStatementElem);
    public void visit(FormPars FormPars);
    public void visit(FormParam FormParam);
    public void visit(LocalVarDeclList LocalVarDeclList);
    public void visit(MulopMod MulopMod);
    public void visit(MulopDiv MulopDiv);
    public void visit(MulopMul MulopMul);
    public void visit(AddopMinus AddopMinus);
    public void visit(AddopPlus AddopPlus);
    public void visit(RelopLessEqual RelopLessEqual);
    public void visit(RelopLess RelopLess);
    public void visit(RelopGreaterEqual RelopGreaterEqual);
    public void visit(RelopGreater RelopGreater);
    public void visit(RelopNotEquals RelopNotEquals);
    public void visit(RelopEquals RelopEquals);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(SingleExpr SingleExpr);
    public void visit(SingleIdent SingleIdent);
    public void visit(NoIdentOrExpr NoIdentOrExpr);
    public void visit(IdentsOrExpresions IdentsOrExpresions);
    public void visit(Designator Designator);
    public void visit(FactorExpr FactorExpr);
    public void visit(NewClassFactor NewClassFactor);
    public void visit(NewMatrixFactor NewMatrixFactor);
    public void visit(NewArrayFactor NewArrayFactor);
    public void visit(FactorBool FactorBool);
    public void visit(FactorChar FactorChar);
    public void visit(FactorNumber FactorNumber);
    public void visit(DesingActParsFactor DesingActParsFactor);
    public void visit(DesignFactor DesignFactor);
    public void visit(Factors Factors);
    public void visit(SingleFactor SingleFactor);
    public void visit(Term Term);
    public void visit(TerminalList TerminalList);
    public void visit(SingleTerm SingleTerm);
    public void visit(NegativeTermList NegativeTermList);
    public void visit(PositiveTermList PositiveTermList);
    public void visit(ConditionList ConditionList);
    public void visit(SingleCondition SingleCondition);
    public void visit(ConditionFactList ConditionFactList);
    public void visit(SingleConditionFact SingleConditionFact);
    public void visit(ConditionTermList ConditionTermList);
    public void visit(SingleConditionTerm SingleConditionTerm);
    public void visit(ActParamList ActParamList);
    public void visit(SingleActParam SingleActParam);
    public void visit(NoActParameters NoActParameters);
    public void visit(ActParameters ActParameters);
    public void visit(NoConditionFactor NoConditionFactor);
    public void visit(ConditionFactor ConditionFactor);
    public void visit(DesignDec DesignDec);
    public void visit(DesignInc DesignInc);
    public void visit(DesingActPars DesingActPars);
    public void visit(DesignAssign DesignAssign);
    public void visit(MultipleDesignatorStmt MultipleDesignatorStmt);
    public void visit(SingleDesignatorStmt SingleDesignatorStmt);
    public void visit(NoDesignatorStatements NoDesignatorStatements);
    public void visit(DesignatorStatements DesignatorStatements);
    public void visit(ErrorAssign ErrorAssign);
    public void visit(StmtList StmtList);
    public void visit(ForStmt ForStmt);
    public void visit(PrintExprNumber PrintExprNumber);
    public void visit(PrintExpr PrintExpr);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnExpr ReturnExpr);
    public void visit(ReturnNoExpr ReturnNoExpr);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(DesignStmt DesignStmt);
    public void visit(MatchedIf MatchedIf);
    public void visit(UnmatchedIfElse UnmatchedIfElse);
    public void visit(UnmatchedIf UnmatchedIf);
    public void visit(UnmatchedStmt UnmatchedStmt);
    public void visit(MatchedStmt MatchedStmt);
    public void visit(NoStatements NoStatements);
    public void visit(Statements Statements);
    public void visit(NoLocalVarDeclarations NoLocalVarDeclarations);
    public void visit(LocalVarDeclarations LocalVarDeclarations);
    public void visit(Type Type);
    public void visit(FormParamArray FormParamArray);
    public void visit(FormParamElement FormParamElement);
    public void visit(SingleFormParam SingleFormParam);
    public void visit(FormParamList FormParamList);
    public void visit(NoMethodParameters NoMethodParameters);
    public void visit(ErrorMethodParameters ErrorMethodParameters);
    public void visit(MethodParameters MethodParameters);
    public void visit(MethodVoidName MethodVoidName);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(Method Method);
    public void visit(NoMethodDeclarationList NoMethodDeclarationList);
    public void visit(MethodDeclarationList MethodDeclarationList);
    public void visit(VarMatrix VarMatrix);
    public void visit(VarArray VarArray);
    public void visit(VarElement VarElement);
    public void visit(ErrorVarDeclComma ErrorVarDeclComma);
    public void visit(VarListDecl VarListDecl);
    public void visit(SingleVarDecl SingleVarDecl);
    public void visit(ErrorVarDeclSemi ErrorVarDeclSemi);
    public void visit(VariableDeclaration VariableDeclaration);
    public void visit(ConstBool ConstBool);
    public void visit(ConstChar ConstChar);
    public void visit(ConstNumber ConstNumber);
    public void visit(ErrorConstDeclComma ErrorConstDeclComma);
    public void visit(ConsantListDecl ConsantListDecl);
    public void visit(SingleConstantDecl SingleConstantDecl);
    public void visit(ErrorConstDeclSemi ErrorConstDeclSemi);
    public void visit(ConstantDeclaration ConstantDeclaration);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(NoDeclarationList NoDeclarationList);
    public void visit(DeclarationList DeclarationList);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
