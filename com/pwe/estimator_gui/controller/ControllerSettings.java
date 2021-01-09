package com.pwe.estimator_gui.controller;

import com.pwe.estimator_gui.prompt.settings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControllerSettings {
    @FXML
    private RadioMenuItem In, Pi, Ce, Mi;
    @FXML
    private TextField proName;
    @FXML
    private MenuButton unit;
    @FXML
    private Button setB;

    @FXML
    void unitChoose(ActionEvent event) {
        if (In.isSelected()) Settings.unitMeasurement = In.getText();
        else if (Pi.isSelected()) Settings.unitMeasurement = Pi.getText();
        else if (Ce.isSelected()) Settings.unitMeasurement = Ce.getText();
        else if (Mi.isSelected()) Settings.unitMeasurement = Mi.getText();

        unit.setText(Settings.unitMeasurement);
        setB.setDisable(false);
    }

    @FXML
    void OnSetting(ActionEvent event) {
        Settings.project = proName.getText();
        if (!Settings.project.equals("") && !Settings.unitMeasurement.equals("")) Settings.set = true;
        Settings.stage.close();
    }

    @FXML
    void onEmpty(KeyEvent event) {
        if (!proName.getText().equals("")) {
            proName.setStyle("-fx-border-width: none");
            if (!unit.getText().equals("Select unit")) setB.setDisable(false);
        } else {
            proName.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-border-radius: 2px");
            setB.setDisable(true);
        }
    }

    @FXML
    void OnCancel(ActionEvent event) {
        Settings.disable();
        Settings.stage.close();
    }
}
