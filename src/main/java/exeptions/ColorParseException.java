package exeptions;

public class ColorParseException extends ProductParseException {
    public ColorParseException(String message) {
        super("Problem while parsing color !\n" + message);
    }
}
