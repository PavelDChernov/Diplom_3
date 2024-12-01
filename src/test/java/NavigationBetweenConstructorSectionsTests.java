import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import service.driver.WebDriverCreator;
import service.page_object.BurgerMainPage;

public class NavigationBetweenConstructorSectionsTests {
    private WebDriver driver;
    private BurgerMainPage mainPage;

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
        mainPage = new BurgerMainPage(driver);
        mainPage.waitForPageLoad();
    }

    @Test
    @DisplayName("User can navigate to sauces section by sauces button")
    @Description("Sauces section opens on sauces button click")
    public void saucesSectionOpensOnSaucesButtonClick() {
        clickSaucesButton();
        checkSaucesSectionIsActive();
    }

    @Test
    @DisplayName("User can navigate to fillings section by fillings button")
    @Description("Fillings section opens on fillings button click")
    public void fillingsSectionOpensOnFillingsButtonClick() {
        clickFillingsButton();
        checkFillingsSectionIsActive();
    }

    @Test
    @DisplayName("User can navigate to buns section by buns button")
    @Description("Buns section opens on buns button click")
    public void bunsSectionOpensOnSaucesButtonClick() {
        // подготовка, т.к. секция "Булки" активна по дефолту
        mainPage.clickSaucesButton();
        // тест
        clickBunsButton();
        checkBunsSectionIsActive();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Step("Click buns button")
    public void clickBunsButton() {
        mainPage.clickBunsButton();
    }

    @Step("Click sauces button")
    public void clickSaucesButton() {
        mainPage.clickSaucesButton();
    }

    @Step("Click fillings button")
    public void clickFillingsButton() {
        mainPage.clickFillingsButton();
    }

    @Step("Check that buns section is active")
    public void checkBunsSectionIsActive() {
        Assert.assertTrue(mainPage.isBunsSectionActive());
    }

    @Step("Check that sauces section is active")
    public void checkSaucesSectionIsActive() {
        Assert.assertTrue(mainPage.isSaucesSectionActive());
    }

    @Step("Check that fillings section is active")
    public void checkFillingsSectionIsActive() {
        Assert.assertTrue(mainPage.isFillingsSectionActive());
    }
}
