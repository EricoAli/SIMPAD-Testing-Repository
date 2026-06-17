package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 * ExtentReportManager - Mengelola pembuatan laporan pengujian otomatis.
 * Menghasilkan laporan HTML interaktif menggunakan ExtentReports + Spark Reporter.
 */
public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();
    private static final String REPORT_PATH = "target/extent-report/SimpadTestReport.html";
    private static boolean initialized = false;

    /** Inisialisasi ExtentReports (dipanggil sekali) */
    public static synchronized void initReport() {
        if (!initialized) {
            try {
                // Buat direktori laporan
                java.nio.file.Files.createDirectories(
                    Paths.get("target/extent-report")
                );
            } catch (Exception e) {
                System.err.println("Gagal membuat direktori laporan: " + e.getMessage());
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);

            // Konfigurasi tampilan laporan
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("SIMPAD Test Report");
            sparkReporter.config().setReportName("Login & Logout Test Suite - SIMPAD");
            sparkReporter.config().setEncoding("UTF-8");
            sparkReporter.config().setTimeStampFormat("dd MMMM yyyy HH:mm:ss");
            sparkReporter.config().setCss(
                ".report-name { color: #4CAF50; } " +
                ".badge-primary { background-color: #2196F3; }"
            );

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            // Metadata sistem
            extentReports.setSystemInfo("Project", "SIMPAD Testing Repository");
            extentReports.setSystemInfo("Module", "Login & Logout");
            extentReports.setSystemInfo("URL", "https://simpad-frontend.vercel.app");
            extentReports.setSystemInfo("Framework", "Java + Selenium + Cucumber + JUnit5");
            extentReports.setSystemInfo("Metode Test", "BVA + Equivalence Partitioning");
            extentReports.setSystemInfo("Tanggal", LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Browser", "Google Chrome");

            initialized = true;
            System.out.println("[REPORT] ExtentReports diinisialisasi: " + REPORT_PATH);
        }
    }

    /** Memulai test baru */
    public static void startTest(String testName, String tags) {
        if (extentReports == null) initReport();
        ExtentTest test = extentReports.createTest(testName, "Tags: " + tags);
        currentTest.set(test);
    }

    /** Log langkah sukses */
    public static void logPass(String message) {
        if (currentTest.get() != null) {
            currentTest.get().pass(message);
        }
    }

    /** Log langkah gagal */
    public static void logFail(String message) {
        if (currentTest.get() != null) {
            currentTest.get().fail(message);
        }
    }

    /** Log informasi */
    public static void logInfo(String message) {
        if (currentTest.get() != null) {
            currentTest.get().info(message);
        }
    }

    /** Log peringatan */
    public static void logWarning(String message) {
        if (currentTest.get() != null) {
            currentTest.get().log(Status.WARNING, message);
        }
    }

    /** Melampirkan screenshot ke laporan */
    public static void attachScreenshot(byte[] screenshotBytes) {
        if (currentTest.get() != null && screenshotBytes != null) {
            try {
                String base64 = Base64.getEncoder().encodeToString(screenshotBytes);
                currentTest.get().fail("Screenshot saat gagal:",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
            } catch (Exception e) {
                System.err.println("Gagal melampirkan screenshot: " + e.getMessage());
            }
        }
    }

    /** Melampirkan screenshot dari path file */
    public static void attachScreenshotFromPath(String screenshotPath) {
        if (currentTest.get() != null) {
            try {
                currentTest.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                System.err.println("Gagal melampirkan screenshot dari path: " + e.getMessage());
            }
        }
    }

    /** Log bug yang ditemukan */
    public static void logBug(String bugId, String description, String severity) {
        if (currentTest.get() != null) {
            currentTest.get().log(Status.FAIL,
                String.format("<b>[BUG %s]</b> [%s] %s", bugId, severity, description));
        }
    }

    /** Finalisasi dan tulis laporan ke disk */
    public static synchronized void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
            System.out.println("[REPORT] Laporan HTML digenerate di: " + REPORT_PATH);
            initialized = false; // Reset untuk run berikutnya
        }
    }

    /** Mendapatkan instance ExtentTest saat ini */
    public static ExtentTest getCurrentTest() {
        return currentTest.get();
    }
}
