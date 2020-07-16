package services.interfaces;

import entities.Product;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface ShopParser {
    List<Product> scrapCurrentPage(WebDriver driver);

    Document getCurrentDocument(WebDriver driver);

    public void click(WebDriver driver, WebElement pageNext);
}
