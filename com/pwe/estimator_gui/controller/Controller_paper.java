package com.pwe.estimator_gui.controller;

import com.pwe.Main;
import com.pwe.estimator.Paper;
import com.pwe.estimator_gui.controller.core.ShareGeneral;
import com.pwe.estimator_gui.prompt.empty.Empty;
import com.pwe.estimator_gui.prompt.settings.Settings;
import com.pwe.export.CreatePdf;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller_paper extends ShareGeneral implements Initializable {
    private final String[] name = {
            "Number of up",
            "Number of out",
            "Working size sheet",
            "Stock size sheet",
            "Spoilage sheet",
            "Total stock size sheet",
            "Number of reams",
            "Cost of paper",
            "Cost of plate",
            "Cost of separation",
            "Cost of material",
            "Overhead cost",
            "Total material cost",
            "Number of impression",
            "Cost of impression",
            "Cost of finishing",
            "Cost of labour",
            "SubTotal cost",
            "Profit",
            "Grand total cost",
            "Unit cost"
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.stage.setTitle("Publication Work Estimator | Paper");
        unit__.setText(dimension);
        projectName.setText(Settings.project);

        if ("Inches".equals(dimension)) {
            unitOne.setSelected(true);
        } else if ("Picas".equals(dimension)) {
            unitTwo.setSelected(true);
        } else if ("Millimeter".equals(dimension)) {
            unitFour.setSelected(true);
        } else {
            unitThree.setSelected(true);
        }
        fn_y.setPromptText(dimension);
        fn_x.setPromptText(dimension);
        Sc_x.setPromptText(dimension);
        Sc_y.setPromptText(dimension);
        Mn_x.setPromptText(dimension);
        Mn_y.setPromptText(dimension);
        allow.setPromptText(dimension);
        workType = "Paper Work";
    }


    @Override
    public boolean valueChangeCheck() {
        if (!fn_x1.equals(fn_x.getText()) || !fn_y1.equals(fn_y.getText()) || !Mn_x1.equals(Mn_x.getText()) ||
                !Mn_y1.equals(Mn_y.getText()) || !Sc_x1.equals(Sc_x.getText()) || !Sc_y1.equals(Sc_y.getText()) ||
                !Qty1.equals(Qty.getText()) || !Spi1.equals(Spi.getText()) || !Sp_c1.equals(Sp_c.getText()) ||
                !allow1.equals(allow.getText()) || !Rm1.equals(Rm.getText()) || !Ov_C1.equals(Ov_C.getText()) ||
                !Pl1.equals(Pl.getText()) || !Pr1.equals(Pr.getText()) || !Fi_C1.equals(Fi_C.getText()) ||
                !Im_C1.equals(Im_C.getText())) {
            fn_x1 = fn_x.getText();
            fn_y1 = fn_y.getText();
            Mn_x1 = Mn_x.getText();
            Mn_y1 = Mn_y.getText();
            Sc_x1 = Sc_x.getText();
            Sc_y1 = Sc_y.getText();
            Qty1 = Qty.getText();
            Spi1 = Spi.getText();
            Sp_c1 = Sp_c.getText();
            allow1 = allow.getText();
            Rm1 = Rm.getText();
            Ov_C1 = Ov_C.getText();
            Pl1 = Pl.getText();
            Pr1 = Pr.getText();
            Fi_C1 = Fi_C.getText();
            Im_C1 = Im_C.getText();

            if (previous_dimension.equals(new_dimension))
                changed = true;
            else {
                changed = textIsEqual;
            }
            previous_dimension = new_dimension;
        }
        return changed;
    }

    public void checkedEstimate() {
        String fields = "not";
        boolean P = true, PP = true, PM = true;
        if (isEmpty(Fi_C) || isEmpty(Pr) || isEmpty(Im_C)) {
            if (!turn_L) {
                P = false;
                fields = "P";
            } else {
                PM = false;
                fields = "PM";
            }
            if (turn_L && turn_M) {
                PP = false;
                P = true;
                PM = true;
                fields = "PP";
            }
        } else if (isEmpty(Sp_c) || isEmpty(Rm) || isEmpty(Ov_C) || isEmpty(Pl)) {
            if (!turn_M) {
                PM = false;
            } else {
                PP = false;
                PM = true;
            }
            P = true;
            fields = "PM";
        } else if (isEmpty(fn_x) || isEmpty(fn_y) || isEmpty(Mn_x) || isEmpty(Mn_y) || isEmpty(Sc_x) || isEmpty(Sc_y)
                || isEmpty(Qty) || isEmpty(Spi) || isEmpty(allow)) {
            if (!done) {
                turn_L = false;
                turn_M = false;
            }
            if (!turn_M && !turn_L) {
                PP = false;
                PM = true;
                P = true;
            }
            fields = "PP";
        } else {
            work_ = "workP";
            checkedTextFields(work_, fields);
        }

        if (!P) workForP(fields);
        else if (!PM) workForPM(fields);
        else if (!PP) workForPP(fields);
        this.f = fields;

        if (CreatePdf.names.size() != 0 && CreatePdf.values.size() != 0) {
            btn_Pdf.setDisable(false);
        }
    }

    private void workForPP(String fields) {
        if (isEmpty(fn_x) && isEmpty(fn_y) && isEmpty(Mn_x) && isEmpty(Mn_y) && isEmpty(Sc_x) &&
                isEmpty(Sc_y) && isEmpty(Qty) && isEmpty(Spi) && isEmpty(allow)) {
            isDigit(fn_x);
            isDigit(fn_y);
            isDigit(Mn_x);
            isDigit(Mn_y);
            isDigit(Sc_x);
            isDigit(Sc_y);
            isDigit(Qty);
            isDigit(Spi);
            isDigit(allow);
            if (run == 9) {
                if (!turn_M) {
                    done = true;
                    turn_L = false;
                }
                if (done) zipUp_1();
                Paper paper_params = new Paper(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(allow));
                preventInfinity(paper_params);
                double[] doubles = {
                        paper_params.number_of_up(),
                        paper_params.number_of_out(),
                        paper_params.number_of_wss(),
                        paper_params.number_of_sss(),
                        paper_params.spoilage_percent(),
                        paper_params.total_sss(),
                        paper_params.number_of_reams()
                };
                if (print) {
                    noInfinity(doubles, name, now);
                }
            }
            run = 0;
        } else {
            boolean bool;
            bool = Empty.show(empty);
            if (bool) {
                work_ = "workPP";
                checkedTextFields(work_, fields);
            }
        }
    }


    private void workForPM(String fields) {
        if (isEmpty(fn_x) && isEmpty(fn_y) && isEmpty(Mn_x) && isEmpty(Mn_y) && isEmpty(Sc_x) &&
                isEmpty(Sc_y) && isEmpty(Qty) && isEmpty(Spi) && isEmpty(Sp_c) && isEmpty(Rm) && isEmpty(Ov_C) &&
                isEmpty(Pl) && isEmpty(allow)) {
            isDigit(fn_x);
            isDigit(fn_y);
            isDigit(Mn_x);
            isDigit(Mn_y);
            isDigit(Sc_x);
            isDigit(Sc_y);
            isDigit(Qty);
            isDigit(Spi);
            isDigit(Sp_c);
            isDigit(Rm);
            isDigit(Ov_C);
            isDigit(Pl);
            isDigit(allow);

            if (run == 13) {
                if (done) zipUp_2();
                Paper paper_material = new Paper(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(allow), convertString(Rm), convertString(Pl), convertString(Sp_c),
                        (int) Double.parseDouble(choose), convertString(Ov_C));
                preventInfinity(paper_material);
                double[] doubles = {
                        paper_material.number_of_up(),
                        paper_material.number_of_out(),
                        paper_material.number_of_wss(),
                        paper_material.number_of_sss(),
                        paper_material.spoilage_percent(),
                        paper_material.total_sss(),
                        paper_material.number_of_reams(),
                        paper_material.paper_cost(),
                        paper_material.plate_cost(),
                        paper_material.separation_cost(),
                        paper_material.material_cost(),
                        paper_material.over_head(),
                        paper_material.total_material_cost()
                };
                if (print)
                    noInfinity(doubles, name, now);
            }
            run = 0;
        } else {
            boolean bool;
            bool = Empty.show(empty);
            if (bool) {
                work_ = "workPM";
                checkedTextFields(work_, fields);
            }
        }
    }

    private void workForP(String fields) {
        if (isEmpty(fn_x) && isEmpty(fn_y) && isEmpty(Mn_x) && isEmpty(Mn_y) && isEmpty(Sc_x) &&
                isEmpty(Sc_y) && isEmpty(Qty) && isEmpty(Spi) && isEmpty(Sp_c) && isEmpty(Rm) && isEmpty(Ov_C) &&
                isEmpty(Fi_C) && isEmpty(Pr) && isEmpty(Pl) && isEmpty(Im_C) && isEmpty(allow)) {
            isDigit(fn_x);
            isDigit(fn_y);
            isDigit(Mn_x);
            isDigit(Mn_y);
            isDigit(Sc_x);
            isDigit(Sc_y);
            isDigit(Qty);
            isDigit(Spi);
            isDigit(Sp_c);
            isDigit(allow);
            isDigit(Rm);
            isDigit(Ov_C);
            isDigit(Pl);
            isDigit(Pr);
            isDigit(Fi_C);
            isDigit(Im_C);

            if (run == 16) {
                Paper paper = new Paper(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(allow), convertString(Rm), convertString(Pl), convertString(Sp_c),
                        (int) Double.parseDouble(choose), convertString(Ov_C), convertString(Im_C),
                        convertString(Fi_C), convertString(Pr));
                preventInfinity(paper);
                double[] doubles = {
                        paper.number_of_up(),
                        paper.number_of_out(),
                        paper.number_of_wss(),
                        paper.number_of_sss(),
                        paper.spoilage_percent(),
                        paper.total_sss(),
                        paper.number_of_reams(),
                        paper.paper_cost(),
                        paper.plate_cost(),
                        paper.separation_cost(),
                        paper.material_cost(),
                        paper.over_head(),
                        paper.total_material_cost(),
                        paper.num_impression(),
                        paper.cost_of_impression(),
                        paper.cost_of_finishing(),
                        paper.cost_of_labour(),
                        paper.sub_total(),
                        paper.profit_cost(),
                        paper.grand_total(),
                        paper.unit_cost()
                };
                if (print)
                    noInfinity(doubles, name, now);
            }
            run = 0;
        } else {
            boolean bool;
            bool = Empty.show(empty);
            if (bool) {
                work_ = "workP";
                checkedTextFields(work_, fields);
            }
        }
    }


    private void preventInfinity(Paper paperType) {
        if (paperType.number_of_out() == 0) {
            Sc_y.setStyle("-fx-border-color: red");
            Sc_x.setStyle("-fx-border-color: red");
            fn_x.setStyle("-fx-border-color: red");
            fn_y.setStyle("-fx-border-color: red");
        } else {
            Sc_y.setStyle("");
            Sc_x.setStyle("");
            fn_x.setStyle("");
            fn_y.setStyle("");
        }
        if (paperType.number_of_up() == 0) {
            Mn_y.setStyle("-fx-border-color: red");
            Mn_x.setStyle("-fx-border-color: red");
            fn_x.setStyle("-fx-border-color: red");
            fn_y.setStyle("-fx-border-color: red");
        } else {
            Mn_y.setStyle("");
            Mn_x.setStyle("");
            fn_x.setStyle("");
            fn_y.setStyle("");
        }
        if (paperType.number_of_out() != 0 && paperType.number_of_up() != 0) print = true;
        else {
            print = false;
            String msg = "Please check your stock size or finish size.";
            if (paperType.number_of_up() == 0) msg = "Please check your machine size or finish size.";
            Empty.show(msg);
            btn_Pdf.setDisable(true);
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


    @FXML
    void typeChecker(KeyEvent event) {
        if (!turn_M && !turn_L) {
            if (!fn_x.getText().equals("")) isDouble(fn_x, "fn_x");
            if (!fn_y.getText().equals("")) isDouble(fn_y, "fn_y");
            if (!Mn_x.getText().equals("")) isDouble(Mn_x, "Mn_x");
            if (!Mn_y.getText().equals("")) isDouble(Mn_y, "Mn_y");
            if (!Sc_x.getText().equals("")) isDouble(Sc_x, "Sc_x");
            if (!Sc_y.getText().equals("")) isDouble(Sc_y, "Sc_y");
            if (!Qty.getText().equals("")) isDouble(Qty, "Qty");
            if (!Spi.getText().equals("")) isDouble(Spi, "Spi");
            if (!Pl.getText().equals("")) isDouble(Pl, "Pl");
            if (!Sp_c.getText().equals("")) isDouble(Sp_c, "Sp_c");
            if (!Ov_C.getText().equals("")) isDouble(Ov_C, "Ov_C");
            if (!Rm.getText().equals("")) isDouble(Rm, "Rm");
            if (!Fi_C.getText().equals("")) isDouble(Fi_C, "Fi_C");
            if (!Pr.getText().equals("")) isDouble(Pr, "Pr");
            if (!Im_C.getText().equals("")) isDouble(Im_C, "Im_C");
            if (!allow.getText().equals("")) isDouble(allow, "allow");
        } else if (turn_L && turn_M) {
            if (!fn_x.getText().equals("")) isDouble(fn_x, "fn_x");
            if (!fn_y.getText().equals("")) isDouble(fn_y, "fn_y");
            if (!Mn_x.getText().equals("")) isDouble(Mn_x, "Mn_x");
            if (!Mn_y.getText().equals("")) isDouble(Mn_y, "Mn_y");
            if (!Sc_x.getText().equals("")) isDouble(Sc_x, "Sc_x");
            if (!Sc_y.getText().equals("")) isDouble(Sc_y, "Sc_y");
            if (!Qty.getText().equals("")) isDouble(Qty, "Qty");
            if (!Spi.getText().equals("")) isDouble(Spi, "Spi");
            if (!allow.getText().equals("")) isDouble(allow, "allow");
        } else {
            if (!fn_x.getText().equals("")) isDouble(fn_x, "fn_x");
            if (!fn_y.getText().equals("")) isDouble(fn_y, "fn_y");
            if (!Mn_x.getText().equals("")) isDouble(Mn_x, "Mn_x");
            if (!Mn_y.getText().equals("")) isDouble(Mn_y, "Mn_y");
            if (!Sc_x.getText().equals("")) isDouble(Sc_x, "Sc_x");
            if (!Sc_y.getText().equals("")) isDouble(Sc_y, "Sc_y");
            if (!Qty.getText().equals("")) isDouble(Qty, "Qty");
            if (!Spi.getText().equals("")) isDouble(Spi, "Spi");
            if (!Pl.getText().equals("")) isDouble(Pl, "Pl");
            if (!Sp_c.getText().equals("")) isDouble(Sp_c, "Sp_c");
            if (!Ov_C.getText().equals("")) isDouble(Ov_C, "Ov_C");
            if (!Rm.getText().equals("")) isDouble(Rm, "Rm");
            if (!allow.getText().equals("")) isDouble(allow, "allow");
        }
    }

    @FXML
    void choose(MouseEvent event) {
        if (one.isSelected()) choose = one.getText();
        else if (two.isSelected()) choose = two.getText();
        else if (three.isSelected()) choose = three.getText();
        else if (four.isSelected()) choose = four.getText();
    }
}
