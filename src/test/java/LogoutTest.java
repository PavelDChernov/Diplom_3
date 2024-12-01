import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import service.driver.WebDriverCreator;
import service.json.User;
import service.page_object.BurgerLoginPage;
import service.page_object.BurgerProfilePage;
import service.utilities.TestUtilities;

public class LogoutTest {
    private WebDriver driver;
    private User user;
    private BurgerLoginPage loginPage;
    private BurgerProfilePage profilePage;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/login");
        user = TestUtilities.getNewRegisteredUser();
        TestUtilities.loginUserFromLoginPage(user, driver);
        TestUtilities.goToProfilePageFromMainPage(driver);
        profilePage = new BurgerProfilePage(driver);
        profilePage.waitForPageLoad();
    }

    @Test
    @DisplayName("Check that logged user can logout by logout button in profile")
    @Description("Logged user can logout by logout button on profile page")
    public void loggedUserCanLogoutByLogoutButton() {
        clickLogoutButtonProfilePage();
        loginPage = new BurgerLoginPage(driver);
        checkLoginPageLoaded();
        checkLoginButtonVisibility();
    }

    @After
    public void tearDown() {
        TestUtilities.deleteUser(user);
        user = null;
        driver.quit();
    }

    @Step("Click logout button")
    public void clickLogoutButtonProfilePage() {
        profilePage.clickLogoutButton();
    }

    @Step("Check that login page loaded")
    public void checkLoginPageLoaded() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(loginPage.isPageLoaded());
    }

    @Step("Check login button visibility")
    public void checkLoginButtonVisibility() {
        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }
}
