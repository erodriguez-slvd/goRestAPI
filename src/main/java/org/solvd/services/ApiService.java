package org.solvd.services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiService {
    public static String baseURI="https://gorest.co.in/public/v2";
    public static RequestSpecification createRequest(String bearerToken) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri(baseURI)
                .auth().oauth2(bearerToken)
                .log().all(true);
    }
}
