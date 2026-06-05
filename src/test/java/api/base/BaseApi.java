package api.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseApi {

    protected RequestSpecification devRequest(){
        return RestAssured
                .given()
                .baseUri(ApiConfig.getDevBaseUrl())
                .header("Content-Type", "application/json");
                //.log().body();
    }

    protected RequestSpecification prodRequest(){
        return RestAssured
                .given()
                .baseUri(ApiConfig.getProdBaseUrl())
                .header("Content-Type", "application/json");
                //.log().all();
    }
}
