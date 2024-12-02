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
import service.utilities.TestUtilities;
import service.pageobject.BurgerLoginPage;
import service.pageobject.BurgerRegistrationPage;
import java.util.Random;

import static service.pageurls.BurgerPageUrls.REGISTRATION_PAGE_URL;

public class RegistrationTests extends AbstractTest {
    private WebDriver driver;
    private BurgerRegistrationPage registrationPage;
    private BurgerLoginPage loginPage;

    private static final String NAME = "Никодим";
    private static final String PASSWORD = "derP@r0l";
    private final String email = String.format("uitest%s@sometestmail.su", new Random().nextInt(100500)).toLowerCase();

    @Before
    public void setUp() {
        driver = WebDriverCreator.getWebDriver();
        driver.get(REGISTRATION_PAGE_URL);
        registrationPage = new BurgerRegistrationPage(driver);
        registrationPage.waitForPageLoad();
    }

    @Test
    @DisplayName("Check successfull registration of new user")
    @Description("New user can be successfully registered")
    public void successfullUserRegistration() {
        fillRegistrationForm(NAME, email, PASSWORD);
        clickRegistrationButton();
        loginPage = new BurgerLoginPage(driver);
        checkLoginPageLoaded();
        // удаление тестовых данных включил в тело теста, т.к. используется только в нем и указывать в @After нецелесообразно
        TestUtilities.deleteUser(email,PASSWORD);
    }

    @Test
    @DisplayName("Check error on registration with short password")
    @Description("Error shown if password is short")
    public void incorrectPasswordErrorDuringRegistration() {
        setUserPassword("P@r0L");
        clickRegistrationButton();
        checkPasswordErrorShown();
        checkPasswordErrorText("Некорректный пароль");
    }

    @Step("Fill registration form")
    public void fillRegistrationForm(String name, String email, String password) {
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
    }

    @Step("Enter user password")
    public void setUserPassword(String password) {
        registrationPage.setPassword(password);
    }

    @Step("Click registration button")
    public void clickRegistrationButton() {
        registrationPage.clickRegisterButton();
    }

    @Step("Check that login page loaded")
    public void checkLoginPageLoaded() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(loginPage.isPageLoaded());
    }

    @Step("Check if password error shown")
    public void checkPasswordErrorShown() {
        Assert.assertTrue(registrationPage.isPasswordErrorVisible());
    }

    @Step("Check password error text")
    public void checkPasswordErrorText(String expectedText) {
        Assert.assertEquals(expectedText, registrationPage.getPasswordErrorText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
