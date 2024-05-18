package src.Exception;

public class ProductException extends TypeException{
    public ProductException(String errorMessage, RangeOperationException rangeOperation, OperationException operation) {
        super(errorMessage, rangeOperation, operation);
    }

    @Override
    protected String getType() {
        return "product";
    }
}
