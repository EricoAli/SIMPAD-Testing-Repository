package pages;

import org.openqa.selenium.By;

public class SimpadLocators {
    // --- NAVIGASI PROFIL ---
    public static final By IKON_PROFIL = By.xpath("//button[@data-slot='trigger']");
    public static final By MENU_HOVER_UPLOAD = By.xpath("//*[contains(text(), 'Upload PAD 1 Project Portfolio')]");

    public static final By MENU_VIEW_PROFILE = By.xpath("//li[@role='menuitem' and descendant::span[normalize-space()='View user profile']]");

    // Opsional: Untuk memvalidasi user yang sedang login (seperti Budi Utomo)
    public static By MENU_NAMA_USER_LOGIN(String namaUser) {
        return By.xpath("//li[@role='menuitem' and .//span[contains(text(), '" + namaUser + "')]]");
    }

    // --- FORM UPLOAD PROJECT (SUDAH DIPERBAIKI) ---
    public static final By INPUT_FILE_BROWSE = By.xpath("//input[@type='file']");
    public static final By INPUT_PARAGRAF = By.xpath("//textarea[@placeholder='Add a paragraph here']");

    // PERBAIKAN: Gunakan XPATH agar tombol link terdeteksi walau berupa span/a dan bisa diklik via JS
    public static final By TOMBOL_ADD_LINK = By.xpath("//*[contains(text(), 'Link')]");

    public static final By INPUT_LINK_YT_AKTIF = By.xpath("//input[@placeholder='Enter Youtube Video Link']");

    // PERBAIKAN: Gunakan CSS Selector untuk mengunci tag <input>, agar tidak salah klik <div>
    // Ubah baris ini di SimpadLocators.java Anda:
    // --- FORM UPLOAD PROJECT ---

    // 1. Locator untuk Judul Project
    public static final By INPUT_PROJECT_TITLE = By.cssSelector("input[placeholder='Project Title']");

    // 2. Locator untuk Nama Tim
    public static final By INPUT_TEAM_NAME = By.cssSelector("input[placeholder='Add your team name here']");

    // (Locator lainnya seperti INPUT_PARAGRAF, TOMBOL_ADD_LINK biarkan saja seperti sebelumnya)
    // --- PEMILIHAN USER & ROLE (Dinamis) ---
    public static By PILIH_USER_BERDASARKAN_NAMA(String namaUser) {
        // Berdasarkan PDF Anda: mencari teks di dalam <h1>
        return By.xpath("//div[contains(@class, 'cursor-pointer') and .//h1[text()='" + namaUser + "']]");
    }

    public static By TOMBOL_DROPDOWN_ROLE_KE(int urutan) {
        // FIX: Mencari tombol pembuka dropdown tanpa gambar di dalamnya (bukan ikon profil)
        return By.xpath("(//button[@aria-haspopup='true' and not(.//img)])[" + urutan + "]");
    }

    public static By OPSI_DROPDOWN_TEKS(String namaOpsi) {
        return By.xpath("//li[@role='menuitemradio' and .//span[text()='" + namaOpsi + "']]");
    }

    // --- SUBMIT ---
    public static final By TOMBOL_POST = By.xpath("//button[text()='Post']");
    // --- FITUR MELIHAT PROJECT ORANG LAIN ---

    // Locator untuk menu "Project" di Navbar
    public static final By MENU_PROJECT = By.xpath("//a[@href='/Project' and text()='Project']");

    // Locator dinamis untuk mencari project berdasarkan judul H1-nya
    // Locator yang tahan terhadap teks terpotong (truncation)
    // Locator yang tahan terhadap teks terpotong dan kebal terhadap tanda petik tunggal (')
    public static By KARTU_PROJECT_NAMA(String kataKunci) {
        // Kita menggunakan escape character petik ganda (\") agar nama seperti "Erico's" tidak merusak struktur XPath
        return By.xpath("//div[contains(@class, 'cursor-pointer') and contains(., \"" + kataKunci + "\")]");
    }
    // Locator untuk foto anggota tim di halaman detail project
    public static final By FOTO_ANGGOTA_TIM = By.cssSelector("img[alt='FotoTeam']");
    // Locator untuk menu Logout (Menyesuaikan standar penulisan Log out / Logout / Sign out)
    // Jika di aplikasi Anda tulisannya "Sign out" atau "Keluar", silakan sesuaikan teksnya.
    // Locator Sapu Jagat untuk mencari kata "out" pada dropdown (Log out, Logout, Sign out)
    public static final By MENU_LOGOUT = By.xpath("//li[@role='menuitem' and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'out')]");
}