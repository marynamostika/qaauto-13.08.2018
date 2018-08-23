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
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Title not matching");
        Assert.assertTrue(signInButton.isDisplayed(), "signIn button is not displayed in login page.");

        emailField.sendKeys("marynamostika@ukr.net");
        passwordField.sendKeys("testpassword");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Home page is not displayed");
        Assert.assertEquals(driver.getTitle(), "LinkedIn", "Title not matching");

        WebElement profileNavItem = driver.findElement(By.xpath("//li[@id=\"profile-nav-item\"]"));
        Assert.assertTrue(profileNavItem.isDisplayed(), "profileNavItem button is not displayed on Home page.");

        WebElement dropdownbutton = driver.findElement(By.xpath("//button[@id=\"nav-settings__dropdown-trigger\"]"));
        dropdownbutton.click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[@class='nav-settings__member-name Sans-16px-black-90%-bold']")).getText(),
                "Testname Testlastname", "My profile name was not found");
    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("a@b.c");
        passwordField.sendKeys("wrong");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
    }

    @Test
    public void invalidPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("marynamostika@ukr.net");
        passwordField.sendKeys("12 Kj).");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");
        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        WebElement errorPasswordMessage = driver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        Assert.assertNotEquals(errorPasswordMessage.getText(), "Hmm, that's not the right password. Please try again or", "Error message is wrong");
    }

    @Test
    public void tooShortPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("marynamostika@ukr.net");
        passwordField.sendKeys("pa");
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");
        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        WebElement passwordShortCharacters = driver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        Assert.assertEquals(passwordShortCharacters.getText(), "The password you provided must have at least 6 characters.",
                "Password error Characters message is wrong");
    }

    @Test
    public void invalidEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("!2 &^");
        passwordField.sendKeys("testpassword");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        WebElement errorEmailMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(errorEmailMessage.getText(), "Be sure to include \"+\" and your country code.", "Error message is wrong");
    }

    @Test
    public void invalidEmailAndPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys(" @ukr.net");
        passwordField.sendKeys("wrongpassword");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        WebElement loginErrorMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(loginErrorMessage.getText(), "Please enter a valid email address.", "Login error message is wrong");
    }

    @Test
    public void tooShortEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("a@");
        passwordField.sendKeys("testpassword");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        WebElement shortEmailMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(shortEmailMessage.getText(), "The text you provided is too short (the minimum length is 3 characters, " +
                        "your text contains 2 characters).",
                "Short email message is wrong");
    }

    @Test
    public void tooLongEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("Anyone who reads Old and Middle English literary texts will be familiar with the mid-brown volumes" +
                " of the EETS with the symbol of Alfred's");
        passwordField.sendKeys("testpassword");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        WebElement longEmailMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(longEmailMessage.getText(), "The text you provided is too long (the maximum length is 128 characters," +
                        " your text contains 138 characters).",
                "Short email message is wrong");
    }

    @Test
    public void notRecognizeEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("@");
        passwordField.sendKeys("testpassword");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Title not matching");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='control_gen_1']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        WebElement shortEmailMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        Assert.assertEquals(shortEmailMessage.getText(), "Hmm, we don't recognize that email. Please try again.",
                "Short email message is wrong");
    }

    @Test
    public void emptyEmail() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement passwordField = driver.findElement(By.id("login-password"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        passwordField.sendKeys("testpassword");
        signInButton.click();
        Assert.assertFalse(signInButton.isEnabled(), "signIn button is enabled");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Title not matching");
    }

    @Test
    public void emptyPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement emailField = driver.findElement(By.id("login-email"));
        WebElement signInButton = driver.findElement(By.id("login-submit"));

        emailField.sendKeys("marynamostika@ukr.net");
        signInButton.click();

        Assert.assertFalse(signInButton.isEnabled(), "signIn button is enabled");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Title not matching");
    }

    @Test
    public void emptyEmailAndPassword() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        WebElement signInButton = driver.findElement(By.id("login-submit"));
        signInButton.click();
        Assert.assertFalse(signInButton.isEnabled(), "signIn button is enabled");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/", "Login page Url is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Title not matching");
    }
}
