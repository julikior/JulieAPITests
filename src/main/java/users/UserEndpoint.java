package users;

import Enum.Gender;
import Enum.Status;
import authorization.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import logs.Log;
import model.Data;
import model.RestClient;
import model.UserRequest;
import model.UserResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class UserEndpoint {

    private static final List<Gender> VALUES_GENDER = Collections.unmodifiableList(Arrays.asList(Gender.values()));
    private static final List<Status> VALUES_STATUS = Collections.unmodifiableList(Arrays.asList(Status.values()));

    public static String randomGender() {
        return String.valueOf(VALUES_GENDER.get(new Random().nextInt(VALUES_GENDER.size())));
    }

    public static String randomStatus() {
        return String.valueOf(VALUES_STATUS.get(new Random().nextInt(VALUES_STATUS.size())));
    }

    private final RequestSpecification requestSpec = new RestClient().withUri(Endpoints.getUSERS()).build();

    @Step
    public List<Data> getUsers() {
        Log.info("Get users list");
        return given()
                .spec(requestSpec)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("data", Data.class);
    }

    @Step("Create User {user.name}")
    public UserResponse createUser(UserRequest user) {
        Log.info("Create a user");
        return given()
                .spec(requestSpec)
                .body(user)
                .post()
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(UserResponse.class);
    }

    @Step
    public UserResponse getUserById(int id) {
        Log.info("Get user by userId");
        return given().spec(requestSpec)
                .get("/" + id)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(UserResponse.class);
    }

    @Step
    public UserResponse updateUser(int id, UserRequest request) {
        Log.info("Update user");
        return given()
                .spec(requestSpec)
                .body(request)
                .put("/" + id)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(UserResponse.class);
    }

    @Step
    public UserResponse patchUser(int id, UserRequest request) {
        Log.info("Patch user");
        return given()
                .spec(requestSpec)
                .body(request)
                .patch("/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(UserResponse.class);
    }

    @Step
    public ValidatableResponse deleteUser(int id) {
        Log.info("Delete user");
        return given()
                .spec(requestSpec)
                .delete("/" + id)
                .then()
                .statusCode(204);
    }
}
