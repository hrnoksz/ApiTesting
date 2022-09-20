package day07;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
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

public class RegionsWithPOJO {
    @BeforeAll
    public static void init(){
        baseURI = ConfigurationReader.getProperty("hr_url");
    }

    @Test
    public void regionsWithPojo(){
        Regions regions = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200)
                .extract().response().as(Regions.class);

       System.out.println("regions.getCount() = " + regions.getCount());
       System.out.println("regions.getLimit() = " + regions.getLimit());
       System.out.println("regions.isHasMore() = " + regions.isHasMore());

       System.out.println("regions.getItems().get(0).getRegion_name() = " + regions.getItems().get(0).getRegion_name());

        ArrayList<Item> items = regions.getItems();
       System.out.println("items.get(0).getRegion_id() = " + items.get(0).getRegion_id());

       System.out.println("items.get(0).getRegion_name() = " + items.get(0).getRegion_name());

        for (Item eachItem : items) {
            System.out.println("eachItem.getRegion_name() = " + eachItem.getRegion_name());
            System.out.println("eachItem.getRegion_id() = " + eachItem.getRegion_id());
        }
        System.out.println("items.get(0).getLinks().get(0).getHref() = " + items.get(0).getLinks().get(0).getHref());

        ArrayList<Link> links = regions.getLinks();
        System.out.println("links.get(0).getHref() = " + links.get(0).getHref());
    }

    @Test
    public void test1(){

        Regions regions = given().accept(ContentType.JSON)
                .when().get("/regions")
                .then().statusCode(200)
                .extract().jsonPath().getObject("", Regions.class);

        System.out.println("regions.getOffset() = " + regions.getOffset());

        System.out.println("regions.getItems().get(1).getRegion_name() = " + regions.getItems().get(1).getRegion_name());
    }
}
