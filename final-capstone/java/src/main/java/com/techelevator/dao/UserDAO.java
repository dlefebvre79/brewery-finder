package com.techelevator.dao;

import com.techelevator.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role,
			 String firstName, String lastName, LocalDate birthDate,
			 String email, String zip, boolean subscribed);
}
