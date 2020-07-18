package services.impl;

import entities.SiteRepresent;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import services.interfaces.ScrapActionService;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ScrapActionServiceImpl implements ScrapActionService {
    private final List<SiteRepresent> siteRepresents;

    public ScrapActionServiceImpl() {
        this.siteRepresents = new ArrayList<>();
    }

    @Override
    public void performScraping() {
        WebDriverManager.chromedriver().version("80.0.3987.106").setup();
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = setChromeOptions();

        for (SiteRepresent site : siteRepresents) {
            for (String url : site.getSubUrls()) {
                driver.get(site.getShopUrl() + url);
                site.parseCurrentPage(driver);
            }
        }
        driver.quit();
    }

    @Override
    public void addSiteRepresent(SiteRepresent siteRepresent) {
        if (siteRepresent != null) siteRepresents.add(siteRepresent);
    }

    private ChromeDriver setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }
}
