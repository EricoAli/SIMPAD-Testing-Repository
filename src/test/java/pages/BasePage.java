package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage — Kelas dasar untuk semua Page Object.
 * Menyediakan utility Selenium yang dipakai bersama oleh semua halaman.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int TIMEOUT = 15;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        waitForElement(locator).click();
    }

    protected String getText(By locator) {
        return waitForVisible(locator).getText().trim();
    }

    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try { driver.findElement(locator); return true; }
        catch (NoSuchElementException e) { return false; }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean waitForUrlContains(String fragment) {
        try { return wait.until(ExpectedConditions.urlContains(fragment)); }
        catch (TimeoutException e) { return false; }
    }

    protected void pause(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
