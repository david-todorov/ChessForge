package com.controllers.settings;

import com.controllers.Controller;
import com.other.settings.config.ApplicationStyleConfigStrategy;
import com.other.settings.config.PieceStyleConfigStrategy;

public interface SettingsController extends Controller {


    void setApplicationStyle(ApplicationStyleConfigStrategy style);


    ApplicationStyleConfigStrategy getCurrentApplicationStyle();


    void setPieceStyle(PieceStyleConfigStrategy style);


    PieceStyleConfigStrategy getCurrentPieceStyle();


    void setApplicationVolume(double volume);


    double getCurrentApplicationVolume();

}