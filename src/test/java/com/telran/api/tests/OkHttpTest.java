package com.telran.api.tests;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import okhttp3.*;
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
    }
}
