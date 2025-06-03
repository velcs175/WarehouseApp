package com.micro.userservice.userservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
}
