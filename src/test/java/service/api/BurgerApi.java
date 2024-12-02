package service.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import service.json.AuthData;
import service.json.User;

import static io.restassured.RestAssured.given;

public class BurgerApi {
    private static final String API_AUTH_REGISTER = "/api/auth/register";
    private static final String API_AUTH_LOGIN = "/api/auth/login";
    private static final String API_AUTH_USER = "/api/auth/user";
    private static final String AUTHORIZATION_HEADER = "authorization";

    // вызвать POST /api/auth/register
    public static Response sendPostAuthRegister(User user) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .and()
                        .body(user)
                        .when()
                        .post(API_AUTH_REGISTER);
        return response;
    }

    // вызвать POST /api/auth/login
    public static Response sendPostAuthLogin(AuthData authData) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .and()
                        .body(authData)
                        .when()
                        .post(API_AUTH_LOGIN);
        return response;
    }

    // вызвать POST /api/auth/login
    public static Response sendPostAuthLogin(String email, String password) {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .and()
                        .body(new AuthData(email, password))
                        .when()
                        .post(API_AUTH_LOGIN);
        return response;
    }

    // вызвать DELETE /api/auth/user
    public static Response sendDeleteAuthUser(String accessToken) {
        Response response =
                given()
                        .header(AUTHORIZATION_HEADER, accessToken)
                        .delete(API_AUTH_USER);
        return response;
    }
}
