package day06;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionsWithDeserialization {

    @BeforeAll
    public static void init(){

        baseURI =  ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200).extract().response();

        Map<String, Object> jsonMap = response.as(Map.class);

        System.out.println("jsonMap = " + jsonMap);

        List<Map<String, Object>> jsonList = (List<Map<String, Object>>) jsonMap.get("items");

        for (Map<String, Object> eachMap : jsonList) {
            System.out.println("eachMap = " + eachMap);
        }

        assertThat(jsonList.get(0).get("region_name"), is("Europe"));

    }
}
