import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.MailRu;


public class BaseTest {
    private final int DEFAULT_TIMEOUT = 30;

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT, 100 );
    }

    protected void waitUntilDisplayed(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForPageLoaded(){
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return isAjaxComplete() && isPageLoaded();
            }
        });

    }

    private boolean isAjaxComplete() {
        final JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (Boolean) executor.executeScript("return $.active == 0");
    }

    private Boolean isPageLoaded() {
        final JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript("return document.readyState").equals("complete");
    }

    protected MailRu startMailRu(){
        return  new MailRu(driver);
    }
}
