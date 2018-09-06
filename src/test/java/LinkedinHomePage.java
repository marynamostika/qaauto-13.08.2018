import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinHomePage extends LinkedinBasePage{

    public LinkedinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//li[@id='profile-nav-item']")
    public WebElement profileNavItem;

    @FindBy(xpath = "//input[@role='combobox']")
    public WebElement searchInput;

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && getCurrentTitle().contains("LinkedIn")
                && profileNavItem.isDisplayed();
    }
    public <T> T searchForHr(String searchTerm) {
        searchInput.click();
        searchInput.sendKeys("hr");
        searchInput.sendKeys(Keys.RETURN);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getCurrentUrl().contains("/search")) {
            return (T) new LinkedinSearchPage(driver);
        }
        else {
            return (T) this;
        }
    }
    }
