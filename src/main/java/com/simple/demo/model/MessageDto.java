package com.simple.demo.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDto {

    @NotNull(message = "Email не может быть пустым")
    @Email(message = "не правильный формат почты")
    String email;

    @NotNull(message = "Email не может быть пустым")
    @Size(min = 5, message = "{Минимальная длина строки 5 символов}")
    String theme;

    @NotNull(message = "Email не может быть пустым")
    @Size(min = 5, message = "{Минимальная длина строки 10 символов}")
    String textMessage;
}
