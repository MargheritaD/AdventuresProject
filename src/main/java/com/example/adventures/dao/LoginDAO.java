package com.example.adventures.dao;

import com.example.adventures.exception.UserNotFoundException;
import com.example.adventures.model.UserProfile;

public interface LoginDAO {
    UserProfile checkUser(String username, String password) throws UserNotFoundException;
}
