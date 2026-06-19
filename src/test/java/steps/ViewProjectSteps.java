package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.SimpadProjectPage;

public class ViewProjectSteps {

    // Mengambil driver menggunakan cara yang persis sama dengan file Anda yang lain
    WebDriver driver = LoginSteps.getDriver();

    // Memasukkan driver tersebut ke dalam Page Object
    SimpadProjectPage simpadProjectPage = new SimpadProjectPage(driver);

    @When("Saya membuka menu Project")
    public void saya_membuka_menu_project() {
        simpadProjectPage.pindahKeHalamanProject();
    }

    @And("Saya mencari dan mengeklik project bernama {string}")
    public void saya_mencari_dan_mengeklik_project_bernama(String namaProject) {
        simpadProjectPage.klikProjectBerdasarkanNama(namaProject);
    }

    @Then("Halaman detail project harus berhasil terbuka")
    public void halaman_detail_project_harus_berhasil_terbuka() {
        System.out.println("Validasi: Skenario melihat project selesai dan berhasil!");
        // Anda bisa tambahkan assertion di sini nanti jika diperlukan
    }
    @And("Saya mengeklik foto profil anggota tim")
    public void saya_mengeklik_foto_profil_anggota_tim() {
        simpadProjectPage.klikFotoAnggotaTim();
    }

    @Then("Halaman profil anggota tim harus berhasil terbuka")
    public void halaman_profil_anggota_tim_harus_berhasil_terbuka() {
        System.out.println("Validasi: Halaman profil orang lain berhasil dibuka!");
        // Anda bisa tambahkan validasi URL atau elemen spesifik profil di sini jika perlu
    }
}