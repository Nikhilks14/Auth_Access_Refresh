package com.Security.JwtAccessRefresh.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfoDto {

    private String username;
    private String lastname;
    private Long phoneNumber;
    private String email;
    private String password;


}
