package com.pwe.estimator_gui.preloader;

import com.pwe.estimator_gui.controller.Controller_Splash;
import com.pwe.estimator_gui.controller.ScreensController;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.pwe.estimator_gui.controller.ScreensController.parent;

public class MyPreloader extends Preloader {
    private Scene scene;
    public static final String imagePath = "/com/pwe/estimator_gui/gui/";
    private static final String path = imagePath.concat("fxml/");
    public static String screen1ID = "main";
    public static String screen1File = path.concat("home.fxml");
    public static String screen2ID = "paper";
    public static String screen2File = path.concat("paperworkspace.fxml");
    public static String screen3ID = "book";
    public static String screen3File = path.concat("bookworkspace.fxml");
    public static String screen4ID = "period";
    public static String screen4File = path.concat("periodworkspace.fxml");
    public static Stage preloader;
    public static String string_path = "";
    public static ScreensController screensController = new ScreensController();

    @Override
    public void init() throws Exception {
        parent = FXMLLoader.load(getClass().getResource(path.concat("SplashScreen.fxml")));
        scene = new Scene(parent);
    }

    @Override
    public void start(Stage primaryStage) {
        preloader = primaryStage;

        Image image = new Image(imagePath.concat("assert/PWE_LOGO-02.png"));
        preloader.getIcons().add(image);
        preloader.setTitle("Publication Work Estimator");
        preloader.setScene(scene);
        preloader.initStyle(StageStyle.UNDECORATED);
        preloader.centerOnScreen();
        preloader.show();
    }

    public void handleApplicationNotification(Preloader.PreloaderNotification info) {
        if (info instanceof ProgressNotification) {
            double timer = ((ProgressNotification) info).getProgress() * 8;
            Controller_Splash.load.setPrefWidth(timer);
        }
    }

    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        StateChangeNotification.Type typedState = info.getType();
        if (typedState == StateChangeNotification.Type.BEFORE_LOAD) {
            String filePath = System.getenv().get("USERPROFILE") + "\\Documents\\PWE-Documents";
            if (!Files.exists(Paths.get(filePath)) && !Files.isDirectory(Paths.get(filePath)))
                try {
                    Files.createDirectories(Paths.get(filePath));
                } catch (IOException e) {
                    e.getMessage();
                }

            string_path = filePath;
        } else if (typedState == StateChangeNotification.Type.BEFORE_START) {
            preloader.hide();
        }
    }
}

