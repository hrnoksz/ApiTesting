package day07;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import day06.Spartan;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class SpartanWithPostRequest {

    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("sp_url");
    }
    @Test
    public void postMethod1(){
        //1. Sending JSON as a String
        String requestJsonBody = "{\"gender\":\"Female\",\n" +
                "\"name\":\"Jennifer\",\n" +
                "\"phone\":3244531111}";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when().post("/api/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        assertThat(response.path("success"), is("A Spartan is Born!"));

        assertThat(response.path("data.name"), is("Jennifer"));

        assertThat(response.path("data.gender"), is("Female"));

        assertThat(response.path("data.phone"), is(3244531111l));

    }

    @Test
    public void postMethod2(){

        //2. Sending JSON as a Map
        Map<String, Object> requestJsonMap = new HashMap<>();
        requestJsonMap.put("name", "Jennifer");
        requestJsonMap.put("gender", "Female");
        requestJsonMap.put("phone", 3244531111l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonMap)
                .when().post("/api/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        assertThat(response.path("success"), is("A Spartan is Born!"));

        assertThat(response.path("data.name"), is("Jennifer"));

        assertThat(response.path("data.gender"), is("Female"));

        assertThat(response.path("data.phone"), is(3244531111l));

    }

    @Test
    public void postMethod3(){

        //3. Using POJO to send JSON request
        Spartan spartan = new Spartan();
        spartan.setName("Jennifer");
        spartan.setGender("Female");
        spartan.setPhone(3244531111l);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when().post("/api/spartans");
        response.prettyPrint();

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        assertThat(response.path("success"), is("A Spartan is Born!"));

        assertThat(response.path("data.name"), is("Jennifer"));

        assertThat(response.path("data.gender"), is("Female"));

        assertThat(response.path("data.phone"), is(3244531111l));

    }



}
