package service.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import service.api.BurgerApi;
import service.json.AuthData;
import service.json.User;
import service.page_object.BurgerLoginPage;
import service.page_object.BurgerMainPage;

import java.util.Random;

public class TestUtilities {
    // Создать пользователя
    public static User getNewRegisteredUser() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        User user = getNewUser();
        Response response = BurgerApi.sendPostAuthRegister(user);
        compareResponseStatusCode(response,200);
        return user;
    }

    // Удалить пользователя
    public static void deleteUser(String email, String password) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        Response response = BurgerApi.sendPostAuthLogin(email, password);
        compareResponseStatusCode(response,200);
        String accessToken = getAccessToken(response);
        BurgerApi.sendDeleteAuthUser(accessToken);
    }

    // Удалить пользователя
    public static void deleteUser(User user) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        Response response = BurgerApi.sendPostAuthLogin(user.getAuthData());
        compareResponseStatusCode(response,200);
        String accessToken = getAccessToken(response);
        BurgerApi.sendDeleteAuthUser(accessToken);
    }

    // Залогиниться
    public static void loginUserFromLoginPage(User user, WebDriver driver) {
        BurgerLoginPage loginPage = new BurgerLoginPage(driver);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickLoginButton();
    }

    public static void goToProfilePageFromMainPage(WebDriver driver) {
        BurgerMainPage mainPage = new BurgerMainPage(driver);
        mainPage.waitForPageLoad();
        mainPage.clickProfileButton();
    }

    // сгенерировать данные нового пользователя
    public static User getNewUser() {
        return new User(String.format("testmailbox%s@qasometestmail.su", new Random().nextInt(100500)).toLowerCase(), "derP@r0l", "Яков");
    }

    // сравнить фактический и ожидаемый код ответа
    public static void compareResponseStatusCode(Response response, int expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    // получить токен авторизации из ответа
    public static String getAccessToken(Response response) {
        return response.path("accessToken");
    }
}
