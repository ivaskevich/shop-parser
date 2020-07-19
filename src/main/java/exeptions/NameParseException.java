package exeptions;

public class NameParseException extends ProductParseException {
    public NameParseException(String message) {
        super("Problem while parsing name !\n" + message);

    }
}
