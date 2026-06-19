package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.SimpadLocators;
import pages.SimpadHomePage;
import pages.GoogleAuthPage;
import pages.SsoUgmPage;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginSteps {

    // DRIVER STATIC AGAR BISA DIBAGIKAN KE STEP LAIN
    private static WebDriver driver;
    private WebDriverWait wait;
    private String mainWindowHandle;

    private SimpadHomePage homePage;
    private GoogleAuthPage googlePage;
    private SsoUgmPage ssoPage;

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-features=BlockInsecurePrivateNetworkRequests");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_setting_values.local_network", 1);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @Given("Saya berada di halaman utama SIMPAD")
    public void saya_berada_di_halaman_utama_simpad() {
        driver.get("https://simpad-frontend.vercel.app/");
        mainWindowHandle = driver.getWindowHandle();
        homePage = new SimpadHomePage(driver);
        googlePage = new GoogleAuthPage(driver);
        ssoPage = new SsoUgmPage(driver);
    }

    @When("Saya memilih login dengan Email UGM")
    public void saya_memilih_login_dengan_email_ugm() throws InterruptedException {
        homePage.clickLogin();
        Thread.sleep(1000);
        homePage.clickLoginUgm();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!mainWindowHandle.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @When("Saya melakukan otentikasi Google dengan email {string}")
    public void saya_melakukan_otentikasi_google_dengan_email(String email) {
        googlePage.inputEmailAndEnter(email);
    }

    @When("Saya memasukkan kredensial SSO UGM dengan email {string} dan password {string}")
    public void saya_memasukkan_kredensial_sso_ugm_dengan_email_dan_password(String email, String password) {
        ssoPage.loginToSso(email, password);
    }

    @When("Saya menyetujui layar persetujuan Google")
    public void saya_menyetujui_layar_persetujuan_google() {
        googlePage.clickContinueConsent();
    }

    @Then("Saya harus berhasil masuk dan melihat ikon profil di dashboard")
    public void saya_harus_berhasil_masuk_dan_melihat_ikon_profil_di_dashboard() {
        wait.until(ExpectedConditions.numberOfWindowsToBe(1));
        driver.switchTo().window(mainWindowHandle);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SimpadLocators.IKON_PROFIL));
        System.out.println("Login BDD Sukses: Ikon profil berhasil ditemukan!");
    }

    // METHOD BACKGROUND UNTUK TEST ADD PROJECT
    @Given("Saya sudah login ke aplikasi SIMPAD")
    public void saya_sudah_login_ke_aplikasi_simpad() throws InterruptedException {
        saya_berada_di_halaman_utama_simpad();
        saya_memilih_login_dengan_email_ugm();

        // --- MASUKKAN EMAIL DAN PASSWORD ANDA DI SINI ---
        saya_melakukan_otentikasi_google_dengan_email("delvianokhayruattahira@mail.ugm.ac.id");
        saya_memasukkan_kredensial_sso_ugm_dengan_email_dan_password("delvianokhayruattahira", "eND3AVOR123");
        // ------------------------------------------------

        saya_menyetujui_layar_persetujuan_google();
        saya_harus_berhasil_masuk_dan_melihat_ikon_profil_di_dashboard();
    }
}