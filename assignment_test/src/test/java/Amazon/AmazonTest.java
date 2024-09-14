package Amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.logging.Level;


public class AmazonTest {
	private WebDriver driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        String browser = System.getProperty("browser", "chrome");
        URL gridUrl = new URL("http://localhost:4444/wd/hub");

        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(gridUrl, firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new RemoteWebDriver(gridUrl, edgeOptions);
                break;
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--headless"); // Optional
                chromeOptions.setCapability("goog:loggingPrefs", Collections.singletonMap("browser", Level.ALL));
                driver = new RemoteWebDriver(gridUrl, chromeOptions);
                break;
        }
    }

    @Test
    public void testAmazonHomePage() {
        driver.get("https://www.amazon.com");
        System.out.println("Title: " + driver.getTitle());
        // Add more test steps as needed
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
