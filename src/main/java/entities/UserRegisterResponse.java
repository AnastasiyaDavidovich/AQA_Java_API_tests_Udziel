package entities;
import lombok.*;

@Setter @Getter @NoArgsConstructor
public class UserRegisterResponse {

    private Integer id;
    private String username;
    private String email;
    private Integer statusCod;

    public UserRegisterResponse(Integer id, String username, String email, Integer statusCod) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.statusCod = statusCod;
    }
}
