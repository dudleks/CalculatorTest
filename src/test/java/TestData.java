/**
 * Created by Alexey on 20.09.2015.
 */
public class TestData {

    enum Operation {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/");

        private String operation;

        Operation(String operation) {
            this.operation = operation;
        }

        public String getOperation() {
            return operation;
        }
    }

    private int operand1;
    private int operand2;
    private Operation operation;
    private int result;


    public TestData(int operand1, int operand2, Operation operation, int result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;

    }

    public int getOperand1() {
        return operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getResult() {
        return result;
    }


    @Override
    public String toString() {
        return "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", operation=" + operation +
                ", result=" + result;
    }
}
