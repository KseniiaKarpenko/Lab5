package Types;

import java.util.Locale;

/**
 * Класс, описывающий координаты музыкальной банды.
 */
public class Coordinates {

    /**
     * Поле координата x музыкальной банды. Максимальное значение поля: 75.
     */
    private Integer x; //Максимальное значение поля: 75, Поле не может быть null

    /**
     * Поле координата y музыкальной банды. Максимальное значение поля: 703.
     */
    private double y; //Максимальное значение поля: 703

    /**
     * Конструктор, позволяющий задать координату x, координату y музыкальной банды.
     * @param x координата x.
     * @param y координата y.
     */
    public Coordinates(Integer x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод для получения строки с координатами музыкальной банды в формате csv.
     * @return строка с координатами музыкальной банды в формате csv.
     */
    public String toCSVString()
    {
        return String.format(Locale.US,"%d;%f", x, y);
    }

    /**
     * Метод для получения строки с координатами музыкальной банды в формате (x, y).
     * @return строка с координатами музыкальной банды в формате (x, y).
     */
    @Override
    public String toString()
    {
        return String.format(Locale.US, "(%d, %f)", x, y);
    }
}