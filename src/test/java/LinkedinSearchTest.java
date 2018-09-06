import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinSearchTest extends LinkedinBasePage {
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
       driver.quit();
    }
    @Test
    public void searchHrTest() {
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login("marynamostika@ukr.net", "testpassword");
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded.");
        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.searchForHr("hr");
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(), "Search page is not loaded");
        Assert.assertTrue(linkedinSearchPage.isNumberSearchResultsCorrect(10), "Search results number is incorrect");
        linkedinSearchPage.searchTerm();
    }
}
