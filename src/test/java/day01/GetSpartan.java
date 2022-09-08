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

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        int id = response.path("id");
        System.out.println("id = " + id);

        String name = response.path("name");
        System.out.println("name = " + name);

       String gender = response.path("gender");
        System.out.println("gender = " + gender);

       long phone = response.path("phone");
        System.out.println("phone = " + phone);

        // verify all data
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);
    }
}
