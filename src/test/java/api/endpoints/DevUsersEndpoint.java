package api.endpoints;

import api.base.ApiConfig;
import api.base.BaseApi;
import api.models.CreateUserRequest;
import api.models.UpdateUserRequest;
import io.restassured.response.Response;

public class DevUsersEndpoint extends BaseApi{

    private static final String USERS_PATH = "/users";

    public Response listUsers() {
        return  devRequest()
                .when()
                .get(USERS_PATH);
    }

    public Response getUser(String email){
        return  devRequest()
                .pathParam("email", email)
                .when()
                .get(USERS_PATH + "/{email}");
    }

    public Response createUser(CreateUserRequest body){
        return devRequest()
                .body(body)
                .when()
                .post(USERS_PATH);
    }

    public Response updateUser(String email, UpdateUserRequest body){
        return devRequest()
                .pathParam("email", email)
                .body(body)
                .when()
                .put(USERS_PATH + "/{email}");
    }

    public Response updateUserWithoutHeader(UpdateUserRequest body){
        return devRequest()
                .body(body)
                .when()
                .put(USERS_PATH);
    }

    public Response deleteUser(String email){
        return devRequest()
                .header("Authentication", ApiConfig.getAuthToken())
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }

    public Response deleteUserWithoutAuth(String email){
        return devRequest()
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }
}
