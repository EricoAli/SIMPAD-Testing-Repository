package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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

        WebElement viewProfileBtn = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.MENU_VIEW_PROFILE));
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
        WebElement kartuProject = wait.until(ExpectedConditions.presenceOfElementLocated(SimpadLocators.KARTU_PROJECT_NAMA(namaProject)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", kartuProject);
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", kartuProject);
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
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
}