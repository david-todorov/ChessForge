package com.controllers.login;

import com.controllers.BasicController;
import com.model.user.User;
import com.model.user.management.UsersManager;
import com.model.user.validators.StringValidatorImpl;
import com.model.user.validators.StringValidators;

import java.io.IOException;
import java.util.function.Function;

public final class LoginControllerImpl extends BasicController implements LoginController {

    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 32;
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 16;

    private final Function<String, StringValidatorImpl.ValidationResult> passwordValidator;
    private final Function<String, StringValidatorImpl.ValidationResult> usernameValidator;
    private final boolean firstUser;

    public LoginControllerImpl() {
        this(true);
    }

    public LoginControllerImpl(final boolean firstUser) {

        this.firstUser = firstUser;
        this.passwordValidator = new StringValidatorImpl().add(StringValidators.NOT_EMPTY)
                .add(StringValidators.LONGER_THAN.apply(MIN_PASSWORD_LENGTH))
                .add(StringValidators.SHORTER_THAN.apply(MAX_PASSWORD_LENGTH)).create();

        this.usernameValidator = new StringValidatorImpl().add(StringValidators.NOT_EMPTY)
                .add(StringValidators.LONGER_THAN.apply(MIN_USERNAME_LENGTH))
                .add(StringValidators.SHORTER_THAN.apply(MAX_USERNAME_LENGTH))
                .add(StringValidators.DIFFERENT_FROM.apply(UsersManager.GUEST.getUsername())).create();
    }


    private boolean loginUser(final User user) {
        if (this.firstUser) {
            this.getModel().setFirstUser(user);
            if (this.getModel().getSecondUser().isEmpty()) {
                this.getModel().setSecondUser(UsersManager.GUEST);
            }
            return true;
        } else {
            if (!user.equals(this.getModel().getFirstUser().get())) {
                this.getModel().setSecondUser(user);
                return true;
            } else {
                return false;
            }

        }

    }


    @Override
    public boolean login(final String username, final String password) {
        return false;
    }


    @Override
    public boolean register(final String username, final String password) {

        return false;
    }


    @Override
    public void loginAsGuest() {
        this.loginUser(UsersManager.GUEST);
    }


    @Override
    public StringValidatorImpl.ValidationResult validatePassword(final String password) {
        return this.passwordValidator.apply(password);
    }


    @Override
    public StringValidatorImpl.ValidationResult validateUsername(final String username) {
        return this.usernameValidator.apply(username);
    }

}
