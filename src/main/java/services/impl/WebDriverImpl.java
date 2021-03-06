package services.impl;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

@Getter
public class WebDriverImpl implements WebDriver {
    private final WebDriver webDriver;
    private final HttpGetCounter httpGetCounter;

    /**
     * Custom WebDriver implementation in order to count requests
     *
     * @param webDriver - WebDriver interface
     */
    public WebDriverImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.httpGetCounter = new HttpGetCounter();
    }

    public void executeScript(String query) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript(query);
        sleep(1000);
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void get(String s) {
        webDriver.get(s);
        httpGetCounter.increment();
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public <T extends WebElement> List<T> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public <T extends WebElement> T findElement(By by) {
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void click(WebElement element){
        scrollToElement(element);
        element.click();
    }
}
