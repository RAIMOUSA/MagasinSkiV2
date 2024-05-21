package Exception;

public class SaleException extends TypeException {
    public SaleException(String errorMessage, RangeOperationException rangeOperation, OperationException operation) {
        super(errorMessage, rangeOperation, operation);
    }

    public SaleException(String errorMessage, Throwable cause) {
        super(errorMessage, null, null);
        initCause(cause);
    }

    public SaleException(String errorMessage) {
        super(errorMessage, null, null);
    }

    @Override
    protected String getType() {
        return "sale";
    }

}
