package com.cgm.repository;

import org.springframework.stereotype.Repository;

import com.cgm.domain.User;

@Repository
public class UserDAO extends AbstractDAO<User> {

	protected UserDAO() {
		super(User.class);
	}

}
