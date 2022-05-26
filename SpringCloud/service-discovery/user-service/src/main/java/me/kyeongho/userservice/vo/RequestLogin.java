package me.kyeongho.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {

    @Email
    @NotNull(message = "Email must be not null")
    @Size(min = 2, message = "Email Not be less than two characters")
    private String email;
    @Email
    @NotNull(message = "Password must be not null")
    @Size(min = 8, message = "Password Not be less than eight characters")
    private String password;
}
