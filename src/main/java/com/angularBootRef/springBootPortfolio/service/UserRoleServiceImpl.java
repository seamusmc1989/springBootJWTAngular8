package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.UserRole;
import com.angularBootRef.springBootPortfolio.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return this.userRoleRepository.findByUserId(userId);
    }
}
