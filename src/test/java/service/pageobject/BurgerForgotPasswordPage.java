package service.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerForgotPasswordPage {
    private WebDriver driver;
    // локатор кнопки "Войти"
    private By loginButton = By.xpath(".//a[@href = '/login']");
    // локатор заголовка страницы
    private By pageHeader = By.xpath(".//h2[text() = 'Восстановление пароля']");

    public BurgerForgotPasswordPage(WebDriver driver){
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

    // нажать кнопку "Войти"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
