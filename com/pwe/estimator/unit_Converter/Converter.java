package com.pwe.estimator.unit_Converter;

import java.util.List;

abstract public class Converter extends UnitToUnit {
    String[] strings = new String[2];


    public String changeF(double v) {
        return String.format("%.2f", v);
    }

    public void setStrings(double x, double y) {
        strings[0] = changeF(x);
        strings[1] = changeF(y);
    }

    public void insert_p(String h, List<Double> x, List<Double> y) {
        if (h.equals(paperSizeName[0])) {
            setStrings(x.get(0), y.get(0));
        } else if (h.equals(paperSizeName[1])) {
            setStrings(x.get(1), y.get(1));
        } else if (h.equals(paperSizeName[2])) {
            setStrings(x.get(2), y.get(2));
        } else if (h.equals(paperSizeName[3])) {
            setStrings(x.get(3), y.get(3));
        } else if (h.equals(paperSizeName[4])) {
            setStrings(x.get(4), y.get(4));
        } else if (h.equals(paperSizeName[5])) {
            setStrings(x.get(5), y.get(5));
        } else if (h.equals(paperSizeName[6])) {
            setStrings(x.get(6), y.get(6));
        }
    }

    public void insert_m(String h, List<Double> x, List<Double> y) {
        if (h.equals(machineSizeName[0])) {
            setStrings(x.get(0), y.get(0));
        } else if (h.equals(machineSizeName[1])) {
            setStrings(x.get(1), y.get(1));
        } else if (h.equals(machineSizeName[2])) {
            setStrings(x.get(2), y.get(2));
        } else if (h.equals(machineSizeName[3])) {
            setStrings(x.get(3), y.get(3));
        } else if (h.equals(machineSizeName[4])) {
            setStrings(x.get(4), y.get(4));
        } else if (h.equals(machineSizeName[5])) {
            setStrings(x.get(5), y.get(5));
        }
    }

    public void populateSize(String h, int index) {
        List<Double> x = PaperSizes.getUnit(unitNameString[index], Type.WORK, 'x');
        List<Double> y = PaperSizes.getUnit(unitNameString[index], Type.WORK, 'y');
        insert_p(h, x, y);
    }

    public void populateSizeMachine(String h, int index) {
        List<Double> x = PaperSizes.getUnit(unitNameString[index], Type.MACHINE, 'x');
        List<Double> y = PaperSizes.getUnit(unitNameString[index], Type.MACHINE, 'y');
        insert_m(h, x, y);
    }

    public void unit(String unitType, String finish_x_, String finish_y_, String machine_x_, String
            machine_y_, String stock_x_, String stock_y_, String allow,String   Spine) {
        unitToUnit(unitType, replace(finish_x_), replace(finish_y_), "finish");
        unitToUnit(unitType, replace(machine_x_), replace(machine_y_), "machine");
        unitToUnit(unitType, replace(stock_x_), replace(stock_y_), "stock");
        unitToUnit(unitType, replace(allow), replace(Spine), "allow");
    }

    private double replace(String string) {
        return Double.parseDouble(string);
    }
}
