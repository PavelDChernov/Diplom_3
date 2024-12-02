package service.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerLoginPage {
    private WebDriver driver;
    // локатор поля "Email"
    private By emailField = By.xpath(".//label[text() = 'Email']/../input");
    // локатор поля "Пароль"
    private By passwordField = By.xpath(".//input[@name = 'Пароль']");
    // локатор кнопки "Войти"
    private By loginButton = By.xpath(".//button[text() = 'Войти']");
    // локатор заголовка страницы
    private By pageHeader = By.xpath(".//h2[text() = 'Вход']");
    // локатор кнопки "Конструктор"
    private By constructorButton = By.xpath(".//a[@href='/' and @class='AppHeader_header__link__3D_hX']");
    // локатор логотипа Stellar Burgers
    private By stellarLogo = By.className("AppHeader_header__logo__2D0X2");

    public BurgerLoginPage(WebDriver driver){
        this.driver = driver;
    }

    // ожидание загрузки страницы
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }

    // отображается ли страница
    public boolean isPageLoaded() {
        if (!driver.findElements(pageHeader).isEmpty()) {
            return driver.findElement(pageHeader).isDisplayed();
        }
        return false;
    }

    // заполнить поле "Email"
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    // заполнить поле "Пароль"
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // нажать кнопку "Войти"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // нажать кнопку "Конструктор"
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    // нажать логотип Stellar Burgers
    public void clickStellarLogo() {
        driver.findElement(stellarLogo).click();
    }

    // проверить отображение кнопки "Войти"
    public boolean isLoginButtonVisible() {
        if (!driver.findElements(loginButton).isEmpty()) {
            return driver.findElement(loginButton).isDisplayed();
        }
        return false;
    }
}
