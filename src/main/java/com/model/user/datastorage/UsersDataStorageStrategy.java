package com.model.user.datastorage;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import com.model.user.User;

public interface UsersDataStorageStrategy {

    boolean isPresent(String username) throws IOException;

    Optional<User> getUserByUsername(String username) throws IOException;

    Set<User> getAllUsers() throws IOException;

    void put(User user) throws IOException;

    Optional<User> remove(String username) throws IOException;
}
