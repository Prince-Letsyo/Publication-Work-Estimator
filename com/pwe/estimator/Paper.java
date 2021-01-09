package com.pwe.estimator;

public class Paper extends Params {
    public Paper(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                 double s_size_y, double sp,double  allow) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp ,allow);

        x_v = (int) (machine_size_x / finish_size_x);
        y_v = (int) (machine_size_y / finish_size_y);

        //  Diagonally
        x_d = (int) (machine_size_x / finish_size_y);
        y_d = (int) (machine_size_y / finish_size_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    public Paper(
            double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
            double s_size_y, double sp, double  allow,double cpr, double cpl, double cos, double num_color, double ovh) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp,allow, cpr, cpl, cos, num_color, ovh);

        x_v = (int) (machine_size_x / finish_size_x);
        y_v = (int) (machine_size_y / finish_size_y);

        //  Diagonally
        x_d = (int) (machine_size_x / finish_size_y);
        y_d = (int) (machine_size_y / finish_size_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    public Paper(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                 double s_size_y, double sp, double allow,double cpr, double cpl, double cos, double num_color, double ovh, double cpi, double cof, double profit_) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp, allow,cpr, cpl, cos, num_color, ovh, cpi, cof, profit_);

        //  Vertically
        x_v = (int) (machine_size_x / finish_size_x);
        y_v = (int) (machine_size_y / finish_size_y);

        //  Diagonally
        x_d = (int) (machine_size_x / finish_size_y);
        y_d = (int) (machine_size_y / finish_size_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    @Override
    public double number_of_out() {
        double x_working_size;
        double y_working_size;

        if (vertical > diagonal) {
            x_working_size = (x_v * finish_size_x) + allowance;
            y_working_size = (y_v * finish_size_y) + allowance;

        } else {
            x_working_size = (x_d * finish_size_y) + allowance;
            y_working_size = (y_d * finish_size_x) + allowance;
        }

        // Number 0f out
        // Vertical_out
        double x_v_out;
        double y_v_out;

        x_v_out = (int) (stock_size_x / x_working_size);
        y_v_out = (int) (stock_size_y / y_working_size);

        // Diagonal_out
        double x_d_out;
        double y_d_out;

        x_d_out = (int) (stock_size_x / y_working_size);
        y_d_out = (int) (stock_size_y / x_working_size);

        double diagonal_out;
        double vertical_out;

        diagonal_out = x_d_out * y_d_out;
        vertical_out = x_v_out * y_v_out;

        return Math.max(diagonal_out, vertical_out);
    }

    @Override
    public double plate_cost() {
        return number_color * co_plate;
    }

    @Override
    public double separation_cost() {
        if (0 < number_color && number_color <= 3) {
            return number_color * co_separation;
        } else {
            return 2 * co_separation;
        }
    }

    @Override
    public double num_impression() {
        return number_of_wss() * number_color;
    }

    @Override
    public double cost_of_finishing() {
        return (number_of_wss() / 1000) * co_finishing;
    }
}


