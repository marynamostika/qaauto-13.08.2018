package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

/**
 * LinkedinResetPasswordTest Page Object class.
 */
public class LinkedinResetPasswordTest extends LinkedinBaseTest {
    LinkedinHomePage linkedinHomePage;

    /**
     * Verify reset password.
     *
     * Preconditions:
     * -Open new browser.
     * -Navigate to linkedin.com.
     *
     * Scenario
     * -Verify that login page is loaded.
     * -Click forgot password link.
     * -Verify that Forgot password page is loaded.
     * -Connect to gMailService.
     * -Enter user email.
     * -Click findAccount button.
     * -Navigate to link from email.
     * -Verify that New password form page is loaded.
     * -Choose new password.
     * -Verify that Changed password page is loaded.
     * -Click to back to Home page button.
     * -Verify that Home Page is loaded.
     */
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
