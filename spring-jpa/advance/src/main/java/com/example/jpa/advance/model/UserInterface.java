package com.example.jpa.advance.model;

public interface UserInterface {
    String getName();
    String getEmail();

    default String getFull() {
        return getName() + "@" + getEmail();
    }
}
