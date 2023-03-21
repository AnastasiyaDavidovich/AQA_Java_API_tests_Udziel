package entities;
import lombok.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class UserCreated {
    private String email;
    private Integer id;
    private String username;

}
