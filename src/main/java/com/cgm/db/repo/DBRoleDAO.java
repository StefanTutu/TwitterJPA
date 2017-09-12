package com.cgm.db.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cgm.db.repository.contract.RoleDataStore;
import com.cgm.db.repository.utils.RoleRowMapper;
import com.cgm.domain.Role;

@Repository("dbRoleDAO")
@EnableTransactionManagement
public class DBRoleDAO implements RoleDataStore {
	public final static Logger logger = LoggerFactory.getLogger(DBRoleDAO.class);

	private SimpleJdbcInsert insertRole;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		insertRole = new SimpleJdbcInsert(dataSource).withTableName("role").usingGeneratedKeyColumns("id");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	@Override
	public void storeRole(Role role) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", role.getName());
		try {
			insertRole.execute(parameters);
			logger.info("Called simple JDBC inserter !");
		} catch (DataAccessException dae) {
			logger.error("A database eroor occured: " + dae.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> readRole() {
		StringBuilder selectStatement = new StringBuilder();
		selectStatement.append("SELECT * FROM ").append("role");
		List<Role> roles = null;
		try {
			roles = jdbcTemplate.query(selectStatement.toString(), new Object[] {}, new RoleRowMapper());
			logger.error("Read list of role: " + roles.size());
		} catch (DataAccessException dae) {
			logger.error("A database error occured: " + dae.getMessage());
		}
		return roles;
	}

}


