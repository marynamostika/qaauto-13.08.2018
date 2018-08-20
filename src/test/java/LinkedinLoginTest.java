import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    @Test
    public void succesfullLoginTest() {
        //navigate to linkedin.com
        //verify that login page is loaded.
        //enter user email.
        //enter user password.
        //click on "Sign in" button.
        //verify Home page is displayed.

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        By emailLocator = By.id("login-email");
        By passwordLocator = By.id("login-password");
        By signInLocator = By.id("login-submit");
        By dropdownLocator = By.xpath("//button[@id=\"nav-settings__dropdown-trigger\"]");
        By myNameLocator = By.xpath("//h3[@class='nav-settings__member-name Sans-16px-black-90%-bold']");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Login page Url is wrong");
        Assert.assertEquals( driver.getTitle(),"LinkedIn: Log In or Sign Up", "Title not matching");

        WebElement emailField = driver.findElement(emailLocator);
        emailField.sendKeys("marynamostika@ukr.net");

        WebElement passwordField = driver.findElement(passwordLocator);
        passwordField.sendKeys("testpassword");

        WebElement signInButton = driver.findElement(signInLocator);
        signInButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Home page is not displayed");

        driver.findElement(dropdownLocator).click();
        Assert.assertEquals( driver.findElement(myNameLocator).getText(),"Testname Testlastname", "My profile name was not found");





    }
}
