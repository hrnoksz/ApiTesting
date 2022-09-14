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
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class HamcrestMatchersWithSpartan {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("sp_url");
    }

    @Test
    public void test1() {
        given().accept(ContentType.JSON)
                .pathParam("id", 15)
        .when()
                .get("/api/spartans/{id}")
        .then()
                .statusCode(200)
                .and().contentType("application/json")
                .and().header("Connection", "keep-alive")
                .header("Date", notNullValue())
                .and().assertThat()
                .body("id", is(15), "name", is("Meta"),
                        "gender", is("Female"), "phone", is(1938695106));
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}");
        assertTrue(response.headers().hasHeaderWithName("Date"));

    }
}
