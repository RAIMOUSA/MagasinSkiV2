package Exception;

public class SaleDetailException extends TypeException{
    public SaleDetailException(String errorMessage, RangeOperationException rangeOperation, OperationException operation) {
        super(errorMessage, rangeOperation, operation);
    }

    public SaleDetailException(String errorMessage, Throwable cause) {
        super(errorMessage, null, null);
        initCause(cause);
    }

    public SaleDetailException(String errorMessage) {
        super(errorMessage, null, null);
    }

    @Override
    protected String getType() {
        return "sale detail";
    }
}
