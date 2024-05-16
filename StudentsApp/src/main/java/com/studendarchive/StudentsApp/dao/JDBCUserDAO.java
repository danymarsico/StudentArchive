package com.studendarchive.StudentsApp.dao;

import com.studendarchive.StudentsApp.model.User;
import com.studendarchive.StudentsApp.security.SecurePasswordEncoder;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class JDBCUserDAO implements UserDAO{

    private JdbcTemplate jdbcTemplate;
    private SecurePasswordEncoder passwordEncoder;

    public JDBCUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {

        if(username == null) throw new IllegalArgumentException("Username cannot be null");

        User selectedUser = null;
        String query = "SELECT * FROM users WHERE username = ?";

        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, username);
            if( rowSet.next()) {
                selectedUser = mapRowToUser(rowSet);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", ex);
        }

        return selectedUser;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findUserByUserId(int userId) {
        return null;
    }

    @Override
    public int findIdByUsername(String username) {
        return 0;
    }

    @Override
    public boolean createUser(String username, String password, Set<String> role) {
        String hashedPassword = passwordEncoder.encode(password);
        String sql = "INSERT INTO users (username, hashedPassword, role) VALUES (?, ?, ?)";
        int createdUser = jdbcTemplate.update(sql, username, hashedPassword,role.iterator().next());

        return createdUser == 1;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(Objects.requireNonNull(rs.getString("role")));

        return user;
    }
}
