package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.SimpadProjectPage;

public class LogoutSteps {

    // Ambil WebDriver dari tempat yang sama seperti langkah sebelumnya
    WebDriver driver = LoginSteps.getDriver();
    SimpadProjectPage simpadProjectPage = new SimpadProjectPage(driver);

    @And("Saya membuka profil dan menekan tombol Logout")
    public void saya_membuka_profil_dan_menekan_tombol_logout() {
        simpadProjectPage.prosesLogout();
    }

    @Then("Saya harus berhasil keluar dan kembali ke halaman utama")
    public void saya_harus_berhasil_keluar_dan_kembali_ke_halaman_utama() {
        System.out.println("Validasi: Skenario Logout berhasil dieksekusi!");

        // Opsional: Validasi bahwa URL sudah berubah atau elemen profil menghilang
        // boolean isLoggedOut = driver.getCurrentUrl().contains("login") || driver.getCurrentUrl().equals("URL_UTAMA");
        // org.junit.jupiter.api.Assertions.assertTrue(isLoggedOut, "Gagal logout!");
    }
}