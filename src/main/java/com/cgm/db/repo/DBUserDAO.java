package com.cgm.db.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.cgm.db.repository.contract.UserDataStore;
import com.cgm.domain.User;

@Repository("dbUserDAO")
@EnableTransactionManagement
public class DBUserDAO implements UserDataStore {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int follow(String user) {
		String username = getUsername();
		String sql = "INSERT INTO follow (user_followed, follower)" + " VALUES (?, ?)";
		try {
			return jdbcTemplate.update(sql, user, username);
		} catch (DataIntegrityViolationException e) {
			return 0;
		}
	}

	@Override
	public int unfollow(String user) {
		String username = getUsername();
		String sql = "DELETE FROM follow WHERE user_followed = ? AND follower = ?";
		try {
			return jdbcTemplate.update(sql, user, username);
		} catch (DataIntegrityViolationException e) {
			return 0;
		}
	}

	public List<User> listUsers() {
		final String username = getUsername();
		String sql = "SELECT * FROM users WHERE NOT username = '" + username + "' ORDER BY username ASC";
		List<User> listUsers = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setUsername(rs.getString("username"));

				String sql_aux = "SELECT EXISTS(SELECT 1 FROM follows WHERE user_followed = '" + user.getUsername()
						+ "' AND follower = '" + username + "' LIMIT 1)";
				int result = jdbcTemplate.queryForObject(sql_aux, Integer.class);
				if (result == 1) {
					user.setStatus(true);
				} else {
					user.setStatus(false);
				}

				return user;
			}
		});
		return listUsers;
	}

	@Override
	public List<User> listFollowing() {
		String username = getUsername();
		String sql = "SELECT * FROM follow WHERE follower = '" + username + "'ORDER BY user_followed ASC";
		List<User> listFollowing = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User follow = new User();

				follow.setUsername(rs.getString("user_followed"));

				return follow;
			}
		});

		return listFollowing;
	}

	@Override
	public List<User> listFollowers() {
		String username = getUsername();
		String sql = "SELECT * FROM follow WHERE user_followed = '" + username + "'ORDER BY follower ASC";
		List<User> listFollowers = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User follow = new User();

				follow.setUsername(rs.getString("follower"));

				return follow;
			}
		});

		return listFollowers;
	}

	public String getUsername() {
		String aux;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			aux = ((UserDetails) principal).getUsername();
		} else {
			aux = principal.toString();
		}
		return aux;
	}
}
