package exeptions;

public class ArticleIdParseException extends ProductParseException {
    public ArticleIdParseException(String message) {
        super("Problem while parsing articleId !\n" + message);
    }
}
