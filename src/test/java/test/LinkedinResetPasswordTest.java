package test;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

public class LinkedinResetPasswordTest extends LinkedinBaseTest {
    @Test
    public void resetPasswordTest() {
        String userEmail = "testmostika@gmail.com";
        String userPassword = "testpassword$";

        LinkedinForgotPasswordPage linkedinForgotPasswordPage = linkedinLoginPage.forgotPasswordForm();
        Assert.assertTrue(linkedinForgotPasswordPage.isPageLoaded(), "ForgotPassword  page is not loaded.");

        LinkedinResetPasswordPage linkedinResetPasswordPage = linkedinForgotPasswordPage.enterEmail(userEmail);
        Assert.assertTrue(linkedinResetPasswordPage.isPageLoaded(), "Reset Password page is not loaded.");

        LinkedinChangedPasswordPage linkedinChangedPasswordPage = linkedinResetPasswordPage.chooseNewPassword(userPassword);
        Assert.assertTrue(linkedinChangedPasswordPage.isPageLoaded(), "ChangedPassword  page is not loaded.");

        linkedinHomePage = linkedinChangedPasswordPage.backToHomepage();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home  page is not loaded.");

    }

}
