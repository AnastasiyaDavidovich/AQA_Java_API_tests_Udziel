package entities;
import lombok.*;

@Setter @Getter @NoArgsConstructor
public class UserRegisterResponse {

    private String password;
    private String username;
    private String email;
    private Integer statusCod;

    public UserRegisterResponse(String password, String username, String email, Integer statusCod) {
        this.password = password;
        this.username = username;
        this.email = email;
        this.statusCod = statusCod;
    }
}
