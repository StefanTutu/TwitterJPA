package com.cgm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.db.repository.contract.RoleDataStore;
import com.cgm.domain.Role;
import com.cgm.dto.ServiceResponse;
import com.cgm.repository.RoleDAO;

@RestController
public class RoleController {

	@Autowired
	RoleDAO roleDAO; // JPA

	@Autowired
	RoleDataStore roleDataStore; // JDBC

	public final static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Role getRole(@PathVariable Long id) throws Exception {
		logger.info("Called get Role (" + id + ") service!");
		return roleDAO.findById(id);
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ServiceResponse CreateRole(@RequestBody Role role) {
		logger.info("Called create ROLE JPA service!");
		roleDAO.save(role);
		return new ServiceResponse();
	}

	@RequestMapping(value = "/role_db", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ServiceResponse createArtistDB(@RequestBody Role role) {
		logger.info("Called create role JDBC service!");
		roleDataStore.storeRole(role);
		return new ServiceResponse();
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ServiceResponse updateRole(@RequestBody Role role, @PathVariable Long id) {
		logger.info("Called update Role service!");
		role.setId(id);
		roleDAO.update(role);
		return new ServiceResponse();
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
	public ServiceResponse deleteRole(@PathVariable Long id) {
		logger.info("Called delete Role (" + id + ")!");
		roleDAO.delete(id);
		return new ServiceResponse();
	}
}
