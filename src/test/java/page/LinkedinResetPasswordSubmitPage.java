package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * LinkedinResetPasswordSubmitPage Page object class.
 */
public class LinkedinResetPasswordSubmitPage extends LinkedinBasePage {
    @FindBy(xpath = "//button[@class='resend__link']")
    private WebElement resendLinkButton;

    /**
     * Constructor for LinkedinResetPasswordSubmitPage.
     *
     * @param driver - driver instance from tests.
     */
    public LinkedinResetPasswordSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(resendLinkButton, 10);
    }

    /**
     * Is LinkedinResetPasswordSubmitPage loaded.
     *
     * @return current Url, Title, resendLink button is displayed.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains("request-password-reset-submit")
                && getCurrentTitle().contains("Please check your mail for reset password link.")
                && resendLinkButton.isDisplayed();
    }

    /**
     * Navigate to link from email.
     *
     * Wait message from gmailservise.
     * Extract link from message.
     * Remove extra characters.
     * Get valid link.
     * @return LinkedinNewPasswordFormPage Page Object class.
     */
    public LinkedinNewPasswordFormPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "testmostika@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String begin = "To change your LinkedIn password, click <a href=\"";
        String end = "\" style";
        message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 400);
        System.out.println("Content: " + message);

        String messagedLink = StringUtils.substringBetween(message, begin, end);
        String passwordLink = messagedLink.replace("&amp;","&");
        System.out.println(passwordLink);

        driver.get(passwordLink);

        return new LinkedinNewPasswordFormPage(driver);
    }
}

