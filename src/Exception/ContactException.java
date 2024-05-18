package src.Exception;

public class ContactException extends TypeException{
    public ContactException(String errorMessage, RangeOperationException rangeOperation, OperationException operation) {
        super(errorMessage, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "contact";
    }
}
