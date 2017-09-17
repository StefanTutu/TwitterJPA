package com.cgm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgm.domain.UserRoles;

public interface RoleRepository extends JpaRepository<UserRoles, Integer> {

}
