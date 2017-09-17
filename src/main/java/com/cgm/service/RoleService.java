package com.cgm.service;

import java.util.List;

import com.cgm.domain.UserRoles;

import javassist.tools.rmi.ObjectNotFoundException;

public interface RoleService {

	List<UserRoles> findAll() throws ObjectNotFoundException;
}
