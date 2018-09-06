import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest extends LinkedinBasePage {
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"marynamostika@ukr.net", "testpassword"},
                {"marynamostika@ukr.Net", "testpassword"}
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void succesfullLoginTest(String userEmail, String userPassword) throws InterruptedException {
        //navigate to linkedin.com
        //verify that login page is loaded.
        //enter user email.
        //enter user password.
        //click on "Sign in" button.
        //verify Home page is displayed.

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded.");
    }
    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                { "a@b.c", "wrong", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
                {"marynamostika@ukr.net", "12 Kj).", "", "Hmm, that's not the right password. Please try again or request a new one."},
                {"marynamostika@ukr.net", "pa", "", "The password you provided must have at least 6 characters."},
                {"!2 &^", "testpassword", "Be sure to include \"+\" and your country code.", ""},
                {" @ukr.net", "wrongpassword", "Please enter a valid email address.", ""},
                {"a@", "testpassword", "The text you provided is too short (the minimum length is 3 characters, your text contains 2 characters).", ""},
                {"Anyone who reads Old and Middle English literary texts will be familiar with the mid-brown volumes" +
                        " of the EETS with the symbol of Alfred's", "testpassword", "The text you provided is too long (the maximum length is 128 characters," +
                        " your text contains 138 characters).", ""},
                {"@", "testpassword", "Hmm, we don't recognize that email. Please try again.", ""}
        };
    }
    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginTest(String userEmail, String userPassword, String userEmailError, String userPasswordError) {
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(), "LoginSubmitPage is not loaded.");
        Assert.assertEquals(linkedinLoginSubmitPage.getAlertMessageText(),"There were one or more errors in your submission. " +
                "Please correct the marked fields below.", "Alert message text is wrong." );
        Assert.assertEquals(linkedinLoginSubmitPage.getUserEmailAlertText(), userEmailError,
                "userEmail alert text is wrong");
        Assert.assertEquals(linkedinLoginSubmitPage.getUserPasswordAlertText(), userPasswordError,
                "userPassword alert text is wrong");
    }
    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                {"", "testpassword"},
                {"marynamostika@ukr.net", ""},
                {"", ""}
        };
    }
    @Test(dataProvider = "emptyDataProvider")
    public void emptyUserEmailAndPassword(String userEmail,String userPassword) {
            Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
            linkedinLoginPage = linkedinLoginPage.login("", "");

            Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        }
        }

