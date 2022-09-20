package day07;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
@Getter
@Setter
@ToString


public class Item{
    @JsonProperty("region_id")
    private int regionId;
    @JsonProperty("region_name")
    private String regionName;

    private ArrayList<Link> links;
}
