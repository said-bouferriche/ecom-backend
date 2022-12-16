package com.example.ecombackend.dao;

import com.example.ecombackend.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, String> {
}
