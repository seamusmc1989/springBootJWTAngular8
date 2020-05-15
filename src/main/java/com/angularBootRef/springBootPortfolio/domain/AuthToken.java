package com.angularBootRef.springBootPortfolio.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class AuthToken {

    private Long userId;
    private String token;
    private List<UserRoleEnum> roles;
}
