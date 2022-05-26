package me.kyeongho.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User API 요청 VO
 */
@Data
public class RequestUser {

    @Email
    @NotNull(message = "email must not be null")
    @Size(min = 2, message = "email must be greater than two")
    private String email;

    @NotBlank(message = "name must not be blank")
    @Size(min = 2, message = "name must be greater than two")
    private String name;

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "pwd must be greater than eight")
    private String pwd;
}
