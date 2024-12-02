package service.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerRegistrationPage {
    private WebDriver driver;
    // локатор поля "Имя"
    private By nameField = By.xpath(".//label[text() = 'Имя']/../input");
    // локатор поля "Email"
    private By emailField = By.xpath(".//label[text() = 'Email']/../input");
    // локатор поля "Пароль"
    private By passwordField = By.xpath(".//input[@name = 'Пароль']");
    // локатор кнопки "Зарегистрироваться"
    private By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    // локатор ошибки "Некорректный пароль"
    private By passwordError = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    // локатор заголовка страницы
    private By pageHeader = By.xpath(".//h2[text() = 'Регистрация']");
    // локатор кнопки "Войти"
    private By loginButton = By.xpath(".//a[@href = '/login']");

    public BurgerRegistrationPage(WebDriver driver){
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

    // заполнить поле "Имя"
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    // заполнить поле "Email"
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    // заполнить поле "Пароль"
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    // нажать кнопку "Зарегистрироваться"
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    // нажать кнопку "Войти"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // проверить отображение ошибки "Некорректный пароль"
    public boolean isPasswordErrorVisible() {
        if (!driver.findElements(passwordError).isEmpty()) {
            return driver.findElement(passwordError).isDisplayed();
        }
        return false;
    }

    // получить текст ошибки "Некорректный пароль"
    public String getPasswordErrorText() {
        return driver.findElement(passwordError).getText();
    }
}
