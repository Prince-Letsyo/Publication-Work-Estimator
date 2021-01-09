package com.pwe.estimator_gui.controller;

import com.pwe.estimator_gui.prompt.empty.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDialog implements Initializable {
    @FXML
    private Label msg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        msg.setText(Dialog.msg);
    }

    @FXML
    void onYes(ActionEvent event) {
        Dialog.set = true;
        Dialog.stage.close();
    }

    @FXML
    void onNo(ActionEvent event) {
        Dialog.set = false;
        Dialog.stage.close();
    }
}
