package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinForgotPasswordPage extends LinkedinBasePage {

    @FindBy(xpath = "//header[@class='content__header']")
    private WebElement headerLocator;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//button[@class='form__submit']" )
    private WebElement findAccountButton;

    public LinkedinForgotPasswordPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("forgot-password")
              &&   getCurrentTitle().contains("Reset Password | LinkedIn")
              && headerLocator.isDisplayed();
    }

    public LinkedinResetPasswordPage enterEmail(String userEmail) {
        GMailService gMailService = new GMailService();
        gMailService.connect();

        userNameField.sendKeys(userEmail);
        findAccountButton.click();
        String messageSubject = "here's the link to reset your password";
        String messageTo = "testmostika@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";


        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println("Content: " + message);

        return new LinkedinResetPasswordPage(driver);
    }
}
