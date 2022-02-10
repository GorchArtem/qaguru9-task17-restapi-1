package models;
//{
//        "email": "eve.holt@reqres.in",
//        "password": "cityslicka"
//        }

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserRequest {
    private String email;
    private String password;

}
