package day05;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
public class AllSpartanDeserial {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("sp_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        //response.prettyPrint();

        List<Map<String, Object>> jsonList = response.body().as(List.class);
        int count = 1;
        for (Map<String, Object> eachMap : jsonList) {
            System.out.println("eachMap" + count + " = " + eachMap);
            count++;
        }

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        assertEquals("Nels", jsonList.get(1).get("name"));

        assertThat(jsonList.get(9).get("gender"), is("Female"));

    }
}
