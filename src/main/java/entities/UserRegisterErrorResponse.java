package entities;
import lombok.*;

@Setter @Getter @NoArgsConstructor
public class UserRegisterErrorResponse {

    private String email;
    private Integer statusCode;

    public UserRegisterErrorResponse(String email, Integer statusCode) {
        this.email = email;
        this.statusCode = statusCode;
    }
}
