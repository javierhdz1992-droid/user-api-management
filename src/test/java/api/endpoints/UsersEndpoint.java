package api.endpoints;

import api.base.ApiConfig;
import api.base.BaseApi;
import api.base.BaseTest;
import api.models.CreateUserRequest;
import api.models.UpdateUserRequest;
import io.restassured.response.Response;

public class UsersEndpoint extends BaseTest {

    private static final String USERS_PATH = "/users";
    BaseApi api = new BaseApi();

    public Response listUsers() {
        return  api.requestSpec()
                .when()
                .get(USERS_PATH);
    }

    public Response getUser(String email){
        return  api.requestSpec()
                .pathParam("email", email)
                .when()
                .get(USERS_PATH + "/{email}");
    }

    public Response createUser(CreateUserRequest body){
        return api.requestSpec()
                .body(body)
                .when()
                .post(USERS_PATH);
    }

    public Response updateUser(String email, UpdateUserRequest body){
        return api.requestSpec()
                .pathParam("email", email)
                .body(body)
                .when()
                .put(USERS_PATH + "/{email}");
    }

    public Response updateUserWithoutHeader(UpdateUserRequest body){
        return api.requestSpec()
                .body(body)
                .when()
                .put(USERS_PATH);
    }

    public Response deleteUser(String email){
        return api.requestSpec()
                .header("Authentication", ApiConfig.getAuthToken())
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }

    public Response deleteUserWithoutAuth(String email){
        return api.requestSpec()
                .pathParam("email", email)
                .when()
                .delete(USERS_PATH + "/{email}");
    }
}
