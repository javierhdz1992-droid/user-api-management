package api.endpoints;

import api.base.ApiConfig;
import api.base.BaseApi;
import api.models.CreateUserRequest;
import api.models.UpdateUserRequest;
import io.restassured.response.Response;

public class UsersEndpoint extends BaseApi{

    private static final String USERS_PATH = "/users";

    public Response listUsers() {
        return  requestSpec()
                .when()
                .get(USERS_PATH);
    }

    public Response getUser(String email){
        return  requestSpec()
                .pathParam("email", email)
                .when()
                .get(USERS_PATH + "/{email}");
    }

    public Response createUser(CreateUserRequest body){
        return requestSpec()
                .body(body)
                .when()
                .post(USERS_PATH);
    }

    public Response updateUser(String email, UpdateUserRequest body){
        return requestSpec()
                .pathParam("email", email)
                .body(body)
                .when()
                .put(USERS_PATH + "/{email}");
    }

    public Response updateUserWithoutHeader(UpdateUserRequest body){
        return requestSpec()
                .body(body)
                .when()
                .put(USERS_PATH);
    }

    public Response deleteUser(String email){
        return requestSpec()
                .header("Authentication", ApiConfig.getAuthToken())
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }

    public Response deleteUserWithoutAuth(String email){
        return requestSpec()
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }
}
