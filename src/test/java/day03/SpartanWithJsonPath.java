package day03;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpartanWithJsonPath {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("sp_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        System.out.println("jsonPath.getInt(\"id\") = " + jsonPath.getInt("id"));

        System.out.println("jsonPath.getString(\"name\") = " + jsonPath.getString("name"));

        System.out.println("jsonPath.getString(\"gender\") = " + jsonPath.getString("gender"));

        System.out.println("jsonPath.getLong(\"phone\") = " + jsonPath.getLong("phone"));

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertEquals("Meta", jsonPath.getString("name"));

        assertEquals("Female", jsonPath.getString("gender"));

        assertEquals(1938695106l, jsonPath.getLong("phone"));

    }

    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

       response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        List<Integer> idList = jsonPath.getList("id");

        System.out.println("idList.size() = " + idList.size());

        int count = 1;
        for (Integer eachId : idList) {
            System.out.println("id" + count +" = " + eachId);
            count++;
        }

        System.out.println("jsonPath.getString(\"name[0]\") = " + jsonPath.getString("name[1]"));

        List<Map<String, Object>> jsonList = response.body().as(List.class);

        System.out.println("jsonList = " + jsonList);

        System.out.println("jsonList.get(jsonList.size()-1).get(\"name\") = "
                + jsonList.get(jsonList.size() - 1).get("name"));

        Map<String, Object> spartan15 = jsonList.get(14);
        System.out.println("spartan15 = " + spartan15);
        spartan15.put("citizen", "Sparta");
        System.out.println("spartan15 = " + spartan15);


    }
}
