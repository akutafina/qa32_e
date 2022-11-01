package com.telran.api.tests;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorResponseDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttpTest {
    public static MediaType MEDIA_TYPE_JSON= MediaType.get("application/json;charset=utf-8");

    @Test
    public void okLoginPositiveTest() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("michael@gmail.com")
                .password("ZZxcv_1!")
                .build();

        Gson gson = new Gson();
        System.out.println("We pass json: " + gson.toJson(requestDto, AuthRequestDto.class));

        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(requestDto, AuthRequestDto.class));

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();

        System.out.println("Response: " + response);

        String responseBody = response.body().string();
        System.out.println("Response body: " + responseBody);

        String responseHeaders = response.headers().toString();
        System.out.println("Response headers: " + responseHeaders);

        AuthResponseDto responseDto = gson.fromJson(responseBody, AuthResponseDto.class);
        System.out.println("AuthResponseDto as a string: " + responseDto);
        System.out.println("Token value from AuthResponseDto obj: " + responseDto.getToken());

        String token = responseDto.getToken();
        Assert.assertTrue(token.length() > 0, "API returns non-empty token" + token);

    }

    @Test
    public void okLoginTest() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("michael@gmail.com")
                .password("ZZxcv_1!")
                .build();

        Gson gson = new Gson();
        System.out.println("We pass json: " + gson.toJson(requestDto, AuthRequestDto.class));

        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(requestDto, AuthRequestDto.class));

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();

        System.out.println("Response: " + response);

        String responseBody = response.body().string();
        System.out.println("Response body: " + responseBody);

        String responseHeaders = response.headers().toString();
        System.out.println("Response headers: " + responseHeaders);


        if(response.isSuccessful()){
            AuthResponseDto responseDto = gson.fromJson(responseBody, AuthResponseDto.class);
            System.out.println("AuthResponseDto as a string: " + responseDto);
            System.out.println("Token value from AuthResponseDto obj: " + responseDto.getToken());

            String token = responseDto.getToken();
            Assert.assertTrue(token.length() > 0, "API returns non-empty token" + token);
        } else {
            ErrorResponseDto responseDto = gson.fromJson(responseBody, ErrorResponseDto.class);
            System.out.println("ErrorResponseDto as a string: " + responseDto);
            System.out.println("Error message from ErrorResponseDto obj: " + responseDto.getMessage());

            String message = responseDto.getMessage();
            Assert.assertEquals(message, "Password length need be 8 or more symbols", "API returns correct error message" + message);
        }
    }

    @Test
    public void okLoginNegativeTest() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder()
                .email("michael@gmail.com")
                .password("1111")
                .build();

        Gson gson = new Gson();
        System.out.println("We pass json: " + gson.toJson(requestDto, AuthRequestDto.class));

        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, gson.toJson(requestDto, AuthRequestDto.class));

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();

        System.out.println("Response: " + response);

        String responseBody = response.body().string();
        System.out.println("Response body: " + responseBody);

        String responseHeaders = response.headers().toString();
        System.out.println("Response headers: " + responseHeaders);


        if(response.isSuccessful()){
            AuthResponseDto responseDto = gson.fromJson(responseBody, AuthResponseDto.class);
            System.out.println("AuthResponseDto as a string: " + responseDto);
            System.out.println("Token value from AuthResponseDto obj: " + responseDto.getToken());

            String token = responseDto.getToken();
            Assert.assertTrue(token.length() > 0, "API returns non-empty token" + token);
        } else {
            ErrorResponseDto responseDto = gson.fromJson(responseBody, ErrorResponseDto.class);
            System.out.println("ErrorResponseDto as a string: " + responseDto);
            System.out.println("Error message from ErrorResponseDto obj: " + responseDto.getMessage());

            String message = responseDto.getMessage();
            Assert.assertEquals(message, "Password length need be 8 or more symbols", "API returns correct error message" + message);
        }
    }
}
