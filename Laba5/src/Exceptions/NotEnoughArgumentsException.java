package Exceptions;

/**
 * Класс для создания исключения, которое проверяет, достаточно ли аргументов введено.
 */
public class NotEnoughArgumentsException extends NotFoundCommandException {

    /**
     * Конструктор, позволяющий задать сообщение для исключения.
     * @param message сообщение.
     */
    public NotEnoughArgumentsException (String message) {super(message); }
}
