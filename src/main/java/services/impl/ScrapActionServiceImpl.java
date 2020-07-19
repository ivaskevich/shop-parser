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
    private WebDriverImpl webDriver;

    public ScrapActionServiceImpl() {
        this.siteRepresents = new ArrayList<>();
        initDriver();
    }

    public void initDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = setChromeOptions();
        this.webDriver = new WebDriverImpl(driver);
    }

    @Override
    public void performScraping() {
        for (SiteRepresent site : siteRepresents) {
            for (String uri : site.getSubUrls()) {
                site.parseCurrentPage(webDriver, buildUrl(site.getShopUrl(), uri));
            }
        }
        webDriver.quit();
    }

    private String buildUrl(String domain, String uri) {
        return domain + uri;
    }

    @Override
    public void addSiteRepresent(SiteRepresent siteRepresent) {
        if (siteRepresent != null) siteRepresents.add(siteRepresent);
    }

    private ChromeDriver setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    @Override
    public int getRequestsNumber(){
        return webDriver.getHttpGetCounter().getCount();
    }
}
