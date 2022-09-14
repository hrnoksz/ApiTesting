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

public class HamcrestMatchersWithHr {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void test1() {

        given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
        .when()
                .get("/employees")
        .then()
                .statusCode(200)
                .contentType("application/json")
                .header("Date", notNullValue())
                .and().assertThat().body("items[0].first_name", equalTo("Alexander"),
                        "items[1].last_name", equalTo("Ernst"),
                        "items[2].employee_id", equalTo(105));
    }
}
