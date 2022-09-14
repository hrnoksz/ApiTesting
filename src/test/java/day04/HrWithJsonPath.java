package day04;
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

public class HrWithJsonPath {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .when().get("/countries");
        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        System.out.println("jsonPath.getString(\"items[0].country_id\") = " +
                "" + jsonPath.getString("items[0].country_id"));

        assertEquals("AR", jsonPath.getString("items[0].country_id"));

        System.out.println("jsonPath.getString(\"items[0].links[0].rel\") = "
                + jsonPath.getString("items[0].links[0].rel"));

        String stringRel = jsonPath.getString("items[0].links[0].rel");
        assertEquals("self", stringRel);

        List<String> list = jsonPath.getList("items.country_id");
        int count = 1;
        for (String eachId : list) {
            System.out.println("eachId" + count + " = " + eachId);
            count++;
        }

        List<String> list1 = jsonPath.getList("items.country_name");
        int count1 = 1;
        for (String eachCountry : list1) {
            System.out.println("eachCountry" + count1 + " = " + eachCountry);
            count1++;

        }

        List<Integer> list2 = jsonPath.getList("items.findAll{it.region_id == 3}.country_name");
        System.out.println("list2 = " + list2);


    }
}
