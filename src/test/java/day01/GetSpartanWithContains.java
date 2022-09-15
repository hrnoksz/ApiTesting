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
import utilities.ConfigurationReader;

public class GetSpartanWithContains {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("sp_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 63)
                .and().when().get("/api/spartans/{id}");
        response.prettyPrint();

        assertTrue(response.body().asString().contains("Clayton"));
        assertTrue(response.body().asString().contains("lay"));
        assertTrue(response.asString().contains("lay"));

    }
}
