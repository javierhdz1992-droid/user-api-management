package api.tests;

import api.endpoints.UsersEndpoint;
import api.models.CreateUserRequest;
import api.utils.TestDataFactory;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetUserTest {

    @Test
    @Story("GET all users successfully - Status Code 200")
    public void getAllUSersSuccessfully(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testData = new TestDataFactory();

        CreateUserRequest createRequest = new CreateUserRequest(testData.getName(), testData.getEmail(), testData.getAge());

        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response getUserResponse = usersEndpoint.listUsers();
        System.out.println("GET Response Body: " + getUserResponse.getBody().asString());
        System.out.println("Status Code: " +  getUserResponse.getStatusCode());
        Assert.assertEquals(getUserResponse.statusCode(),200);

        usersEndpoint.deleteUser(createRequest.getEmail());
    }

    @Test
    @Story("Get user successfully by email - Status Code 200")
    public void getUserByEmail(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testData = new TestDataFactory();

        CreateUserRequest createRequest = new CreateUserRequest(testData.getName(), testData.getEmail(), testData.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response getUserResponse = usersEndpoint.getUser(testData.getEmail());
        System.out.println("GET Response Body: " + getUserResponse.getBody().asString());
        System.out.println("Status Code: " +  getUserResponse.getStatusCode());
        Assert.assertEquals(getUserResponse.statusCode(),200);
        Assert.assertEquals(getUserResponse.jsonPath().getString("email"), createRequest.getEmail());

        usersEndpoint.deleteUser(createRequest.getEmail());
    }

    @Test
    @Story("Get user by non-existing email - Status Code 404")
    public void getUserByNonExistingEmail(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testData = new TestDataFactory();

        Response getUserResponse = usersEndpoint.getUser(testData.getEmail());
        System.out.println("GET Response Body: " + getUserResponse.getBody().asString());
        System.out.println("Status Code: " +  getUserResponse.getStatusCode());
        Assert.assertEquals(getUserResponse.statusCode(),404);
    }

    @Test
    @Story("Get user by name - Status Code 404")
    public void getUserByName(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testData = new TestDataFactory();
        SoftAssert softAssert = new SoftAssert();

        CreateUserRequest createRequest = new CreateUserRequest(testData.getName(), testData.getEmail(), testData.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response getUserResponse = usersEndpoint.getUser(testData.getName());
        System.out.println("GET Response Body: " + getUserResponse.getBody().asString());
        System.out.println("Status Code: " +  getUserResponse.getStatusCode());
        softAssert.assertEquals(getUserResponse.statusCode(),404);

        usersEndpoint.deleteUser(createRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Get user by age - Status Code 404")
    public void getUserByAge(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        TestDataFactory testData = new TestDataFactory();
        SoftAssert softAssert = new SoftAssert();

        CreateUserRequest createRequest = new CreateUserRequest(testData.getName(), testData.getEmail(), testData.getAge());
        Response createResponse = usersEndpoint.createUser(createRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        Response getUserResponse = usersEndpoint.getUser(testData.getAge().toString());
        System.out.println("GET Response Body: " + getUserResponse.getBody().asString());
        System.out.println("Status Code: " +  getUserResponse.getStatusCode());
        softAssert.assertEquals(getUserResponse.statusCode(),404);

        usersEndpoint.deleteUser(createRequest.getEmail());

        softAssert.assertAll();
    }
}
