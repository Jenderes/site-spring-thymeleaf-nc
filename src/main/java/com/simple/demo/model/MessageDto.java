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

    @NotNull(message = "Фамилия не может быть пустым")
    @Size(min = 2, message = "Минимальная длина строки 2 символова")
    String lastName;

    @NotNull(message = "Имя не может быть пустым")
    @Size(min = 2, message = "Минимальная длина строки 2 символова")
    String firstName;

    @NotNull(message = "Тема не может быть пустой")
    @Size(min = 5, message = "Минимальная длина строки 5 символов")
    String theme;

    @NotNull(message = "Текст сообщения не может быть пустым")
    @Size(min = 5, message = "Минимальная длина строки 10 символов")
    String textMessage;

    String email;
}
