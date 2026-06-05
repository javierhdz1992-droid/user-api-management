package api.tests;

import api.endpoints.DevUsersEndpoint;
import api.models.CreateUserRequest;
import api.models.UpdateUserRequest;
import api.utils.TestDataFactory;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateUserTest {

    @Test
    @Story("Update User Successfully - Status Code 200")
    public void updateUserSuccessfully(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(testdata.getName(), createUserRequest.getEmail(), testdata.getAge());
        Response updateResponse = usersEndpoint.updateUser(createUserRequest.getEmail(), updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 200);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Update User Without Name - Status Code 400")
    public void updateUserWithoutName(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        updateUserRequest.UpdateUserRequestWithoutName(createUserRequest.getEmail(), testdata.getAge());
        Response updateResponse = usersEndpoint.updateUser(createUserRequest.getEmail(), updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 400);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Update User Without Email - Status Code 400")
    public void updateUserWithoutEmail(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        updateUserRequest.UpdateUserRequestWithoutEmail(testdata.getName(), testdata.getAge());
        Response updateResponse = usersEndpoint.updateUser(createUserRequest.getEmail(), updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 400);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Update User Without Age - Status Code 400")
    public void updateUserWithoutAge(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        updateUserRequest.UpdateUserRequestWithoutAge(testdata.getName(), testdata.getEmail());
        Response updateResponse = usersEndpoint.updateUser(createUserRequest.getEmail(), updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 400);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Update User with duplicate email - Status Code 201")
    public void updateUserWithDuplicateEmail() {
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory user1 = new  TestDataFactory();
        String email1 = user1.getEmail();

        CreateUserRequest createUserRequest = new CreateUserRequest(user1.getName(), email1, user1.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        TestDataFactory user2 = new TestDataFactory();
        String email2 = user2.getEmail();
        createUserRequest = new CreateUserRequest(user2.getName(), email2, user2.getAge());

        createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(user1.getName(), email1, user1.getAge());
        Response updateResponse = usersEndpoint.updateUser(email2, updateUserRequest);

        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 409);

        usersEndpoint.deleteUser(email1);
        usersEndpoint.deleteUser(email2);

        softAssert.assertAll();
    }

    @Test
    @Story("Update User with Age Less than 1 - Status Code 400")
    public void updateUserWithAgeLessThan1(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(testdata.getName(), createUserRequest.getEmail(), -1);
        Response updateResponse = usersEndpoint.updateUser(createUserRequest.getEmail(), updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 400);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Update User with Age Greater than 1 - Status Code 400")
    public void updateUserWithAgeGreaterThan150(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(testdata.getName(), createUserRequest.getEmail(), 151);
        Response updateResponse = usersEndpoint.updateUser(createUserRequest.getEmail(), updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 400);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Update User with invalid age format - Status Code 400")
    public void updateUserWithinvalidAge(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        updateUserRequest.UpdateUserRequestStringAge(testdata.getName(), createUserRequest.getEmail(), "twenty");
        Response updateResponse = usersEndpoint.updateUser(createUserRequest.getEmail(), updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 400);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }

    @Test
    @Story("Update User without email Header - Status Code 400")
    public void updateUserWithoutEmailHeader(){
        DevUsersEndpoint usersEndpoint = new DevUsersEndpoint();
        SoftAssert softAssert = new SoftAssert();
        TestDataFactory testdata = new  TestDataFactory();

        CreateUserRequest createUserRequest = new CreateUserRequest(testdata.getName(), testdata.getEmail(), testdata.getAge());

        Response createResponse = usersEndpoint.createUser(createUserRequest);
        System.out.println("POST Response Body: " + createResponse.getBody().asString());
        System.out.println("Status Code: " +  createResponse.getStatusCode());

        testdata = new TestDataFactory();

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(testdata.getName(), createUserRequest.getEmail(), testdata.getAge());
        Response updateResponse = usersEndpoint.updateUserWithoutHeader(updateUserRequest);
        System.out.println("PUT Response Body: " + updateResponse.getBody().asString());
        System.out.println("Status Code: " +  updateResponse.getStatusCode());
        softAssert.assertEquals(updateResponse.getStatusCode(), 500);

        usersEndpoint.deleteUser(createUserRequest.getEmail());

        softAssert.assertAll();
    }
}
