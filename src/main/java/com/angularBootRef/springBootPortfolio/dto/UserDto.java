package com.angularBootRef.springBootPortfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements IObjectDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
}

