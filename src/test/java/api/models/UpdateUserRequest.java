package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String name;
    private String email;
    private Integer age;
    private String ageString;

    public UpdateUserRequest(String name, String email, Integer age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void UpdateUserRequestWithoutName(String email, Integer age){
        this.email = email;
        this.age = age;
    }

    public void UpdateUserRequestWithoutEmail(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public void UpdateUserRequestWithoutAge(String name, String email){
        this.name = name;
        this.email = email;
    }

    public void UpdateUserRequestStringAge(String name, String email, String ageString){
        this.name = name;
        this.email = email;
        this.ageString = ageString;
    }
}
