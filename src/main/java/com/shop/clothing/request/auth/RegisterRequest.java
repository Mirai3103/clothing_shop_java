package com.shop.clothing.request.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder

public class RegisterRequest {
    @NotEmpty(message = "Họ và tên đệm không được để trống")
    private String lastName;
    @NotEmpty(message = "Tên không được để trống")
    private String firstName;
    @NotEmpty(message = "Email không được để trống")
    private String email;
    @NotEmpty(message = "Mật khẩu không được để trống")
    @Length(min = 6,message = "Mật khẩu phải có ít nhất 6 ký tự")
    private String rawPassword;
}
