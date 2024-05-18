package Exception;

public class CustomerException extends TypeException{
    public CustomerException(String errorMessage, RangeOperationException rangeOperation, OperationException operation) {
        super(errorMessage,rangeOperation,operation);
    }

    protected String getType(){
        return "Client";
    }
}
