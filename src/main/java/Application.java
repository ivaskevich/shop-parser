import entities.SiteRepresent;
import repositories.impl.ProductRepositoryImpl;
import repositories.interfaces.ProductRepository;
import services.impl.ProductServiceImpl;
import services.impl.ScrapActionServiceImpl;
import services.interfaces.ProductService;
import services.interfaces.ScrapActionService;
import shops.AboutYou;

import java.util.Collections;

public class Application {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.silentOutput", "true");

        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl(productRepository);
        SiteRepresent aboutYou = new AboutYou("https://www.aboutyou.de",
                Collections.singletonList("/maenner/bekleidung"), productService);
        ScrapActionService scrapActionService = new ScrapActionServiceImpl();
        scrapActionService.addSiteRepresent(aboutYou);
        scrapActionService.performScraping();

        System.out.println("Number of products : " + productService.findAllProducts().size());
        System.out.println("Number of requests : " + scrapActionService.getRequestsNumber());

        productService.writeJsonDataToFile();
    }
}
