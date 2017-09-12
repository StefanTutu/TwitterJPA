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

	@Column(name = "username")
	private String username;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	private User user;

	public Tweet() {
	}

	public Tweet(int id, String tweet, String username) {
		this.id = id;
		this.tweet = tweet;
		this.username = username;
	}

	public Tweet(String tweet, String username) {
		this.tweet = tweet;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}