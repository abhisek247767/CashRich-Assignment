package com.assignment.CashRich.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.CashRich.Repository.UserRepository;
import com.assignment.CashRich.Services.UserService;
import com.assignment.CashRich.models.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signUp(User user) {
        // Implement validation for username and password
        if (!isValidUsername(user.getUsername()) || !isValidPassword(user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Save user
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        // Retrieve the user from the database by username
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Verify password
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }

        // Return the authenticated user
        return user;
    }
    
    @Override
    public User updateUser(User user) {
        // Update user data
        return userRepository.save(user);
    }


    // Validate username format
    private boolean isValidUsername(String username) {
        // Username should contain only characters and digits with length 4 to 15
        return username.matches("[a-zA-Z0-9]{4,15}");
    }

    // Validate password format
    private boolean isValidPassword(String password) {
        // Password should be 8 to 15 characters with at least one uppercase, one lowercase,
        // one digit, and one special character
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$");
    }
}
