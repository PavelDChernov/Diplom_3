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
import service.pageobject.BurgerMainPage;
import service.pageobject.BurgerProfilePage;
import service.utilities.TestUtilities;

import static service.pageurls.BurgerPageUrls.LOGIN_PAGE_URL;

public class GoToProfilePageTest extends AbstractTest {
    private WebDriver driver;
    private User user;
    private BurgerMainPage mainPage;
    private BurgerProfilePage profilePage;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        driver.get(LOGIN_PAGE_URL);
        user = TestUtilities.getNewRegisteredUser();
        TestUtilities.loginUserFromLoginPage(user, driver);
        mainPage = new BurgerMainPage(driver);
        mainPage.waitForPageLoad();
    }

    @Test
    @DisplayName("Check that logged user can get into profile by profile button")
    @Description("Logged user can open profile page by profile button")
    public void loggedUserCanOpenProfilePageByProfileButton() {
        clickProfileButtonMainPage();
        profilePage = new BurgerProfilePage(driver);
        checkProfilePageLoaded();
    }

    @After
    public void tearDown() {
        TestUtilities.deleteUser(user);
        driver.quit();
    }

    @Step("Click profile button (main page)")
    public void clickProfileButtonMainPage() {
        mainPage.clickProfileButton();
    }

    @Step("Check that profile page loaded")
    public void checkProfilePageLoaded() {
        profilePage.waitForPageLoad();
        Assert.assertTrue(profilePage.isPageLoaded());
    }
}
