package com.studendarchive.StudentsApp.dao;

import com.studendarchive.StudentsApp.model.User;

import java.util.List;

public interface UserDAO {

    User findByUsername(String username);
    List<User> findAll();
    User findUserByUserId(int userId);
    int findIdByUsernam(String username);
}
