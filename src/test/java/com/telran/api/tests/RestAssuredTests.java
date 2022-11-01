package com.telran.api.tests;

import com.jayway.restassured.RestAssured;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorResponseDto;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RestAssuredTests {
    @BeforeMethod
    public void Init (){
        RestAssured.baseURI = "https://contacts-telran.herokuapp.com";
        RestAssured.basePath = "api";
    }

    @Test
    public void loginTestPositive(){
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("michael@gmail.com")
                .password("ZZxcv_1!")
                .build();

        AuthResponseDto authResponseDto = given()
                .contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDto.class);

        System.out.println("AuthResponseDto obj: " + authResponseDto);

        String expectedToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im1pY2hhZWxAZ21haWwuY29tIn0.jheijxR8UOgsp_2k9bQy0HJXDeq1ZsiEL9YhJd43Zxc";
        // could be flaky, because the token can depends on a timestamp

        given()
                .contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat()
                .statusCode(200)
                .body(containsString("token"))
                .body("token", Matchers.equalTo(expectedToken))
                .extract().response().as(AuthResponseDto.class);

    }

    @Test
    public void loginTestNegative(){
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("michael@gmail.com")
                .password("1111")
                .build();

        ErrorResponseDto errorResponseDto = given()
                .contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat().statusCode(400)
                .extract().response().as(ErrorResponseDto.class);

        System.out.println("ErrorResponseDto obj: " + errorResponseDto);

        String expectedErrorMessage = "Password length need be 8 or more symbols";

        String timestamp = given()
                .contentType("application/json")
                .body(requestDto)
                .post("login")
                .then()
                .assertThat()
                .statusCode(400)
                .body(containsString("timestamp"))
                .body("message", Matchers.equalTo(expectedErrorMessage))
                .extract().path("timestamp").toString();

        System.out.println("TimeStamp: " + timestamp);
    }
}
