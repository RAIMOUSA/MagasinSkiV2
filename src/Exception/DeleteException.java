package Exception;

public class DeleteException extends OperationException{
    @Override
    public String getDescription() {
        return "supprimer";
    }
}
