package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class MailRu extends AbstractMailRu {

    @FindBy(xpath = "//div[@class = 'search']/form/div/div[2]/label/span/input")
    WebElement txtSearch;


    public MailRu(WebDriver driver){
        super(driver);
        load();
        waitForPageLoaded();
    }

    protected void load(){
        driver.get("http://mail.ru");
    }

    protected void isLoaded() throws Error{
        assertTrue("Base Y page was not loaded", isPageLoaded() && isAjaxComplete());
    }

    public void searchFor(String searchCriteria){
        txtSearch.sendKeys(searchCriteria);
        txtSearch.submit();
//        waitUntilDisplayed();
    }

    public void switchToSearchTab(){
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void closeSearchTab(){
        driver.close();
    }

    public void checkSearchResultsPageTitle(String expectedTitle){
        System.out.println(driver.getTitle());
        assertTrue(driver.getTitle().contains(expectedTitle));
    }



}
