package com.example.marketplaceservice.dto;

import com.example.marketplaceservice.util.Constants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class SignUpDto {

    @NotEmpty(message = Constants.EMPTY_MESSAGE)
    @NotBlank(message = Constants.EMPTY_MESSAGE)
    @NotNull(message = Constants.EMPTY_MESSAGE)
    @Size(min = 3, message = "Логин должен состоять из более 3 символов")
    private String username;

    @NotEmpty(message = Constants.EMPTY_MESSAGE)
    @NotBlank(message = Constants.EMPTY_MESSAGE)
    @NotNull(message = Constants.EMPTY_MESSAGE)
    @Size(min = 5, message = "Пароль должен состоять из более 5 символов")
    private String password;

}
