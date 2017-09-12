package com.cgm.db.repository.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cgm.domain.Role;

@SuppressWarnings("rawtypes")
public class RoleRowMapper implements RowMapper {

	@Override
	public Role mapRow(ResultSet rs, int line) throws SQLException {
		RoleResultSetExtractor extractor = new RoleResultSetExtractor();
		return extractor.extractData(rs);
	}

}
