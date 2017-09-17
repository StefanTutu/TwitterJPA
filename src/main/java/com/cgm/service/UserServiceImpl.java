package com.cgm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgm.domain.User;
import com.cgm.repository.UserRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findByUsernameOrEmail(String usernameOrEmail) {
		try {
			User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
			return user;
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// problem
		return null;

	}

	@Override
	@Transactional(readOnly = true)
	public User findById(Long id) throws ObjectNotFoundException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new ObjectNotFoundException("User not found");
		}
		return user;
	}

}
