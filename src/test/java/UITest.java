import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class UITest {
    WebDriver driver;

    private static final String USER = "automation@keepitqa.com";
    private static final String PASSWORD = "E#*b2wGIbFHz";
    private static final String USERNAME_FIELD = "//input[@name='email']";
    private static final String PASSWORD_FIELD = "//input[@name='password']";
    private static final String LEFT_MENU = "//*[contains(@class,'gWrgS__leftNavToggleIcon--gWrgS WwqnL__collapsed--WwqnL')]";
    private static final String CONNECTORS_BUTTON = "//*[contains(text(), 'Connectors')]";
    private static final String MICROSOFT365_CONNECTOR = "//*[text()='Add Microsoft 365 connector']";
    private static final String SIGN_IN = "//button[text()='Sign in']";
    private static final String ADD_CONNECTOR_DROPDOWN = "//button[contains(text(),'Add connector')]";
    private static final String ADD_CONNECTOR_DROPDOWN_MENU = "[class='dropdown-popup-content animation-out']";
    private static final String MICROSOFT_LOGIN_PAGE_TILE = "Вхід в обліковий запис";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(driver);
        driver.get("https://ws-test.keepit.com");
    // login
        WebElement userNameField = wait.until(presenceOfElementLocated(By.xpath(USERNAME_FIELD)));
        userNameField.sendKeys(USER);
        WebElement passwordField = wait.until(presenceOfElementLocated(By.xpath(PASSWORD_FIELD)));
        passwordField.sendKeys(PASSWORD);
        WebElement loginBtn = wait.until(presenceOfElementLocated(By.xpath(SIGN_IN)));
        loginBtn.click();
    // navigate to microsoft cloud
        WebElement leftExpandedMenu = wait.until(presenceOfElementLocated(By.xpath(LEFT_MENU)));
        leftExpandedMenu.click();
        WebElement connectorsIcon = wait.until(presenceOfElementLocated(By.xpath(CONNECTORS_BUTTON)));
        connectorsIcon.click();
        WebElement addConnectorDropdown = wait.until(presenceOfElementLocated(By.xpath(ADD_CONNECTOR_DROPDOWN)));
        addConnectorDropdown.click();
        WebElement dropdownMenu = wait.until(presenceOfElementLocated(By.cssSelector(ADD_CONNECTOR_DROPDOWN_MENU)));
        WebElement dropdownItem = wait.until(presenceOfElementLocated(By.xpath(MICROSOFT365_CONNECTOR)));
        dropdownItem.click();
        WebElement loginToServiceBtn = wait.until(presenceOfElementLocated(By.xpath(SIGN_IN)));
        actions.scrollToElement(loginToServiceBtn);
        actions.perform();
        loginToServiceBtn.click();
    // Verify required page;
        Thread.sleep(5000);
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, MICROSOFT_LOGIN_PAGE_TILE);
    }
}
