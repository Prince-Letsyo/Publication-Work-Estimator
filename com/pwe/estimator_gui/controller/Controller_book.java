package com.pwe.estimator_gui.controller;

import com.pwe.Main;
import com.pwe.estimator.Book;
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

public class Controller_book extends ShareGeneral implements Initializable {
    private final String[] name = {
            "Number of sections",
            "Number of panels",
            "Number of ups",
            "Number of outs",
            "Working size sheet",
            "Stock size sheet",
            "Spoilage sheet",
            "Total stock size sheet",
            "Number of Reams for cover",
            "Number of reams",
            "Cost of paper",
            "Cost of plate",
            "Cost of film",
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
        Main.stage.setTitle("Publication Work Estimator | Book");
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
        Spine.setPromptText(dimension);
        workType = "Book Work";
    }

    @Override
    public boolean valueChangeCheck() {
        if (!fn_x1.equals(fn_x.getText()) || !fn_y1.equals(fn_y.getText()) || !Mn_x1.equals(Mn_x.getText()) ||
                !Mn_y1.equals(Mn_y.getText()) || !Sc_x1.equals(Sc_x.getText()) || !Sc_y1.equals(Sc_y.getText()) ||
                !Qty1.equals(Qty.getText()) || !Spi1.equals(Spi.getText()) || !Sp_c1.equals(Sp_c.getText()) ||
                !pages1.equals(pages.getText()) || !allow1.equals(allow.getText()) || !Rm1.equals(Rm.getText()) ||
                !Ov_C1.equals(Ov_C.getText()) || !Pl1.equals(Pl.getText()) || !Pr1.equals(Pr.getText()) ||
                !Fi_C1.equals(Fi_C.getText()) || !Im_C1.equals(Im_C.getText())) {

            fn_x1 = fn_x.getText();
            fn_y1 = fn_y.getText();
            Mn_x1 = Mn_x.getText();
            Mn_y1 = Mn_y.getText();
            Sc_x1 = Sc_x.getText();
            Sc_y1 = Sc_y.getText();
            Qty1 = Qty.getText();
            Spi1 = Spi.getText();
            pages1 = pages.getText();
            Sp_c1 = Sp_c.getText();
            allow1 = allow.getText();
            Rm1 = Rm.getText();
            Ov_C1 = Ov_C.getText();
            Pl1 = Pl.getText();
            Pr1 = Pr.getText();
            Fi_C1 = Fi_C.getText();
            Im_C1 = Im_C.getText();
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
                || isEmpty(Qty) || isEmpty(Spi) || isEmpty(pages) || isEmpty(allow)) {
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
                isEmpty(Sc_y) && isEmpty(Qty) && isEmpty(Spi) && isEmpty(pages) && isEmpty(allow)) {
            isDigit(fn_x);
            isDigit(fn_y);
            isDigit(Mn_x);
            isDigit(Mn_y);
            isDigit(Sc_x);
            isDigit(Sc_y);
            isDigit(Qty);
            isDigit(Spi);
            isDigit(Spine);
            isDigit(pages);
            isDigit(allow);
            if (run == 11) {
                if (!turn_M) {
                    done = true;
                    turn_L = false;
                }
                if (done) zipUp_1();
                Book book_params = new Book(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(allow), (int) convertString(pages));

                preventInfinity(book_params);
                double[] doubles = {
                        book_params.number_of_section(),
                        book_params.number_of_panels(),
                        book_params.number_of_up(),
                        book_params.number_of_out(),
                        book_params.number_of_wss(),
                        book_params.number_of_sss(),
                        book_params.spoilage_percent(),
                        book_params.total_sss(),
                        book_params.ream_for_cover(),
                        book_params.number_of_reams()
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
        if (isEmpty(fn_x) && isEmpty(fn_y) && isEmpty(Mn_x) && isEmpty(Mn_y) && isEmpty(Sc_x) && isEmpty(Sc_y) &&
                isEmpty(Qty) && isEmpty(Spi) && isEmpty(Spine) && isEmpty(pages) && isEmpty(Sp_c) && isEmpty(Rm) &&
                isEmpty(Ov_C) && isEmpty(Pl) && isEmpty(allow) && isEmpty(film_C) && isEmpty(Coloured_pages)) {
            isDigit(fn_x);
            isDigit(fn_y);
            isDigit(Mn_x);
            isDigit(Mn_y);
            isDigit(Sc_x);
            isDigit(Sc_y);
            isDigit(Qty);
            isDigit(pages);
            isDigit(Spi);
            isDigit(Spine);
            isDigit(Sp_c);
            isDigit(Rm);
            isDigit(Ov_C);
            isDigit(Pl);
            isDigit(allow);
            isDigit(film_C);
            isDigit(Coloured_pages);

            if (run == 17) {
                if (done) zipUp_2();
                Book book_material = new Book(convertString(Mn_x), convertString(Mn_y), convertString(fn_x),
                        convertString(fn_y), (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y),
                        convertString(Spi), convertString(Spine), convertString(allow), (int) convertString(pages),
                        convertString(Rm), convertString(Pl), convertString(Sp_c), Integer.parseInt(choose),
                        convertString(Ov_C), Integer.parseInt(choose_cover), convertString(Coloured_pages),
                        convertString(film_C));
                preventInfinity(book_material);
                double[] doubles = {
                        book_material.number_of_section(),
                        book_material.number_of_panels(),
                        book_material.number_of_up(),
                        book_material.number_of_out(),
                        book_material.number_of_wss(),
                        book_material.number_of_sss(),
                        book_material.spoilage_percent(),
                        book_material.total_sss(),
                        book_material.ream_for_cover(),
                        book_material.number_of_reams(),
                        book_material.paper_cost(),
                        book_material.plate_cost(),
                        book_material.film_cost(),
                        book_material.separation_cost(),
                        book_material.material_cost(),
                        book_material.over_head(),
                        book_material.total_material_cost()
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
                isEmpty(Sc_y) && isEmpty(Qty) && isEmpty(Spi) && isEmpty(Sp_c) && isEmpty(pages) && isEmpty(Rm) &&
                isEmpty(Ov_C) && isEmpty(Fi_C) && isEmpty(Pr) && isEmpty(Pl) && isEmpty(Im_C) && isEmpty(allow) &&
                isEmpty(Coloured_pages) && isEmpty(film_C) && isEmpty(Spine)) {
            isDigit(fn_x);
            isDigit(fn_y);
            isDigit(Mn_x);
            isDigit(Mn_y);
            isDigit(Sc_x);
            isDigit(Sc_y);
            isDigit(Qty);
            isDigit(pages);
            isDigit(Spi);
            isDigit(Spine);
            isDigit(Sp_c);
            isDigit(Rm);
            isDigit(Ov_C);
            isDigit(Pl);
            isDigit(allow);
            isDigit(film_C);
            isDigit(Coloured_pages);
            isDigit(Pr);
            isDigit(Fi_C);
            isDigit(Im_C);

            if (run == 20) {
                Book book = new Book(convertString(Mn_x), convertString(Mn_y), convertString(fn_x), convertString(fn_y),
                        (int) convertString(Qty), convertString(Sc_x), convertString(Sc_y), convertString(Spi),
                        convertString(Spine), convertString(allow), (int) convertString(pages), convertString(Rm),
                        convertString(Pl), convertString(Sp_c), Integer.parseInt(choose), convertString(Ov_C),
                        convertString(Im_C), convertString(Fi_C), convertString(Pr), Integer.parseInt(choose_cover),
                        convertString(Coloured_pages), convertString(film_C));
                preventInfinity(book);
                double[] doubles = {
                        book.number_of_section(),
                        book.number_of_panels(),
                        book.number_of_up(),
                        book.number_of_out(),
                        book.number_of_wss(),
                        book.number_of_sss(),
                        book.spoilage_percent(),
                        book.total_sss(),
                        book.ream_for_cover(),
                        book.number_of_reams(),
                        book.paper_cost(),
                        book.plate_cost(),
                        book.film_cost(),
                        book.separation_cost(),
                        book.material_cost(),
                        book.over_head(),
                        book.total_material_cost(),
                        book.num_impression(),
                        book.cost_of_impression(),
                        book.cost_of_finishing(),
                        book.cost_of_labour(),
                        book.sub_total(),
                        book.profit_cost(),
                        book.grand_total(),
                        book.unit_cost()
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

    private void preventInfinity(Book bookType) {
        if (bookType.number_of_out() == 0) {
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
        if (bookType.number_of_up() == 0) {
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
        if (bookType.number_of_out() != 0 && bookType.number_of_up() != 0) print = true;
        else {
            print = false;
            String msg = "Please check your stock size or finish size.";
            if (bookType.number_of_up() == 0) msg = "Please check your machine size or finish size.";
            Empty.show(msg);
            btn_Pdf.setDisable(true);
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
            checkedEmpty(pages);
            checkedEmpty(allow);
            checkedEmpty(Sp_c);
            checkedEmpty(Rm);
            checkedEmpty(Ov_C);
            checkedEmpty(Pl);
            checkedEmpty(Spine);
            checkedEmpty(film_C);
            checkedEmpty(Coloured_pages);
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
            checkedEmpty(pages);
            checkedEmpty(allow);
            checkedEmpty(Spine);
            if (!isEmpty(Sp_c) || !isEmpty(Rm) || !isEmpty(Ov_C) || !isEmpty(Pl) || !isEmpty(Fi_C) || !isEmpty(Pr)
                    || !isEmpty(Im_C) || isEmpty(Coloured_pages) || isEmpty(film_C)) {
                checkedEmpty(Sp_c);
                checkedEmpty(Rm);
                checkedEmpty(film_C);
                checkedEmpty(Coloured_pages);
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
            checkedEmpty(pages);
            checkedEmpty(allow);
            checkedEmpty(Sp_c);
            checkedEmpty(Rm);
            checkedEmpty(Ov_C);
            checkedEmpty(Pl);
            checkedEmpty(Spine);
            checkedEmpty(film_C);
            checkedEmpty(Coloured_pages);
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
        notDigit(Spine);
        notDigit(pages);
        notDigit(allow);
        notDigit(Rm);
        notDigit(Ov_C);
        notDigit(Pl);
        notDigit(film_C);
        notDigit(Coloured_pages);
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
            if (!pages.getText().equals("")) isDouble(pages, "pages");
            if (!Pl.getText().equals("")) isDouble(Pl, "Pl");
            if (!Sp_c.getText().equals("")) isDouble(Sp_c, "Sp_c");
            if (!Ov_C.getText().equals("")) isDouble(Ov_C, "Ov_C");
            if (!Rm.getText().equals("")) isDouble(Rm, "Rm");
            if (!film_C.getText().equals("")) isDouble(film_C, "film_C");
            if (!Spine.getText().equals("")) isDouble(Spine, "Spine");
            if (!Coloured_pages.getText().equals("")) isDouble(Coloured_pages, "Coloured_pages");
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
            if (!pages.getText().equals("")) isDouble(pages, "pages");
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
            if (!film_C.getText().equals("")) isDouble(film_C, "film_C");
            if (!Spine.getText().equals("")) isDouble(Spine, "Spine");
            if (!Coloured_pages.getText().equals("")) isDouble(Coloured_pages, "Coloured_pages");
            if (!pages.getText().equals("")) isDouble(pages, "pages");
            if (!allow.getText().equals("")) isDouble(allow, "allow");
        }
    }

    @FXML
    void choose_cover(MouseEvent event) {
        if (one_cover.isSelected()) choose_cover = one_cover.getText();
        else if (two_cover.isSelected()) choose_cover = two_cover.getText();
        else if (three_cover.isSelected()) choose_cover = three_cover.getText();
        else if (four_cover.isSelected()) choose_cover = four_cover.getText();
    }

}
