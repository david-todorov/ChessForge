package com.controllers.home;

import com.controllers.Controller;
import com.model.user.User;

import java.util.Optional;

public interface HomeController extends Controller {


    Optional<User> getFirstUser();


    Optional<User> getSecondUser();

}