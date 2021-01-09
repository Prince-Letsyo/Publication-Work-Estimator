package com.pwe.estimator_gui.controller;

import com.pwe.export.CreatePdf;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ControllerListDisplay implements Initializable {
    private static String string;
    private static double aDouble;
    private static double anInt = 1.00;
    public static double anIntGeneral = 0;
    public static double getaDouble;
    private String prevCss = "";
    private static final List<String> names = new LinkedList<>();
    private static final List<String> values = new LinkedList<>();
    private static int anInt_1 = 0;

    @FXML
    private HBox list;
    @FXML
    private Label l1;
    @FXML
    private Label l2;

    public static void setString(String string) {
        ControllerListDisplay.string = string;
    }

    public static void setaDouble(double aDouble) {
        ControllerListDisplay.aDouble = aDouble;
    }

    public String changeF(double v, String typeFormat) {
        return String.valueOf(String.format(typeFormat, v));
    }

    private Matcher replace(String check, String toCheck) {
        Pattern p = Pattern.compile(check, Pattern.CASE_INSENSITIVE);
        return p.matcher(toCheck);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (ControllerListDisplay.getaDouble / 2 == ControllerListDisplay.anInt) {
            list.setStyle("-fx-background-color: rgba(245, 245, 245, 0.2)");
            ControllerListDisplay.anInt += 1.00;
        } else list.setStyle("-fx-background-color: rgba(245,245,245,0.5)");

        if (ControllerListDisplay.anIntGeneral == ControllerListDisplay.getaDouble) ControllerListDisplay.anInt = 1.00;

        l1.setText(string);

        if (replace("up", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f up"));
            else l2.setText(changeF(aDouble, "%,.2f ups"));
        } else if (replace("out", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f out"));
            else l2.setText(changeF(aDouble, "%,.2f outs"));
        } else if (replace("panels", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f panel"));
            else l2.setText(changeF(aDouble, "%,.2f panels"));
        } else if (replace("sections", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f section"));
            else l2.setText(changeF(aDouble, "%,.2f sections"));
        } else if (replace("sheet", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f sheet"));
            else l2.setText(changeF(aDouble, "%,.2f sheets"));
        } else if (replace("reams", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f ream"));
            else l2.setText(changeF(aDouble, "%,.2f reams"));
        } else if (replace("cost", string).find() || replace("profit", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f pesewas"));
            else l2.setText(changeF(aDouble, "Ghc %,.2f"));
        } else if (replace("impression", string).find()) {
            if (aDouble <= 1) l2.setText(changeF(aDouble, "%,.2f imp"));
            else l2.setText(changeF(aDouble, "%,.2f imps"));
        }
        names.add(l1.getText());
        values.add(l2.getText());

        ++anInt_1;
        if (anIntGeneral == anInt_1) {
            if (CreatePdf.values.size() != 0 && CreatePdf.names.size() != 0) {
                CreatePdf.names.clear();
                CreatePdf.values.clear();
            }
            CreatePdf.names.addAll(names);
            CreatePdf.values.addAll(values);
            anInt_1 = 0;
            values.clear();
            names.clear();
        }
    }

    @FXML
    void onHighlight(MouseEvent event) {
        String string = "-fx-text-fill: #423A42;-fx-font-size: 18px";
        prevCss = list.getStyle();
        list.setStyle("-fx-background-color: #F46B1A;");
        l1.setStyle(string);
        l2.setStyle(string);
    }

    @FXML
    void onNormal(MouseEvent event) {
        String string = "-fx-text-fill: #f5f5f5;-fx-font-size: 15px";
        list.setStyle(prevCss);
        l1.setStyle(string);
        l2.setStyle(string);
        prevCss = "";
    }
}
