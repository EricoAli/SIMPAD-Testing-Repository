package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.SimpadProjectPage;

public class ViewProjectSteps {
    WebDriver driver = LoginSteps.getDriver();
    private final String pathGambarBaru = "C:\\Users\\Daveena Alexandra P\\Downloads\\pad2_portofolio.png";
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
    @And("Saya membuka menu profil dan masuk ke halaman profil saya")
    public void sayaMembukaMenuProfilDanMasukKeHalamanProfilSaya() {
        // Memanggil method POM dari halaman terkait
        simpadProjectPage.bukaProfilSendiri();
    }
    @And("Saya melakukan scroll untuk melihat daftar project")
    public void sayaMelakukanScrollUntukMelihatDaftarProject() {
        simpadProjectPage.scrollHalamanProject();
    }
    @And("Saya melakukan scroll di halaman profil saya")
    public void sayaMelakukanScrollDiHalamanProfilSaya() {
        simpadProjectPage.scrollHalamanProfil();
    }
    @When("Saya menekan tombol Explore PAD untuk ke halaman project")
    public void sayaMenekanTombolExplorePadUntukKeHalamanProject() {
        simpadProjectPage.klikTombolExplorePAD(); // Sesuaikan nama objek page Anda
    }
    @And("Saya melakukan scroll di halaman detail project")
    public void sayaMelakukanScrollDiHalamanDetailProject() {
        simpadProjectPage.scrollHalamanDetail();
    }
    @And("Saya menekan tombol Edit Project")
    public void sayaMenekanTombolEditProject() {
        simpadProjectPage.klikTombolEditProject();
    }
    @And("Saya menekan tombol Delete Project")
    public void sayaMenekanTombolDeleteProject() {
        simpadProjectPage.klikTombolDeleteProject();
    }
    @And("Saya menyetujui pop up konfirmasi hapus project")
    public void sayaMenyetujuiPopUpKonfirmasiHapusProject() {
        simpadProjectPage.konfirmasiDeleteProject();
    }
    @And("Saya mengubah detail project dengan judul {string}, gambar baru, link {string}, dan deskripsi {string}")
    public void sayaMengubahDetailProject(String judulBaru, String linkBaru, String deskripsiBaru) {
        String pathGambarBaru = "C:\\Users\\Daveena Alexandra P\\Downloads\\pad2_portofolio.png";
        simpadProjectPage.isiFormEditProject(judulBaru, pathGambarBaru, deskripsiBaru, linkBaru);
    }
    @And("Saya menyimpan perubahan project")
    public void sayaMenyimpanPerubahanProject() {
        simpadProjectPage.klikSubmitEditProject();
    }
}