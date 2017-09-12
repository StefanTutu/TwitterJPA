package com.cgm.repository;

import org.springframework.stereotype.Repository;

import com.cgm.domain.User;

@Repository
public class UserLoginDAO extends AbstractDAO<User> {

	protected UserLoginDAO() {
		super(User.class);
	}

}
