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

        LinkedinResetPasswordSubmitPage linkedinResetPasswordSubmitPage = linkedinForgotPasswordPage.enterEmail(userEmail);
        Assert.assertTrue(linkedinResetPasswordSubmitPage.isPageLoaded(), "Reset Password Submit page is not loaded.");

        LinkedinNewPasswordFormPage linkedinNewPasswordFormPage = linkedinResetPasswordSubmitPage.navigateToLinkFromEmail();
       Assert.assertTrue(linkedinNewPasswordFormPage.isPageLoaded(), "New PasswordForm page is not loaded.");

        LinkedinChangedPasswordPage linkedinChangedPasswordPage = linkedinNewPasswordFormPage.chooseNewPassword(userPassword);
        Assert.assertTrue(linkedinChangedPasswordPage.isPageLoaded(), "ChangedPassword  page is not loaded.");

        linkedinHomePage = linkedinChangedPasswordPage.backToHomepage();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home  page is not loaded.");
    }

}
