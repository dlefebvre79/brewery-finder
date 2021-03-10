package com.techelevator.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.UserDAO;
import com.techelevator.model.User;
import com.techelevator.model.exceptions.UserNotLegalAgeException;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/user")
public class UserController
{
	private static final int LEGAL_AGE = 21;
	@Autowired
	private UserDAO userDAO;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public User update(@Valid @RequestBody User userToUpdate)
	{
		User user = null;
		try
		{
			if (isLegalAge(userToUpdate.getBirthDate()))
			{
				userDAO.update(userToUpdate);
			}
			else
			{
				throw new UserNotLegalAgeException();
			}
			user = userDAO.findByUsername(userToUpdate.getUsername());
		}
		catch (UsernameNotFoundException e)
		{
			
		}
		return user;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/role", method = RequestMethod.PUT)
	public User updateRole(@Valid @RequestBody User userToUpdate, @RequestParam String role)
	{
		User user = null;
		try
		{
			userDAO.updateRole(userToUpdate.getUsername(), role);
			user = userDAO.findByUsername(userToUpdate.getUsername());
		}
		catch (UsernameNotFoundException e)
		{
			user = userToUpdate;
		}
		return user;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public User changePassword(@Valid @RequestBody User userToUpdate, @RequestParam String oldPassword, @RequestParam String newPassword)
	{
		User user = null;
		try
		{
			user = userDAO.changePassword(userToUpdate.getUsername(), oldPassword, newPassword);
		}
		catch (Exception e)
		{
			user = userToUpdate;
		}
		return user;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAll()
	{
		return userDAO.findAll();
	}
	
	
	private boolean isLegalAge(LocalDate birthDate)
	{
		LocalDate today = LocalDate.now();
		int interval = today.compareTo(birthDate);
		return interval >= LEGAL_AGE;

	}
	

}
