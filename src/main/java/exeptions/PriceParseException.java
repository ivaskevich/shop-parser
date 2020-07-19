package exeptions;

public class PriceParseException extends ProductParseException {
    public PriceParseException(String message) {
        super("Problem while parsing price !\n" + message);
    }
}
