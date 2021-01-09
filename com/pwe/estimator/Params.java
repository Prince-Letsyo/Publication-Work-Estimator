package com.pwe.estimator;

abstract public class Params {
    //PARAMS
    protected double stock_size_x;
    protected double stock_size_y;
    protected double finish_size_y;
    protected double finish_size_x;
    protected double machine_size_y;
    protected double machine_size_x;
    protected double working_x;
    protected double working_y;
    protected double vertical;
    protected double diagonal;
    protected double x_v;
    protected double y_v;
    protected double x_d;
    protected double y_d;
    protected double spoilage;
    protected double allowance;

    //MATERIAL
    protected int quantity;
    protected double overHead;
    protected double cp_ream;
    protected double co_plate;
    protected double number_color;
    protected double co_separation;

    //LABOUR
    public double profit;
    public double co_finishing;
    public double cp_impression;

    public Params(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                  double s_size_y, double sp, double allow) {
        machine_size_x = m_size_x;
        machine_size_y = m_size_y;
        finish_size_x = f_size_x;
        finish_size_y = f_size_y;
        stock_size_x = s_size_x;
        stock_size_y = s_size_y;
        quantity = q;
        spoilage = sp;
        allowance = allow;
    }

    public Params(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                  double s_size_y, double sp, double allow, double cpr, double cpl, double cos, double num_color, double ovh) {
        machine_size_x = m_size_x;
        machine_size_y = m_size_y;
        finish_size_x = f_size_x;
        finish_size_y = f_size_y;
        stock_size_x = s_size_x;
        stock_size_y = s_size_y;
        quantity = q;
        spoilage = sp;
        allowance = allow;
        cp_ream = cpr;
        co_plate = cpl;
        co_separation = cos;
        number_color = num_color;
        overHead = ovh;

    }

    public Params(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                  double s_size_y, double sp, double  allow, double cpr, double cpl, double cos, double num_color, double ovh,
                  double cpi, double cof, double profit_) {
        machine_size_x = m_size_x;
        machine_size_y = m_size_y;
        finish_size_x = f_size_x;
        finish_size_y = f_size_y;
        stock_size_x = s_size_x;
        stock_size_y = s_size_y;
        quantity = q;
        spoilage = sp;
        allowance = allow;
        cp_ream = cpr;
        co_plate = cpl;
        co_separation = cos;
        number_color = num_color;
        overHead = ovh;
        cp_impression = cpi;
        co_finishing = cof;
        profit = profit_;
    }

    //Params
    public double number_of_up() {
        return Math.max(diagonal, vertical);
    }

    abstract public double number_of_out();

    public double number_of_wss() {
        return quantity / number_of_up();
    }

    public double number_of_sss() {
        return number_of_wss() / number_of_out();
    }

    public double spoilage_percent() {
        return (spoilage / 100) * number_of_sss();
    }

    public double total_sss() {
        return spoilage_percent() + number_of_sss();
    }

    public double number_of_reams() {
        return total_sss() / 500;
    }

    //Material
    public double paper_cost() {
        return number_of_reams() * cp_ream;
    }

    abstract public double plate_cost();

    abstract public double separation_cost();

    public double material_cost() {
        return paper_cost() + plate_cost() + separation_cost();
    }

    public double over_head() {
        return (overHead / 100) * material_cost();
    }

    public double total_material_cost() {
        return material_cost() + over_head();
    }

    //Labour
    abstract public double num_impression();

    public double cost_of_impression() {
        return (num_impression() / 1000) * cp_impression;
    }

    abstract public double cost_of_finishing();

    public double cost_of_labour() {
        return cost_of_impression() + cost_of_finishing();
    }

    public double sub_total() {
        return cost_of_labour() + total_material_cost();
    }

    public double profit_cost() {
        return (profit / 100) * sub_total();
    }

    public double grand_total() {
        return sub_total() + profit_cost();
    }

    public double unit_cost() {
        return grand_total() / quantity;
    }
}
