package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import static pages.SimpadLocators.MENU_VIEW_PROFILE;

public class SimpadProjectPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SimpadProjectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ========================================================================
    // HELPER: Bypass React's Synthetic Events agar tulisan tidak menumpuk
    // ========================================================================
    private void setReactInputValue(WebElement element, String value) {
        String jsScript =
                "var input = arguments[0];" +
                        "var value = arguments[1];" +
                        "var setter = Object.getOwnPropertyDescriptor((input.tagName.toLowerCase() === 'textarea' ? window.HTMLTextAreaElement : window.HTMLInputElement).prototype, 'value').set;" +
                        "setter.call(input, value);" +
                        "input.dispatchEvent(new Event('input', { bubbles: true }));" +
                        "input.dispatchEvent(new Event('change', { bubbles: true }));";
        ((JavascriptExecutor) driver).executeScript(jsScript, element, value);
    }

    // ========================================================================
    // FITUR UPLOAD PROJECT
    // ========================================================================
    public void bukaMenuUploadProject() {
        wait.until(ExpectedConditions.elementToBeClickable(SimpadLocators.IKON_PROFIL)).click();

        WebElement viewProfileBtn = wait.until(ExpectedConditions.presenceOfElementLocated(MENU_VIEW_PROFILE));
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProfileBtn);

        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        WebElement menuUpload = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.MENU_HOVER_UPLOAD));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(menuUpload)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuUpload);
        }
    }

    public void uploadFile(String pathAbsolut) {
        try {
            Thread.sleep(1500);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            Thread.sleep(500);
        } catch (Exception e) {}

        WebElement inputFile = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.INPUT_FILE_BROWSE));
        inputFile.sendKeys(pathAbsolut);
    }

    // MEMPERBAIKI GARIS MERAH: Menerima 4 Parameter (Judul, Tim, Link, Deskripsi)
    public void isiDeskripsiDanTim(String projectTitle, String teamName, String url, String deskripsi) {
        // 1. Mengisi Project Title
        WebElement inputTitle = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.INPUT_PROJECT_TITLE));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", inputTitle);
        setReactInputValue(inputTitle, projectTitle);
        inputTitle.sendKeys(Keys.TAB);

        // 2. Mengisi Nama Team
        WebElement inputTeam = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.INPUT_TEAM_NAME));
        setReactInputValue(inputTeam, teamName);
        inputTeam.sendKeys(Keys.TAB);

        // 3. Mengisi Deskripsi
        WebElement inputDesk = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.INPUT_PARAGRAF));
        setReactInputValue(inputDesk, deskripsi);
        inputDesk.sendKeys(Keys.TAB);

        // 4. Klik Tombol Link
        WebElement tombolLink = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.TOMBOL_ADD_LINK));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", tombolLink);
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tombolLink);

        // 5. Mengisi Kolom Link YouTube
        WebElement inputLink = wait.until(ExpectedConditions.visibilityOfElementLocated(SimpadLocators.INPUT_LINK_YT_AKTIF));
        setReactInputValue(inputLink, url);

        try {
            Thread.sleep(500);
            inputLink.sendKeys(Keys.SPACE, Keys.BACK_SPACE, Keys.ENTER);
            Thread.sleep(2000);
        } catch (Exception e) {}
    }

    public void pilihTigaUser(String user1, String user2, String user3) {
        String[] users = {user1, user2, user3};
        for (String user : users) {
            WebElement userElement = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.PILIH_USER_BERDASARKAN_NAMA(user)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", userElement);
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            new Actions(driver).moveToElement(userElement).click().perform();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }

    public void pilihTigaRole(String role1, String role2, String role3) {
        String[] roles = {role1, role2, role3};
        for (int i = 1; i <= 3; i++) {
            WebElement btnRole = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.TOMBOL_DROPDOWN_ROLE_KE(i)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnRole);
            try { Thread.sleep(500); } catch (InterruptedException e) {}

            new Actions(driver).moveToElement(btnRole).click().perform();
            try { Thread.sleep(600); } catch (InterruptedException e) {}

            WebElement opsiRole = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.OPSI_DROPDOWN_TEKS(roles[i - 1])));
            new Actions(driver).moveToElement(opsiRole).click().perform();
            try { Thread.sleep(600); } catch (InterruptedException e) {}
        }
    }

    public void klikPost() {
        WebElement btnPost = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.TOMBOL_POST));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnPost);
        try { Thread.sleep(500); } catch (InterruptedException e) {}
        new Actions(driver).moveToElement(btnPost).click().perform();
    }

    // ========================================================================
    // FITUR MELIHAT PROJECT & PROFIL ORANG LAIN
    // ========================================================================
    public void pindahKeHalamanProject() {
        // 1. Jeda 2 detik setelah menekan tombol POST agar server selesai memproses
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // 2. Trik Soft Refresh: Klik sembarang area kosong atau Ikon Profil untuk menutup pop-up/dropdown (jika ada)
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

        // 3. Cari dan klik menu Project
        WebElement menuProject = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.MENU_PROJECT));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuProject);

        // 4. Jeda untuk memastikan kartu-kartu project baru berhasil dimuat oleh React
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    public void klikProjectBerdasarkanNama(String namaProject) {
        // 1. Minta Selenium mencari elemen kartu berdasarkan potongan namanya
        WebElement kartuProject = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.KARTU_PROJECT_NAMA(namaProject)));

        // 2. Paksa browser untuk scroll tepat ke elemen tersebut agar masuk ke dalam layar
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", kartuProject);
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Jeda sebentar setelah scroll

        // 3. JURUS PAMUNGKAS: Paksa klik menggunakan Javascript Executor!
        // Ini akan menembus segala jenis bug UI atau gambar pecah.
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", kartuProject);

        // 4. Jeda agar halaman detail project sempat terbuka
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    public void klikFotoAnggotaTim() {
        WebElement fotoAnggota = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.FOTO_ANGGOTA_TIM));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", fotoAnggota);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fotoAnggota);
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }

    // ========================================================================
    // FITUR LOGOUT (ANTI-GAGAL)
    // ========================================================================
    public void prosesLogout() {
        // 1. WAJIB: Scroll kembali ke paling atas halaman!
        // Karena di langkah E2E sebelumnya layar digeser ke bawah, kita harus kembalikan posisinya.
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // 2. Buka dropdown profil (Gunakan kombinasi Actions & JS)
        WebElement ikonProfil = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.IKON_PROFIL));
        try {
            new Actions(driver).moveToElement(ikonProfil).click().perform();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ikonProfil);
        }

        // Jeda agar animasi dropdown terbuka sempurna
        try { Thread.sleep(1500); } catch (InterruptedException e) {}

        // 3. Cari tombol Logout
        WebElement menuLogout = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.MENU_LOGOUT));

        // 4. Serangan Ganda (Dual Click Attack)
        // Coba klik layaknya manusia (Actions), jika di-intercept oleh React, paksa pakai JS
        try {
            new Actions(driver).moveToElement(menuLogout).click().perform();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuLogout);
        }

        System.out.println("Tombol Logout berhasil dieksekusi!");

        // Jeda agar proses pembersihan sesi (session) dan redirect ke halaman awal selesai
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }
    public void bukaProfilSendiri() {
        // 1. Klik ikon foto profil di pojok kanan atas (menggunakan locator yang biasa Anda pakai untuk logout)
        WebElement ikonProfil = wait.until(ExpectedConditions.elementToBeClickable(SimpadLocators.IKON_PROFIL));
        ikonProfil.click();

        // 2. Beri sedikit jeda agar animasi dropdown selesai
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // 3. Klik menu profil user
        WebElement menuProfile = wait.until(ExpectedConditions.elementToBeClickable(MENU_VIEW_PROFILE));
        menuProfile.click();

        // 4. Jeda untuk membiarkan halaman profil dimuat sebelum lanjut ke langkah Logout
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }
    public void scrollHalamanProject() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Jeda sebentar setelah masuk ke halaman project
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // Scroll ke bawah sebanyak 500 pixel
        js.executeScript("window.scrollBy(0, 500);");

        // Jeda 1 detik (seolah user sedang membaca)
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // Scroll lagi ke bawah sebanyak 500 pixel
        js.executeScript("window.scrollBy(0, 500);");

        // Jeda lagi sebelum lanjut ke langkah berikutnya (buka profil)
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }
    public void scrollHalamanProfil() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // Hanya scroll ke bawah
        js.executeScript("window.scrollBy(0, 600);");
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }
    public void klikTombolExplorePAD() {
        // 1. Tunggu sampai tombol muncul dan bisa diklik
        WebElement btnExplore = wait.until(ExpectedConditions.elementToBeClickable(SimpadLocators.TOMBOL_EXPLORE_PAD));

        // 2. Scroll sedikit jika tombolnya berada di luar layar (opsional tapi aman)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnExplore);
        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // 3. Klik tombolnya
        btnExplore.click();


        // 4. Jeda untuk memastikan halaman Project selesai dimuat
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }
    public void scrollHalamanDetail() {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Jeda sebentar memastikan halaman detail project sudah terbuka penuh
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // 2. Scroll ke bawah sedikit (misal sejauh 400 pixel) seolah membaca deskripsi
        js.executeScript("window.scrollBy(0, 400);");

        // 3. Jeda 1 detik sebelum sistem mencari dan mengeklik foto profil
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
    }
    public void klikTombolEditProject() {
        // 1. Tunggu tombol muncul
        WebElement btnEdit = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.TOMBOL_EDIT_PROJECT));

        // 2. Pastikan tombol benar-benar ada di tengah layar sebelum diklik
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnEdit);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        // 3. Eksekusi klik
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnEdit);

        // 4. Jeda untuk membiarkan halaman Form Edit terbuka
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }
    public void klikTombolDeleteProject() {
        // 1. Klik tombol delete pertama
        WebElement btnDelete = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.TOMBOL_DELETE_PROJECT));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnDelete);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDelete);

        // 2. Jeda agar animasi pop-up konfirmasi selesai muncul ke layar
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
    }

    public void konfirmasiDeleteProject() {
        // 1. Cari tombol Delete merah di pop-up
        WebElement btnConfirm = wait.until(ExpectedConditions.elementToBeClickable(SimpadLocators.TOMBOL_KONFIRMASI_DELETE));

        // 2. Klik tombol konfirmasi (tombol pop-up biasanya aman diklik langsung tanpa JS Executor)
        btnConfirm.click();

        // 3. Jeda untuk membiarkan sistem memproses penghapusan data dari database
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }
    // PASTIKAN BARIS INI MEMILIKI 4 PARAMETER: judulBaru, pathGambarBaru, deskripsiBaru, linkBaru
    public void isiFormEditProject(String judulBaru, String pathGambarBaru, String deskripsiBaru, String linkBaru) {
        // 1. Edit Judul
        WebElement inputTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(SimpadLocators.INPUT_EDIT_TITLE));
        inputTitle.sendKeys(Keys.CONTROL + "a");
        inputTitle.sendKeys(Keys.BACK_SPACE);
        inputTitle.sendKeys(judulBaru);

        // 2. Upload File Baru
        WebElement inputFile = driver.findElement(SimpadLocators.INPUT_EDIT_FILE);
        inputFile.sendKeys(pathGambarBaru);

        // 3. Edit Deskripsi
        WebElement inputDesc = driver.findElement(SimpadLocators.TEXTAREA_EDIT_DESC);
        inputDesc.sendKeys(Keys.CONTROL + "a");
        inputDesc.sendKeys(Keys.BACK_SPACE);
        inputDesc.sendKeys(deskripsiBaru);

        // 4. Edit Link YouTube
        WebElement btnLink = wait.until(ExpectedConditions.elementToBeClickable(SimpadLocators.INPUT_EDIT_LINK));
        btnLink.click();
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // Jeda pop up input link

        WebElement inputLink = driver.findElement(SimpadLocators.INPUT_EDIT_LINK);
        inputLink.sendKeys(Keys.CONTROL + "a");
        inputLink.sendKeys(Keys.BACK_SPACE);
        inputLink.sendKeys(linkBaru);

        // --- JURUS RAHASIA: Tekan TAB agar kursor keluar dari kotak input ---
        inputLink.sendKeys(Keys.TAB);

        // Jeda ekstra (3 detik) agar React selesai memproses seluruh inputan sebelum pindah ke langkah Submit
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
    }

    public void klikSubmitEditProject() {
        WebElement btnSubmit = wait.until(ExpectedConditions.elementToBeClickable(SimpadLocators.TOMBOL_SUBMIT_EDIT));
        // Jeda setelah scroll sebelum diklik
        try { Thread.sleep(10); } catch (InterruptedException e) {}

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSubmit);

        try { Thread.sleep(3000); } catch (InterruptedException e) {}

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btnSubmit);

    }
}