package com.example.AttachNet.service;

import com.example.AttachNet.model.User;
import com.example.AttachNet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    public List<User> getUsers() {
        return repo.findAll();
    };

    public User getUserById(Long id) {
        return repo.findById(id).orElse(new User());
    }

    public void addUser(User user) {
        repo.save(user);
    }

    public void updateUser(User user) {
        repo.save(user);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}
