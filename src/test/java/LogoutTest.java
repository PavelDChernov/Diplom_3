import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import service.abstractions.AbstractTest;
import service.driver.WebDriverCreator;
import service.json.User;
import service.pageobject.BurgerLoginPage;
import service.pageobject.BurgerProfilePage;
import service.utilities.TestUtilities;

import static service.pageurls.BurgerPageUrls.LOGIN_PAGE_URL;

public class LogoutTest extends AbstractTest {
    private WebDriver driver;
    private User user;
    private BurgerLoginPage loginPage;
    private BurgerProfilePage profilePage;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        driver.get(LOGIN_PAGE_URL);
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
