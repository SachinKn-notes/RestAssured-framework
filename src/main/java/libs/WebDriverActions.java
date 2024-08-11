package libs;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverActions {
    
    private WebDriver driver;
    static final int TIMEOUT = 60;
    private int driverNumber;

    public WebDriver getWebDriver() {
        return driver;
    }

    public int getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(int driverNumber) {
        this.driverNumber = driverNumber;
    }

    public void waitForElementToDisappear(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToBePresent(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementToBeVisible(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeEnabled(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(driver -> element.isEnabled());
    }

    public void waitForElementToBeClickable(By locator) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void openUrl(String url) {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "D:/Work/EXEs/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
        driver.get(url);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void click(By locator) {
        try {
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        } catch(Exception ex) {
            ex.printStackTrace();
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        }
    }

    public Object executeJavascript(String script, Object... elements) {
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            return javascriptExecutor.executeScript(script, elements);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void type(By locator, String value) {
        try {
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            driver.findElement(locator).sendKeys(value);
        } catch(Exception ex) {
            ex.printStackTrace();
            waitForElementToBePresent(locator);
            waitForElementToBeVisible(locator);
            waitForElementToBeEnabled(locator);
            driver.findElement(locator).sendKeys(value);
        }
    }

    public void type(By locator, String value, Keys keys) {
        type(locator, value);
        driver.findElement(locator).sendKeys(keys);
    }

    public void closeDriver() {
        driver.close();
    }

    public void quitDriver() {
        driver.quit();
    }
}


