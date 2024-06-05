package com.assignment.CashRich.Services;

import com.assignment.CashRich.models.User;


public interface UserService {
	User signUp(User user);
    User login(String username, String password);
    User updateUser(User user);
}
