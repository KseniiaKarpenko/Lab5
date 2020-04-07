package Types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий музыкальную банду.
 */
public class MusicBand implements Comparable{

    /**
     * Поле идентификационный номер музыкальной банды. Значение этого поля, которое является уникальным, генерируется автоматически и не может быть меньше 0.
     */
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    /**
     * Поле название музыкальной банды.
     */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /**
     * Поле координаты музыкальной банды.
     */
    private Coordinates coordinates; //Поле не может быть null

    /**
     * Поле дата основания музыкальной банды. Значение этого поля генерируется автоматически.
     */
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    /**
     * Поле количество участников музыкальной банды. Значение поля должно быть больше 0.
     */
    private Long numberOfParticipants; //Поле не может быть null, Значение поля должно быть больше 0

    /**
     * Поле жанр, который предпочитает музыкальная банда.
     */
    private MusicGenre genre; //Поле не может быть null

    /**
     * Поле студия, в которой располагается музыкальная банда.
     */
    private Studio studio; //Поле не может быть null

    /**
     * Конструктор, позволяющий задать идентификационный номер, название, координаты, дату основания, число участников, жанр, студию.
     * @param id идентификационный номер музыкальной банды.
     * @param name название музыкальной банды.
     * @param coordinates координаты музыкальной банды.
     * @param creationDate дата основания музыкальной банды.
     * @param numberOfParticipants количество участников музыкальной банды.
     * @param genre жанр, который предпочитает музыкальная банда.
     * @param studio студия, в которой располагаетая музыкальная банда.
     */
    public MusicBand (int id, String name, Coordinates coordinates,java.time.LocalDate creationDate, Long numberOfParticipants, MusicGenre genre, Studio studio)
    {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.studio = studio;
    }

    /**
     * Метод для задания значения поля название музыкальной банды.
     * @param name название музыкальной банды.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Метод для задания значения поля координаты музыкальной банды.
     * @param coordinates координаты музыкальной банды.
     */
    public void setCoordinates(Coordinates coordinates)
    {
        this.coordinates = coordinates;
    }

    /**
     * Метод для задания значения поля дата основания музыкальной банды.
     * @param date дата основания музыкальной банды.
     */
    public void setDate(LocalDate date)
    {
        this.creationDate = date;
    }

    /**
     * Метод для задания значения поля жанр, который предпочитает музыкальная банда.
     * @param genre жанр, который предпочитает музыкальная банда.
     */
    public void setMusicGenre(MusicGenre genre)
    {
        this.genre = genre;
    }

    /**
     * Метод для получения значения поля жанр, который предпочитает музыкальная банда.
     * @return жанр, который предпочитает музыкальная банда.
     */
    public MusicGenre getGenre()
    {
        return this.genre;
    }


    /**
     * Метод для задания значения поля количество участников музыкальной банды.
     * @param value количество участников музыкальной банды.
     */
    public void setNumberOfParticipants(Long value)
    {
        this.numberOfParticipants = value;
    }


    /**
     * Метод для получения значения поля количество участников музыкальной банды.
     * @return количество участников музыкальной банды.
     */
    public Long getNumberOfParticipants()
    {
        return this.numberOfParticipants;
    }


    /**
     * Метод для задания значения поля студия, в которой располагается музыкальная банда.
     * @param studio название студии, в которой располагается музыкальная банда.
     */
    public void setStudio(Studio studio)
    {
        this.studio = studio;
    }

    /**
     * Метод для получения значения поля студия, в которой располагается музыкальная банда.
     * @return студия, в которой располагается музыкальная банда.
     */
    public Studio getStudio()
    {
        return this.studio;
    }

    /**
     * Метод для получения строки с данными в формате csv.
     * @return строка с данными в формате csv.
     */
    public String toCSVString()
    {
        return String.format("%d;%s;%s;%s;%d;%s;%s",
                id, name, coordinates.toCSVString(),
                creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), numberOfParticipants, genre, studio);
    }

    /**
     * Метод для получения значения поля идентификационный номер.
     * @return идентификационный номер музыкальной банды.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Метод для получения строки, где каждое поле выводится на новой строке.
     * @return строка, где каждое поле выводится на новой строке.
     */
    @Override
    public String toString()
    {
        return String.format("ID: %d\nName: %s\nCoordinates: %s\nDate: %s\nNumber of parts: %d\nGenre: %s\nStudio: %s\n",
                id, name, coordinates, creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                numberOfParticipants, genre, studio);
    }

    /**
     * Метод для сравнения идентификационных номеров.
     * @param o идентификационный номер из коллекции.
     * @return положительное число, если вызывающий объект больше объекта, переданного в качестве параметра; отрицательное число, если вызывающий объект меньше объекта, переданного в качестве параметра; нуль, если объекты равны.
     */
    @Override
    public int compareTo(Object o) {
        return this.id - ((MusicBand) o).id;
    }
}