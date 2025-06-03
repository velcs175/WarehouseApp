package com.micro.userservice.userservice.service;

import com.micro.userservice.userservice.dto.UserRequestDTO;
import com.micro.userservice.userservice.dto.UserResponseDTO;
import com.micro.userservice.userservice.model.Users;
import com.micro.userservice.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
        Users user = new Users();
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword()); // Later, hash it

        Users savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDTO getUserByEmail(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}
