package Exceptions;

/**
 * Класс для создания исключения, которое проверяет, известно ли поле программе.
 */
public class InvalidValueException extends Exception {

    /**
     * Конструктор, позволяющий задать сообщение для исключения.
     * @param s сообщение.
     */
    public InvalidValueException(String s) {super(s);}
}
