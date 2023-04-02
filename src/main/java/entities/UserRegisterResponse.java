package entities;
import lombok.*;

@Setter @Getter @NoArgsConstructor
public class UserRegisterResponse {

    private Integer id;
    private String username;
    private String email;
    private Integer statusCode;
    private String password;

    public UserRegisterResponse(Integer id, String username, String email, Integer statusCode, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.statusCode = statusCode;
        this.password = password;
    }
}
