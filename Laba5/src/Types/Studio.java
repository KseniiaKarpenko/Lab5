package Types;

/**
 * Класс, описывающий студию, в которой располагается музыкальная банда.
 */
public class Studio implements Comparable {

    /**
     * Поле адрес студии музыкальной банды.
     */
    private String address = null; //Поле может быть null

    /**
     * Конструктор, позволяющий задать адресс студии, если адрес не null и длина адреса не равна 0.
     * @param address адрес студии музыкальной банды.
     */
    public Studio (String address)
    {
        if (address != null && address.length() != 0)
            this.address = address;
    }

    /**
     * Метод для возвращения строки адрес студии музыкальной банды.
     * @return адрес студии музыкальной банды.
     */
    @Override
    public String toString() {
        return address;
    }

    /**
     * Метод для сравнения адреса студии музыкальной банды.
     * @param o адрес студии музыкальной банды.
     * @return положительное число, если вызывающий объект больше объекта, переданного в качестве параметра; отрицательное число, если вызывающий объект меньше объекта, переданного в качестве параметра; нуль, если объекты равны.
     */
    @Override
    public int compareTo(Object o) {
        if (address == null) {
            return 1;
        }
        if (((Studio)o).address == null)
        {
            return -1;
        }
        return ((Studio)o).address.compareTo(this.address);
    }
}