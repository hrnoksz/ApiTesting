package day08;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;


public class SpartanWithAuthTest {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("sptn_url");
    }
    @DisplayName("GET /api/spartans as a public user(guest)")
    @Test
    public void test1(){
        try {
            given()
                    .accept(ContentType.JSON)
            .when()
                    .get("/api/spartans")
            .then().statusCode(401)
                    .log().all();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        //how to pass admin admin as a username and password?
        given()
                .auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                .log().all();
    }
}
