package com.browserStack.APIUtils;

import com.browserStack.utilities.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpanishToEnglishTranslateAPI {

    public static Response translateText(String text) {
        RestAssured.baseURI = ConfigLoader.getProperty("baseURI");

        TranslateAPIClass translateAPIClass=new TranslateAPIClass("auto","en",text);

        return RestAssured
                .given()
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", ConfigLoader.getProperty("X-RapidAPI-Key"))
                .header("x-aibit-key", ConfigLoader.getProperty("x-aibit-key"))
                .body(translateAPIClass)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();

    }
}
