package com.pwe.estimator_gui.prompt.empty;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowDir {
    public static Stage stage;
    public static boolean set;
    public static String path;

    public static boolean show(String msg) {
        ShowDir.path = msg;
        stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Saved to");
        try {
            Parent root = FXMLLoader.load(ShowDir.class.getResource("/com/pwe/estimator_gui/gui/fxml/showDir.fxml"));
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return set;
    }
}
