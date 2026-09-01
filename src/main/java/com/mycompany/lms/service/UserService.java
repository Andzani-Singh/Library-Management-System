package com.mycompany.lms.service;

import com.mycompany.lms.dao.UserDO;
import com.mycompany.lms.model.User;
import java.util.List;

public class UserService {
    private UserDO userDO = new UserDO();
    
    public User saveUser(User user) {
        return userDO.save(user);
    }
    
    public User getUserById(Long id) {
        return userDO.findById(id);
    }
    
    public User getUserByUsername(String username) {
        return userDO.findByUsername(username);
    }
    
    public List<User> getAllUsers() {
        return userDO.findAll();
    }
    
    public void deleteUser(Long id) {
        userDO.delete(id);
    }
}
