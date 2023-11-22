package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

public class BaseMethod {

    protected static Wait<WebDriver> wait;
    private static final long WEB_DRIVER_WAIT = 20;
    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected void setDriverAndWait(WebDriver driver) {
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(WEB_DRIVER_WAIT))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public Boolean goToHomepage() {
        try {
            openBrowser();
            getDriver().get("https://regressionshopsm.dermpro.com/");
        } catch (Exception e) {
            System.out.println("Unable to navigate to the homepage");
            e.fillInStackTrace();
            return false;
        }
        return true;
    }

    private void openBrowser() {


        // In case browser is set from a file system, use it instead of property file
        String browser = "chrome";

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        switch (browser.toLowerCase()) {
            case "chrome":
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--start-maximized");
                driver.set(ThreadGuard.protect(new ChromeDriver(options)));
                break;
            case "chrome_headless":
                // Run in headless mode
                options.addArguments("--headless");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--start-maximized");
                driver.set(ThreadGuard.protect(new ChromeDriver(options)));
                break;
        }

        assert getDriver() != null;
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }

    public void closeBrowser() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    protected boolean isElementPresent(String element) {
        List<WebElement> elements = getDriver().findElements(By.xpath(element));
        return !elements.isEmpty();
    }

    protected void scrollIntoView(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' })", element);
        sleep(300);
    }

    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
    }

    private void waitWithScroll(WebElement element) {
        waitWithScroll(element, wait);
    }

    private void waitWithScroll(WebElement element, Wait<WebDriver> driverWait) {
        scrollIntoView(element);
        driverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitWithScroll(element);
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", element);
        }
    }

    protected void sendText(WebElement element, String value) {
        waitWithScroll(element);
        element.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
        element.sendKeys(value);
    }

    protected void openUrl(String url) {
        getDriver().navigate().to(url);
    }

    public void openNewTabWithUrl(String url) {
        ((JavascriptExecutor) getDriver()).executeScript("window.open('about:blank', '_blank')");
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        getDriver().get(url);
    }

    protected String getCurrentDayMonth() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMdd");
        LocalDateTime currentDate = LocalDateTime.now();
        return dateFormat.format(currentDate);
    }

    public void waitUntilCheckoutLoaderDisappears() throws TimeoutException {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='checkout-loader']")));
        } catch (TimeoutException ex) {
            //throw ex;
        }
    }

    public void closeSecondTabIfOpen() {
        List<String> browserTabs = new ArrayList<String>(getDriver().getWindowHandles());
        if (browserTabs.size() == 2) {
            getDriver().switchTo().window(browserTabs.get(1));
            getDriver().close();
            getDriver().switchTo().window(browserTabs.get(0));
            List<String> browserTabs2 = new ArrayList<String>(getDriver().getWindowHandles());

            Assert.assertEquals(browserTabs2.size(), 1, "Opened tab should be closed");
        }
    }

    public void switchToLastTab() {
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        int tabsSize = tabs.size();
        getDriver().switchTo().window(tabs.get(tabsSize - 1));
    }

    public void closeAllExtraTabs() {
        List<String> browserTabs = new ArrayList<>(getDriver().getWindowHandles());
        for (int i = 1; i < browserTabs.size(); i++) {
            getDriver().switchTo().window(browserTabs.get(i));
            getDriver().close();
        }
        getDriver().switchTo().window(browserTabs.get(0));
        getDriver().switchTo().defaultContent();
    }

    public Wait<WebDriver> customWait(int seconds) {
        return new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
    }

    public void refreshPageWithWait() {
        getDriver().navigate().refresh();
        wait.until(driver -> ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").equals("complete"));
    }

    public void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        getDriver().manage().addCookie(cookie);
    }

    public void deleteAllCookies() {
        getDriver().manage().deleteAllCookies();
    }

}
