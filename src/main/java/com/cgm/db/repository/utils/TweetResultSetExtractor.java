package com.cgm.db.repository.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.cgm.domain.Tweet;


public class TweetResultSetExtractor implements ResultSetExtractor<Tweet> {

	@Override
	public Tweet extractData(ResultSet rs) throws SQLException {
		Tweet cashDrawer = new Tweet();
		cashDrawer.setTweet(rs.getString("tweet"));
		return cashDrawer;
	}
}
