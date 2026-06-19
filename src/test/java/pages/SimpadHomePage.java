package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class SimpadHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginButton = By.xpath("//button[text()='Log In']");
    private By loginUgmButton = By.xpath("//button[contains(., 'Log In with Email UGM')]");

    public SimpadHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickLoginUgm() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(loginUgmButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }
}