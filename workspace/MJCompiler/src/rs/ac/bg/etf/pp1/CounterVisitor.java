package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor {

    protected int count;

    public int getCount(){
        return count;
    }

    public static class FormParamCounter extends CounterVisitor{

        public void visit(FormParamElement formParam){
            count++;
        }

        public void visit(FormParamArray formParam){
            count++;
        }
    }

    public static class VarCounter extends CounterVisitor{

        public void visit(VarElement var){
            count++;
        }

        public void visit(VarArray var){
            count++;
        }

        public void visit(VarMatrix var){
            count++;
        }
    }
}
