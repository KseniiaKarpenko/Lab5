package Exceptions;

/**
 * Класс для создания исключения, которое проверяет, попадает ли заданное значение в пределы значений.
 */
public class LimitValueException extends Exception{

    /**
     * Конструктор, позволяющий задать сообщение для исключения.
     * @param message сообщение.
     */
    public LimitValueException (String message) {super(message); }
}
