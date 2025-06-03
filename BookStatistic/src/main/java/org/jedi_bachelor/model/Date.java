package org.jedi_bachelor.model;

/*
Локальный класс для даты
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

public class Date implements Serializable {
    @Setter
    @Getter
    private int month;
    @Setter
    @Getter
    private int year;

    public Date() {

    }

    public Date(int _m, int _y) {
        if(_m >= 1 && _m <= 12 && _y > 0) {
            this.month = _m;
            this.year = _y;
        }
    }

    public static Date now() {
        int month = LocalDate.now().getDayOfMonth();
        int year = LocalDate.now().getDayOfYear();

        return new Date(month, year);
    }

    // Для отладки || Потом удали (!!!)
    @Override
    public String toString() {
        return month + " " + year;
    }
}