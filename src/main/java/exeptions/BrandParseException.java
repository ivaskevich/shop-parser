package exeptions;

public class BrandParseException extends ProductParseException {
    public BrandParseException(String message) {
        super("Problem while parsing brand !\n" + message);

    }
}
