package com.cgm.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "userlogin")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "passwordConfirm")
	private String passwordConfirm;
	
	@Column(name="status")
	private boolean status;

	@ManyToMany
	@JoinTable(name = "userlogin_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@ManyToMany
	@JoinTable(name = "follow", joinColumns = 
			@JoinColumn(name = "user_followed", referencedColumnName = "user_id", nullable = false) , inverseJoinColumns = 
					@JoinColumn(name = "follower", referencedColumnName = "user_id", nullable = false) )
	private Collection<User> followToCollection;
	
	@OneToMany(fetch=FetchType.LAZY, cascade =CascadeType.ALL)
	@JoinColumn(name="userLogin")
	public List<Tweet> Tweet;

	public Collection<User> getFollowToCollection() {
		return followToCollection;
	}

	public void setFollowToCollection(Collection<User> followToCollection) {
		this.followToCollection = followToCollection;
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

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
