package day01;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetSpartan {

    @BeforeAll
    public static void init(){

        baseURI = "http://3.83.123.243:8000";
    }

    @DisplayName("GET all Spartans")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint();

       assertEquals(200, response.statusCode());

       assertEquals("application/json", response.contentType());

       System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));

       assertTrue(response.headers().hasHeaderWithName("Connection"));

    }
}
