package Exception;

public class UpdateException extends OperationException{
    @Override
    public String getDescription() {
        return "mise à jour";
    }
}
