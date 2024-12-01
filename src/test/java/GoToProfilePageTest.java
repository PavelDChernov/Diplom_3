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
import service.page_object.BurgerMainPage;
import service.page_object.BurgerProfilePage;
import service.utilities.TestUtilities;

public class GoToProfilePageTest {
    private WebDriver driver;
    private User user;
    private BurgerMainPage mainPage;
    private BurgerProfilePage profilePage;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/login");
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
        user = null;
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
