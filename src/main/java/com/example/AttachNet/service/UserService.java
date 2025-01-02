package com.example.AttachNet.service;

import com.example.AttachNet.dto.LoginResponse;
import com.example.AttachNet.dto.loginRequest;
import com.example.AttachNet.dto.registrationRequest;
import com.example.AttachNet.model.User;
import com.example.AttachNet.repository.UserRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepo repo;
    public List<User> getUsers() {
        return repo.findAll();
    };

    public User getUserById(UUID id) {
        return repo.findById(id).orElse(new User());
    }

    public LoginResponse login(loginRequest request) {
        Optional<User> userOpt = repo.findByEmail(request.getEmail());

        if (userOpt.isEmpty() || !userOpt.get().getUserPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        // Generate a token (replace this with actual token generation logic, e.g., JWT)
        String token = "dummy-token";

        return new LoginResponse("Login successful", token);
    }

    public void addUser(User user) {
        repo.save(user);
    }

    public void updateUser(User user) {
        repo.save(user);
    }

    public void deleteUser(UUID id) {
        repo.deleteById(id);
    }

    // Check if the email already exists
    public boolean emailAlreadyUsed(registrationRequest request) {
        return repo.findByEmail(request.getEmail()).isPresent();
    }
    public void doRegistration(registrationRequest request) {
        User newUser = request.toUser();
        repo.save(newUser);
    }
}
