package com.angularBootRef.springBootPortfolio.repository;

import com.angularBootRef.springBootPortfolio.domain.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    @Query("select ur from UserRole ur join ur.user u where u.id = :userId")
    List<UserRole> findByUserId(@Param("userId") Long userId);
}
