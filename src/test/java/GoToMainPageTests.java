import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import service.driver.WebDriverCreator;
import service.pageobject.BurgerLoginPage;
import service.pageobject.BurgerMainPage;

import static service.pageurls.BurgerPageUrls.LOGIN_PAGE_URL;

public class GoToMainPageTests {
    private WebDriver driver;
    private BurgerMainPage mainPage;
    private BurgerLoginPage loginPage;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        driver.get(LOGIN_PAGE_URL);
        loginPage = new BurgerLoginPage(driver);
        loginPage.waitForPageLoad();
    }

    @Test
    @DisplayName("Check that user can get to main page by constructor button")
    @Description("User can open main page by constructor button")
    public void userCanOpenMainPageByConstructorButton() {
        clickConstructorButtonLoginPage();
        mainPage = new BurgerMainPage(driver);
        checkMainPageLoaded();
    }

    @Test
    @DisplayName("Check that user can get to main page by stellar burger logo")
    @Description("User can open main page by clicking on stellar burger logo")
    public void userCanOpenMainPageByClickingOnStellarLogo() {
        clickStellarLogoLoginPage();
        mainPage = new BurgerMainPage(driver);
        checkMainPageLoaded();
    }

    @After
    public void tearDown() { driver.quit(); }

    @Step("Click constructor button")
    public void clickConstructorButtonLoginPage() {
        loginPage.clickConstructorButton();
    }

    @Step("Click stellar burger logo")
    public void clickStellarLogoLoginPage() {
        loginPage.clickStellarLogo();
    }

    @Step("Check that main page loaded")
    public void checkMainPageLoaded() {
        mainPage.waitForPageLoad();
        Assert.assertTrue(mainPage.isPageLoaded());
    }
}
