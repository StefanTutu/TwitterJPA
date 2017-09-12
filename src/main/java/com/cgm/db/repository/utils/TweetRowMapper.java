package com.cgm.db.repository.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cgm.domain.Tweet;

@SuppressWarnings("rawtypes")
public class TweetRowMapper implements RowMapper {

	@Override
	public Tweet mapRow(ResultSet rs, int line) throws SQLException {
		TweetResultSetExtractor extractor = new TweetResultSetExtractor();
		return extractor.extractData(rs);
	}
}
