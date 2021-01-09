package com.pwe.estimator.unit_Converter;

import com.pwe.estimator_gui.controller.core.ShareGeneral;

public class UnitConverter extends Converter {
    private String dimension;

    public UnitConverter(ShareGeneral sharegeneral) {
        super();
        this.dimension = sharegeneral.dimension;
    }

    public String[] Sizes(String h) {
        if (dimension.equals(unitNameString[0])) {
            populateSize(h, 0);
        } else if (dimension.equals(unitNameString[2])) {
            populateSize(h, 2);
        } else if (dimension.equals(unitNameString[3])) {
            populateSize(h, 3);
        } else if (dimension.equals(unitNameString[1])) {
            populateSize(h, 1);
        }
        return strings;
    }

    public String[] sizeMachine(String h) {
        if (dimension.equals(unitNameString[0])) {
            populateSizeMachine(h, 0);
        } else if (dimension.equals(unitNameString[2])) {
            populateSizeMachine(h, 2);
        } else if (dimension.equals(unitNameString[3])) {
            populateSizeMachine(h, 3);
        } else if (dimension.equals(unitNameString[1])) {
            populateSizeMachine(h, 1);
        }
        return strings;
    }


    public double[] unit_m(String h, String finish_x_, String finish_y_, String machine_x_, String
            machine_y_, String stock_x_, String stock_y_, String allow, String Spine) {
        if (dimension.equals(unitNameString[0])) {
            if (h.equals(unitNameString[2])) {
                unit(unitString[8], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[2];
                doubles[6] = 2.00;
            } else if (h.equals(unitNameString[3])) {
                unit(unitString[7], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[3];
                doubles[6] = 3.00;
            } else if (h.equals(unitNameString[1])) {
                unit(unitString[6], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[1];
                doubles[6] = 1.00;
            } else {
                unit("", finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[0];
                doubles[6] = 0.00;
            }
        } else if (dimension.equals(unitNameString[2])) {
            if (h.equals(unitNameString[0])) {
                unit(unitString[1], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[0];
                doubles[6] = 0.00;
            } else if (h.equals(unitNameString[3])) {
                unit(unitString[2], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[3];
                doubles[6] = 3.00;
            } else if (h.equals(unitNameString[1])) {
                unit(unitString[0], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[1];
                doubles[6] = 1.00;
            } else {
                unit("", finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[2];
                doubles[6] = 2.00;
            }
        } else if (dimension.equals(unitNameString[1])) {
            if (h.equals(unitNameString[2])) {
                unit(unitString[3], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[2];
                doubles[6] = 2.00;
            } else if (h.equals(unitNameString[3])) {
                unit(unitString[5], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[3];
                doubles[6] = 3.00;
            } else if (h.equals(unitNameString[0])) {
                unit(unitString[4], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[0];
                doubles[6] = 0.00;
            } else {
                unit("", finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[1];
                doubles[6] = 1.00;
            }
        } else if (dimension.equals(unitNameString[3])) {
            if (h.equals(unitNameString[2])) {
                unit(unitString[11], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[2];
                doubles[6] = 2.00;
            } else if (h.equals(unitNameString[1])) {
                unit(unitString[9], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[1];
                doubles[6] = 1.00;
            } else if (h.equals(unitNameString[0])) {
                unit(unitString[10], finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[0];
                doubles[6] = 0.00;
            } else {
                unit("", finish_x_, finish_y_, machine_x_, machine_y_, stock_x_, stock_y_, allow, Spine);
                dimension = unitNameString[3];
                doubles[6] = 3.00;
            }
        }
        return doubles;
    }
}