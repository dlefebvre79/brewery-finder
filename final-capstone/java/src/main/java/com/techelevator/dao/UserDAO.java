package com.techelevator.dao;

import com.techelevator.model.User;

import java.time.LocalDate;
import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;

public interface UserDAO {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role,
			 String firstName, String lastName, LocalDate birthDate,
			 String email, String zip, boolean subscribed);

    User update(User userToUpdate);
    
    User updateRole(String username, String role);
    
    User changePassword(String username, String oldPassword, String newPassword) throws AuthenticationException;
    
    // TODO: activate/deactivate user
    //boolean updateUserStatus(User userToUpdate, boolean isActive);


}
