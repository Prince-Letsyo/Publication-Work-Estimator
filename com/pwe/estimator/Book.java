package com.pwe.estimator;

public class Book extends Params {
    public double _fullColourPages;
    public double _oneColourPages;
    public double working_paper_size;
    public double num_imp;
    public double w_imp;

    protected int pages;
    protected double spine;
    private double numReamCover;
    protected double cmyk;
    protected double _yk;
    protected double _pl_c;
    protected double _part_colour;
    protected double _fc;
    protected double sc;
    protected double flat;
    protected double qty_book;


    public Book(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                double s_size_y, double sp, double allow, int page) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp, allow);
        pages = page;
        qty_book = quantity;
        working_x = (finish_size_x * 2) + (2 * allowance);
        working_y = (finish_size_y * 2) + (4 * allowance);
        //  Vertically
        x_v = (int) (machine_size_x / working_x);
        y_v = (int) (machine_size_y / working_y);

        //  Diagonally
        x_d = (int) (machine_size_x / working_y);
        y_d = (int) (machine_size_y / working_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    public Book(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                double s_size_y, double sp, double spi, double allow, int page, double cpr, double cpl, double cos,
                double num_color, double ovh, double cover_hue, double __part_colour, double film_) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp, allow, cpr, cpl, cos, num_color, ovh);
        spine = spi;
        pages = page;
        flat = film_;
        cmyk = number_color;
        _yk = cover_hue;
        _part_colour = __part_colour;
        qty_book = quantity;


        working_x = (finish_size_x * 2) + (2 * allowance);
        working_y = (finish_size_y * 2) + (4 * allowance);
        //  Vertically
        x_v = (int) (machine_size_x / working_x);
        y_v = (int) (machine_size_y / working_y);

        //  Diagonally
        x_d = (int) (machine_size_x / working_y);
        y_d = (int) (machine_size_y / working_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    public Book(double m_size_x, double m_size_y, double f_size_x, double f_size_y, int q, double s_size_x,
                double s_size_y, double sp, double spi, double allow, int page, double cpr, double cpl, double cos,
                double num_color, double ovh, double cpi, double cof, double profit_, double cover_hue,
                double __part_colour, double film_) {
        super(m_size_x, m_size_y, f_size_x, f_size_y, q, s_size_x, s_size_y, sp, allow, cpr, cpl, cos, num_color, ovh,
                cpi, cof, profit_);
        spine = spi;
        pages = page;
        flat = film_;
        cmyk = number_color;
        _yk = cover_hue;
        _part_colour = __part_colour;
        qty_book = quantity;
        // Pages

        working_x = (finish_size_x * 2) + (2 * allowance);
        working_y = (finish_size_y * 2) + (4 * allowance);
        //  Vertically
        x_v = (int) (machine_size_x / working_x);
        y_v = (int) (machine_size_y / working_y);

        //  Diagonally
        x_d = (int) (machine_size_x / working_y);
        y_d = (int) (machine_size_y / working_x);

        vertical = x_v * y_v;
        diagonal = x_d * y_d;
    }

    public double number_of_section() {
        return (double) pages / (double) (4 * 2);
    }

    public double number_of_panels() {
        return number_of_section() / number_of_up();
    }

    @Override
    public double number_of_out() {
        double x_working_size;
        double y_working_size;

        if (vertical > diagonal) {
            x_working_size = x_v * working_x;
            y_working_size = y_v * working_y;

        } else {
            x_working_size = x_d * working_y;
            y_working_size = y_d * working_x;
        }

        // Number 0f out
        // Vertical_out
        double xVOut;
        double yVOut;
        xVOut = (int) (stock_size_x / x_working_size);
        yVOut = (int) (stock_size_y / y_working_size);

        // Diagonal_out
        double xDOut;
        double yDOut;
        xDOut = (int) (stock_size_x / y_working_size);
        yDOut = (int) (stock_size_y / x_working_size);

        double diagonal_out;
        double vertical_out;
        diagonal_out = xDOut * yDOut;
        vertical_out = xVOut * yVOut;

        return Math.max(diagonal_out, vertical_out);
    }

    public double number_of_wss() {
        return quantity * number_of_section() / number_of_up();
    }

    public double ream_for_cover() {
        //Cover Size
        double i = (finish_size_x * 2) + spine;
        double j = finish_size_y;
        double newCover_x_size;
        double newCover_y_size;

        if (i > j) {
            newCover_x_size = j;
            newCover_y_size = i;
        } else {
            newCover_x_size = i;
            newCover_y_size = j;
        }

        double workingSize_x_cover;
        double workingSize_y_cover;

        if (newCover_x_size > 0 && newCover_y_size > 0) {
            //New Cover WorkingSize
            workingSize_x_cover = newCover_x_size + (2 * allowance);
            workingSize_y_cover = newCover_y_size + (2 * allowance);

            //Number of Cover to view(Machine)
            //Vertically
            double xv_cover;
            double yv_cover;
            double xd_cover;
            double yd_cover;

            xv_cover = (int) (machine_size_x / workingSize_x_cover);
            yv_cover = (int) (machine_size_y / workingSize_y_cover);

            //Diagonally
            xd_cover = (int) (machine_size_x / workingSize_y_cover);
            yd_cover = (int) (machine_size_y / workingSize_x_cover);

            double vertical_cover;
            double diagonal_cover;

            vertical_cover = xv_cover * yv_cover;
            diagonal_cover = xd_cover * yd_cover;

            double up_cover;
            up_cover = Math.max(vertical_cover, diagonal_cover);

            //New Working size
            double x_working_size;
            double y_working_size;

            if (vertical_cover > diagonal_cover) {
                x_working_size = (xv_cover * workingSize_x_cover);
                y_working_size = (yv_cover * workingSize_y_cover);
            } else {
                x_working_size = (xd_cover * workingSize_y_cover);
                y_working_size = (yd_cover * workingSize_x_cover);
            }

            //NUMBER OUT
            //Vertical_out
            double xv_out;
            double yv_out;
            xv_out = (int) (stock_size_x / x_working_size);
            yv_out = (int) (stock_size_y / y_working_size);

            //Diagonal_out
            double xd_out;
            double yd_out;
            xd_out = (int) (stock_size_x / y_working_size);
            yd_out = (int) (stock_size_y / x_working_size);

            double diagonal_out;
            double vertical_out;
            diagonal_out = xd_out * yd_out;
            vertical_out = xv_out * yv_out;

            double out_cover;
            out_cover = Math.max(vertical_out, diagonal_out);

            //Working Size for cover

            working_paper_size = quantity / up_cover;

            //Stock Size for cover
            double stock_paper_size;
            stock_paper_size = working_paper_size / out_cover;

            //number of ream for cover
            numReamCover = stock_paper_size / 500;
        }
        return numReamCover;
    }

    public double number_of_reams() {
        double reams = total_sss() / 500;
        if (spine > 0) {
            reams += ream_for_cover();
        }
        return reams;
    }

    @Override
    public double plate_cost() {
        double number_of_plates;
        if (flat == 0 || number_color == 1) {
            _part_colour = 0;
        } else {
            _fullColourPages = (_part_colour / pages) * number_of_section();
            _oneColourPages = number_of_section() - _fullColourPages;
        }
        if (1 <= number_color && number_color < 4) {
            if (spine > 0) {
                if (_yk > 0) {
                    if (_part_colour == 0) {
                        number_of_plates = (number_of_section() * 2 * number_color) / number_of_up() + _yk;
                    } else {
                        number_of_plates = (((_fullColourPages * 2 * number_color) / number_of_up()) + (_oneColourPages * 2) / number_of_up()) + _yk;
                    }
                    _pl_c = co_plate * number_of_plates;
                }
            } else if (spine == 0) {
                if (_part_colour == 0) {
                    number_of_plates = (number_of_section() * 2 * number_color) / number_of_up();
                } else {
                    number_of_plates = ((_fullColourPages * 2 * number_color) / number_of_up()) + ((_oneColourPages * 2) / number_of_up());
                }
                _pl_c = co_plate * number_of_plates;
            }
        } else {
            if (spine > 0) {
                if (0 < _yk) {
                    if (_part_colour == 0) {
                        number_of_plates = (number_color * 2 * number_of_section()) / number_of_up() + _yk;
                    } else {
                        number_of_plates = ((_fullColourPages * 2 * number_color) / number_of_up()) + ((_oneColourPages * 2) / number_of_up()) + _yk;
                    }
                    _pl_c = co_plate * number_of_plates;
                }
            } else if (spine == 0) {
                if (_part_colour == 0) {
                    number_of_plates = (number_color * 2 * number_of_section()) / number_of_up();
                } else {
                    number_of_plates = ((_fullColourPages * 2 * number_color) / number_of_up()) + ((_oneColourPages * 2) / number_of_up());
                }
                _pl_c = co_plate * number_of_plates;
            }
        }
        return _pl_c;
    }

    public double film_cost() {
        if (flat == 0 || number_color == 1) {
            _part_colour = 0;
        }
        if (quantity == _part_colour) {
            flat = 0;
        }
        if (_part_colour > 0) {
            _fullColourPages = (_part_colour / pages) * number_of_section();
            _oneColourPages = number_of_section() - _fullColourPages;

            if ((1 <= _yk && _yk < 4) && number_color == 4) {
                _fc = _oneColourPages * 2 * flat + (2 * _yk * flat);
            } else if (_yk == 4 && (number_color >= 1 && number_color < 4)) {
                _fc = _oneColourPages * 2 * number_color * flat;
            } else if (_yk == 4 && number_color == 4) {
                _fc = _oneColourPages * 2 * flat;
            } else if (flat == 0) {
                if (_yk == 4 && number_color == 4) {
                    _fc = 0;
                } else if (_yk < 4 && _yk >= 1 && number_color == 4) {
                    _fc = 0;
                } else if (number_color < 4 && number_color >= 1 && _yk == 4) {
                    _fc = 0;
                } else if (_yk == 1 && number_color == 1) {
                    _fc = 0;
                }
            } else if (flat != 0) {
                if ((_yk >= 1 && _yk < 4) && (number_color >= 1 && number_color < 4)) {
                    _fc = (number_of_section() * 2 * number_color * flat) + (2 * _yk * flat);
                }
            }
        } else if (_part_colour == 0) {
            if (flat != 0) {
                if (_yk == 4 && number_color == 4) {
                    _fc = 0;
                } else if ((1 <= _yk && _yk < 4) && number_color >= 1 && number_color < 4) {
                    _fc = (number_of_section() * 2 * number_color * flat) + (2 * _yk * flat);
                } else if (_yk == 4 && number_color >= 1 && number_color < 4) {
                    _fc = number_of_section() * 2 * number_color * flat;
                } else if (number_color == 4 && _yk >= 1 && _yk < 4) {
                    _fc = 2 * _yk * flat;
                }
            } else {
                if (_yk == 4 && number_color == 4) {
                    _fc = 0;
                } else if (_yk == 4 && number_color >= 1 && number_color <= 3) {
                    _fc = 0; //_f=0
                } else if (number_color == 4 && _yk >= 1 && _yk <= 3) {
                    _fc = 0; //_f=0
                } else {
                    _fc = 0;
                }
            }
        }
        return _fc;
    }

    @Override
    public double separation_cost() {
        if (_part_colour > 0) {
            if ((1 <= _yk && _yk < 4) && number_color == 4) {
                sc = _fullColourPages * 2 * co_separation;
            } else if (_yk == 4 && (number_color >= 1 && number_color < 4)) {
                sc = _fullColourPages * 2 * co_separation + (2 * co_separation);
            } else if (_yk == 4 && number_color == 4) {
                sc = (_fullColourPages * 2 * co_separation) + (2 * co_separation);
            } else if (flat == 0) {
                if (_yk == 4 && number_color == 4) {
                    sc = _fullColourPages * 2 * co_separation + (2 * co_separation);
                } else if (_yk < 4 && _yk >= 1 && number_color == 4) {
                    sc = _fullColourPages * 2 * co_separation;
                } else if (number_color < 4 && number_color >= 1 && _yk == 4) {
                    sc = 2 * co_separation;
                } else if (_yk == 1 && number_color == 0) {
                    sc = 0;
                }
            } else if (flat != 0) {
                if ((_yk >= 1 && _yk < 4) && (number_color >= 1 && number_color < 4)) {
                    sc = 0;
                }
            }
        } else if (_part_colour == 0) {
            if (flat != 0) {
                if (_yk == 4 && number_color == 4) {
                    sc = (number_of_section() * 2 * co_separation) + (2 * co_separation);
                } else if ((1 <= _yk && _yk < 4) && number_color >= 1 && number_color < 4) {
                    sc = 0;
                } else if (_yk == 4 && number_color >= 1 && number_color < 4) {
                    sc = 2 * co_separation;
                } else if (number_color == 4 && _yk >= 1 && _yk < 4) {
                    sc = (number_of_section() * 2 * co_separation);
                }
            } else {
                if (_yk == 4 && number_color == 4) {
                    sc = (number_of_section() * 2 * co_separation) + (2 * co_separation);
                } else if (_yk == 4 && number_color >= 1 && number_color <= 3) {
                    sc = 2 * co_separation;
                } else if (number_color == 4 && _yk >= 1 && _yk <= 3) {
                    sc = number_of_section() * 2 * co_separation;
                } else {
                    sc = 0;
                }
            }
        }
        return sc;
    }


    public double material_cost() {
        return paper_cost() + plate_cost() + separation_cost() + film_cost();
    }

    @Override
    public double num_impression() {
        double one_colour_imp;
        double full_colour_imp;
        double cover_imp;

        if (film_cost() > 0) {
            if (spine > 0) {
                one_colour_imp = (_oneColourPages * 2 * qty_book) / number_of_up();
                full_colour_imp = (_fullColourPages * 2 * cmyk * qty_book) / number_of_up();
                cover_imp = working_paper_size * _yk;
                num_imp = one_colour_imp + full_colour_imp + cover_imp;
            } else if (spine == 0) {
                one_colour_imp = (_oneColourPages * 2 * qty_book) / number_of_up();
                full_colour_imp = (_fullColourPages * 2 * cmyk * qty_book) / number_of_up();
                num_imp = one_colour_imp + full_colour_imp;
            } else if (film_cost() == 0) {
                if (spine > 0) {
                    w_imp = (qty_book * cmyk) / number_of_up();
                    cover_imp = working_paper_size * _yk;
                    num_imp = w_imp + cover_imp;
                } else if (spine == 0) {
                    w_imp = (qty_book * cmyk) / number_of_up();
                    num_imp = w_imp;
                }
            }
        }
        return num_imp;
    }

    @Override
    public double cost_of_finishing() {
        return co_finishing * qty_book;
    }
}