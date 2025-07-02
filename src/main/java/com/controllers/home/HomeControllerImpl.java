package com.controllers.home;

import com.controllers.BasicController;
import com.model.user.User;

import java.util.Optional;

public final class HomeControllerImpl extends BasicController implements HomeController {


    @Override
    public Optional<User> getFirstUser() {
        return this.getModel().getFirstUser();
    }


    @Override
    public Optional<User> getSecondUser() {
        return this.getModel().getSecondUser();
    }

}
