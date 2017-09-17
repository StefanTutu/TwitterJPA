package com.cgm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgm.domain.UserRoles;
import com.cgm.repository.RoleRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional(readOnly = true)
	public List<UserRoles> findAll() throws ObjectNotFoundException {
		
			List<UserRoles> roles = roleRepository.findAll();
			if(roles.isEmpty()){
				throw new ObjectNotFoundException("Role list is empty");
			}
			return roles;

	}

}
