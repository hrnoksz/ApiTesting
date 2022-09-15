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

public class OneSpartanDeserial {

    @BeforeAll
    public static void init(){

        baseURI = ConfigurationReader.getProperty("sp_url");
    }

    @Test
    public void test1() {

       Response response = given().accept(ContentType.JSON).and()
                .pathParam("id", 63)
                .and().when().get("/api/spartans/{id}");
       response.prettyPrint();

        Map<String, Object> jsonMap = response.body().as(Map.class);
        System.out.println("jsonMap.get(\"id\") = " + jsonMap.get("id"));
        System.out.println("jsonMap.get(\"name\") = " + jsonMap.get("name"));
        System.out.println("jsonMap.get(\"gender\") = " + jsonMap.get("gender"));
        System.out.println("jsonMap.get(\"phone\") = " + jsonMap.get("phone"));

        assertEquals(63, jsonMap.get("id"));
        assertThat(jsonMap.get("name"), is("Clayton"));
        assertThat(jsonMap.get("gender"), equalTo("Male"));
        assertEquals(1782167106, jsonMap.get("phone"));

        String gender = (String) jsonMap.get("gender");

        //MatcherAssert.assertThat();
    }
}
