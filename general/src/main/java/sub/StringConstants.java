package sub;

public class StringConstants {

    public class StartTreatment {
        public final static String START_HELPER = "Для вывода справки по командам введите help.";
        public final static String COMMAND_NOT_EXISTS = "Команды не существует.";
        public final static String PARSE_FAILED = "Не удалось распарсить.";
        public final static String ENTER_COMMAND = "Введите команду:";
        public final static String EXECUTE_FAILED = "В файле найдена несуществующая команда. Выполнение прекращено.";
        public final static String EXECUTE_ENDED = "Выполнение файла окончено.";
        public final static String COLLECTION_INPUT_NOT_EXISTS = "Файл с входной коллекцией не найден или недостаточно прав.";
        public final static String COLLECTION_OUTPUT_NOT_EXISTS = "Файл для сохранения коллекции не найден.";
    }

    public class Commands {
        public final static String CMD_WITH_ARG = "Введите команду с аргументом.";
        public final static String CMD_WITHOUT_ARG = "Введите команду без аргумента.";

        public final static String ADD_HELP = "добавить новый элемент в коллекцию.";
        public final static String ADD_IF_MIN_HELP = "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции.";
        public final static String CLEAR_HELP = "очистить коллекцию.";
        public final static String EXIT_HELP = "завершить программу.";
        public final static String EXIT_RESULT = "Программа завершила свою работу";
        public final static String GROUP_COUNTING_BY_TAGLINE_HELP = "сгруппировать элементы коллекции по значению поля tagline, вывести количество элементов в каждой группе.";
        public final static String GROUP_COUNTING_RESULT = "";
        public final static String HELP_HELP = "получить справку по командам.";
        public final static String INFO_HELP = "получить информацию о коллекции.";
        public final static String INSERT_AT_HELP = "добавить новый элемент в заданную позицию.";
        public final static String PRINT_DESCENDING_HELP = "вывести элементы коллекции в порядке убывания.";
        public final static String REMOVE_ALL_BY_SCREENWRITER_HELP = "удалить из коллекции все элементы по значению поля screenwriter.";
        public final static String REMOVE_BY_ID_HELP = "удалить элемент из коллекции по его id.";
        public final static String SAVE_HELP = "сохранить коллекцию в файл.";
        public final static String SHOW_HELP = "просмотреть все элементы коллекции.";
        public final static String SHUFFLE_HELP = "перемешать элементы коллекции в случайном порядке.";
        public final static String UPDATE_BY_ID_HELP = "обновить значение элемента коллекции, id которого равен заданному.";


        public final static String EXECUTE_SCRIPT_HELP = "считать и исполнить скрипт из указанного файла.";
        public final static String EXECUTE_ENTER_FILENAME = "Передайте название файла.";
        public final static String EXECUTE_IS_DIRECTORY = "Передана директория.";
        public final static String EXECUTE_RECURSION = "Обнаружена рекурсия.";
        public final static String EXECUTE_FILE_NOT_EXISTS = "Файл не найден.";
    }

    public class MovieMaking{
        public final static String ADD_SUCCESS = "Фильм добавлен в коллекцию.";
        public final static String ADD_FAIL = "Фильм добавить не удалось.";

        public final static String ENTER_MOVIE_NAME = "Введите название фильма: ";
        public final static String WRONG_NAME = "Значение поля \"name\" не можeт быть равно null или быть пустым.";
        public final static String ENTER_COORDINATE = "Введите координат";
        public final static String WRONG_X_COORDINATE = "Коордната должна быть типа Doable и ее значение должно быть не более 398.";
        public final static String WRONG_Y_COORDINATE = "Коордната должна быть типа Float.";
        public final static String ENTER_OSCAR_COUNT = "Введтие количество оскаров, полученных фильмом: ";
        public final static String WRONG_OSCAR_COUNT = "Количество оскаров должно быть типа Long и его значение должно быть больше 0.";
        public final static String ENTER_BUDGET = "Введите бюджет фильма: ";
        public final static String WRONG_BUDGET = "Количество оскаров должно быть long и его значение должно быть больше 0.";
        public final static String ENTER_TAGLINE = "Введие слоган фильма: ";
        public final static String WRONG_TAGLINE = "Длина поля \"tagline\"  не должна быть больше 158.";
        public final static String ENTER_MPAA_RATING = "Выберете рейтинг фильма : ";
        public final static String WRONG_MPAA_RATING = "Должно быть введен порядковый номер соответсвующего рейтинга.";
        public final static String ENTER_PERSON_NAME = "Введите имя персонажа: ";
        public final static String WRONG_PERSON_NAME = "Значение поля \"name\" можeт быть равно null или быть пустым.";
        public final static String ENTER_HEIGHT = "Введите рост персонажа: ";
        public final static String WRONG_HEIGHT = "Количество оскаров должно быть long и его значение должно быть больше 0.";
        public final static String ENTER_COUNTRY = "Выберете гражданство персонажа : ";
        public final static String WRONG_COUNTRY = "Должно быть введен порядковый номер соответсвующей страны.";
        public final static String ENTER_COLOR = "Выберете цвет волос персонажа : ";
        public final static String WRONG_COLOR = "Должно быть введен порядковый номер соответсвующего цвета волос";
    }

    public class PatternCommands{
        public final static String INVOKER_WRONG_COMMAND = "Введена несуществующая команда.";

        public final static String RECEIVER_EMPTY_COLLECTION_RESULT = "Коллекция пуста.";
        public final static String RECEIVER_EXIT_RESULT = "Спасибо за работу, до свидания!";
        public final static String RECEIVER_CLEAR_RESULT = "Коллекция очищена.";
        public final static String RECEIVER_INFO_TYPE_COLLECTION = "Тип коллекции: ";
        public final static String RECEIVER_INFO_AMOUNT = "\nКоличество элементов: ";
        public final static String RECEIVER_INFO_INITIALIZATION_DATE = "\nДата инициализации: ";
        public final static String RECEIVER_REMOVE_BY_ID_ACTION = "Удален объект с id = ";
        public final static String RECEIVER_REMOVE_BY_ID_WRONG_ACTION = "Введите существующее значение id";
        public final static String RECEIVER_REMOVE_ALL_BY_SCREENWRITER_RESULT = "Все элементы с указанным полем удалены. screenwriter = ";
        public final static String RECEIVER_REMOVE_ALL_BY_SCREENWRITER_WROMG_RESULT = " Элементов с указанным полем не найдено. screenwriter = ";
        public final static String RECEIVER_UPDATE_RESULT = "Объект с указанным id обновлен. id = ";
        public final static String RECEIVER_UPDATE_WRONG_RESULT = "Объект с указанным id не найден.";
        public final static String RECEIVER_INSERT_AT_RESULT = "Элемент внесен в коллекцию";
        public final static String RECEIVER_INSERT_AT_WRONG_RESULT = "Введен некорректный индекс";
        public final static String RECEIVER_SAVE_RESULT = "Коллекция успешно сохранена";
    }
}
