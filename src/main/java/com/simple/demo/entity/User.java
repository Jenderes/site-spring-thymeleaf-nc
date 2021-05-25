package com.simple.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, message = "Минимальная длина строки 2 символа")
    private String firstName;

    @NotEmpty(message = "Фамилия не может быть пустой")
    @Size(min = 2, message = "Минимальная длина строки 2 символа")
    private String lastName;

    @NotEmpty(message = "Отчество не может быть пустым")
    @Size(min = 2, message = "Минимальная длина строки 2 символа")
    private String middleName;

    @NotNull(message = "Возраст не может быть пустой")
    @Range(min = 18, max = 110, message = "недопустимый возраст")
    private Integer age;

    @NotNull(message = "Зараплата не может быть пустой")
    @Range(min = 500, max = 1_000_000, message = "недопустимый зарплата")
    private Integer salary;

    @NotEmpty(message = "email не может быть пустым")
    @Email
    private String email;

    @NotEmpty(message = "Адресс не может быть пустым")
    private String address;

}
