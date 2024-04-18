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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class UserTest {
    private static RequestSpecification request;
    private static ApiService apiService;
    private static UserService userService;
    public static String baseUri="https://gorest.co.in/public/v2";
    @BeforeClass
    public void init(){
        request = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", "Bearer "+ Auth.getTokenFromPropertiesFile())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    @Test(description = "GET Method")
    public void getAllUsersTest() {
        given().log().all(true).
                spec(request).
        when().
                get("/users").
        then().log().all(true).
                assertThat().
                statusCode(200);
    }
    @Test(description = "GET Method")
    public void getOneUser(){
        given().log().all(true).
                spec(request).
        when().
                get("/users/6859132").
        then().log().all(true).
                assertThat().statusCode(200);
    }
    @Test(description = "POST Method")
    public void createOneUser(){
        User user = new User("Anna Stuart", "astuart@mail.com","female", "active");
        given().log().all(true).
                spec(request).
                body(user).
                post("/users").
        then().log().all(true).
                assertThat().statusCode(201);
    }
    @Test(description = "PUT Method")
    public void updateUser(){
        Map<String,String> map=new HashMap<>();
        map.put("name", "Mary Stuart");
        map.put("email", "mstuart@mail.com");
        given().log().all().
                spec(request).
                body(map).
                patch("/users/6859132").
                then().log().all(true).
                assertThat().statusCode(200);
    }
    @Test(description = "DELETE Method")
    public void deleteUser(){
        given().log().all().
                spec(request).
                delete("/users/6859132").
                then().log().all(true).
                assertThat().statusCode(204);
    }
}
