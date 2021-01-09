package com.pwe.estimator_gui.controller;

import com.pwe.Main;
import com.pwe.estimator.Period;
import com.pwe.estimator_gui.controller.core.ShareGeneral;
import com.pwe.estimator_gui.prompt.empty.Empty;
import com.pwe.estimator_gui.prompt.settings.Settings;
import com.pwe.export.CreatePdf;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller_period extends ShareGeneral implements Initializable {
    private final String[] name = {
            "Number of panels",
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
        Main.stage.setTitle("Publication Work Estimator | Period");
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
        workType = "Periodic Work";
    }


    @Override
    public boolean valueChangeCheck() {

        if (!fn_x1.equals(fn_x.getText()) || !fn_y1.equals(fn_y.getText()) || !Mn_x1.equals(Mn_x.getText()) ||
                !Mn_y1.equals(Mn_y.getText()) || !Sc_x1.equals(Sc_x.getText()) || !Sc_y1.equals(Sc_y.getText()) ||
                !Qty1.equals(Qty.getText()) || !Spi1.equals(Spi.getText()) || !Sp_c1.equals(Sp_c.getText()) ||
                !allow1.equals(allow.getText()) || !Rm1.equals(Rm.getText()) || !Ov_C1.equals(Ov_C.getText()) ||
                !Pl1.equals(Pl.getText()) || !Pr1.equals(Pr.getText()) || !Fi_C1.equals(Fi_C.getText()) ||
                !Im_C1.equals(Im_C.getText()) || !periodFlat1.equals(periodFlat.getText()) ||
                !numPeriod1.equals(numPeriod.getText()) || !fb1.equals(fb)) {

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
            periodFlat1 = periodFlat.getText();
            numPeriod1 = numPeriod.getText();
            fb1 = fb;
            changed = true;
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
                || isEmpty(Qty) || isEmpty(Spi) || isEmpty(allow) || isEmpty(periodFlat) || isEmpty(numPeriod)) {
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

    private boolean front(String string) {
        return string.equals("Yes");
    }

    private void workForPP(String fields) {
        if (isEmpty(fn_x) && isEmpty(fn_y) && isEmpty(Mn_x) && isEmpty(Mn_y) && isEmpty(Sc_x) &&
                isEmpty(Sc_y) && isEmpty(Qty) && isEmpty(Spi) && isEmpty(allow) && isEmpty(numPeriod) &&
                isEmpty(periodFlat)) {
            isDigit(fn_x);
            isDigit(fn_y);
            isDigit(Mn_x);
            isDigit(Mn_y);
            isDigit(Sc_x);
            isDigit(Sc_y);
            isDigit(Qty);
            isDigit(Spi);
            isDigit(periodFlat);
            isDigit(numPeriod);
            isDigit(allow);
            if (run == 11) {
                if (!turn_M) {
                    done = true;
                    turn_L = false;
                }
                if (done) zipUp_1();
                Period period_params = new Period(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(allow), (int) convertString(periodFlat), (int) convertString(numPeriod));
                period_params.front_back = front(fb);
                preventInfinity(period_params);
                double[] doubles = {
                        period_params.number_of_period_to_view(),
                        period_params.number_of_up(),
                        period_params.number_of_out(),
                        period_params.number_of_wss(),
                        period_params.number_of_sss(),
                        period_params.spoilage_percent(),
                        period_params.total_sss(),
                        period_params.number_of_reams()
                };
                if (print)
                    noInfinity(doubles, name, now);
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
                isEmpty(Pl) && isEmpty(allow) && isEmpty(periodFlat) && isEmpty(numPeriod)) {
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
            isDigit(periodFlat);
            isDigit(numPeriod);

            if (run == 15) {
                if (done) zipUp_2();
                Period period_material = new Period(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(allow), (int) convertString(periodFlat),
                        (int) convertString(numPeriod), convertString(Rm), convertString(Pl), convertString(Sp_c),
                        (int) Double.parseDouble(choose), convertString(Ov_C));
                period_material.front_back = front(fb);
                preventInfinity(period_material);
                double[] doubles = {
                        period_material.number_of_period_to_view(),
                        period_material.number_of_up(),
                        period_material.number_of_out(),
                        period_material.number_of_wss(),
                        period_material.number_of_sss(),
                        period_material.spoilage_percent(),
                        period_material.total_sss(),
                        period_material.number_of_reams(),
                        period_material.paper_cost(),
                        period_material.plate_cost(),
                        period_material.separation_cost(),
                        period_material.material_cost(),
                        period_material.over_head(),
                        period_material.total_material_cost()
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
                isEmpty(Fi_C) && isEmpty(Pr) && isEmpty(Pl) && isEmpty(Im_C) && isEmpty(allow) &&
                isEmpty(periodFlat) && isEmpty(numPeriod)) {
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
            isDigit(periodFlat);
            isDigit(numPeriod);
            isDigit(Rm);
            isDigit(Ov_C);
            isDigit(Pl);
            isDigit(Pr);
            isDigit(Fi_C);
            isDigit(Im_C);

            if (run == 18) {
                Period period = new Period(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(allow), (int) convertString(periodFlat),
                        (int) convertString(numPeriod), convertString(Rm), convertString(Pl), convertString(Sp_c),
                        (int) Double.parseDouble(choose), convertString(Ov_C), convertString(Im_C),
                        convertString(Fi_C), convertString(Pr));
                period.front_back = front(fb);
                preventInfinity(period);
                double[] doubles = {
                        period.number_of_period_to_view(),
                        period.number_of_up(),
                        period.number_of_out(),
                        period.number_of_wss(),
                        period.number_of_sss(),
                        period.spoilage_percent(),
                        period.total_sss(),
                        period.number_of_reams(),
                        period.paper_cost(),
                        period.plate_cost(),
                        period.separation_cost(),
                        period.material_cost(),
                        period.over_head(),
                        period.total_material_cost(),
                        period.num_impression(),
                        period.cost_of_impression(),
                        period.cost_of_finishing(),
                        period.cost_of_labour(),
                        period.sub_total(),
                        period.profit_cost(),
                        period.grand_total(),
                        period.unit_cost()
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


    private void preventInfinity(Period periodType) {
        if (periodType.number_of_out() == 0) {
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
        if (periodType.number_of_up() == 0) {
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
        if (periodType.number_of_out() != 0 && periodType.number_of_up() != 0) print = true;
        else {
            print = false;
            String msg = "Please check your stock size or finish size.";
            if (periodType.number_of_up() == 0) msg = "Please check your machine size or finish size.";
            Empty.show(msg);
            btn_Pdf.setDisable(true);
        }
    }

    @Override
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
            checkedEmpty(periodFlat);
            checkedEmpty(numPeriod);
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
            checkedEmpty(periodFlat);
            checkedEmpty(numPeriod);
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
            checkedEmpty(periodFlat);
            checkedEmpty(numPeriod);
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
        notDigit(periodFlat);
        notDigit(numPeriod);
        notDigit(Rm);
        notDigit(Ov_C);
        notDigit(Pl);
        notDigit(Pr);
        notDigit(Fi_C);
        notDigit(Im_C);
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
            if (!periodFlat.getText().equals("")) isDouble(periodFlat, "periodFlat");
            if (!numPeriod.getText().equals("")) isDouble(numPeriod, "numPeriod");
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
            if (!periodFlat.getText().equals("")) isDouble(periodFlat, "periodFlat");
            if (!numPeriod.getText().equals("")) isDouble(numPeriod, "numPeriod");
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
            if (!periodFlat.getText().equals("")) isDouble(periodFlat, "periodFlat");
            if (!numPeriod.getText().equals("")) isDouble(numPeriod, "numPeriod");
        }
    }

    @FXML
    void onFb(MouseEvent event) {
        if (fb_Yes.isSelected()) fb = fb_Yes.getText();
        else if (fb_No.isSelected()) fb = fb_No.getText();
    }
}
