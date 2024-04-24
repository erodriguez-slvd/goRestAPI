import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.solvd.models.User;
import org.solvd.utils.Auth;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.Gson;

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
        User userObj= new User("Mary Doe", "mdoe@gmail.com", "female", "active");
        String jsonObj = new Gson().toJson(userObj);
        System.out.println(jsonObj);
        //        JSONPObject createUser= new JSONPObject();
//        JSONObject createUserVars = new JSONObject();
        //Create the graphql query in JSON format
        String query="{\n" +
                "  \"query\": \"mutation CreateUser { createUser( input: { name: \\\"Erika Smith\\\" gender: \\\"female\\\" email: \\\"esmith@mail.com\\\" status: \\\"active\\\" } ) { user { email gender id name status } }}\"\n" +
                "}";
        String bodyQuery="{\n" +
                "  \"query\": \"mutation CreateUser { createUser( input: { name: \\\"Erika Smith\\\" gender: \\\"female\\\" email: \\\"esmith@mail.com\\\" status: \\\"active\\\" } ) { user { email gender id name status } }}\"\n" +
                "}";

        Response response = given().log().all()
                .spec(request)
                .body(bodyQuery)
                .when()
                .post("/graphql");

        response.then().log().all(true).assertThat().statusCode(200);
    }
    @Test
    public void updateUser(){
        User userObj= new User("Mary Doe", "mdoe@gmail.com", "female", "active");
        String jsonObj = new Gson().toJson(userObj);
        System.out.println(jsonObj);
        //        JSONPObject createUser= new JSONPObject();
//        JSONObject createUserVars = new JSONObject();
        //Create the graphql query in JSON format
        String bodyQuery="{\n" +
                "  \"query\": \"mutation UpdateUser { updateUser(input: { name: null }) { clientMutationId }}\"\n" +
                "}";

        Response response = given().log().all()
                .spec(request)
                .body(bodyQuery)
                .when()
                .post("/graphql");

        response.then().log().all(true).assertThat().statusCode(200);
    }
    @Test
    public void deleteUser(){
        User userObj= new User("Mary Doe", "mdoe@gmail.com", "female", "active");
        String jsonObj = new Gson().toJson(userObj);
        System.out.println(jsonObj);
        //        JSONPObject createUser= new JSONPObject();
//        JSONObject createUserVars = new JSONObject();
        //Create the graphql query in JSON format
        String bodyQuery="{\n" +
                "  \"query\": \"mutation DeleteUser { deleteUser(input: { id: null }) { clientMutationId }}\"\n" +
                "}";

        Response response = given().log().all()
                .spec(request)
                .body(bodyQuery)
                .when()
                .post("/graphql");

        response.then().log().all(true).assertThat().statusCode(200);
    }

}
