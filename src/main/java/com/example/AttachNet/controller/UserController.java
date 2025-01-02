package com.example.AttachNet.controller;

import com.example.AttachNet.dto.LoginResponse;
import com.example.AttachNet.dto.loginRequest;
import com.example.AttachNet.dto.registrationRequest;
import com.example.AttachNet.model.User;
import com.example.AttachNet.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUsers();
    };

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public void newUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/user/new")
    public ResponseEntity<?> register(@Valid @RequestBody registrationRequest request) {
        if(userService.emailAlreadyUsed(request)) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Email is already used!");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        userService.doRegistration(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody loginRequest request) {
        try {
            LoginResponse response = userService.login(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(new LoginResponse(e.getMessage(), null));
        }
    }

}
