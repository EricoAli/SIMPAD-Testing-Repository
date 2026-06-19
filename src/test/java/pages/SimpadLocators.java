package pages;

import org.openqa.selenium.By;

public class SimpadLocators {
    public static final By IKON_PROFIL = By.xpath("//button[@data-slot='trigger']");
    public static final By MENU_HOVER_UPLOAD = By.xpath("//*[contains(text(), 'Upload PAD 1 Project Portfolio')]");
    public static final By MENU_VIEW_PROFILE = By.xpath("//li[@role='menuitem' and descendant::span[normalize-space()='View user profile']]");
    public static By MENU_NAMA_USER_LOGIN(String namaUser) {
        return By.xpath("//li[@role='menuitem' and .//span[contains(text(), '" + namaUser + "')]]");
    }
    public static final By INPUT_FILE_BROWSE = By.xpath("//input[@type='file']");
    public static final By INPUT_PARAGRAF = By.xpath("//textarea[@placeholder='Add a paragraph here']");
    public static final By TOMBOL_ADD_LINK = By.xpath("//*[contains(text(), 'Link')]");
    public static final By INPUT_LINK_YT_AKTIF = By.xpath("//input[@placeholder='Enter Youtube Video Link']");
    public static final By INPUT_PROJECT_TITLE = By.cssSelector("input[placeholder='Project Title']");
    public static final By INPUT_TEAM_NAME = By.cssSelector("input[placeholder='Add your team name here']");
    public static By PILIH_USER_BERDASARKAN_NAMA(String namaUser) {
        return By.xpath("//div[contains(@class, 'cursor-pointer') and .//h1[text()='" + namaUser + "']]");
    }
    public static By TOMBOL_DROPDOWN_ROLE_KE(int urutan) {
        return By.xpath("(//button[@aria-haspopup='true' and not(.//img)])[" + urutan + "]");
    }
    public static By OPSI_DROPDOWN_TEKS(String namaOpsi) {
        return By.xpath("//li[@role='menuitemradio' and .//span[text()='" + namaOpsi + "']]");
    }
    public static final By TOMBOL_POST = By.xpath("//button[text()='Post']");
    public static final By MENU_PROJECT = By.xpath("//a[@href='/Project' and text()='Project']");
    public static final By TOMBOL_EXPLORE_PAD = By.xpath("//button[contains(., 'Explore PAD')]");
    public static By KARTU_PROJECT_NAMA(String kataKunci) {
        return By.xpath("//div[contains(@class, 'cursor-pointer') and contains(., \"" + kataKunci + "\")]");
    }
    public static final By MENU_MY_PROFILE = By.xpath("//li[@role='menuitem' and contains(., 'Profile')]");
    public static final By FOTO_ANGGOTA_TIM = By.cssSelector("img[alt='FotoTeam']");
    public static final By MENU_LOGOUT = By.xpath("//li[@role='menuitem' and contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'out')]");
    public static final By TOMBOL_EDIT_PROJECT = By.xpath("//button[contains(., 'Edit Project')]");
    public static final By TOMBOL_DELETE_PROJECT = By.xpath("//button[contains(., 'Delete Project')]");
    public static final By TOMBOL_KONFIRMASI_DELETE = By.xpath("//button[contains(@class, 'bg-[#FF4D4F]') and text()='Delete']");
    public static final By INPUT_EDIT_TITLE = By.id("title"); // Berdasarkan id="title"
    public static final By TEXTAREA_EDIT_DESC = By.xpath("//textarea[@placeholder='Add a paragraph here']");
    public static final By INPUT_EDIT_FILE = By.xpath("//input[@type='file']");
    public static final By INPUT_EDIT_LINK = By.xpath("//a[contains(., 'Link')]");
    public static final By TOMBOL_SUBMIT_EDIT = By.xpath("//button[contains(@class, 'bg-[#017777]') and contains(., 'Edit Project')]");
}