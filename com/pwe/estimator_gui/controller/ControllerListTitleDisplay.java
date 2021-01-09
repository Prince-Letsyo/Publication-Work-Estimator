package com.pwe.estimator_gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerListTitleDisplay implements Initializable {
    @FXML
    private Label listTitle;
    private static String string;

    public static void setString(String string) {
        ControllerListTitleDisplay.string = string;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listTitle.setText(string);
    }
}
