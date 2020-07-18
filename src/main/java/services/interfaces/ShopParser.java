package services.interfaces;

import entities.size.Size;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ShopParser {

    void parseProduct(WebDriver driver);

    String parseArticleId(Document document);

    String parseName(Document document);

    String parseBrand(Document document);

    String parseColor(Document document);

    List<Size> parseSize(WebDriver driver, Document document);

    double parsePrice(Document document);

    Document getCurrentDocument(WebDriver driver);
}
