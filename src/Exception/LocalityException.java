package src.Exception;

public class LocalityException extends TypeException{
    public LocalityException(String errorMessage, RangeOperationException rangeOperation, OperationException operation) {
        super(errorMessage, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "locality";
    }
}
