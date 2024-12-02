package service.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String email;
    private String password;
    private String name;

    public AuthData getAuthData() {
        return new AuthData(email, password);
    }
}
