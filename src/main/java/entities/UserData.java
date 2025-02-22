package entities;

import lombok.*;

@Setter @Getter @NoArgsConstructor
public class UserData {
    private String password;
    private String username;
    private String email;

    public UserData(String password, String username, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }
}