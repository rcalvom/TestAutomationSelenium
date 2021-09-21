package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Abstract Class to define a Page.
 */
public abstract class BasePage {

    /**
     * Reference to current Web driver, it could be Chrome, Firefox, Edge, Opera, Internet Explorer or Safari.
     */
    private final WebDriver driver;

    /**
     * Reference to Web Driver Wait.
     */
    private final WebDriverWait driverWait;

    /**
     * Constructor. Create the Page according to specific browser and time out.
     * @param browser Enumeration to select the browser. (Chrome, Firefox, Edge, Internet Explorer, Opera and Safari)
     * @param timeOutInSeconds Time out in seconds.
     */
    public BasePage(Browsers browser, long timeOutInSeconds) {
        switch (browser){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "/home/ricardo/.webdrivers/chromedriver");
                this.driver = new ChromeDriver(new ChromeOptions());
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "/home/ricardo/.webdrivers/geckodriver");
                this.driver = new FirefoxDriver();
                break;
            case EDGE:
                this.driver = new EdgeDriver(); //TODO: Driver de Edge.
                break;
            case INTERNET_EXPLORER:
                this.driver = new InternetExplorerDriver(); //TODO: Driver de Internet explorer.
                break;
            case OPERA:
                System.setProperty("webdriver.opera.driver", "/home/ricardo/.webdrivers/operadriver");
                this.driver = new OperaDriver(new OperaOptions());
                break;
            case SAFARI:
                this.driver = new SafariDriver(); //TODO: Driver de Safari.
                break;
            default:
                throw new UnsupportedOperationException("The specific browser is not supported by Selenium.");
        }
        this.driverWait = new WebDriverWait(this.driver, timeOutInSeconds);
    }

    /**
     * Constructor. Create the Page according to specific browser and default time out.
     * @param browser Enumeration to select the browser. (Chrome, Firefox, Edge, Internet Explorer, Opera and Safari)
     */
    public BasePage(Browsers browser){
        this(browser, 10L);
    }

    /**
     * Opens a URL in the browser.
     * @param url It's the URL address to open in the browser.
     */
    public void openURL(String url){
        this.driver.get(url);
    }

    // This class will be completed according to functionality needed.

}
