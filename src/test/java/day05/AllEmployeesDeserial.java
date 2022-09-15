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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AllEmployeesDeserial {

    @BeforeAll
    public static  void init(){
        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/employees");
        //response.prettyPrint();

        Map<String, Object> jsonMap = response.body().as(Map.class);

        List<Map<String, Object>> jsonList = (List<Map<String, Object>>) jsonMap.get("items");

        for (Map<String, Object> eachMap : jsonList) {
            System.out.println("eachMap = " + eachMap);
        }

        assertEquals("Steven", jsonList.get(0).get("first_name"));

        Map<String, Object> employeeTwo = jsonList.get(1);
        System.out.println("employeeTwo = " + employeeTwo);

       List<Map<String, Object>> links = (List<Map<String, Object>>) jsonList.get(0).get("links");
       System.out.println("links.get(0).get(\"href\") = " + links.get(0).get("href"));

        assertEquals("http://3.83.123.243:1000/ords/hr/employees/100", links.get(0).get("href"));

    }
}
