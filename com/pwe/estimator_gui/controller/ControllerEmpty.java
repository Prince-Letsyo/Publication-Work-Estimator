package com.pwe.estimator_gui.controller;

import com.pwe.estimator_gui.prompt.empty.Empty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEmpty implements Initializable {
    @FXML
    private Label msg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        msg.setText(Empty.msg);
    }

    @FXML
    void onMsg(ActionEvent event) {
        Empty.set = true;
        Empty.stage.close();
    }
}
