package Task;

import Exceptions.*;
import Types.Coordinates;
import Types.MusicBand;
import Types.MusicGenre;
import Types.Studio;
import Task.Readers;

import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Класс, описывающий команды, доступные для управления коллекцией объектов.
 */
public class MusicBandList {

    /**
     * Поле элемент коллекции.
     */
    LinkedList<MusicBand> list;

    /**
     * Поле имя файла.
     */
    String fileName;

    /**
     * Поле дата инициализации.
     */
    Date initDate;

    /**
     * Конструктор, позволяющий задать имя файла и создать объект коллекции, дату инициализации.
     * @param fileName имя файла.
     */
    public MusicBandList(String fileName) {
        this.fileName = fileName;
        list = new LinkedList<MusicBand>();
        initDate = new Date();
    }

    /**
     * Метод для вывода справки по доступным командам.
     */
    public void help() {
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element} : добавить новый элемент в коллекцию");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла");
        System.out.println("exit : завершить программу");
        System.out.println("insert_at index {element} : добавить новый элемент в заданную позицию");
        System.out.println("add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("average_of_number_of_participants : вывести среднее значение поля numberOfParticipants для всех элементов коллекции");
        System.out.println("filter_by_genre genre : вывести элементы, значение поля genre которых равно заданному");
        System.out.println("filter_less_than_studio studio : вывести элементы, значение поля studio которых меньше заданного");
    }

    /**
     * Метод для очистки коллекции.
     */
    public void clear() { list.clear(); }

    /**
     * Метод для считывания и выполнения скрипта из указанного файла.
     * @param fileName имя файла.
     * @throws IOException проверяет, не прервалась ли операция ввода/вывода.
     * @throws NullValueException проверяет, не null ли строка введена.
     * @throws NotFoundCommandException проверяет, существует ли введенная команда.
     * @throws NotEnoughArgumentsException проверяет, достаточно ли аргументов введено.
     * @throws ListEmptyException проверяет, не пуст ли список.
     * @throws InvalidValueException проверяет, известно ли поле программе.
     */
    public void executeScript(String fileName) throws IOException, NullValueException,
            NotFoundCommandException, NotEnoughArgumentsException, ListEmptyException, InvalidValueException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            try {
                exec(line);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Метод для считывания файла.
     * @throws IOException проверяет, не прервалась ли операция ввода/вывода.
     * @throws ParseException проверяет, не произошла ли ошибка в приведении типов данных.
     */
    public void loadFile() throws IOException, ParseException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(";");
            int id = Integer.parseInt(fields[0]);
            String name = fields[1];
            Integer x = Integer.parseInt(fields[2]);
            double y = Double.parseDouble(fields[3]);
            LocalDate date = LocalDate.parse(fields[4], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Long numberOfParticipants = Long.parseLong(fields[5]);
            MusicGenre genre = MusicGenre.getMusicGenre(fields[6]);
            String address = "";
            if (fields.length > 7)
                address = fields[7];
            list.add(new MusicBand(id, name, new Coordinates(x, y), date,
                    numberOfParticipants, genre, new Studio(address)));
        }
        br.close();
        fr.close();
    }

    /**
     * Метод для сохранения коллекции в файл.
     * @throws IOException проверяет, не прервалась ли операция ввода/вывода.
     */
    public void save() throws IOException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        for (MusicBand m : list) {
            bw.write(m.toCSVString());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    /**
     * Метод для удаления элемента из коллекции по его идентификационному номеру.
     * @param id идентификационный номер.
     */
    public void removeById(int id) {
        if (list.size() == 0)
        {
            System.out.println("Список пуст!");
        }
        else {
            list.removeIf(o -> o.getId() == id);
        }
    }

    /**
     * Метод для вывода в стандартный поток всех элементов коллекции.
     */
    public void show() {
        for (MusicBand m : list) {
            System.out.println(m);
        }
        if (list.size() == 0)
        {
            System.out.println("Список пуст!");
        }
    }

    /**
     * Метод для создания элемента коллекции.
     * @param id идентификацонный номер.
     * @param line считываемая строка.
     * @return элемент коллекции.
     * @throws NullValueException проверяет, не null ли строка введена.
     */
    public MusicBand createElement(Integer id, String line) throws NullValueException {
        String name;
        Integer x;
        double y;
        LocalDate date;
        Long numberOfParticipants;
        MusicGenre genre;
        String address;

        if (line.length() > 0) {
            line = line.replaceAll("[{}\\s]", "");
            String[] values = line.split(";");
            name = values[0];
            x = Integer.parseInt(values[1]);
            y = Double.parseDouble(values[2]);
            date = LocalDate.parse(values[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            numberOfParticipants = Long.parseLong(values[4]);
            genre = MusicGenre.getMusicGenre(values[5]);
            address = "";
            if (values.length > 6)
                address = values[6];
        }
        else
        {
            name = Readers.readString("Введите имя: ", false);
            x = Readers.readIntegerMax("Введите координату x: ", 75, false);
            y = Readers.readDoubleMax("Введите координату y: ", 411.0, false);
            numberOfParticipants = Readers.readLongMin("Введите кол-во участников: ", 0L, false);
            genre = Readers.readMusicGenre("Введите жанр: ");
            address = Readers.readString("Введите адрес студии: ", true);
            date = LocalDate.now();
        }

        if (id == null) {
            id = this.list.stream().max((a, b) -> (Integer.compare(a.getId(), b.getId()))).get().getId();
            return new MusicBand(id + 1, name, new Coordinates(x, y), date,
                    numberOfParticipants, genre, new Studio(address));
        } else {
            Integer finalId = id;
            MusicBand mb = list.stream().filter(o -> o.getId() == finalId).findFirst().get();
            if (mb == null)
                throw new NullValueException("id не найден!");
            int index = list.indexOf(mb);
            list.get(index).setName(name);
            list.get(index).setCoordinates(new Coordinates(x, y));
            list.get(index).setDate(date);
            list.get(index).setNumberOfParticipants(numberOfParticipants);
            list.get(index).setMusicGenre(genre);
            list.get(index).setStudio(new Studio(address));
            return mb;
        }
    }

    /**
     * Метод для добавления нового элемента в коллекцию.
     * @param line считываемая строка.
     * @throws NullValueException проверяет, не null ли строка введена.
     */
    public void add(String line) throws NullValueException {
        list.add(createElement(null, line));
    }

    /**
     * Метод для обновления значения элемента коллекции, идентификационный номер которого равен заданному.
     * @param id заданный идентификационный номер.
     * @param line считываемая строка.
     * @throws NullValueException проверяет, не null ли строка.
     */
    public void update(int id, String line) throws NullValueException {
        if (list.size() == 0)
        {
            System.out.println("Список пуст!");
        }
        else {
            createElement(id, line);
        }
    }

    /**
     * Метод для вывода в стандартный поток вывода информации о коллекции.
     */
    public void info()
    {
        System.out.printf("Тип коллекции: MusicBand\nКол-во элементов: %d\nДата инициализации: %s\n", list.size(), initDate.toString());
    }

    /**
     * Метод для добавления нового элемента в заданную позицию.
     * @param mb элемент коллекции.
     * @param index позиция.
     */
    public void insertAt(MusicBand mb, int index)
    {
        list.add(index, mb);
    }

    /**
     * Метод для вывода элементов, значение поля студия, в которой располагается музыкальная банда, которых меньше заданного.
     * @param studio студия, в которой располагается музыкальная банда.
     */
    public void filterLessThanStudio(Studio studio)
    {
        if (list.size() == 0)
        {
            System.out.println("Список пуст!");
        }
        else {
            for (MusicBand m : list) {
                if (m.getStudio() != null && m.getStudio().compareTo(studio) > 0)
                    System.out.println(m);
            }
        }
    }

    /**
     * Метод для вывода элементов, значение поле жанр, который предпочитает музыкальная банда, которых равно заданному.
     * @param genre жанр, который предпочитает музыкальная банда.
     * @throws InvalidValueException проверяет, известно ли поле программе.
     */
    public void filterByGenre(MusicGenre genre) throws InvalidValueException {
        if (genre == null) {
            throw new InvalidValueException("Жанр не известен");
        }
        if (list.size() == 0)
        {
            System.out.println("Список пуст!");
        }
        else {
            for (MusicBand m : list) {
                if (m.getGenre() == genre)
                    System.out.println(m);

            }
        }
    }

    /**
     * Метод для вывода среднего значения поля количество участников музыкальной банды.
     * @return среднее значение поля количество участников музыкальной банды.
     */
    public double averageOfNumberOfParticipants()
    {
        return list.stream().mapToDouble(MusicBand::getNumberOfParticipants).average().getAsDouble();
    }

    /**
     * Метод для считывания и выполнения команд.
     * @param inputCommand введенная команда.
     * @return значение true, пока не будет вызвана команда exit.
     * @throws IOException проверяет, не прервалась ли операция ввода/вывода.
     * @throws NullValueException проверяет, не null ли строка введена.
     * @throws NotFoundCommandException проверяет, существует ли введенная команда.
     * @throws NotEnoughArgumentsException проверяет, достаточно ли аргументов введено.
     * @throws ListEmptyException проверяет, не пуст ли список.
     * @throws InvalidValueException проверяет, известно ли поле программе.
     */
    public boolean exec(String inputCommand) throws IOException, NullValueException, NotFoundCommandException, NotEnoughArgumentsException, ListEmptyException, InvalidValueException {
        String[] command = inputCommand.split("[\\s]", 2);
        int id;
        MusicBand musicBand;
        switch (command[0]) {
            case "help":
                help();
                break;
            case "info":
                info();
                break;
            case "show":
                show();
                break;
            case "add":
                if (command.length == 1)
                    add("");
                else
                    add(command[1]);
                break;
            case "update":
                if (command.length == 1)
                {
                    throw new NotEnoughArgumentsException("Недостаточно аргументов");
                }
                String[] op = command[1].split(" ", 2);
                id = Integer.parseInt(op[0]);
                if (op.length == 1)
                {
                    update (id, "");
                }
                else {
                    update(id, op[1]);
                }
                break;
            case "remove_by_id":
                id = Integer.parseInt(command[1]);
                removeById(id);
                break;
            case "clear":
                clear();
                break;
            case "save":
                save();
                break;
            case "execute_script":
                executeScript(command[1]);
                break;
            case "exit":
                return false;
            case "insert_at":
                op = command[1].split(" ", 2);
                int index = Integer.parseInt(op[0]);
                if (index > list.size())
                    throw new IndexOutOfBoundsException("Значение позиции слишком велико!");
                if (op.length == 1)
                {
                    insertAt(createElement(null, ""), index);
                }
                else {
                    insertAt(createElement(null, op[1]), index);
                }
                break;
            case "add_if_max":
                if (command.length == 1)
                    musicBand = createElement(null, "");
                else
                    musicBand = createElement(null, command[1]);
                if (list.size() == 0)
                    throw new ListEmptyException("Список пуст!");
                long value = this.list.stream().max((a, b) -> (Long.compare(a.getNumberOfParticipants(), b.getNumberOfParticipants()))).get().getNumberOfParticipants();
                if (musicBand.getNumberOfParticipants() > value) {
                    list.add(musicBand);
                }
                break;
            case "add_if_min":
                if (command.length == 1)
                    musicBand = createElement(null, "");
                else
                    musicBand = createElement(null, command[1]);
                if (list.size() == 0)
                    throw new ListEmptyException("Список пуст!");
                value = this.list.stream().min((a, b) -> (Long.compare(a.getNumberOfParticipants(), b.getNumberOfParticipants()))).get().getNumberOfParticipants();
                if (musicBand.getNumberOfParticipants() < value) {
                    list.add(musicBand);
                }
                break;
            case "average_of_number_of_participants":
                if (list.size() == 0)
                    throw new ListEmptyException("Список пуст!");
                System.out.printf("Среднее кол-во: %.2f\n", averageOfNumberOfParticipants());
                break;
            case "filter_by_genre":
                filterByGenre(MusicGenre.getMusicGenre(command[1]));
                break;
            case "filter_less_than_studio":
                filterLessThanStudio(new Studio(command[1]));
                break;
            default:
                throw new NotFoundCommandException("Команда не найдена");
        }
        return true;
    }

    /**
     * Метод для сортировки элементов коллекции по идентификационному номеру.
     */
    public void sort() {
        list.sort((MusicBand::compareTo));
    }
}