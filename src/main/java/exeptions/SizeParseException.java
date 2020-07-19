package exeptions;

public class SizeParseException extends ProductParseException {
    public SizeParseException(String message) {
        super("Problem while parsing size !\n" + message);
    }
}
