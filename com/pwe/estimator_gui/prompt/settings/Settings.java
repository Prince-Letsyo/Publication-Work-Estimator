package com.pwe.estimator_gui.prompt.settings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Settings {
    public static Stage stage;
    public static boolean set;
    public static String project = "";
    public static String unitMeasurement = "";

    public static boolean show() {
        stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Settings");
        try {
            Parent root = FXMLLoader.load(Settings.class.getResource("/com/pwe/estimator_gui/gui/fxml/settings.fxml"));
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return set;
    }

    public static void disable() {
        Settings.unitMeasurement = "";
        Settings.project = "";
        Settings.set = false;
    }
}
