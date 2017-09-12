package com.cgm.db.repository.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.cgm.domain.Role;

public class RoleResultSetExtractor implements ResultSetExtractor<Role> {

	@Override
	public Role extractData(ResultSet rs) throws SQLException {
		Role cashDrawer = new Role();
		cashDrawer.setName(rs.getString("name"));
		return cashDrawer;
	}
}
