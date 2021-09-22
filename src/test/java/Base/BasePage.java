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

import java.io.File;

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
        String projectPath = System.getProperty("user.dir") + "/src/test/resources/drivers";
        String osFolder = System.getProperty("os.name");
        String driverPathPrefix = projectPath + "/" + osFolder;
        switch (browser){
            case CHROME:
                System.out.println(driverPathPrefix + "/chromedriver");
                if(!(new File(driverPathPrefix + "/chromedriver")).exists()){
                    throw new UnsupportedOperationException("Chrome driver for \"" + osFolder + "\" is not available.");
                }
                System.setProperty("webdriver.chrome.driver", driverPathPrefix + "/chromedriver");
                this.driver = new ChromeDriver(new ChromeOptions());
                break;
            case FIREFOX:
                if(!(new File(driverPathPrefix + "/geckodriver")).exists()){
                    throw new UnsupportedOperationException("Firefox driver for \"" + osFolder + "\" is not available.");
                }
                System.setProperty("webdriver.gecko.driver", driverPathPrefix + "/geckodriver");
                this.driver = new FirefoxDriver();
                break;
            case EDGE:
                if(!(new File(driverPathPrefix + "/msedgedriver")).exists()){
                    throw new UnsupportedOperationException("Edge driver for \"" + osFolder + "\" is not available.");
                }
                System.setProperty("webdriver.gecko.driver", driverPathPrefix + "/msedgedriver");
                this.driver = new EdgeDriver();
                break;
            case INTERNET_EXPLORER:
                if(!(new File(driverPathPrefix + "/IEDriverServer")).exists()){
                    throw new UnsupportedOperationException("Internet explorer driver for \"" + osFolder + "\" is not available.");
                }
                System.setProperty("webdriver.ie.driver", driverPathPrefix + "/IEDriverServer");
                this.driver = new InternetExplorerDriver();
                break;
            case OPERA:
                if(!(new File(driverPathPrefix + "/operadriver")).exists()){
                    throw new UnsupportedOperationException("Opera driver for \"" + osFolder + "\" is not available.");
                }
                System.setProperty("webdriver.opera.driver", driverPathPrefix + "/operadriver");
                this.driver = new OperaDriver(new OperaOptions());
                break;
            case SAFARI:
                if(!(new File(driverPathPrefix + "/safaridriver")).exists()){
                    throw new UnsupportedOperationException("Safari driver for \"" + osFolder + "\" is not available.");
                }
                System.setProperty("webdriver.safari.driver", driverPathPrefix + "/safaridriver");
                this.driver = new SafariDriver();
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
