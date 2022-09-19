package day06;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpartanWithPOJO {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("sp_url");
    }

    @Test
    public void test1() {

        // 1. using as() method
        /*
            {
                "id": 33,
                "name": "Wilek",
                "gender": "Male",
                "phone": 2877865902
            }
        */

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 33)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();

        Spartan spartan33 = response.as(Spartan.class);

        assertEquals(33, spartan33.getId());
        assertThat(spartan33.getName(), is("Wilek"));
        assertThat(spartan33.getGender(), equalTo("Male"));
        assertThat(spartan33.getPhone(), is(2877865902l));
    }

    @Test
    public void test2() {

        //2. Using JsonPath to deserialize to Java Custom Class
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 33)
                .when().get("/api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();

        Spartan s33 = jsonPath.getObject("", Spartan.class);

        assertEquals(33, s33.getId());
        assertEquals("Wilek", s33.getName());
        assertEquals("Male", s33.getGender());
        assertEquals(2877865902l, s33.getPhone());
    }
    @Test
    public void test3() {

        //2. Using JsonPath to deserialize to Java Custom Class
        Spartan s33 = given().accept(ContentType.JSON)
                .pathParam("id", 33)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().jsonPath().getObject("", Spartan.class);

        assertEquals(33, s33.getId());
        assertEquals("Wilek", s33.getName());
        assertEquals("Male", s33.getGender());
        assertEquals(2877865902l, s33.getPhone());
    }

    @Test
    public void test4() {

        //3. Using gson() method to deserialize our json body
        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "    \"id\": 33,\n" +
                "    \"name\": \"Wilek\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 2877865902\n" +
                "}";
        Spartan spartanWilek = gson.fromJson(myJsonBody, Spartan.class);

        System.out.println("spartanWilek = " + spartanWilek);

        assertEquals(33, spartanWilek.getId());
    }

    @Test
    public void test5() {

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

       Spartan spartanMeade = given().accept(ContentType.JSON)
                .queryParams(queryMap)
                .when().get("/api/spartans/search")
                .then().statusCode(200).extract().jsonPath().getObject("content[0]", Spartan.class);

       assertEquals(1, spartanMeade.getId());
    }
}























