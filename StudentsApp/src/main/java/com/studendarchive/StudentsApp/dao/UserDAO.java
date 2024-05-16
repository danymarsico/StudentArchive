package com.studendarchive.StudentsApp.dao;

import com.studendarchive.StudentsApp.model.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {

    User findByUsername(String username);
    List<User> findAll();
    User findUserByUserId(int userId);
    int findIdByUsername(String username);
    boolean createUser(String username, String password, Set<String> role);
}
