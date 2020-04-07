package Exceptions;

/**
 * Класс для создания исключения, которое проверяет, не null ли строка введена.
 */
public class NullValueException extends Exception {

    /**
     * Конструктор, позволяющий задать сообщение для исключения.
     * @param message сообщение.
     */
    public NullValueException(String message) {super(message);}
}
