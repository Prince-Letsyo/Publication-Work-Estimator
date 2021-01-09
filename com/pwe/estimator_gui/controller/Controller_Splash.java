package com.pwe.estimator_gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Splash implements Initializable {


    @FXML
    private HBox loading;
    public static HBox load;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load = loading;
    }
}
