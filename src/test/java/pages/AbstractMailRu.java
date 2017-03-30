package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by dkozakevich on 3/28/2017.
 */
public abstract class AbstractMailRu extends LoadableComponent<AbstractMailRu> {
    private final int DEFAULT_TIMEOUT = 30;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractMailRu(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT, 100);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    public WebDriver driver() {
        return this.driver;
    }

    protected void waitUntilDisplayed(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForPageLoaded() {
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return isAjaxComplete() && isPageLoaded();
            }
        });

    }

    //There is no jQuery on mail.ru, that's whu return true; change for the jQuery websites
    protected boolean isAjaxComplete () {
//        final JavascriptExecutor executor = (JavascriptExecutor) driver;
//        return (Boolean) executor.executeScript("return $.active == 0");
        return true;
    }

    protected Boolean isPageLoaded() {
        final JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript("return document.readyState").equals("complete");
    }

    public void quit() {
        if (driver != null) driver.quit();
    }
}
