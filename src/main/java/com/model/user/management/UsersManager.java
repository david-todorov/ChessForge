package com.model.user.management;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import com.model.user.User;
import com.model.user.UserBuilderImpl;


public interface UsersManager {


    User GUEST = new UserBuilderImpl().username("GUEST").build();


    User COMPUTER = new UserBuilderImpl().username("COMPUTER").build();


    default Collection<User> getForbidUsers() {
        return Set.of(GUEST, COMPUTER);
    }

    Optional<User> login(String username, String password) throws IOException;


    Optional<User> register(String username, String password) throws IOException;


    Optional<User> delete(String username) throws IOException;


    Optional<User> put(User user) throws IOException;


    Set<User> getAllUsers() throws IOException;
}
