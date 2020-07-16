package shops;

import entities.Product;
import entities.Size;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import services.impl.ProductService;
import services.impl.ShopParserImpl;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AboutYou extends ShopParserImpl {

    public AboutYou(List<String> urls, ProductService productService) {
        super(urls, productService);
    }

    @Override
    public void parseCurrentPage(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.className("sc-1qheze-0 gsZQBd"));
        for (WebElement element : elements) {
            click(driver, element);
            splitForColors(driver);
        }
    }

    public void splitForColors(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.className("oib7dg-0 fXuRgN"));
        for (WebElement element : elements) {
            click(driver, element);
            parseProduct(driver);
        }
    }

    public void parseProduct(WebDriver driver) {
        WebElement dropdownSize = driver.findElement(By.className("sc-807db0-3 lhcQGG"));
        if (dropdownSize != null) click(driver, dropdownSize);

        Document document = getCurrentDocument(driver);
        String articleId =
                document.getElementsByAttributeValue("data-test-id", "ArticleNumber").text().split(" ")[1];
        String productName = document.getElementsByAttributeValue("data-test-id", "ProductName").text();
        String brand = document.getElementsByAttributeValue("data-test-id", "BrandLogo").attr("alt");
        String color = document.getElementsByClass("jlvxcb-3 fPNSJX active")
                .first().getElementsByAttributeValue("data-test-id", "ColorVariantColorInfo").text();
        List<Size> sizes = new ArrayList<Size>();
        Elements sizesElements;
        if (dropdownSize != null) {
            sizesElements = document.getElementsByAttributeValue("data-test-id", "DropdownItem_Active");
            if (document.getElementsByAttributeValue("data-test-id", "EmbeddedDropdownTitle").first().hasText()) {
                for (Element e : sizesElements) {
                    Element child = e.children().first();
                    Element sizeEnum = child.children().first();
                    Element sizeValue = sizeEnum.nextElementSibling();
                    Size size = Size.valueOf(sizeEnum.text());
                    size.setSizeValue(sizeValue.text());
                    sizes.add(size);
                }
            } else {
                for (Element e : sizesElements) {
                    Element sizeEnum = e.children().first();
                    Size size = Size.valueOf(sizeEnum.text());
                    sizes.add(size);
                }
            }
        } else {
            sizesElements = document.getElementsByAttributeValue("data-test-id", "SizeBubble_available");
            for (Element e : sizesElements) {
                Element sizeEnum = e.children().first();
                Size size = Size.valueOf(sizeEnum.text());
                sizes.add(size);
            }
        }
        double price = Double.parseDouble(
                document.getElementsByAttributeValue("data-test-id","ProductPriceCampaignWithSale")
                .text().split(" ")[0]);

        Product product = new Product(articleId,productName,brand,color,sizes,price);
        getProductService().addProduct(product);
    }
}
