import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.solvd.utils.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class GraphqlTest {
    private static RequestSpecification request;
    public static String baseURI="https://gorest.co.in/public/v2";
    @BeforeClass
    public void setUp(){
        //Create the request
        request=new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", "Bearer "+ Auth.getTokenFromPropertiesFile())
                .build();
    }
    @Test
    public void getAllUsers(){
        //Create the graphql query in JSON format
        String bodyQuery="{\n" +
                "  \"query\": \"query Users { users { nodes { email gender id name status } }}\"\n" +
                "}";
        Response response = given().log().all()
                .spec(request)
                .body(bodyQuery)
                .when()
                .post("/graphql");

        response.then().log().all(true).assertThat().statusCode(200);
    }
    @Test
    public void getAUserByID(){
        //Create the graphql query in JSON format
        String bodyQuery="{\n" +
                "  \"query\": \"query User { user(id: \\\"6849956\\\") { email gender id name status }}\"\n" +
                "}";
        Response response = given().log().all()
                .spec(request)
                .body(bodyQuery)
                .when()
                .post("/graphql");

        response.then().log().all(true).assertThat().statusCode(200);
    }
    @Test
    public void createAUser(){
        //Create the graphql query in JSON format
        String bodyQuery="{\n" +
                "  \"query\": \"mutation CreateUser { createUser(input: null) { clientMutationId }}\"\n" +
                "}";
        Response response = given().log().all()
                .spec(request)
                .body(bodyQuery)
                .when()
                .post("/graphql");

        response.then().log().all(true).assertThat().statusCode(200);
    }

}
