package Exceptions;

/**
 * Класс для создания исключения, которое проверяет, существует ли введенная программа.
 */
public class NotFoundCommandException extends Throwable {

    /**
     * Конструктор, позволяющий задать сообщения для исключения.
     * @param message сообщение.
     */
    public NotFoundCommandException (String message) {super(message); }
}
