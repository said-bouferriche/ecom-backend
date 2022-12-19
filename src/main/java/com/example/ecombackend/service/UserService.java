package com.example.ecombackend.service;

import com.example.ecombackend.dao.RoleDao;
import com.example.ecombackend.dao.UserDao;
import com.example.ecombackend.entity.Role;
import com.example.ecombackend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(User user){

        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);

        user.setUserPassword(getEncoderPassword(user.getUserPassword()));

        return userDao.save(user);
    }


    public void initRolesAndUsers() {
        Role adminRole = new Role("Admin", "This is the admin");
        roleDao.save(adminRole);

        Role userRole = new Role("User", "user role default");
        roleDao.save(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User adminUser = new User("admin", "said", "Bouf", getEncoderPassword("said@1234"),adminRoles);
        userDao.save(adminUser);

        User simpleUser = new User("user", "abcd", "jiji", getEncoderPassword("said@12345"),userRoles);
        userDao.save(simpleUser);
    }

    public String getEncoderPassword(String password){
        return passwordEncoder.encode(password);
    }
}
