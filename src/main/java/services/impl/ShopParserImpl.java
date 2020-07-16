package services.impl;

import entities.Product;
import entities.SiteRepresent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import services.interfaces.ShopParser;

import java.util.ArrayList;
import java.util.List;

public abstract class ShopParserImpl extends SiteRepresent implements ShopParser {

    public ShopParserImpl(List<String> urls, ProductService productService) {
        super(urls, productService);
    }

    @Override
    public List<Product> scrapCurrentPage(WebDriver driver) {
        List<Product> products = new ArrayList<Product>();
        return products;
    }

    @Override
    public Document getCurrentDocument(WebDriver driver) {
        String pageSource = driver.getPageSource();
        return Jsoup.parse(pageSource);
    }

    @Override
    public void click(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    @Override
    public abstract void parseCurrentPage(WebDriver driver);
}
