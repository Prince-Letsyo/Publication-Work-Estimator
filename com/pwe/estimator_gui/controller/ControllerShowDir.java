package com.pwe.estimator_gui.controller;

import com.pwe.estimator_gui.prompt.empty.ShowDir;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerShowDir implements Initializable {
    @FXML
    private Label  path_;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        path_.setText(ShowDir.path);
    }

    @FXML
    void onMsg(ActionEvent event) {
        ShowDir.set = true;
        ShowDir.stage.close();
    }
}
