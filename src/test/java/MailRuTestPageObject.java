import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MailRu;

/**
 * Created by dkozakevich on 3/29/2017.
 */
public class MailRuTestPageObject extends BaseTest{
   /* private MailRu mailRu;

    @BeforeMethod
    public void setUp(){
        mailRu = startMailRu();
    }*/

    @Test
    public void checkSearch(){

        MailRu mailRu = startMailRu();
        mailRu.searchFor("cars");
        mailRu.switchToSearchTab();
        mailRu.checkSearchResultsPageTitle("cars");
        mailRu.closeSearchTab();
    }

}

