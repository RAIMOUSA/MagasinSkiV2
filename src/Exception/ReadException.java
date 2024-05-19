package Exception;

public class ReadException extends OperationException {
    @Override
    public String getDescription() {
        return "lire";
    }
}
