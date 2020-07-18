package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import services.impl.HttpGetCounter;
import services.interfaces.ProductService;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class SiteRepresent {
    private String shopUrl;
    private List<String> subUrls;
    private ProductService productService;

    public abstract void parseCurrentPage(WebDriver driver, String url);
}
