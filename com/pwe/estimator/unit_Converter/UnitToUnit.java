package com.pwe.estimator.unit_Converter;

public class UnitToUnit extends Result {
    public void unitToUnit(String unitType, double x_, double y_, String paperType) {
        if (x_ == 0 && y_ == 0) setDoubles(x_, y_, paperType);
        else if (x_ != 0 && y_ != 0) setFigures(unitType, x_, y_, paperType);
        else setFigures(unitType, x_, y_, paperType);
    }

    private void setFigures(String unitType, double x_, double y_, String paperType) {
        switch (unitType) {
            case "cm_to_pica":
                setDoubles(notZero(unitDoubles[0], x_), notZero(unitDoubles[0], y_), paperType);
                break;
            case "cm_to_inches":
                setDoubles(notZero(unitDoubles[1], x_), notZero(unitDoubles[1], y_), paperType);
                break;
            case "cm_to_mm":
                setDoubles(notZero(unitDoubles[2], x_), notZero(unitDoubles[2], y_), paperType);
                break;
            case "pica_to_cm":
                setDoubles(notZero(unitDoubles[3], x_), notZero(unitDoubles[3], y_), paperType);
                break;
            case "pica_to_inches":
                setDoubles(notZero(unitDoubles[4], x_), notZero(unitDoubles[4], y_), paperType);
                break;
            case "pica_to_mm":
                setDoubles(notZero(unitDoubles[5], x_), notZero(unitDoubles[5], y_), paperType);
                break;
            case "inches_to_pica":
                setDoubles(notZero(unitDoubles[6], x_), notZero(unitDoubles[6], y_), paperType);
                break;
            case "inches_to_mm":
                setDoubles(notZero(unitDoubles[7], x_), notZero(unitDoubles[7], y_), paperType);
                break;
            case "inches_to_cm":
                setDoubles(notZero(unitDoubles[8], x_), notZero(unitDoubles[8], y_), paperType);
                break;
            case "mm_to_pica":
                setDoubles(notZero(unitDoubles[9], x_), notZero(unitDoubles[9], y_), paperType);
                break;
            case "mm_to_inches":
                setDoubles(notZero(unitDoubles[10], x_), notZero(unitDoubles[10], y_), paperType);
                break;
            case "mm_to_cm":
                setDoubles(notZero(unitDoubles[11], x_), notZero(unitDoubles[11], y_), paperType);
                break;
            default:
                setDoubles(notZero(1, x_), notZero(1, y_), paperType);
                break;
        }
    }
}
