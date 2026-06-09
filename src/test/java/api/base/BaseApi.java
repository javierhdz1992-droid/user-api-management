package api.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected RequestSpecification requestSpec(){
        System.out.println("Base URL: " + ApiConfig.getBaseUrl());
        return RestAssured
                .given()
                .baseUri(ApiConfig.getBaseUrl())
                .contentType(ContentType.JSON);
                //.log().body();
    }
}
