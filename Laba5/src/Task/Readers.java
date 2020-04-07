package Task;

import Exceptions.LimitValueException;
import Exceptions.NullValueException;
import Types.MusicGenre;

import java.util.Scanner;

/**
 * Класс для чтения команд.
 */
public class Readers {

    /**
     * Статический метод для чтения строк.
     * @param message считываемая строка
     * @param nullable равный нулю, значение false по умолчанию.
     * @return считываемая строка.
     * @throws NullValueException проверяет, не null ли строка введена.
     */
    public static String readString(String message, boolean nullable) throws NullValueException {
        String output = "";
        Scanner scan = new Scanner(System.in);
        do {
            if (output == null) {
                System.out.println("Строка не может быть пустой");
            }
            System.out.print(message);
            output = scan.nextLine();
            output = output.isEmpty() ? null : output;
        }while (!nullable && output == null);
        if(!nullable && output == null)
            throw new NullValueException("Ожидалась не null строка");
        return output;
    }

    /**
     * Статический метод для чтения значений Double и проверки на превышение максимального значения.
     * @param message считываемая строка.
     * @param max максимально возможное значение Double.
     * @param nullable равный нулю, значение false по умолчанию.
     * @return число Double, если число не превышает максимальное значение и нет ошибки в формате данных.
     * @throws NullValueException проверяет, не null строка ли введена.
     */
    public static Double readDoubleMax(String message, Double max, boolean nullable) throws NullValueException {
        String output = "";
        Double d;

        do {
            output = readString(message, nullable);

            try {
                d = Double.parseDouble(output);
                if (d > max)
                {
                    throw new LimitValueException("Значение не может быть больше " + max.toString());
                }
                break;
            }
            catch (LimitValueException ex) {
                System.out.println(ex.getMessage());
            }

            catch (NumberFormatException ex) {
                System.out.println ("Ошибка в формате данных");
            }
        }while (true);

        return Double.parseDouble(output);
    }

    /**
     * Статический метод для чтения значений Integer и проверки на превышение максимального значения.
     * @param message считываемая строка.
     * @param max максимально возможное значение Integer.
     * @param nullable равный нулю, значение false по умолчанию.
     * @return число Integer, если число не превышает максимальное значение и нет ошибки в формате данных.
     * @throws NullValueException проверяет, не null ли строка введена.
     */
    public static Integer readIntegerMax(String message, Integer max, boolean nullable) throws NullValueException {
        String output = "";
        Integer i;

        do {
            output = readString(message, nullable);

            try {
                i = Integer.parseInt(output);
                if (i > max)
                {
                    throw new LimitValueException("Значение не может быть больше " + max.toString());
                }
                break;
            }
            catch (LimitValueException ex) {
                System.out.println (ex.getMessage());
            }

            catch (NumberFormatException ex) {
                System.out.println ("Ошибка в формате данных");
            }

        }while (true);

        return Integer.parseInt(output);
    }

    /**
     * Статический метод для чтения значений Long и проверки, не меньше ли число минимального значения.
     * @param message считываемая строка.
     * @param min минимально возможное значение Long.
     * @param nullable равный нулю, значение false по умолчанию.
     * @return число Long, если число не меньше минимального значения и нет ошибки в формате данных.
     * @throws NullValueException проверяет, не null ли строка введена.
     */
    public static Long readLongMin(String message, Long min, boolean nullable) throws NullValueException {
        String output = "";
        Long l;

        do {
            output = readString(message, nullable);

            try {
                l = Long.parseLong(output);
                if (l <= 0) {
                    throw new LimitValueException("Значение не может быть меньше или равно 0");
                }
                break;
            }

            catch (LimitValueException ex) {
                System.out.println (ex.getMessage());
            }

            catch (NumberFormatException ex) {
                System.out.println ("Ошибка в вводе данных");
            }
        }while (true);

        return Long.parseLong(output);
    }

    /**
     * Статический метод для чтения поля жанр, который предпочитает музыкальная банда, и проверки на то, введено ли поле жанр, который предпочитает музыкальная группа из набора констант.
     * @param message считываемая строка.
     * @return музыкальный жанр, если он есть в наборе констант.
     * @throws NullValueException проверяет, не null ли строка введена.
     */
    static MusicGenre readMusicGenre(String message) throws NullValueException {
        String output = "";
        StringBuilder m = new StringBuilder();
        m.append("Жанры:");
        for (MusicGenre value : MusicGenre.values()) {
            m.append("\n").append(value.toString());
        }
        message = m + "\n" + message;
        do {
            output = readString(message, false);
        }while (MusicGenre.getMusicGenre(output) == null);

        return MusicGenre.getMusicGenre(output);
    }

}