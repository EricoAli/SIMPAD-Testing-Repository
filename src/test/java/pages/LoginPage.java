package pages;

import org.openqa.selenium.*;

/**
 * LoginPage — POM untuk "Login Page" SIMPAD.
 *
 * URL: https://simpad-frontend.vercel.app/login
 *
 * Halaman ini menggunakan Google SSO (Email UGM).
 * Tidak ada form username/password — hanya tombol "Log In with Email UGM".
 */
public class LoginPage extends BasePage {

    // ── URL (Ini akan memperbaiki error LandingPreLoginPage dan DashboardPage) ──
    public static final String BASE_URL = "https://simpad-frontend.vercel.app";
    public static final String LOGIN_URL = BASE_URL + "/login";

    // ── Constructor (Ini akan memperbaiki error "no parameterless constructor") ──
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ── Locators ─────────────────────────────────────────────
    private final By loginTitle     = By.xpath(
            "//*[normalize-space(text())='Log in' or normalize-space(text())='Login']");

    private final By ssoButton      = By.xpath(
            "//button[contains(.,'Log In with Email UGM')] | " +
                    "//a[contains(.,'Log In with Email UGM')]");

    private final By backToHomeBtn  = By.xpath(
            "//button[contains(.,'Back to Home')] | " +
                    "//a[contains(.,'Back to Home')]");

    // Toast error "Login Failed"
    private final By toastContainer = By.cssSelector(
            "[class*='toast' i], [class*='notification' i], [class*='alert' i], [role='alert']");

    private final By toastTitle     = By.xpath(
            "//*[contains(text(),'Login Failed') or contains(text(),'login failed')]");

    private final By toastMessage   = By.xpath(
            "//*[contains(text(),'Network error') or contains(text(),'check your connection')]");

    private final By toastCloseBtn  = By.cssSelector(
            "[class*='toast' i] button[class*='close' i], [role='alert'] button, " +
                    "[class*='toast' i] .close, [class*='dismiss' i]");

    // ── Navigation ───────────────────────────────────────────

    public void open() {
        driver.get(LOGIN_URL);
        pause(2000);
        System.out.println("[PAGE] Login Page: " + getCurrentUrl());
    }

    // ── Page State ───────────────────────────────────────────

    public boolean isDisplayed() {
        return getCurrentUrl().contains("/login") ||
                isElementVisible(loginTitle) ||
                isElementVisible(ssoButton);
    }

    public boolean isTitleVisible()      { return isElementVisible(loginTitle); }
    public boolean isSsoButtonVisible()  { return isElementVisible(ssoButton); }
    public boolean isBackToHomeVisible() { return isElementVisible(backToHomeBtn); }

    public boolean isToastVisible() {
        return isElementVisible(toastTitle) || isElementVisible(toastContainer);
    }

    public boolean isErrorToastDisplayed() {
        pause(1500);
        return isElementVisible(toastTitle) ||
                isElementVisible(toastMessage) ||
                isElementVisible(toastContainer);
    }

    public String getToastText() {
        if (isElementVisible(toastContainer)) return getText(toastContainer);
        return "";
    }

    // ── Actions ──────────────────────────────────────────────

    /** Klik tombol SSO — akan redirect ke Google OAuth */
    public void clickSsoButton() {
        click(ssoButton);
        pause(2500);
        System.out.println("[ACTION] Klik SSO button → URL: " + getCurrentUrl());
    }

    public void clickBackToHome() {
        click(backToHomeBtn);
        pause(1500);
    }

    public void closeToast() {
        if (isElementVisible(toastCloseBtn)) click(toastCloseBtn);
    }

    // ── Post-click Checks ────────────────────────────────────

    /** Apakah browser sudah redirect ke Google OAuth? */
    public boolean isRedirectedToGoogleOAuth() {
        pause(3000);
        String url = getCurrentUrl();
        System.out.println("[CHECK] URL setelah klik SSO: " + url);
        return url.contains("accounts.google.com") || url.contains("google.com/o/oauth2");
    }
}