import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.solvd.models.User;
import org.solvd.services.ApiService;
import org.solvd.services.UserService;
import org.solvd.utils.Auth;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

import static io.restassured.RestAssured.*;

public class UserTest {
    private static RequestSpecification request;
    private static ApiService apiService;
    private static UserService userService;
    public static String baseUri="https://gorest.co.in/public/v2";
    @BeforeClass
    public void init(){
//        userService= new UserService();
        request = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorizaton", "Bearer "+ Auth.getTokenFromPropertiesFile())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    @Test
    public void getAllUsersTest() {
        given().log().all(true).
                spec(request).
        when().
                get("/users").
        then().log().all(true).
                assertThat().
                statusCode(200);
//    Response response = userService.getAllUsers();
//    List<User> userList = response.jsonPath().getList(".", User.class);
//    Assert.assertEquals(response.statusCode(),200);
//    //Assert.assertFalse(userList.isEmpty());
    }
    @Test
    public void getOneUser(){
        given().log().all(true).
                spec(request).
        when().
                get("/users/6856561").
        then().log().all(true).
                assertThat().statusCode(200);
    }
    @Test
    public void createOneUser(){
        User user = new User("Anna Stuart", "astuart@mail.com","female", "active");
        given().log().all(true).
                spec(request).
                body(user).
                post("/users").
                then().log().all(true).
                assertThat().statusCode(201);
    }
}
