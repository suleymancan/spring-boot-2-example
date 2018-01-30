package com.suleymancan.myblog.repository;

import com.suleymancan.myblog.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    //KeyboardByField
    public Optional<User> findByUsername(String username);
}
