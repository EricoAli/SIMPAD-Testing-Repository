package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class GoogleAuthPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By continueConsentButton = By.xpath("//span[text()='Lanjutkan' or text()='Continue']");

    public GoogleAuthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void inputEmailAndEnter(String email) {
        wait.until(ExpectedConditions.urlContains("accounts.google.com"));
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        driver.switchTo().activeElement().sendKeys(email + Keys.ENTER);
    }

    public void clickContinueConsent() {
        wait.until(ExpectedConditions.elementToBeClickable(continueConsentButton)).click();
    }
}