package com.controllers.login;

import com.controllers.Controller;
import com.model.user.validators.StringValidatorImpl.ValidationResult;

public interface LoginController extends Controller {


    boolean login(String username, String password);


    boolean register(String username, String password);


    ValidationResult validatePassword(String password);


    ValidationResult validateUsername(String username);


    void loginAsGuest();

}

