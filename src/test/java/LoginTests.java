import service.abstractions.AbstractLoginTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Test;
import service.pageobject.BurgerForgotPasswordPage;
import service.pageobject.BurgerLoginPage;
import service.pageobject.BurgerMainPage;
import service.pageobject.BurgerRegistrationPage;

import static service.pageurls.BurgerPageUrls.*;

public class LoginTests extends AbstractLoginTest {
    private BurgerMainPage mainPage;
    private BurgerLoginPage loginPage;
    private BurgerRegistrationPage registrationPage;
    private BurgerForgotPasswordPage forgotPasswordPage;

    @Test
    @DisplayName("Check successfull user login from main page by login button")
    @Description("User can login by login button on main page")
    public void userLoginByLoginButtonMainPage() {
        // подготовка
        driver.get(MAIN_PAGE_URL);
        mainPage = new BurgerMainPage(driver);
        mainPage.waitForPageLoad();
        // тест
        clickLoginButtonMainPage();
        loginPage = new BurgerLoginPage(driver);
        checkLoginPageLoaded();
        fillLoginForm(user.getEmail(), user.getPassword());
        clickLoginButtonLoginPage();
        checkMainPageLoaded();
        checkPlaceOrderButtonVisibility();
    }

    @Test
    @DisplayName("Check successfull user login from main page by profile button")
    @Description("User can login by profile button on main page")
    public void userLoginByProfileButtonMainPage() {
        // подготовка
        driver.get(MAIN_PAGE_URL);
        mainPage = new BurgerMainPage(driver);
        mainPage.waitForPageLoad();
        // тест
        clickProfileButtonMainPage();
        loginPage = new BurgerLoginPage(driver);
        checkLoginPageLoaded();
        fillLoginForm(user.getEmail(), user.getPassword());
        clickLoginButtonLoginPage();
        checkMainPageLoaded();
        checkPlaceOrderButtonVisibility();
    }

    @Test
    @DisplayName("Check successfull user login from registration page by login button")
    @Description("User can login by login button on registration page")
    public void userLoginByLoginButtonRegistrationPage() {
        // подготовка
        driver.get(REGISTRATION_PAGE_URL);
        registrationPage = new BurgerRegistrationPage(driver);
        registrationPage.waitForPageLoad();
        // тест
        clickLoginButtonRegistrationPage();
        loginPage = new BurgerLoginPage(driver);
        checkLoginPageLoaded();
        fillLoginForm(user.getEmail(), user.getPassword());
        clickLoginButtonLoginPage();
        mainPage = new BurgerMainPage(driver);
        checkMainPageLoaded();
        checkPlaceOrderButtonVisibility();
    }

    @Test
    @DisplayName("Check successfull user login from forgot password page by login button")
    @Description("User can login by login button on forgot password page")
    public void userLoginByLoginButtonForgotPasswordPage() {
        // подготовка
        driver.get(FORGOT_PASSWORD_PAGE_URL);
        forgotPasswordPage = new BurgerForgotPasswordPage(driver);
        forgotPasswordPage.waitForPageLoad();
        // тест
        clickLoginButtonForgotPasswordPage();
        loginPage = new BurgerLoginPage(driver);
        checkLoginPageLoaded();
        fillLoginForm(user.getEmail(), user.getPassword());
        clickLoginButtonLoginPage();
        mainPage = new BurgerMainPage(driver);
        checkMainPageLoaded();
        checkPlaceOrderButtonVisibility();
    }

    @Step("Click login button (main page)")
    public void clickLoginButtonMainPage() {
        mainPage.clickLoginButton();
    }

    @Step("Check that login page loaded")
    public void checkLoginPageLoaded() {
        loginPage.waitForPageLoad();
        Assert.assertTrue(loginPage.isPageLoaded());
    }

    @Step("Fill login form")
    public void fillLoginForm(String email, String password) {
        loginPage.setEmail(email);
        loginPage.setPassword(password);
    }

    @Step("Click login button (login page)")
    public void clickLoginButtonLoginPage() {
        loginPage.clickLoginButton();
    }

    @Step("Check that main page loaded")
    public void checkMainPageLoaded() {
        mainPage.waitForPageLoad();
        Assert.assertTrue(mainPage.isPageLoaded());
    }

    @Step("Check place order button visibility")
    public void checkPlaceOrderButtonVisibility() {
        Assert.assertTrue(mainPage.isPlaceOrderButtonVisible());
    }

    @Step("Click profile button (main page)")
    public void clickProfileButtonMainPage() {
        mainPage.clickProfileButton();
    }

    @Step("Click login button (registration page)")
    public void clickLoginButtonRegistrationPage() {
        registrationPage.clickLoginButton();
    }

    @Step("Click login button (forgot password page)")
    public void clickLoginButtonForgotPasswordPage() {
        forgotPasswordPage.clickLoginButton();
    }
}
