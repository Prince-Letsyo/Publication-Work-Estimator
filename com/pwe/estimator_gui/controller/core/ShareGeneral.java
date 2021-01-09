package com.pwe.estimator_gui.controller.core;

import animatefx.animation.FadeIn;
import com.itextpdf.text.DocumentException;
import com.pwe.Main;
import com.pwe.estimator.unit_Converter.UnitConverter;
import com.pwe.estimator_gui.controller.ControllerListDisplay;
import com.pwe.estimator_gui.controller.ControllerListTitleDisplay;
import com.pwe.estimator_gui.controller.ScreensController;
import com.pwe.estimator_gui.preloader.MyPreloader;
import com.pwe.estimator_gui.prompt.empty.Dialog;
import com.pwe.estimator_gui.prompt.empty.Empty;
import com.pwe.estimator_gui.prompt.empty.ShowDir;
import com.pwe.estimator_gui.prompt.settings.Settings;
import com.pwe.export.CreatePdf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ShareGeneral implements ControlledScreen {
    @FXML
    protected TextField fn_y, Mn_x, Mn_y, Sc_y, Sc_x, fn_x, Qty, Spi, Pl, Sp_c, Rm, allow, Ov_C, Fi_C, Pr, Im_C,
            numPeriod, periodFlat, Spine, pages, film_C, Coloured_pages;
    @FXML
    protected RadioButton one, two, three, four, fb_Yes, fb_No, one_cover, two_cover, three_cover, four_cover;
    @FXML
    protected Button btnCollapse_2, btnCollapse1, btn_Pdf;
    @FXML
    protected VBox L, now, M;
    @FXML
    protected GridPane L_2, M_2;
    @FXML
    protected MenuButton unit__, st_s_, ma_s_, fn_s_;
    @FXML
    protected Label projectName;
    @FXML
    protected ImageView up_1, up_2;
    @FXML
    protected Separator sep_M, sep_L;
    @FXML
    protected RadioMenuItem fnThree, fnFour, fnFive, fnSix, stZero, stOne, stTwo, stThree, stFour, M1, M2, M3, fnTwo,
            M4, M5, M6, unitOne, unitTwo, unitThree, unitFour;
    protected int i1, i2, i3 = 0;
    protected double format, run = 0;
    public String dimension = Settings.unitMeasurement;
    protected String choose, stringFn, stringSt, stringM, f, fb, choose_cover, Spi1 = "", pages1 = "", fb1 = "",
            periodFlat1 = "", numPeriod1 = "", work_, fn_x1 = "", fn_y1 = "", Mn_x1 = "", Mn_y1 = "", Sc_x1 = "",
            Sc_y1 = "", Qty1 = "", Sp_c1 = "", allow1 = "", Rm1 = "", new_dimension = "", previous_dimension = "",
            Ov_C1 = "", Pl1 = "", Pr1 = "", Fi_C1 = "", Im_C1 = "", path_ = "", string_fn = "",
            string_sn = "", string_mn = "", imagePath = "/com/pwe/estimator_gui/gui/assert/",
            empty = "Please fill all required fields.", only = "Please only numbers required.",
            save = "Do you want to save the ", not_pressed = "", workType,
            pressed = "-fx-background-color: whitesmoke;-fx-border-width: 1px;-fx-border-color: #F46B1A;-fx-border-radius: 50px;";
    protected final String[] stringsTitle = {
            "The number of reams needed for the job",
            "The cost of materials to incur on the job",
            "The cost of labour to incur on the job",
            "The unit cost per one finished sheet"
    };
    protected final ArrayList<String> temp_ = new ArrayList<>();
    protected final Image imageWhite = new Image(imagePath.concat("icons8_angle_up_512px_1.png")),
            imageYellow = new Image(imagePath.concat("icons8_angle_down_512px_5.png"));
    protected ScreensController myController;
    protected boolean changed = false, textIsEqual = false, Leave = true, turn_M = false, turn_L = false,
            done = true, print = true;
    protected UnitConverter unitConverter = new UnitConverter(this);

    @FXML
    void fnChange(ActionEvent event) {
        String[] i;
        if (fnTwo.isSelected()) stringFn = fnTwo.getText();
        else if (fnThree.isSelected()) stringFn = fnThree.getText();
        else if (fnFour.isSelected()) stringFn = fnFour.getText();
        else if (fnFive.isSelected()) stringFn = fnFive.getText();
        else if (fnSix.isSelected()) stringFn = fnSix.getText();

        i = unitConverter.Sizes(stringFn);
        fn_x.setText(i[0]);
        fn_y.setText(i[1]);

        if (i1 == 0) {
            string_fn = fn_s_.getText();
            i1 = 1;
        }
        fn_s_.setText(string_fn + " - " + stringFn);
    }

    @FXML
    void stChange(ActionEvent event) {
        String[] i;
        if (stZero.isSelected()) stringSt = stZero.getText();
        else if (stOne.isSelected()) stringSt = stOne.getText();
        else if (stTwo.isSelected()) stringSt = stTwo.getText();
        else if (stThree.isSelected()) stringSt = stThree.getText();
        else if (stFour.isSelected()) stringSt = stFour.getText();

        i = unitConverter.Sizes(stringSt);
        Sc_x.setText(i[0]);
        Sc_y.setText(i[1]);

        if (i2 == 0) {
            string_sn = st_s_.getText();
            i2 = 1;
        }
        st_s_.setText(string_sn + " - " + stringSt);
    }

    @FXML
    void Return(ActionEvent event) throws FileNotFoundException, DocumentException {
        if (CreatePdf.values.size() != 0 && CreatePdf.names.size() != 0) {
            boolean bool;
            bool = Dialog.show(save + "estimate before you leave?");
            if (bool) {
                printPDF(projectName, workType);
                showDir();
            } else {
                Leave = Dialog.Leave;
                if (Leave) stay();
            }
        }
        if (Leave) willLeave();
    }

    @FXML
    void onPdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        if (CreatePdf.values.size() != 0 && CreatePdf.names.size() != 0) {
            printPDF(projectName, workType);
            showDir();
            if (Leave) {
                now.getChildren().clear();
                btn_Pdf.setDisable(true);
            }
        }
    }

    @FXML
    void Est_Action(ActionEvent event) throws FileNotFoundException, DocumentException {
        if (valueChangeCheck())
            if (CreatePdf.values.size() != 0 && CreatePdf.names.size() != 0) {
                boolean bool;
                bool = Dialog.show(save + "previous estimate?");
                if (bool) printPDF(projectName, workType);
                else {
                    stay();
                    now.getChildren().clear();
                }
            }
        checkedEstimate();
        changed = false;
    }

    @FXML
    void mChange(ActionEvent event) {
        String[] i;
        if (M1.isSelected()) stringM = M1.getText();
        else if (M2.isSelected()) stringM = M2.getText();
        else if (M3.isSelected()) stringM = M3.getText();
        else if (M4.isSelected()) stringM = M4.getText();
        else if (M5.isSelected()) stringM = M5.getText();
        else if (M6.isSelected()) stringM = M6.getText();

        i = unitConverter.sizeMachine(stringM);
        Mn_x.setText(i[0]);
        Mn_y.setText(i[1]);

        if (i3 == 0) {
            string_mn = ma_s_.getText();
            i3 = 1;
        }
        ma_s_.setText(string_mn + " - " + stringM);
    }

    @FXML
    void unitChange(ActionEvent event) {
        fill(fn_x);
        fill(fn_y);
        fill(allow);
        fill(Sc_x);
        fill(Sc_y);
        fill(Mn_y);
        fill(Mn_x);
        if (workType.equals("Book Work")) fill(Spine);
        populate();
        unit__.setText(dimension);
    }

    @FXML
    void collapse_1(ActionEvent event) {
        zipUp_1();
    }

    @FXML
    void collapse_2(ActionEvent event) {
        zipUp_2();
    }

    @FXML
    void choose(MouseEvent event) {
        if (one.isSelected()) choose = one.getText();
        else if (two.isSelected()) choose = two.getText();
        else if (three.isSelected()) choose = three.getText();
        else if (four.isSelected()) choose = four.getText();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    protected void stay() {
        CreatePdf.names.clear();
        CreatePdf.values.clear();
        Leave = true;
    }

    private void populate() {
        double[] i;
        if (unitOne.isSelected()) dimension = unitOne.getText();
        else if (unitTwo.isSelected()) dimension = unitTwo.getText();
        else if (unitThree.isSelected()) dimension = unitThree.getText();
        else if (unitFour.isSelected()) dimension = unitFour.getText();

        if (!workType.equals("Book Work")) i = unitConverter.unit_m(dimension, fn_x.getText(), fn_y.getText(),
                Mn_x.getText(), Mn_y.getText(), Sc_x.getText(), Sc_y.getText(), allow.getText(), allow.getText());
        else i = unitConverter.unit_m(dimension, fn_x.getText(), fn_y.getText(), Mn_x.getText(), Mn_y.getText(),
                Sc_x.getText(), Sc_y.getText(), allow.getText(), Spine.getText());
        double dimension_;
        fn_x.setText(String.valueOf(i[0]));
        fn_y.setText(String.valueOf(i[1]));
        Sc_x.setText(String.valueOf(i[2]));
        Sc_y.setText(String.valueOf(i[3]));
        Mn_x.setText(String.valueOf(i[4]));
        Mn_y.setText(String.valueOf(i[5]));
        allow.setText(String.valueOf(i[7]));
        if (workType.equals("Book Work")) Spine.setText(String.valueOf(i[8]));
        dimension_ = i[6];

        if (dimension_ == 0.00) dimension = unitOne.getText();
        else if (dimension_ == 1.00) dimension = unitTwo.getText();
        else if (dimension_ == 2.00) dimension = unitThree.getText();
        else if (dimension_ == 3.00) dimension = unitFour.getText();
    }


    protected void printPDF(Label projectName, String workType) throws FileNotFoundException, DocumentException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(projectName.getText());
        fileChooser.setInitialDirectory(new File(Main.string_path));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            CreatePdf.title = this.stringsTitle;
            CreatePdf.type = this.f;
            CreatePdf.file = file;
            CreatePdf.workType = workType;
            path_ = file.getParent();

            String company = file.getName().substring(0, file.getName().indexOf(".", -1));
            if (company.equals(projectName.getText()))
                CreatePdf.projectTitle = projectName.getText();
            else if (!company.equals(projectName.getText()))
                CreatePdf.projectTitle = company;

            CreatePdf.printPdf();
            CreatePdf.names.clear();
            CreatePdf.values.clear();
            Leave = true;
        } else Leave = false;
    }

    protected void showDir() {
        if (!path_.equals(""))
            ShowDir.show(path_);
    }

    private void willLeave() {
        myController.setScreen(MyPreloader.screen1ID);
        Main.stage.setTitle("Publication Work Estimator");
        Settings.disable();
    }

    private void fill(TextField textField) {
        if ("".equals(textField.getText())) {
            textField.setText("0.0");
        }
    }

    private void printToDisplay(double[] doubles, String[] name, VBox now) {
        Node[] nodes = new Node[doubles.length];
        Node[] nodes1 = new Node[stringsTitle.length];
        now.getChildren().clear();
        String path = "/com/pwe/estimator_gui/gui/fxml/";
        int title_int = 0;
        int j = 0;
        ControllerListDisplay.anIntGeneral = this.format;

        for (int i = 0; i < nodes.length; i++) {
            if (title_int == i) {
                ControllerListTitleDisplay.setString(stringsTitle[j]);
                try {
                    nodes1[j] = FXMLLoader.load(getClass().getResource(path.concat("listTitleDisplay.fxml")));
                    now.getChildren().add(nodes1[j]);
                    j++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (name.length == 25) {
                    if (title_int == 0) title_int += 10;
                    else if (title_int == 10) title_int += 7;
                    else if (title_int == 17) {
                        stringsTitle[3] = "The unit cost per one finished book";
                        title_int += 4;
                    }
                } else if (name.length == 22) {
                    if (title_int == 0) title_int += 8;
                    else if (title_int == 8) title_int += 6;
                    else if (title_int == 14) title_int += 4;
                } else if (name.length == 21) {
                    if (title_int == 0) title_int += 7;
                    else if (title_int == 7) title_int += 6;
                    else if (title_int == 13) title_int += 4;
                }
            }
            ControllerListDisplay.setString(name[i]);
            ControllerListDisplay.setaDouble(doubles[i]);
            ControllerListDisplay.getaDouble = i + 1;
            try {
                nodes[i] = FXMLLoader.load(getClass().getResource(path.concat("listdisplay.fxml")));
                new FadeIn(nodes[i]).play();
                now.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void noInfinity(double[] doubles, String[] name, VBox now) {
        this.format = doubles.length;
        printToDisplay(doubles, name, now);
    }

    protected void checked(TextField textField) {
        textField.setStyle("-fx-border-color: none");
    }

    protected void checkedEmpty(TextField textField) {
        if (textField.getText().equals(""))
            textField.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-border-radius: 2px");
        else
            textField.setStyle("-fx-border-color: none");
    }

    protected boolean isEmpty(TextField sam) {
        return !sam.getText().equals("");
    }

    protected void notDigit(TextField sam) {
        if (!sam.getText().equals(""))
            try {
                Double.parseDouble(sam.getText());
                sam.setStyle("-fx-border-color: none");
            } catch (NumberFormatException e) {
                sam.setStyle("-fx-border-color: red; -fx-border-width: 2px;-fx-border-radius: 2px");
            }
    }

    protected double convertString(TextField sam) {
        return Double.parseDouble(sam.getText());
    }

    protected void isDouble(TextField sam, String string) {
        if (!sam.getText().equals("")) {
            String checked = "-fx-border-color: none";
            String unchecked = "-fx-border-color: red; -fx-border-width: 2px;-fx-border-radius: 2px";
            try {
                Double.parseDouble(sam.getText());
                if (temp_.size() == 0) temp_.add(string);
                else if (!temp_.contains(string)) temp_.add(string);
                sam.setStyle(checked);
            } catch (NumberFormatException e) {
                if (temp_.size() != 0) temp_.remove(string);
                sam.setStyle(unchecked);
            }
        }
    }

    protected void zipUp_2() {
        if (!turn_L) {
            L.getChildren().remove(L_2);
            turn_L = true;
            done = false;
            btnCollapse_2.setStyle(pressed);
            sep_L.setVisible(true);
            up_2.setImage(imageYellow);
        } else {
            L.getChildren().addAll(L_2);
            done = true;
            turn_L = false;
            sep_L.setVisible(false);
            btnCollapse_2.setStyle(not_pressed);
            up_2.setImage(imageWhite);
            checked(Fi_C);
            checked(Pr);
            checked(Im_C);
        }
    }

    protected void zipUp_1() {
        if (!turn_M) {
            M.getChildren().remove(M_2);
            if (!turn_L) {
                L.getChildren().remove(L_2);
                turn_L = true;
                done = false;
                sep_L.setVisible(true);
                up_2.setImage(imageYellow);
            }
            btnCollapse_2.setStyle(pressed);
            btnCollapse_2.setDisable(true);
            btnCollapse1.setStyle(pressed);
            up_1.setImage(imageYellow);
            sep_M.setVisible(true);
            turn_M = true;
        } else {
            M.getChildren().addAll(M_2);
            if (turn_L) {
                L.getChildren().addAll(L_2);
                turn_L = false;
                done = true;
                btnCollapse1.setStyle(not_pressed);
                btnCollapse_2.setStyle(not_pressed);
                sep_M.setVisible(false);
                up_2.setImage(imageWhite);
                if (turn_M) sep_L.setVisible(false);
            }
            up_1.setImage(imageWhite);
            btnCollapse_2.setDisable(false);
            turn_M = false;

            if (workType.equals("Book Work")) {
                checked(film_C);
                checked(Coloured_pages);
            }
            checked(Sp_c);
            checked(Rm);
            checked(Ov_C);
            checked(Pl);
            checked(Fi_C);
            checked(Pr);
            checked(Im_C);
        }
    }

    protected void isDigit(TextField sam) {
        try {
            if (!Double.isNaN(Double.parseDouble(sam.getText()))) run++;
        } catch (NumberFormatException e) {
            boolean bool = Empty.show(only);
            if (bool) checkedTextFields(work_, this.f);
        }
    }

    protected void checkedTextFields(String work, String fields) {
        if (turn_M && turn_L) {
            work = "workP";
            fields = "not";
        }

        if (work.equals("workP") && (fields.equals("not") || fields.equals("P"))) {
            checkedEmpty(fn_x);
            checkedEmpty(fn_y);
            checkedEmpty(Mn_x);
            checkedEmpty(Mn_y);
            checkedEmpty(Sc_x);
            checkedEmpty(Sc_y);
            checkedEmpty(Qty);
            checkedEmpty(Spi);
            checkedEmpty(allow);
            checkedEmpty(Sp_c);
            checkedEmpty(Rm);
            checkedEmpty(Ov_C);
            checkedEmpty(Pl);
            checkedEmpty(Fi_C);
            checkedEmpty(Pr);
            checkedEmpty(Im_C);
        } else if (work.equals("workPP") && fields.equals("PP")) {
            checkedEmpty(fn_x);
            checkedEmpty(fn_y);
            checkedEmpty(Mn_x);
            checkedEmpty(Mn_y);
            checkedEmpty(Sc_x);
            checkedEmpty(Sc_y);
            checkedEmpty(Qty);
            checkedEmpty(Spi);
            checkedEmpty(allow);
            if (!isEmpty(Sp_c) || !isEmpty(Rm) || !isEmpty(Ov_C) || !isEmpty(Pl) || !isEmpty(Fi_C) || !isEmpty(Pr)
                    || !isEmpty(Im_C)) {
                checkedEmpty(Sp_c);
                checkedEmpty(Rm);
                checkedEmpty(Ov_C);
                checkedEmpty(Pl);
                checkedEmpty(Fi_C);
                checkedEmpty(Pr);
                checkedEmpty(Im_C);
            }
        } else if (work.equals("workPM") && fields.equals("PM")) {
            checkedEmpty(fn_x);
            checkedEmpty(fn_y);
            checkedEmpty(Mn_x);
            checkedEmpty(Mn_y);
            checkedEmpty(Sc_x);
            checkedEmpty(Sc_y);
            checkedEmpty(Qty);
            checkedEmpty(Spi);
            checkedEmpty(allow);
            checkedEmpty(Sp_c);
            checkedEmpty(Rm);
            checkedEmpty(Ov_C);
            checkedEmpty(Pl);
            if (!isEmpty(Fi_C) || !isEmpty(Pr) || !isEmpty(Im_C)) {
                checkedEmpty(Fi_C);
                checkedEmpty(Pr);
                checkedEmpty(Im_C);
            }
        }
        notDigit(fn_x);
        notDigit(fn_y);
        notDigit(Mn_x);
        notDigit(Mn_y);
        notDigit(Sc_x);
        notDigit(Sc_y);
        notDigit(Qty);
        notDigit(Spi);
        notDigit(Sp_c);
        notDigit(allow);
        notDigit(Rm);
        notDigit(Ov_C);
        notDigit(Pl);
        notDigit(Pr);
        notDigit(Fi_C);
        notDigit(Im_C);
    }

    abstract public boolean valueChangeCheck();

    abstract public void checkedEstimate();
}
