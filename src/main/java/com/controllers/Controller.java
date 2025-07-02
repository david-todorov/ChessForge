package com.controllers;

import com.model.Model;
import com.views.interfaces.View;

public interface Controller {


    View getView();


    void setView(View view);


    Model getModel();


    void setModel(Model model);
}
