import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

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

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Login page Url is wrong");
        Assert.assertEquals( driver.getTitle(),"LinkedIn: Log In or Sign Up", "Title not matching");
        Assert.assertTrue(signInButton.isDisplayed(), "signIn button is not displayed in login page.");

        emailField.sendKeys("marynamostika@ukr.net");
        passwordField.sendKeys("testpassword");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Home page is not displayed");
        Assert.assertEquals(driver.getTitle(), "LinkedIn", "Title not matching");

        WebElement profileNavItem = driver.findElement(By.xpath("//li[@id=\"profile-nav-item\"]"));
        Assert.assertTrue(profileNavItem.isDisplayed(), "profileNavItem button is not displayed on Home page.");

        WebElement dropdownbutton =  driver.findElement(By.xpath("//button[@id=\"nav-settings__dropdown-trigger\"]"));
        dropdownbutton.click();
        Assert.assertEquals( driver.findElement(By.xpath("//h3[@class='nav-settings__member-name Sans-16px-black-90%-bold']")).getText(),
                "Testname Testlastname", "My profile name was not found");

    }
    @Test
    public void negativeLoginTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Login page Url is wrong");
        Assert.assertEquals( driver.getTitle(),"LinkedIn: Log In or Sign Up", "Title not matching");
        Assert.assertTrue(signInButton.isDisplayed(), "signIn button is not displayed in login page.");

        emailField.sendKeys("a@b.c");
        passwordField.sendKeys("wrong");
        signInButton.click();
        try {
            sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");

    }
}
