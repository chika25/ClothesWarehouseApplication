package com.cpan252.clotheswarehouseproject.model.form;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.cpan252.clotheswarehouseproject.model.User;
@Data
public class RegistrationForm {
    private String username;
    private String password;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
