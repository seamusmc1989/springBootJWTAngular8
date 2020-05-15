package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.UserRole;

import java.util.List;

public interface UserRoleService {
    List<UserRole> findByUserId(Long userId);
}
