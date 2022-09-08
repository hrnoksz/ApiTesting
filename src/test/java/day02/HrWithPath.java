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
import java.util.List;
import java.util.Map;
public class HrWithPath {

    @BeforeAll
    public static void init(){

        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");
        response.prettyPrint();

        //verify that first first_name is Alexander
        String firstName = response.path("items[0].first_name");
        System.out.println("firstName = " + firstName);
        assertEquals("Alexander", firstName);

        //verify that first href is http://3.83.123.243:1000/ords/hr/employees/103
        String firstHref = response.path("items[0].links[0].href");
        System.out.println("firstHref = " + firstHref);
        assertEquals("http://3.83.123.243:1000/ords/hr/employees/103", firstHref);

        //print all first_name
        List<String> allFirstNames = response.path("items.first_name");
        for (String eachFirstName : allFirstNames) {
            System.out.println("eachFirstName = " + eachFirstName);
        }

        //print all salary
        List<Integer> allSalaries = response.path("items.salary");
        for (Integer eachSalary : allSalaries) {
            System.out.println("eachSalary = " + eachSalary);
        }

        //Map<String, Integer> mapSalary = new HashMap<>();
        //mapSalary.put(response.path("items.first_name"), response.path("items.salary"));
        //System.out.println("mapSalary = " + mapSalary);

    }
}
