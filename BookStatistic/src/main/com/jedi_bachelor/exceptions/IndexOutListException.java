package main.com.jedi_bachelor.exceptions;

/*
Класс исключения IndexOutListException

Вызывается, если введённый пользователем индекс либо меньше 0, либо больше длины списка
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

    public static void validateInput(int _index) throws IllegalPagesException {
        if (_index > books.size() || _index < 0) {
            throw new IllegalPagesException("Индекс вышел за пределы списка книг");
        }
    }
 */

public class IndexOutListException extends Exception {
    public IndexOutListException(String message) {
        super(message);
    }
}
