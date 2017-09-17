package com.cgm.service;

import com.cgm.domain.User;

import javassist.tools.rmi.ObjectNotFoundException;

public interface UserService {

	User findByUsernameOrEmail(String usermaneOrEmail);

	User findById(Long id) throws ObjectNotFoundException;
}
