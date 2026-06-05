package api.endpoints;

import api.base.ApiConfig;
import api.base.BaseApi;
import api.models.CreateUserRequest;
import io.restassured.response.Response;

public class ProdUsersEndpoint extends BaseApi {
    private static final String USERS_PATH = "/users";

    public Response listUsers() {
        return  prodRequest()
                .when()
                .get(USERS_PATH);
    }

    public Response getUser(String email){
        return  prodRequest()
                .pathParam("email", email)
                .when()
                .get(USERS_PATH + "/{email}");
    }

    public Response createUser(CreateUserRequest body){
        return prodRequest()
                .body(body)
                .when()
                .post(USERS_PATH);
    }

    public Response updateUser(String email, CreateUserRequest body){
        return prodRequest()
                .pathParam("email", email)
                .body(body)
                .when()
                .put(USERS_PATH + "/{email}");
    }

    public Response deleteUser(String email){
        return prodRequest()
                .header("Authentication", ApiConfig.getAuthToken())
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }

    public Response deleteUserWithoutAuth(String email){
        return prodRequest()
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }
}
