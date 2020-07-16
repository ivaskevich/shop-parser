package services.impl;

import entities.SiteRepresent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import services.interfaces.ScrapActionService;

import java.util.List;

public class ScrapActionServiceImpl implements ScrapActionService {
    @Override
    public void performScraping(List<SiteRepresent> siteRepresents) {
        WebDriverManager.chromedriver().version("80.0.3987.106").setup();
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver driver = setChromeOptions();

        for (SiteRepresent site : siteRepresents) {
            for (String url : site.getUrls()) {
                driver.get(url);
                site.parse(driver);
            }
        }


    }

    private ChromeDriver setChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }
}
