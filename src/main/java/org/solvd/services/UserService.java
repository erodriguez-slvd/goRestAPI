package org.solvd.services;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.solvd.utils.Auth;

public class UserService extends ApiService{
    private final String bearerToken;
    public UserService() {
        this.bearerToken = Auth.getTokenFromPropertiesFile();
    }

    public Response getAllUsers(){
        Response response = createRequest(this.bearerToken)
                .when()
                .get("/users");
        response.prettyPrint();
        response.then().statusCode(HttpStatus.SC_OK);
        return response;
    }

}
