package com.cgm.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private boolean status;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "user_followed", referencedColumnName = "id", nullable = false), 
		inverseJoinColumns = @JoinColumn(name = "follower", referencedColumnName = "id", nullable = false), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "user_followed", "follower" }) })
	private Collection<User> follows;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	public List<Tweet> Tweet;

	public Collection<User> getFollows() {
		return follows;
	}

	public void setFollows(Collection<User> follows) {
		this.follows = follows;
	}

	public List<Tweet> getTweet() {
		return Tweet;
	}

	public void setTweet(List<Tweet> tweet) {
		Tweet = tweet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
