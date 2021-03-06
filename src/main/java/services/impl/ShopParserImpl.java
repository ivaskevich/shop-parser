package services.impl;

import entities.SiteRepresent;
import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import services.interfaces.ProductService;
import services.interfaces.ShopParser;

import java.util.List;

@Getter
public abstract class ShopParserImpl extends SiteRepresent implements ShopParser {

    public ShopParserImpl(String shopUrl, List<String> subUrls, ProductService productService) {
        super(shopUrl, subUrls, productService);
    }

    @Override
    public Document getCurrentDocument(WebDriverImpl driver) {
        String pageSource = driver.getPageSource();
        return Jsoup.parse(pageSource);
    }
}
