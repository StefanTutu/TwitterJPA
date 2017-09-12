package com.cgm.db.repository.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.cgm.domain.User;

public class UserResultSetExtractor implements ResultSetExtractor<User>{

	@Override
	public User extractData(ResultSet rs) throws SQLException, DataAccessException {
		User cashDrawer = new User();
		cashDrawer.setUsername(rs.getString("username"));
		return cashDrawer;
	}

}