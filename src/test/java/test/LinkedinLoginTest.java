package test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLoginSubmitPage;


/**
 * LinkedinLoginTest Page Object class.
 */
public class LinkedinLoginTest extends LinkedinBaseTest {

    /**
     * Parameters for succesfull user login.
     *
     * @return object with valid parameters.
     */
    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"testmostika@gmail.com", "testpassword$"},
                {"testmostika@gmail.com", "testpassword$"}
        };
    }

    /**
     * Verify succesfull user login.
     *
     * Preconditions:
     *-Open new browser
     *-Navigate to linkedin.com
     *
     * Scenario
     * -Verify that login page is loaded.
     * -Enter user email.
     * -Enter user password.
     * -Click on "Sign in" button.
     * -Verify Home page is displayed.
     */
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

    /**
     * Parameters for negative test.
     *
     * @return object with invalid parameters.
     */
    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                { "a@b.c", "wrong", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
                {"marynamostika@ukr.net", "12 Kj).", "", "Hmm, that's not the right password. Please try again or request a new one."},
                {"marynamostika@ukr.net", "pa", "", "The password you provided must have at least 6 characters."},
                {"!2 &^", "testpassword$", "Be sure to include \"+\" and your country code.", ""},
                {" @ukr.net", "wrongpassword", "Please enter a valid email address.", ""},
                {"a@", "testpassword$", "The text you provided is too short (the minimum length is 3 characters, your text contains 2 characters).", ""},
                {"Anyone who reads Old and Middle English literary texts will be familiar with the mid-brown volumes" +
                        " of the EETS with the symbol of Alfred's", "testpassword$", "The text you provided is too long (the maximum length is 128 characters," +
                        " your text contains 138 characters).", ""},
                {"@", "testpassword$", "Hmm, we don't recognize that email. Please try again.", ""}
        };
    }

    /**
     * Verify invalid user login.
     *
     * Preconditions:
     * -Open new browser
     * -Navigate to linkedin.com
     *
     * Scenario
     * -Verify that login page is loaded.
     * -Enter user email.
     * -Enter user password.
     * -Click on "Sign in" button.
     * -Verify Login Submit Page is loaded.
     * -Verify alert message text.
     * -Verify user email error message.
     * -Verify user password error message.
     */
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

    /**
     * Parameters for emty user email and password test.
     *
     * @return object with parameters.
     */
    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                {"", "testpassword$"},
                {"marynamostika@ukr.net", ""},
                {"", ""}
        };
    }

    /**
     * Verify invalid user login.
     *
     * Preconditions:
     *-Open new browser
     *-Navigate to linkedin.com
     *
     * Scenario
     *-Verify that login page is loaded.
     *-Enter user email.
     *-Enter user password.
     *-Click on "Sign in" button.
     *-Verify Login Page is displayed.
     *
     */
    @Test(dataProvider = "emptyDataProvider")
    public void emptyUserEmailAndPassword(String userEmail,String userPassword) {
            Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
            linkedinLoginPage = linkedinLoginPage.login("", "");

            Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        }
        }

