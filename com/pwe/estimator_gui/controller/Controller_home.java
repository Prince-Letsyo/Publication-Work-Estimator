package com.pwe.estimator_gui.controller;

import com.pwe.estimator_gui.controller.core.ControlledScreen;
import com.pwe.estimator_gui.prompt.settings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import static com.pwe.estimator_gui.preloader.MyPreloader.*;

public class Controller_home implements ControlledScreen {
    ScreensController myController;

    @FXML
    void bookWork(ActionEvent event) {
        boolean confirm;
        confirm = Settings.show();
        if (confirm) {
            screensController.loadScreen(screen3ID, screen3File);
            myController.setScreen(screen3ID);
        }
    }

    @FXML
    void paperWork(ActionEvent event) {
        boolean confirm;
        confirm = Settings.show();
        if (confirm) {
            screensController.loadScreen(screen2ID, screen2File);
            myController.setScreen(screen2ID);
        }
    }

    @FXML
    void periodWork(ActionEvent event) {
        boolean confirm;
        confirm = Settings.show();
        if (confirm) {
            screensController.loadScreen(screen4ID, screen4File);
            myController.setScreen(screen4ID);
        }
    }


    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
