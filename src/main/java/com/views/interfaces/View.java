package com.views.interfaces;

import com.controllers.Controller;

public interface View {

    Controller getController();

    void setController(Controller controller);

}
