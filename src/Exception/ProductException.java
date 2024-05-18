package Exception;

public class ProductException extends TypeException {
    public ProductException(String errorMessage, RangeOperationException rangeOperation, OperationException operation) {
        super(errorMessage, rangeOperation, operation);
    }

    public ProductException(String errorMessage, Throwable cause) {
        super(errorMessage, null, null);
        initCause(cause);
    }

    public ProductException(String errorMessage) {
        super(errorMessage, null, null);
    }

    @Override
    protected String getType() {
        return "product";
    }
}
