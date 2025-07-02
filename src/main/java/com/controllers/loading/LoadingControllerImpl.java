package com.controllers.loading;

import com.controllers.BasicController;
import com.other.DirectoryConfigurations;

import java.io.IOException;

public final class LoadingControllerImpl extends BasicController implements LoadingController {


    @Override
    public void load() {
        try {
            DirectoryConfigurations.validateUsersDataFile();
            DirectoryConfigurations.validateHistoryDirectory();
            DirectoryConfigurations.validateResourcesDirectory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
