package org.jedi_bachelor.model;

import java.time.LocalDate;

public class Date {
    private int month;
    private int year;

    public Date() {

    }

    public Date(int _m, int _y) {
        if(_m >= 1 && _m <= 12 && _y > 0) {
            this.month = _m;
            this.year = _y;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int _month) {
        this.month = _month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int _year) {
        this.year = _year;
    }

    public static Date now() {
        int month = LocalDate.now().getDayOfMonth();
        int year = LocalDate.now().getDayOfYear();

        return new Date(month, year);
    }
}
