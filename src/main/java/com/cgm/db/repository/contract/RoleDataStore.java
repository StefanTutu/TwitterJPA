package com.cgm.db.repository.contract;

import java.util.List;

import com.cgm.domain.Role;

public interface RoleDataStore {

	void storeRole(Role role);
	List<Role> readRole();
}
