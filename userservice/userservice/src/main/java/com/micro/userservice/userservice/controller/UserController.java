package com.micro.userservice.userservice.controller;

import com.micro.userservice.userservice.dto.UserRequestDTO;
import com.micro.userservice.userservice.dto.UserResponseDTO;
import com.micro.userservice.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO response = userService.registerUser(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String email) {
        UserResponseDTO response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }
}
