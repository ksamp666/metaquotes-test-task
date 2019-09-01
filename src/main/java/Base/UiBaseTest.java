package Base;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import Configuration.ConfigurationProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static Utils.Log.getLogger;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class UiBaseTest {
    private final static String USER_AGENT = ConfigurationProperties.getProperties().getUserAgent();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=" + USER_AGENT);
        WebDriver webDriver = new ChromeDriver(options);
        setWebDriver(webDriver);
        getLogger().info("Chrome driver successfully initialized");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        getLogger().info("Closing web driver");
        WebDriverRunner.getAndCheckWebDriver().close();
    }
}
