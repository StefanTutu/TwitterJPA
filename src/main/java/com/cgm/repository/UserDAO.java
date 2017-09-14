package com.cgm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.cgm.domain.User;
import com.fasterxml.jackson.annotation.JsonSetter;

@Repository
public class UserDAO extends AbstractDAO<User> {

	protected UserDAO() {
		super(User.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional
	public User findByName(final String entityName) {

		try {
			CriteriaBuilder cb = em().getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);
			Root root = cq.from(User.class);
			cq.select(root);
			cq.where(cb.equal(root.get("username"), entityName));
			TypedQuery<User> q = em().createQuery(cq);
			User result = q.getSingleResult();
			return result;
		} catch (NoResultException ex) {

		}
		return null;
	}

	@Transactional
	public void insertUser(User user) {
		super.update(user);
	}

	public String getUsername() {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

}
