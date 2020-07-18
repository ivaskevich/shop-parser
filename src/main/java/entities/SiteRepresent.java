package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import services.impl.WebDriverImpl;
import services.interfaces.ProductService;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class SiteRepresent {
    private String shopUrl;
    private List<String> subUrls;
    private ProductService productService;

    public abstract void parseCurrentPage(WebDriverImpl driver, String url);
}
