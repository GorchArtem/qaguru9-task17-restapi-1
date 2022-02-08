package models;

import lombok.AllArgsConstructor;
import lombok.Data;

/*{
        "name": "morpheus",
        "job": "zion resident"
        }
        */
@Data
@AllArgsConstructor
public class UpdateUserRequest {
    private String name;
    private String job;

}
