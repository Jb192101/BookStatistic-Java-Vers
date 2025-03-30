package main.com.jedi_bachelor.exceptions;

/*
Класс исключения IllegalPagesException

Вызывается, если количество прочитанных страниц больше всех страниц в книге
или если произведён некорректный ввод данных
 */

/*
Пример:

    public static void main(String[] args) {
        try {
            validateInput(123);
        } catch (IllegalPagesException e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        }
    }

    public static void validateInput(int _pages) throws IllegalPagesException {
        if (_completePages < this.allPages) {
            throw new IllegalPagesException("Количество прочитанных страниц больше общего числа страниц!");
        }
    }
 */

import java.lang.Exception;

public class IllegalPagesException extends Exception {
    public IllegalPagesException(String message) {
        super(message);
    }
}
