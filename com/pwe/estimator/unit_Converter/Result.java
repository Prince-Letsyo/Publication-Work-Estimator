package com.pwe.estimator.unit_Converter;

abstract public class Result extends UnitUtils {
    double[] doubles = new double[9];

    public double changeF(double unit, double v) {
        return Double.parseDouble(String.format("%.2f", v * unit));
    }

    public double notZero(double unit, double v) {
        return changeF(unit, v);
    }

    public void setDoubles(double x, double y, String paperType) {
        switch (paperType) {
            case "finish":
                doubles[0] = x;
                doubles[1] = y;
                break;
            case "stock":
                doubles[2] = x;
                doubles[3] = y;
                break;
            case "machine":
                doubles[4] = x;
                doubles[5] = y;
                break;
            case "allow":
                doubles[7] = x;
                doubles[8] = y;
                break;
        }
    }
}
