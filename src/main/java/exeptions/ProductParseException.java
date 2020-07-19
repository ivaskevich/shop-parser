package exeptions;

public class ProductParseException extends RuntimeException {
    public ProductParseException(String message) {
        super("Exception while trying to parse product !\n" + message);
    }
}
