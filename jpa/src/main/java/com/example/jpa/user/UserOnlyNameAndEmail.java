package com.example.jpa.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserOnlyNameAndEmail {
    private String name;

    private String email;
}
