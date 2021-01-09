package com.pwe.estimator_gui.prompt.empty;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dialog {
    public static Stage stage;
    public static boolean set;
    public static String msg;
    public static boolean Leave = true;

    public static boolean show(String msg) {
        Leave = true;
        Dialog.msg = msg;
        stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Save prompt");
        try {
            Parent root = FXMLLoader.load(Dialog.class.getResource("/com/pwe/estimator_gui/gui/fxml/Dialog.fxml"));
            stage.setScene(new Scene(root));
            Dialog.stage.setOnCloseRequest(event1 -> Leave = false);
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
}
