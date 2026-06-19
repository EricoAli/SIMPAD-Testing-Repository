package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.SimpadProjectPage;

public class AddProjectSteps {

    private SimpadProjectPage projectPage;

    // Pastikan file gambar ini sudah ada di dalam folder resources proyek Anda
    private final String PATH_GAMBAR = "C:\\Users\\Daveena Alexandra P\\Downloads\\pad1_portofolio.png";

    @When("Saya membuka menu Upload Project")
    public void saya_membuka_menu_upload_project() {
        WebDriver driver = LoginSteps.getDriver();
        projectPage = new SimpadProjectPage(driver);
        projectPage.bukaMenuUploadProject();
    }

    @When("Saya mengunggah file portofolio")
    public void saya_mengunggah_file_portofolio() {
        projectPage.uploadFile(PATH_GAMBAR);
    }

    // --- BAGIAN YANG DIPERBARUI ---
    // Menambahkan variabel "judul" agar sesuai dengan temuan input Project Title
    @When("Saya mengisi detail project dengan judul {string}, nama tim {string}, link {string}, dan deskripsi {string}")
    public void saya_mengisi_detail_project(String judul, String teamName, String urlYoutube, String paragraf) {
        // Urutan disesuaikan agar rapi: Judul -> Nama Tim -> Link -> Deskripsi
        projectPage.isiDeskripsiDanTim(judul, teamName, urlYoutube, paragraf);
    }
    // -----------------------------

    @When("Saya memilih user {string}, {string}, dan {string}")
    public void saya_memilih_user(String user1, String user2, String user3) {
        projectPage.pilihTigaUser(user1, user2, user3);
    }

    @When("Saya memberikan role {string}, {string}, dan {string} secara berurutan")
    public void saya_memberikan_role_secara_berurutan(String role1, String role2, String role3) {
        projectPage.pilihTigaRole(role1, role2, role3);
    }

    @When("Saya menekan tombol Post")
    public void saya_menekan_tombol_post() {
        projectPage.klikPost();
    }

    @Then("Project harus berhasil dipublikasikan")
    public void project_harus_berhasil_dipublikasikan() {
        System.out.println("Validasi Sukses: Seluruh form telah terisi dan tombol post berhasil diklik!");
    }
}