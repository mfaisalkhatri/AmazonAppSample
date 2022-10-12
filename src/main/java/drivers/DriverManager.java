package drivers;

import static java.text.MessageFormat.format;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Faisal Khatri
 * @since 10/10/2022
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver>   DRIVER = new ThreadLocal<> ();
    private static final Logger                   LOG    = LogManager.getLogger ("DriverManager.class");
    private static final String                   URL    = "http://localhost:4723/wd/hub";
    private static       AppiumDriverLocalService service;

    private static final String APP_PATH = DriverManager.class.getClassLoader ()
        .getResource ("app\\amazon.apk")
        .getPath ();

    public static void createChromeDriver () {
        setupChromeDriver ();
        setupBrowserTimeouts ();
        maximizeBrowserWindow ();
    }

    @SneakyThrows
    public static void createAndroidDriver () {
        startServer ();
        try {
            DRIVER.set (new AndroidDriver (new URL (format ("https://{0}", URL)), setCapabilities ()));
        } catch(MalformedURLException e) {
            LOG.error ("Error in forming URL", e.getMessage ());
        }
        setupDriverTimeouts ();
    }

    public static <D extends WebDriver> D getDriver () {
        return (D) DriverManager.DRIVER.get ();
    }

    public static void quitDriver () {
        if (null != DRIVER.get ()) {
            LOG.info ("Closing the driver...");
            getDriver ().quit ();
            DRIVER.remove ();
        }
    }

    private static <D extends WebDriver> void setDriver (final D driver) {
        DriverManager.DRIVER.set (driver);
    }

    private static void setupBrowserTimeouts () {
        LOG.info ("Setting Browser Timeouts....");
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (Duration.ofSeconds (30));
        getDriver ().manage ()
            .timeouts ()
            .pageLoadTimeout (Duration.ofSeconds (30));
        getDriver ().manage ()
            .timeouts ()
            .scriptTimeout (Duration.ofSeconds (30));
    }

    private static void maximizeBrowserWindow () {
        getDriver ().manage ()
            .window ()
            .maximize ();
    }

    private static void setupChromeDriver () {
        LOG.info ("Setting up Chrome Driver....");
        final boolean isHeadless = Boolean.parseBoolean (
            Objects.requireNonNullElse (System.getProperty ("headless"), "true"));
        final HashMap<String, Object> chromePrefs = new HashMap<> ();
        chromePrefs.put ("safebrowsing.enabled", "true");
        chromePrefs.put ("download.prompt_for_download", "false");
        chromePrefs.put ("download.default_directory",
            String.valueOf (Paths.get (System.getProperty ("user.home"), "Downloads")));

        final ChromeOptions options = new ChromeOptions ();
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-dev-shm-usage");
        if (isHeadless) {
            options.addArguments ("--headless");
        }
        options.addArguments ("--safebrowsing-disable-download-protection");
        options.setExperimentalOption ("prefs", chromePrefs);

        setDriver (WebDriverManager.chromedriver ()
            .capabilities (options)
            .create ());
        LOG.info ("Chrome Driver created successfully!");
    }

    private DriverManager () {
    }

    private static void setupDriverTimeouts () {
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (Duration.ofSeconds (30));
    }

    private static DesiredCapabilities setCapabilities () {
        DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability (MobileCapabilityType.DEVICE_NAME, "Pixel_5_API_30");
        capabilities.setCapability (MobileCapabilityType.APP, APP_PATH);
        capabilities.setCapability ("noReset", false);
        capabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        return capabilities;
    }

    public static void startServer () {
        AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        builder.withIPAddress ("127.0.0.1");
        builder.usingPort (4723);
        builder.withArgument (GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument (GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService (builder);
        service.start ();
    }

    public static void stopServer () {
        service.stop ();
    }
}