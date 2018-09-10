package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginSubmitPage extends LinkedinBasePage {
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailAlert;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement userPasswordAlert;

    public LinkedinLoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains("uas/login-submit")
                && getCurrentTitle().contains("Sign In to LinkedIn")
                && alertMessage.isDisplayed();
    }
    public String getAlertMessageText() {
        return alertMessage.getText();
    }

    public String getUserEmailAlertText() {
        return userEmailAlert.getText();
    }

    public String getUserPasswordAlertText() {
        return userPasswordAlert.getText();
    }
    }

