package day02;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class SpartanWithQueryParam {

    @BeforeAll
    public static void init(){

        baseURI = ConfigurationReader.getProperty("sp_url");
    }
    @DisplayName("Get Spartan with Query Param")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "n")
                .and().queryParam("gender", "Male")
                .when().get("/api/spartans/search");
        response.prettyPrint();
    }

    @Test
    public void test2(){

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "n");
        queryMap.put("gender", "Male");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get("/api/spartans/search");
        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        //verify that first id is 2;
        int firstId = response.path("content[0].id");
        System.out.println("firstId = " + firstId);
        assertEquals(2, firstId);

        //verify that second name is Tedmund
        String secondName = response.path("content[1].name");
        System.out.println("secondName = " + secondName);
        assertEquals("Tedmund", secondName);

        //verify that total element is 36
        int actualTotalElement = response.path("totalElement");
        System.out.println("actualTotalElement = " + actualTotalElement);
        assertEquals(36, actualTotalElement);
    }
}
