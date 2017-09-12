package com.cgm.db.repository.contract;

import java.util.List;

import com.cgm.domain.Tweet;
public interface TweetDataStore {

	int add(Tweet tweet);
    List<Tweet> searchTweets(String search);
    List<Tweet> searchUserTweets(String username, String search);
}
