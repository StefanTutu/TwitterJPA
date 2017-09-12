package com.cgm.repository;

import org.springframework.stereotype.Repository;

import com.cgm.domain.Role;

@Repository
public class RoleDAO extends AbstractDAO<Role> {

	protected RoleDAO() {
		super(Role.class);
	}
}
