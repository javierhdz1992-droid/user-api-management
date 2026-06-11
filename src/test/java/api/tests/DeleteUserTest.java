package api.tests;

import api.endpoints.UsersEndpoint;
import api.models.CreateUserRequest;
import api.utils.TestDataFactory;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DeleteUserTest {

    @Test
    @Story("Delete User Successfully - Status Code 204")
    public void deleteUserSuccessfully(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response deleteResponse = usersEndpoint.deleteUser(testdata.getEmail());
        System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());
        System.out.println("Status Code: " +  deleteResponse.getStatusCode());
        Assert.assertEquals(deleteResponse.getStatusCode(), 204);
    }

    @Test
    @Story("Delete user by non-existing email - Status Code 404")
    public void getUserByNonExistingEmail(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testdata = new  TestDataFactory();

        Response deleteResponse = usersEndpoint.deleteUser(testdata.getEmail());
        System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());
        System.out.println("Status Code: " +  deleteResponse.getStatusCode());
        Assert.assertEquals(deleteResponse.getStatusCode(), 404);
    }

    @Test
    @Story("Delete User by name - Status Code 404")
    public void deleteUserByName(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testdata = new  TestDataFactory();
        SoftAssert softAssert = new SoftAssert();

        CreateUserRequest createRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response deleteResponse = usersEndpoint.deleteUser(testdata.getName());
        System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());
        System.out.println("Status Code: " +  deleteResponse.getStatusCode());
        softAssert.assertEquals(deleteResponse.getStatusCode(), 404);

        usersEndpoint.deleteUser(createRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Delete User by age - Status Code 404")
    public void deleteUserByAge(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testdata = new  TestDataFactory();
        SoftAssert softAssert = new SoftAssert();

        CreateUserRequest createRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response deleteResponse = usersEndpoint.deleteUser(testdata.getAge().toString());
        System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());
        System.out.println("Status Code: " +  deleteResponse.getStatusCode());
        softAssert.assertEquals(deleteResponse.getStatusCode(), 404);

        usersEndpoint.deleteUser(createRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Delete User Twice - Status Code 204")
    public void deleteUserTwice(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response deleteResponse = usersEndpoint.deleteUser(testdata.getEmail());
        deleteResponse = usersEndpoint.deleteUser(testdata.getEmail());
        System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());
        System.out.println("Status Code: " +  deleteResponse.getStatusCode());
        Assert.assertEquals(deleteResponse.getStatusCode(), 404);
    }

    @Test
    @Story("Delete User without authentication - Status Code 204")
    public void deleteUserWithoutAuth(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testdata = new  TestDataFactory();
        SoftAssert softAssert = new SoftAssert();

        CreateUserRequest createRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response deleteResponse = usersEndpoint.deleteUserWithoutAuth(testdata.getEmail());
        System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());
        System.out.println("Status Code: " +  deleteResponse.getStatusCode());
        softAssert.assertEquals(deleteResponse.getStatusCode(), 401);
        softAssert.assertAll();
    }
}
