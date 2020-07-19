package services.interfaces;

import entities.size.Size;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import services.impl.WebDriverImpl;

import java.util.List;

public interface ShopParser {

    void parseProduct(WebDriverImpl driver);

    String parseArticleId(Document document);

    String parseName(Document document);

    String parseBrand(Document document);

    String parseColor(Document document);

    List<Size> parseSize(WebDriverImpl driver, Document document);

    double parsePrice(Document document);

    Document getCurrentDocument(WebDriverImpl driver);
}
