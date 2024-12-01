package service.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerMainPage {
    private WebDriver driver;
    // локатор кнопки "Войти в аккаунт"
    private By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    // локатор кнопки "Оформить заказ"
    private By placeOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    // локатор кнопки "Личный кабинет"
    private By profileButton = By.xpath(".//a[@href = '/account']");
    // локатор заголовка страницы
    private By pageHeader = By.xpath(".//h1[text() = 'Соберите бургер']");
    // локатор кнопки "Булки"
    private By bunsButton = By.xpath(".//div[contains(@class, 'tab_tab')]/*[text() = 'Булки']/..");
    // локатор кнопки "Соусы"
    private By saucesButton = By.xpath(".//div[contains(@class, 'tab_tab')]/*[text() = 'Соусы']/..");
    // локатор кнопки "Начинки"
    private By fillingsButton = By.xpath(".//div[contains(@class, 'tab_tab')]/*[text() = 'Начинки']/..");

    public BurgerMainPage(WebDriver driver){
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

    // нажать кнопку "Войти в аккаунт"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // нажать кнопку "Личный кабинет"
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    // проверить отображение кнопки "Оформить заказ"
    public boolean isPlaceOrderButtonVisible() {
        if (!driver.findElements(placeOrderButton).isEmpty()) {
            return driver.findElement(placeOrderButton).isDisplayed();
        }
        return false;
    }

    // нажать кнопку "Булки"
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    // нажать кнопку "Соусы"
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    // нажать кнопку "Начинки"
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    // проверка активен ли раздел "Булки"
    public boolean isBunsSectionActive() {
        return !driver.findElements(By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/*[text() = 'Булки']/..")).isEmpty();
    }

    // проверка активен ли раздел "Соусы"
    public boolean isSaucesSectionActive() {
        return !driver.findElements(By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/*[text() = 'Соусы']/..")).isEmpty();
    }

    // проверка активен ли раздел "Начинки"
    public boolean isFillingsSectionActive() {
        return !driver.findElements(By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/*[text() = 'Начинки']/..")).isEmpty();
    }
}
