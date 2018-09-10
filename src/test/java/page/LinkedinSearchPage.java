package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage {

    @FindBy(xpath = "//ul[@class='search-results__list list-style-none']")
    private WebElement resultForSearch;

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    public LinkedinSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("search/results/")
                && getCurrentTitle().contains("| Search | LinkedIn")
                && resultForSearch.isDisplayed();
    }
    public int getSearchResultsNumber() {
        return searchResults.size();
    }

    public List<String> getSearchResultList() {
        List<String>searchResultList = new ArrayList<String>();
        for (WebElement searchResult : searchResults) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", searchResult);
            searchResultList.add(searchResult.getText());
        }
            return searchResultList;
    }
}