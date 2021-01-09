package com.pwe.estimator_gui.controller;

import com.pwe.estimator_gui.controller.core.ControlledScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public class ScreensController extends StackPane {
    private final HashMap<String, Node> screens = new HashMap<>();

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public static Parent parent;

    public Node getScreen(String name) {
        return screens.get(name);
    }

    public void loadScreen(String name, String resource) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resource));
            parent = fxmlLoader.load();

            ControlledScreen controlledScreen = fxmlLoader.getController();
            controlledScreen.setScreenParent(this);
            addScreen(name, parent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setScreen(final String name) {
        if (screens.get(name) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, getScreen(name));
            } else {
                getChildren().add(screens.get(name));
            }
        } else {
            System.out.println("screen hasn't been loaded yet!!!");
        }
    }

    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("screen doesn't exist!!!");
            return false;
        } else return true;
    }
}
