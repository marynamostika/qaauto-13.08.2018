package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * LinkedinSearchPage Page Object class.
 */
public class LinkedinSearchPage extends LinkedinBasePage {

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private WebElement resultForSearch;

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    /**
     * Constructor for LinkedinSearchPage.
     *
     * @param driver - driver instance from tests.
     */
    public LinkedinSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(resultForSearch, 10);
    }

    /**
     * Is LinkedinSearchPage loaded.
     *
     * @return current Url, Title, result for Search is displayed.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains("search/results/")
                && getCurrentTitle().contains("| Search | LinkedIn")
                && resultForSearch.isDisplayed();
    }

    /**
     * Get search results number.
     *
     * @return size of search results.
     */
    public int getSearchResultsNumber() {
        return searchResults.size();
    }

    /**
     * Get list of search results.
     *
     * @return search results list.
     */
    public List<String> getSearchResultList() {
        List<String>searchResultList = new ArrayList<String>();
        for (WebElement searchResult : searchResults) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", searchResult);
            searchResultList.add(searchResult.getText());
        }
            return searchResultList;
    }
}