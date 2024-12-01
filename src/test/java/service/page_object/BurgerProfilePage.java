package service.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerProfilePage {
    private WebDriver driver;
    // локатор кнопки "Сохранить"
    private By saveButton = By.xpath(".//button[text() = 'Сохранить']");
    // локатор кнопки "Выход"
    private By logoutButton = By.xpath(".//button[text() = 'Выход']");

    public BurgerProfilePage(WebDriver driver){
        this.driver = driver;
    }

    // ожидание загрузки страницы
    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButton));
    }

    // отображается ли страница
    public boolean isPageLoaded() {
        if (!driver.findElements(saveButton).isEmpty()) {
            return driver.findElement(saveButton).isDisplayed();
        }
        return false;
    }

    // нажать кнопку "Выход"
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
