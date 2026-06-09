package api.tests;

import api.endpoints.UsersEndpoint;
import api.models.CreateUserRequest;
import api.utils.TestDataFactory;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateUserTest {

    @Test
    @Story("Create User Successfully - Status Code 200")
    public void createUserSuccessfully(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertEquals(response.jsonPath().getString("name"), createUserRequest.getName());
        softAssert.assertEquals(response.jsonPath().getString("email"), createUserRequest.getEmail());

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Create User without name - Status Code 400")
    public void createUserWithoutName(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        CreateUserRequest createUserRequest = new CreateUserRequest();
        TestDataFactory testdata = new  TestDataFactory();

        createUserRequest.CreateUserRequestWithoutName(testdata.getEmail(), testdata.getAge());

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Story("Create User without email - Status Code 400")
    public void createUserWithoutEmail(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        CreateUserRequest createUserRequest = new CreateUserRequest();
        TestDataFactory testdata = new  TestDataFactory();

        createUserRequest.CreateUserRequestWithoutEmail(testdata.getName(), testdata.getAge());

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Story("Create User without age - Status Code 400")
    public void createUserWithoutAge(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        CreateUserRequest createUserRequest = new CreateUserRequest();
        TestDataFactory testdata = new  TestDataFactory();

        createUserRequest.CreateUserRequestWithoutAge(testdata.getName(), testdata.getEmail());

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    @Story("Create two users with the same name - Status Code 201")
    public void createUsersWithSameName() {
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory user1 = new TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(user1.getName(), user1.getEmail(), user1.getAge());

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());

        TestDataFactory user2 = new TestDataFactory();

        createUserRequest = new CreateUserRequest(user1.getName(), user2.getEmail(), user2.getAge());

        response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 201);

        usersEndpoint.deleteUser(user1.getEmail());
        usersEndpoint.deleteUser(user2.getEmail());

        softAssert.assertAll();
    }

    @Test(enabled = false)
    @Story("Create two users with the same email - Status Code 409")
    public void createUsersWithSameEmail() {
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory user1 = new TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(user1.getName(), user1.getEmail(), user1.getAge());
        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());


        TestDataFactory user2 = new TestDataFactory();
        createUserRequest = new CreateUserRequest(user2.getName(), user1.getEmail(), user2.getAge());

        response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 409);

        usersEndpoint.deleteUser(user1.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Create two users with the same age - Status Code 201")
    public void createUsersWithSameAge() {
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory user1 = new TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(user1.getName(), user1.getEmail(), user1.getAge());
        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());

        TestDataFactory user2 = new TestDataFactory();
        createUserRequest = new CreateUserRequest(user2.getName(), user2.getEmail(), user1.getAge());

        response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 201);

        usersEndpoint.deleteUser(user1.getEmail());
        usersEndpoint.deleteUser(user2.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Create User with age less than 1 - Status Code 400")
    public void createUserWithAgeLessThan1(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), -1);

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 400);

        softAssert.assertAll();
    }

    @Test
    @Story("Create User with age greater than 150 - Status Code 400")
    public void createUserWithAgeGreaterThan150(){
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), 151);

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 400);

        softAssert.assertAll();
    }

    @Test
    @Story("Create User with invalid age format - Status Code 400")
    public void createUserWithInvalidAge(){
        TestDataFactory testdata = new  TestDataFactory();
        UsersEndpoint usersEndpoint = new UsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        CreateUserRequest createUserRequest = new CreateUserRequest();

        createUserRequest.CreateUserRequestStringAge(testdata.getName(), testdata.getEmail(), "twenty");

        Response response = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " +  response.getStatusCode());
        softAssert.assertEquals(response.getStatusCode(), 400);

        softAssert.assertAll();
    }
}
