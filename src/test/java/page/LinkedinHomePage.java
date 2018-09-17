package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinHomePage Page Object class.
 */
public class LinkedinHomePage extends LinkedinBasePage{
    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//input[@placeholder and @role='combobox']")
    private WebElement searchField;

    @FindBy(xpath = "//li[@id='profile-nav-item']//li-icon")
    private WebElement dropdownProfileLocator;

    @FindBy(xpath = "//a[@data-control-name='nav.settings_signout']")
    private WebElement signOutLocator;

    /**
     * Constructor for LinkedinHomePage.
     *
     * @param driver - driver instance from tests.
     */
    public LinkedinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(searchField, 10);
    }

    /**
     * Is Home page loaded.
     *
     * @return current Url, Title, profile is displayed.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && getCurrentTitle().contains("LinkedIn")
                && profileNavItem.isDisplayed();
    }

    /**
     * Search parameter enter.
     *
     * @param searchTerm - String with search term.
     * @return LinkedinSearchPage Page object class.
     */
    public LinkedinSearchPage searchPage(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new LinkedinSearchPage(driver);
    }

}
