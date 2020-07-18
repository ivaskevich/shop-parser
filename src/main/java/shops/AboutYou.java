package shops;

import entities.Product;
import entities.size.*;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import services.impl.HttpGetCounter;
import services.impl.ShopParserImpl;
import services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AboutYou extends ShopParserImpl {

    public AboutYou(String shopUrl, List<String> urls, ProductService productService) {
        super(shopUrl, urls, productService);
    }

    @Override
    public void parseCurrentPage(WebDriver driver,String url) {
        driver.get(url);
        Document document = getCurrentDocument(driver);
        Elements elements = document.getElementsByAttributeValue("data-test-id", "ProductTile");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        for (Element e : elements) {
            driver.get(getShopUrl() + e.attr("href"));
            splitForColors(driver);
        }
    }

    public void splitForColors(WebDriver driver) {
        Document document = getCurrentDocument(driver);
        Elements colors = document.getElementsByAttributeValue("data-test-id", "ThumbnailsList")
                .first().getElementsByTag("a");
        colors.remove(document.getElementsByAttributeValue("data-test-id", "ThumbnailsList")
                .first().getElementsByClass("active").first().parent());
        parseProduct(driver);
        for (Element color : colors) {
            driver.get(getShopUrl() + color.attr("href"));
            parseProduct(driver);
        }
    }

    @Override
    public void parseProduct(WebDriver driver) {
        Document document = getCurrentDocument(driver);
        String articleId = parseArticleId(document);
        String productName = parseName(document);
        String brand = parseBrand(document);
        String color = parseColor(document);
        List<Size> sizes = parseSize(driver, document);
        double price = parsePrice(document);

        Product product = new Product(articleId, productName, brand, color, sizes, price);
        getProductService().addProduct(product);
    }

    @Override
    public String parseArticleId(Document document) {
        return document.getElementsByAttributeValue("data-test-id", "ArticleNumber").text().split(" ")[1];
    }

    @Override
    public String parseName(Document document) {
        return document.getElementsByAttributeValue("data-test-id", "ProductName").text();
    }

    public String parseBrand(Document document) {
        return document.getElementsByAttributeValue("data-test-id", "BrandLogo").attr("alt");
    }

    public String parseColor(Document document) {
        String color;
        Element colorList = document.getElementsByAttributeValue("data-test-id", "ThumbnailsList")
                .first();
        if (document.getElementsByAttributeValue("data-test-id", "Slider").first() != null) {
            color = colorList.getElementsByClass("slick-current").first()
                    .getElementsByAttributeValue("data-test-id", "ColorVariantColorInfo").text();
        } else {
            color = colorList.getElementsByClass("active").first()
                    .getElementsByAttributeValue("data-test-id", "ColorVariantColorInfo").text();
        }
        return color;
    }

    @Override
    public List<Size> parseSize(WebDriver driver, Document document) {
        List<Size> sizes = new ArrayList<>();
        Element sizeWrapper = document.getElementsByAttributeValue("data-test-id", "SizeSelectorWrapper").first();

        Element sizeSelector;
        if (sizeWrapper.children().size() == 1) {
            sizeSelector = sizeWrapper.child(0).child(1);
        } else {
            sizeSelector = sizeWrapper.child(1);
        }

        String sizeSelectorType = sizeSelector.attr("data-test-id");
        switch (sizeSelectorType) {
            case "SizeBubbleList": {
                Elements sizeElements = sizeSelector.children();
                Element selectorVendorSize = document
                        .getElementsByAttributeValue("data-test-id", "SizeSelectorHeadlineVendorSize").first();
                if (selectorVendorSize != null) {
                    List<LabelInchSize> curSizes = new ArrayList<>();
                    for (Element sizeElement : sizeElements) {
                        if (sizeElement.attr("data-test-id").equals("SizeBubble_selected") ||
                                sizeElement.attr("data-test-id").equals("SizeBubble_available")) {
                            LabelInchSize size = new LabelInchSize();
                            size.setLabel(sizeElement.children().first().text());
                            curSizes.add(size);
                        }
                    }
                    driver.findElement(By.className("bBDHNF"));
                    document = getCurrentDocument(driver);
                    sizeSelector = document.getElementsByAttributeValue("data-test-id", "SizeBubbleList").first();
                    sizeElements = sizeSelector.children();
                    for (int i = 0, j = 0; i < sizeElements.size(); i++) {
                        if (sizeElements.get(i).attr("data-test-id").equals("SizeBubble_selected") ||
                                sizeElements.get(i).attr("data-test-id").equals("SizeBubble_available")) {
                            curSizes.get(j++).setInch(sizeElements.get(i).children().first().text());
                        }
                    }
                    sizes.addAll(curSizes);
                } else {
                    List<LabelSize> curSizes = new ArrayList<>();
                    for (Element sizeChild : sizeElements) {
                        if (sizeChild.attr("data-test-id").equals("SizeBubble_selected")) {
                            LabelSize size = new LabelSize();
                            size.setLabel(sizeChild.children().first().text());
                            curSizes.add(size);
                        }
                    }
                    sizes.addAll(curSizes);
                }
            }
            break;
            case "SingleSizeDropdown": {
                driver.findElement(By.className("lhcQGG")).click();
                document = getCurrentDocument(driver);
                Element dropdownList = document.getElementsByAttributeValue("data-test-id", "DropdownList").first();
                Elements sizeElements = dropdownList
                        .getElementsByAttributeValue("data-test-id", "DropdownItem_Active");
                Element dropdownTitle = dropdownList
                        .getElementsByAttributeValue("data-test-id", "EmbeddedDropdownTitle").first();

                if (dropdownTitle.children().size() != 0) {
                    List<LabelInchSize> curSizes = new ArrayList<>();
                    for (Element sizeElement : sizeElements) {
                        curSizes.add(new LabelInchSize(sizeElement.child(0).child(0).text(),
                                sizeElement.child(0).child(1).text()));
                    }
                    sizes.addAll(curSizes);
                } else {
                    if (sizeElements.get(0).child(0).text().replace("-", "")
                            .chars().allMatch(Character::isDigit)) {
                        List<InchSize> curSizes = new ArrayList<>();
                        for (Element sizeElement : sizeElements) {
                            curSizes.add(new InchSize(sizeElement.child(0).text()));
                        }
                        sizes.addAll(curSizes);
                    } else {
                        List<LabelSize> curSizes = new ArrayList<>();
                        for (Element sizeElement : sizeElements) {
                            curSizes.add(new LabelSize(sizeElement.child(0).text()));
                        }
                        sizes.addAll(curSizes);
                    }
                }
            }
            break;
            case "DoubleSizeDropdown": {
                List<WebElement> buttons = driver.findElements(By.className("lhcQGG"));
                buttons.get(0).click();
                document = getCurrentDocument(driver);
                Element widthDropdownList = document
                        .getElementsByAttributeValue("data-test-id", "DropdownList").first();
                Elements widthSizeElements = widthDropdownList
                        .getElementsByAttributeValue("data-test-id", "DropdownItem_Active");
                buttons.get(0).click();
                String[] widthId = new String[widthSizeElements.size()];
                for (int i = 0; i < widthSizeElements.size(); i++) {
                    widthId[i] = widthSizeElements.get(i).attr("id");
                }
                List<ProportionsSize> curSizes = new ArrayList<>();
                int idSwitcher = 0;
                for (Element widthElement : widthSizeElements) {
                    String width = widthElement.child(0).text();
                    buttons.get(0).click();
                    driver.findElement(By.id(widthId[idSwitcher++])).click();
                    buttons.get(1).click();
                    document = getCurrentDocument(driver);
                    Element lengthDropdownList = document
                            .getElementsByAttributeValue("data-test-id", "DropdownList").last();
                    Elements lengthSizeElements = lengthDropdownList
                            .getElementsByAttributeValue("data-test-id", "DropdownItem_Active");
                    for (Element lengthElement : lengthSizeElements) {
                        curSizes.add(new ProportionsSize(width, lengthElement.child(0).text()));
                    }
                }
                sizes.addAll(curSizes);
            }
            break;
        }
        return sizes;
    }

    @Override
    public double parsePrice(Document document) {
        Element priceElement = document.getElementsByAttributeValue("data-test-id", "ProductPriceFormattedBasePrice")
                .first();

        if (priceElement == null) {
            priceElement = document.getElementsByAttributeValue("data-test-id", "ProductPriceCampaignWithSale")
                    .first();
        }

        if (priceElement == null) {
            priceElement = document.getElementsByAttributeValue("data-test-id", "ProductPriceCampaignWithoutSale")
                    .first();
        }

        if (priceElement == null) {
            priceElement = document.getElementsByAttributeValue("data-test-id", "FormattedSalePrice")
                    .first();
        }

        return Double.parseDouble(priceElement.text()
                .replaceAll("[A-Za-z]", "").trim().replace(",", "."));
    }
}
