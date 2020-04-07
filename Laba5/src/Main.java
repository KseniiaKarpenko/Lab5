import Exceptions.*;
import Task.MusicBandList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Класс для исполнения программы.
 */
public class Main {

    /**
     * Метод для исполнения программы.
     * @param args входные значения.
     * @throws IOException проверяет, не прервалась ли операция ввода/вывода.
     * @throws ParseException проверяет, не произошла ли ошибка в приведении типов данных.
     * @throws NullValueException проверяет, не null ли строка введена.
     * @throws NotEnoughArgumentsException проверяет, достаточно ли аргументов введено.
     */
    public static void main(String[] args) throws IOException, ParseException, NullValueException, NotEnoughArgumentsException {
        if (args.length == 0)
        {
            System.out.println("Параметры отсутствуют");
            System.exit(1);
        }
        MusicBandList mbl = new MusicBandList(args[0]);

        try {
            mbl.loadFile();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Файл не найден");
            System.out.println("Попробуйте начать заново");
            System.exit(2);
        }

        boolean isRun = true;
        String inputString;
        Scanner scanner = new Scanner(System.in);

        while(isRun)
        {
            System.out.print("> ");
            inputString = scanner.nextLine();
            try {
                isRun = mbl.exec(inputString);
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Ошибка в типе данных.");
            }
            catch(NotFoundCommandException | IndexOutOfBoundsException ex)
            {
                System.out.println(ex.getMessage());
            } catch (ListEmptyException e) {
                System.out.println(e.getMessage());
            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
