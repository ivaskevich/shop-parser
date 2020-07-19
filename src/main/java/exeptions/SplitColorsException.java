package exeptions;

public class SplitColorsException extends RuntimeException{
    public SplitColorsException(String message) {
        super("Exception while trying to split product for colors!\n" + message);
    }
}
