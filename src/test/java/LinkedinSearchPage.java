import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage {
    private WebElement resultForSearch;


    public LinkedinSearchPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        resultForSearch = driver.findElement(By.xpath("//ul[@class='search-results__list list-style-none']"));
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/search/results/index/?keywords=hr&origin=GLOBAL_SEARCH_HEADER")
                && getCurrentTitle().contains("hr")
                && resultForSearch.isDisplayed();
    }
    public boolean isNumberSearchResultsCorrect(int expectedResult) {
        List<WebElement> searchResults =driver.findElements(By.xpath("//li[@class[contains(.,'search-result search-result__occluded-item')]]"));
        return searchResults.size()== expectedResult;
    }
    public void searchTerm() {
        WebElement searchResults = driver.findElement(By.xpath("//ul[@class='search-results__list list-style-none']"));
        List<WebElement>searchResult =searchResults.findElements(By.xpath("//li[@class[contains(.,'search-result search-result__occluded-item')]]"));
        System.out.println(searchResult.size());
        for(int i=0; i<searchResult.size(); i++){
            System.out.println(searchResult.get(i).getText().toLowerCase().contains("hr"));
        }
    }

   /* public void searchTerm() {
        List<WebElement> searchResults =driver.findElements(By.xpath("//li[@class[contains(.,'search-result search-result__occluded-item')]]"));
        for (WebElement searchResult : searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.toLowerCase().contains("hr")) {
                System.out.println("SearchTerm found.");
            }
            else {
                System.out.println("SearchTerm not found.");
            }
            System.out.println(searchResultText);
        }
    }*/
}
