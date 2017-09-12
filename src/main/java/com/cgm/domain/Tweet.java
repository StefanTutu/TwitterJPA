package com.cgm.domain;

import java.io.Serializable;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "tweet")
public class Tweet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "tweet")
	private String tweet;

	@Column(name = "user_username")
	private String userUsername;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loginuserid", nullable = false)
	private User userLogin;

	public Tweet() {
	}

	public Tweet(int id, String tweet, String user_username) {
		this.id = id;
		this.tweet = tweet;
		this.userUsername = user_username;
	}

	public Tweet(String tweet, String user_username) {
		this.tweet = tweet;
		this.userUsername = user_username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getUser_username() {
		return userUsername;
	}

	public void setUser_username(String user_username) {
		this.userUsername = user_username;
	}
}