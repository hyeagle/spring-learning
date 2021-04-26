package com.example.jpa.advance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
    private String name;

    private String email;
}
