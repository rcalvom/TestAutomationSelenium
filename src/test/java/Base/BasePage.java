package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

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
     * Reference to Actions object, it allows the driver to interact using uncommon actions.
     */
    private final Actions actions;

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
                System.setProperty("webdriver.edge.driver", driverPathPrefix + "/msedgedriver");
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
        this.driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
        this.actions = new Actions(this.driver);
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

    /**
     * Gets a WebElement using an XPath expression.
     * @param locator XPath expression
     * @return WebElement Reference
     */
    private WebElement find(String locator){
        return this.driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    /**
     * Click a Web element.
     * @param locator XPath expression.
     */
    public void clickElement(String locator){
        this.find(locator).click();
    }

    /**
     * Write a String in a writable web element.
     * @param locator XPath expression.
     * @param text String to write.
     */
    public void write(String locator, String text){
        WebElement element = this.find(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Select an element from a dropdown using its value.
     * @param locator XPath expression.
     * @param value Value of Option to select.
     */
    public void selectFromDropdownByValue(String locator, String value){
        Select select = new Select(this.find(locator));
        select.selectByValue(value);
    }

    /**
     * Select an element from a dropdown using its index.
     * @param locator XPath expression.
     * @param index Index of element to select.
     */
    public void selectFromDropdownByIndex(String locator, int index){
        Select select = new Select(this.find(locator));
        select.selectByIndex(index);
    }

    /**
     * Select an element from a dropdown using its text.
     * @param locator XPath expression.
     * @param text Text of the Option to select.
     */
    public void selectFromDropdownByText(String locator, String text){
        Select select = new Select(this.find(locator));
        select.selectByVisibleText(text);
    }

    /**
     * Put the mouse over a web element.
     * @param locator XPath expression.
     */
    public void hoverOverElement(String locator){
        this.actions.moveToElement(this.find(locator));
    }

    /**
     * Do double click over a web element.
     * @param locator XPath expression.
     */
    public void doubleClickElement(String locator){
        this.actions.doubleClick(this.find(locator));
    }

    /**
     * Do right-click over a web element
     * @param locator
     */
    public void rightClickElement(String locator){
        this.actions.contextClick(this.find(locator));
    }

    /**
     * Get the text of a cell in a table.
     * @param locator XPath expression of table's parent element.
     * @param row Row index.
     * @param column Column index.
     * @return Text of the cell
     */
    public String getValueFromTable(String locator, int row, int column){
        String cell = locator + "/table/tbody/tr[" + row + "]/td[" + column + "]";
        return this.find(cell).getText();
    }

    /**
     * Set a value in a table element.
     * @param locator XPath expression.
     * @param row row index.
     * @param column columns index.
     * @param text Text to put into the cell.
     */
    public void setValueOnTable(String locator, int row, int column, String text){
        String cell = locator + "/table/tbody/tr[" + row + "]/td[" + column + "]";
        this.find(cell).sendKeys(text);
    }

    /**
     * Switch the focus to an iframe.
     * @param IFrameIndex index of iframe element.
     */
    public void switchToIFrame(int IFrameIndex){
        this.driver.switchTo().frame(IFrameIndex);
    }

    /**
     * Switch the focus to the parent frame.
     */
    public void switchToParentFrame(){
        this.driver.switchTo().parentFrame();
    }

    /**
     * Dismiss an alert.
     */
    public void dismissAlert(){
        this.driver.switchTo().alert().dismiss();
    }

    // This class will be completed according to functionality needed.

}
