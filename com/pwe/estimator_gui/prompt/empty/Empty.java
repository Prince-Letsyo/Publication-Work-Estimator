package com.pwe.estimator_gui.prompt.empty;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Empty {
    public static Stage stage;
    public static boolean set;
    public static String msg;

    public static boolean show(String msg) {
        Empty.msg = msg;
        stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Alert");
        try {
            Parent root = FXMLLoader.load(Empty.class.getResource("/com/pwe/estimator_gui/gui/fxml/empty.fxml"));
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
}
