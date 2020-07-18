package services.impl;

import entities.SiteRepresent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import services.interfaces.ProductService;
import services.interfaces.ShopParser;

import java.util.List;

public abstract class ShopParserImpl extends SiteRepresent implements ShopParser {

    public ShopParserImpl(String shopUrl, List<String> subUrls, ProductService productService) {
        super(shopUrl, subUrls, productService);
    }

    @Override
    public Document getCurrentDocument(WebDriver driver) {
        String pageSource = driver.getPageSource();
        return Jsoup.parse(pageSource);
    }
}
