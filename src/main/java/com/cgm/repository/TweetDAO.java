package com.cgm.repository;

import org.springframework.stereotype.Repository;

import com.cgm.domain.Tweet;
import com.cgm.domain.User;

@Repository
public class TweetDAO extends AbstractDAO<Tweet> {

	protected TweetDAO() {
		super(Tweet.class);
	}
}
