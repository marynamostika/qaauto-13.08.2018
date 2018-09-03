import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginSubmitPage {
    private WebDriver driver;
    private WebElement profileNavItem;

    public LinkedinLoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }
    private void initElements() {
        profileNavItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }
}
