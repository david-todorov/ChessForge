package com.model.user;

public interface UserBuilder {

    UserBuilder username(String username);

    UserBuilder hashedPassword(String hashedPassword);

    UserBuilder winCount(int count);

    UserBuilder drawCount(int count);

    UserBuilder lostCount(int count);

    User build();
}
