import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String args[]) {
        System.out.println("Hello world!!!");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        By searchLocator = By.id("lst-ib");
        WebElement searchField = driver.findElement(searchLocator);

        searchField.click();
        searchField.clear();
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.RETURN);

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[@class = 'srg']/div[@class='g']"));
        System.out.println("Search results count: "+searchResults.size());

        int searchResultCount = searchResults.size();
        if (searchResultCount == 10) {
            System.out.println("Search result count is correct: "+searchResultCount);
        }
        else {
            System.out.println("Search result count is incorrect: "+searchResultCount);
        }

        for (WebElement searchResult : searchResults) {
            String searchResultText = searchResult.getText();
            if (searchResultText.toLowerCase().contains("Selenium")) {
                System.out.println("SearchTerm found.");
            }
            else {
                System.out.println("SearchTerm not found.");
            }
            System.out.println(searchResultText);
        }
        driver.close();
    }
}
