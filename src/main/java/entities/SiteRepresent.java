package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import services.impl.ProductService;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class SiteRepresent {
    private List<String> urls;
    private ProductService productService;

    public abstract void parseCurrentPage(WebDriver driver);
}
