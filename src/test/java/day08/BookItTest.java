package day08;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

public class BookItTest {

    @BeforeAll
    public static void init(){
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";
    }
    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMTI4MiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.7ysiaVjlFnVW7-dwsH2VS40_McjC3h1WYRHCmt2hkVs";

    @DisplayName("GET all campuses")
    @Test
    public void testAuth1(){

        //how to pass bearer token for bookit? Use header method to give as key value header
        given().header("Authorization", accessToken)
                .and().accept(ContentType.JSON)
        .when()
                .get("/api/campuses")
        .then()
                .statusCode(200)
                .log().all();
    }
}
