package pages;

import org.openqa.selenium.*;

/**
 * DashboardPage - Page Object Model untuk halaman Dashboard SIMPAD setelah login.
 *
 * Karena login menggunakan Google SSO (Email UGM), halaman dashboard
 * hanya dapat diakses oleh akun UGM yang valid.
 * Test logout & akses terproteksi dilakukan dari sini.
 */
public class DashboardPage extends BasePage {

    // ===== LOCATORS =====
    // Indikator dashboard berhasil dimuat
    private final By dashboardContainer  = By.cssSelector(
        "#Upper, .dashboard, main, .main-content, .container, [class*='dashboard']");
    private final By navigationMenu      = By.cssSelector(
        "nav, .navbar, .sidebar, header");
    private final By userProfileArea     = By.cssSelector(
        ".user-info, .profile, .avatar, [class*='user'], [class*='profile']");

    // Logout elements
    private final By logoutButton = By.xpath(
        "//button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'logout') " +
        "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'log out') " +
        "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'sign out') " +
        "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'keluar')]");
    private final By logoutLink = By.xpath(
        "//a[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'logout') " +
        "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'log out') " +
        "or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'keluar')]");
    private final By userDropdown = By.cssSelector(
        ".dropdown-toggle, [class*='avatar'], [class*='user-menu'], [class*='profile-btn']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ===== PAGE STATE =====

    public boolean isDashboardLoaded() {
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        boolean urlOk = !getCurrentUrl().contains("/login") &&
                        getCurrentUrl().startsWith(LoginPage.BASE_URL);
        boolean elementOk = isElementVisible(dashboardContainer) ||
                            isElementVisible(navigationMenu);
        return urlOk && elementOk;
    }

    public boolean isUserLoggedIn() {
        return !getCurrentUrl().contains("/login");
    }

    public boolean isLogoutButtonVisible() {
        return isElementVisible(logoutButton) || isElementVisible(logoutLink) ||
               isElementVisible(userDropdown);
    }

    // ===== LOGOUT =====

    public void performLogout() {
        // Metode 1: klik langsung tombol/link logout
        if (isElementVisible(logoutLink)) {
            click(logoutLink);
            return;
        }
        if (isElementVisible(logoutButton)) {
            click(logoutButton);
            return;
        }
        // Metode 2: buka user dropdown dulu
        if (isElementVisible(userDropdown)) {
            click(userDropdown);
            try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            if (isElementVisible(logoutButton)) {
                click(logoutButton);
                return;
            }
            if (isElementVisible(logoutLink)) {
                click(logoutLink);
                return;
            }
        }
        System.out.println("[WARN] Tombol logout tidak ditemukan di halaman: " + getCurrentUrl());
    }

    public boolean isRedirectedToLoginPage() {
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        String url = getCurrentUrl();
        return url.contains("/login") ||
               url.equals(LoginPage.BASE_URL) ||
               url.equals(LoginPage.BASE_URL + "/");
    }
}
