package day06;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
{
    "id": 33,
    "name": "Wilek",
    "gender": "Male",
    "phone": 2877865902
}
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value="id", allowSetters = true)
public class Spartan {
    private int id;
    private String name;
    private String gender;
    private long phone;

}
