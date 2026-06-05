package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String name;
    private String email;
    private Integer age;
    private String ageString;

    public CreateUserRequest(String name, String email, Integer age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void CreateUserRequestWithoutName(String email, Integer age){
        this.email = email;
        this.age = age;
    }

    public void CreateUserRequestWithoutEmail(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public void CreateUserRequestWithoutAge(String name, String email){
        this.name = name;
        this.email = email;
    }

    public void CreateUserRequestStringAge(String name, String email, String ageString){
        this.name = name;
        this.email = email;
        this.ageString = ageString;
    }

}


