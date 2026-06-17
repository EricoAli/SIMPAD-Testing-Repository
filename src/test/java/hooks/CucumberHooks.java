package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.DriverManager;
import reporting.ExtentReportManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CucumberHooks — Before/After hooks untuk setiap Scenario.
 *
 * Before : inisialisasi WebDriver + mulai entry di ExtentReport.
 * After  : screenshot jika gagal + catat hasil ke ExtentReport.
 * AfterAll: tutup browser + flush laporan HTML.
 */
public class CucumberHooks {

    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("[SCENARIO] " + scenario.getName());
        System.out.println("[TAGS]     " + scenario.getSourceTagNames());
        System.out.println("═".repeat(60));

        this.driver = DriverManager.getDriver();
        ExtentReportManager.initReport();
        ExtentReportManager.startTest(scenario.getName(),
            scenario.getSourceTagNames().toString());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("[STATUS] " + scenario.getName() + " → " + scenario.getStatus());

        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

                // Lampirkan ke Cucumber HTML report
                scenario.attach(screenshot, "image/png",
                    "Screenshot — " + scenario.getName());

                // Simpan ke disk
                String dir  = "target/screenshots";
                String ts   = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String safe = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
                Files.createDirectories(Paths.get(dir));
                Files.write(Paths.get(dir + "/" + safe + "_" + ts + ".png"), screenshot);

                // Catat ke ExtentReport
                ExtentReportManager.logFail("Scenario GAGAL: " + scenario.getName());
                ExtentReportManager.attachScreenshot(screenshot);

            } catch (Exception e) {
                System.err.println("[ERROR] Screenshot gagal: " + e.getMessage());
            }
        } else {
            ExtentReportManager.logPass("Scenario LULUS: " + scenario.getName());
        }

        System.out.println("═".repeat(60) + "\n");
    }

    @AfterAll
    public static void globalTearDown() {
        System.out.println("\n[SUITE SELESAI] Menutup browser dan generate laporan...");
        ExtentReportManager.flushReport();
        DriverManager.quitDriver();
        System.out.println("[LAPORAN] target/extent-report/SimpadTestReport.html");
    }
}
