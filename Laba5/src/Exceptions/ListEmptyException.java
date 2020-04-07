package Exceptions;

/**
 * Класс для создания исключения, которое проверяет, не пуст ли список.
 */
public class ListEmptyException extends Exception {

    /**
     * Конструктор, позволяющий задать сообщение для исключения.
     * @param s сообщение.
     */
    public ListEmptyException(String s) {super(s); }
}
