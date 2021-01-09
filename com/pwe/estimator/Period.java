package com.pwe.estimator;

public class Period extends Params {
    public boolean front_back;
    protected double period_per_flat;
    protected double number_of_periods;
    private double periods_to_view_per_panel;
    protected int qty_paper;


    public Period(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                  double s_size_y, double sp, double allow, int ppf, int nop) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp, allow);

        period_per_flat = ppf;
        number_of_periods = nop;

        working_x = finish_size_x + (2 * allowance);
        working_y = finish_size_y + (2 * allowance);

        //  Vertically
        x_v = (int) (machine_size_x / working_x);
        y_v = (int) (machine_size_y / working_y);

        //  Diagonally
        x_d = (int) (machine_size_x / working_y);
        y_d = (int) (machine_size_y / working_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;

    }

    public Period(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                  double s_size_y, double sp, double allow, int ppf, int nop, double cpr, double cpl, double cos,
                  double num_color, double ovh) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp, allow, cpr, cpl, cos, num_color, ovh);

        period_per_flat = ppf;
        number_of_periods = nop;
        qty_paper = quantity;

        working_x = finish_size_x + (2 * allowance);
        working_y = finish_size_y + (2 * allowance);

        //  Vertically
        x_v = (int) (machine_size_x / working_x);
        y_v = (int) (machine_size_y / working_y);

        //  Diagonally
        x_d = (int) (machine_size_x / working_y);
        y_d = (int) (machine_size_y / working_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    public Period(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                  double s_size_y, double sp, double allow, int ppf, int nop, double cpr, double cpl, double cos,
                  double num_color, double ovh, double cpi, double cof, double profit_) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp, allow, cpr, cpl, cos, num_color, ovh,
                cpi, cof, profit_);
        period_per_flat = ppf;
        number_of_periods = nop;
        qty_paper = quantity;

        working_x = finish_size_x + (2 * allowance);
        working_y = finish_size_y + (2 * allowance);

        //  Vertically
        x_v = (int) (machine_size_x / working_x);
        y_v = (int) (machine_size_y / working_y);

        //  Diagonally
        x_d = (int) (machine_size_x / working_y);
        y_d = (int) (machine_size_y / working_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    public int number_of_period_to_view() {
        if (front_back) periods_to_view_per_panel = (number_of_periods / period_per_flat) / 2;
        else periods_to_view_per_panel = number_of_periods / period_per_flat;
        return (int) periods_to_view_per_panel;
    }

    public double number_of_out() {
        double x_working_size;
        double y_working_size;
        double xVOut;
        double yVOut;
        double xDOut;
        double yDOut;
        double diagonal_out;
        double vertical_out;

        if (vertical > diagonal) {
            x_working_size = working_x * x_v;
            y_working_size = working_y * y_v;
        } else {
            x_working_size = working_x * x_d;
            y_working_size = working_y * y_d;
        }

        xVOut = (int) (stock_size_x / x_working_size);
        yVOut = (int) (stock_size_y / y_working_size);

        xDOut = (int) (stock_size_x / y_working_size);
        yDOut = (int) (stock_size_y / x_working_size);

        diagonal_out = xDOut * yDOut;
        vertical_out = xVOut * yVOut;

        return Math.max(diagonal_out, vertical_out);
    }

    public double number_of_wss() {
        return quantity * periods_to_view_per_panel / number_of_up();
    }

    @Override
    public double plate_cost() {
        if (front_back) return number_of_period_to_view() * 2 * number_color * co_plate;
        else return number_of_period_to_view() * number_color * co_plate;
    }

    @Override
    public double separation_cost() {
        if (front_back) return number_of_period_to_view() * 2 * co_separation;
        else return number_of_period_to_view() * co_separation;
    }

    @Override
    public double num_impression() {
        if (front_back) return (number_of_period_to_view() * 2 * number_color) * (qty_paper / number_of_up());
        else return (number_of_period_to_view() * number_color) * (qty_paper / number_of_up());
    }

    @Override
    public double cost_of_finishing() {
        return qty_paper * co_finishing;
    }
}
