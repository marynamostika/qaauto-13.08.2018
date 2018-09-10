import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinResetPasswordTest extends LinkedinBasePage {
    LinkedinLoginPage linkedinLoginPage;
    LinkedinHomePage linkedinHomePage;

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

    @Test
    public void resetPasswordTest() {
        String userEmail = "marynamostika@ukr.net";
        String newPassword = "testpassword$";

        LinkedinForgotPasswordPage linkedinForgotPasswordPage = linkedinLoginPage.forgotPasswordForm();
        Assert.assertTrue(linkedinForgotPasswordPage.isPageLoaded(), "ForgotPassword  page is not loaded.");
        LinkedinResetPasswordPage linkedinResetPasswordPage = linkedinForgotPasswordPage.enterEmail(userEmail);
        Assert.assertTrue(linkedinResetPasswordPage.isPageLoaded(), "Reset Password page is not loaded.");
        LinkedinChangedPasswordPage linkedinChangedPasswordPage = linkedinResetPasswordPage.chooseNewPassword(newPassword);
        Assert.assertTrue(linkedinChangedPasswordPage.isPageLoaded(), "ChangedPassword  page is not loaded.");
        linkedinHomePage = linkedinChangedPasswordPage.backToHomepage();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home  page is not loaded.");
    }

}
