package entities;
import lombok.*;

@Setter @Getter @NoArgsConstructor
public class UserRegisterErrorResponse {

    private String email;
    private Integer statusCode;
    private String password;
    private String name;

    public UserRegisterErrorResponse(String email, Integer statusCode, String password, String name) {
        this.email = email;
        this.statusCode = statusCode;
        this.password = password;
        this.name = name;
    }
}
