package com.techelevator.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class UserSqlDAO implements UserDAO
{

	private JdbcTemplate jdbcTemplate;

	public UserSqlDAO(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int findIdByUsername(String username)
	{
		return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
	}

	@Override
	public User getUserById(Long userId)
	{
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next())
		{
			return mapRowToUser(results);
		}
		else
		{
			throw new RuntimeException("userId " + userId + " was not found.");
		}
	}

	@Override
	public List<User> findAll()
	{
		List<User> users = new ArrayList<>();
		String sql = "select * from users";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next())
		{
			User user = mapRowToUser(results);
			users.add(user);
		}

		return users;
	}

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException
	{
		for (User user : this.findAll())
		{
			if (user.getUsername().toLowerCase().equals(username.toLowerCase()))
			{
				return user;
			}
		}
		throw new UsernameNotFoundException("User " + username + " was not found.");
	}

	@Override
	public boolean create(String username, String password, String role,
			String firstName, String lastName, LocalDate birthDate,
			String email, String zip, boolean subscribed)
	{
		boolean userCreated = false;

		// create user
		String insertUser = "insert into users (username,password_hash,role,"
				+ "first_name,last_name,date_of_birth,email,zip_code,is_subscribed )"
				+ " values(?,?,?,?,?,?,?,?,?)";
		String password_hash = new BCryptPasswordEncoder().encode(password);
		String ssRole = "ROLE_" + role.toUpperCase();

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		String id_column = "user_id";
		userCreated = jdbcTemplate.update(con ->
		{
			PreparedStatement ps = con.prepareStatement(insertUser, new String[] { id_column });
			ps.setString(1, username);
			ps.setString(2, password_hash);
			ps.setString(3, ssRole);
			ps.setString(4, firstName);
			ps.setString(5, lastName);
			ps.setDate(6, Date.valueOf(birthDate));
			ps.setString(7, email);
			ps.setString(8, zip);
			ps.setBoolean(9, subscribed);
			return ps;
		}, keyHolder) == 1;
		int newUserId = (int) keyHolder.getKeys().get(id_column);

		return userCreated;
	}

	@Override
	public User update(User userToUpdate)
	{
		User user = null;
		
		String sql = "UPDATE user "
				   + "SET username = ?, "
				   + "first_name = ?, "
				   + "last_name = ?, "
				   + "date_of_birth = ?, "
				   + "email = ?, "
				   + "is_subscribed = ?, "
				   + "zip_code = ? "
				   + "WHERE user_id = ?;";
		jdbcTemplate.update(sql,
							userToUpdate.getUsername(),
							userToUpdate.getFirstName(),
							userToUpdate.getLastName(),
							userToUpdate.getBirthDate(),
							userToUpdate.getEmail(),
							userToUpdate.isSubscribed(),
							userToUpdate.getZip(),
							userToUpdate.getId());

		try
		{
			user = findByUsername(userToUpdate.getUsername());
		}
		catch (UsernameNotFoundException e)
		{
			
		}
		return user;
	}

	@Override
	public User updateRole(User userToUpdate, String role)
	{
		User user = null;
		String ssRole = "ROLE_" + role.toUpperCase();
		
		String sql = "UPDATE user "
				   + "SET role = ?, "
				   + "WHERE user_id = ?;";
		jdbcTemplate.update(sql,
							ssRole,
							userToUpdate.getId());

		try
		{
			user = findByUsername(userToUpdate.getUsername());
		}
		catch (UsernameNotFoundException e)
		{
			
		}
		return user;
	}
	
	@Override
	public User changePassword(User userToUpdate, String oldPassword, String newPassword) throws AuthenticationException
	{
		User user = null;
		try
		{
			user = findByUsername(userToUpdate.getUsername());
		}
		catch (UsernameNotFoundException e)
		{
			
		}
		if(oldPassword.matches(userToUpdate.getPassword()))
		{
			String sql = "UPDATE user "
					   + "SET password_hash = ?, "
					   + "WHERE user_id = ?;";
			jdbcTemplate.update(sql,
								newPassword,
								userToUpdate.getId());
			user = findByUsername(userToUpdate.getUsername());

		}
		else
		{
			throw new AuthenticationException("Incorrect password");
		}
		return user;
	}

	
	private User mapRowToUser(SqlRowSet rs)
	{
		User user = new User();
		user.setId(rs.getLong("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password_hash"));
		user.setAuthorities(rs.getString("role"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setBirthDate(rs.getDate("date_of_birth").toLocalDate());
		user.setEmail(rs.getString("email"));
		user.setZip(rs.getString("zip_code"));
		user.setSubscribed(rs.getBoolean("is_subscribed"));
		// TODO: get activation status from DB
		user.setActivated(true);
		return user;
	}
}
