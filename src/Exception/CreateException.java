package Exception;

public class CreateException extends OperationException{
    @Override
    public String getDescription() {
        return "créer";
    }
}

