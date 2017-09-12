package com.cgm.db.repository.contract;

import java.util.List;

import com.cgm.domain.User;


public interface UserDataStore {

	int follow(String user);
    int unfollow(String user);
    List<User> listUsers();
    List<User> listFollowing();
    List<User> listFollowers();
}
