package test;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinSearchPage;

import java.util.List;

/**
 * LinkedinSearchTest Page object class.
 */
public class LinkedinSearchTest extends LinkedinBaseTest {

    /**
     * Search test.
     *
     * Precondition:
     * -Open new browser.
     * -Navigate to linkedin.com.
     *
     * Scenario:
     * - Open login page.
     * - Verify login page is loaded.
     * - Login with valid credentials.
     * - Verify home page is loaded.
     * - Search for search term.
     * - Verify Search page is loaded.
     * - Verify 10 results displayed on search page.
     * - Verify each result item contains search term.
     */
    @Test
    public void basicSearchTest() {
        String userEmail = "marynamostika@ukr.net";
        String userPassword = "testpassword$";
        String searchTerm = "hr";

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded.");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.searchPage(searchTerm);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(), "Search page is not loaded.");

        Assert.assertEquals(linkedinSearchPage.getSearchResultsNumber(), 10,
                "Wrong number of searchResults on Search page");

        List<String> searchResultList = linkedinSearchPage.getSearchResultList();

        for (String searchResult : searchResultList ) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm "+ searchTerm+" not found in:\n"+searchResult);
        }

    }
}
