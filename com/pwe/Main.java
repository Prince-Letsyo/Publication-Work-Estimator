package com.pwe;

import com.pwe.estimator_gui.preloader.MyPreloader;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.pwe.estimator_gui.preloader.MyPreloader.*;

public class Main extends Application {
    private static final int COUNT_DOWN = 130000;
    public static Stage stage;
    public boolean startMain = true;
    public static String string_path = "";

    @Override
    public void init() {
        Main.string_path = MyPreloader.string_path;
        for (int i = 0; i < COUNT_DOWN; i++) {
            int timer = ((100 * i) / COUNT_DOWN) + 1;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(timer));
            MyPreloader.preloader.setOnCloseRequest(event -> startMain = false);
            if (!startMain) {
                break;
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        if (startMain) {
            screensController.loadScreen(screen1ID, screen1File);
            screensController.setScreen(screen1ID);

            VBox root = new VBox();
            root.getChildren().addAll(screensController);
            root.setPrefHeight(945);
            root.setStyle("-fx-background-color: #423A42;");

            Image image = new Image(imagePath.concat("assert/PWE_LOGO-02.png"));
            stage.getIcons().add(image);
            primaryStage.setTitle("Publication Work Estimator");
            primaryStage.setScene(new Scene(root));
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.show();
        } else {
            primaryStage.close();
        }
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, MyPreloader.class, args);
    }
}
