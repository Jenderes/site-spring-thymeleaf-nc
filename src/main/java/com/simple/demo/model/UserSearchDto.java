package com.simple.demo.model;

import com.simple.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSearchDto {

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    private String address;

    private Integer salary;

    private Integer age;

    private String date;

    private String userAgent;

    public static UserSearchDto mapUserSearch(User user, String userAgent, String date){
        return new UserSearchDto(
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getEmail(),
                user.getAddress(),
                user.getSalary(),
                user.getAge(),
                date,
                userAgent
        );
    }
}
