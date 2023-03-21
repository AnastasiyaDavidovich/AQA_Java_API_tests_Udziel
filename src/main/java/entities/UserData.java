package entities;

import lombok.*;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class UserData {
    private String password;
    private String username;
    private String email;

}