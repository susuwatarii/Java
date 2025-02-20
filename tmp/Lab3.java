import java.util.InputMismatchException;
import java.util.Scanner;


public class Lab3 { // не буду наследовать, возможно буду использовать некоторые методы
    private static DBInteraction  db; //  для базы данных с которой работаем (lab1)
    private static final String wrongChoiceMsg = "Выбранного пункта меню не существует!";
    private static final Scanner scanner = new Scanner(System.in);

    private static String getTableName() {
        System.out.println("Название таблицы в которою нужно сохранить результат: ");
        return scanner.next();
    }

    // считывание целых чисел, обработка неправильного ввода, сохранение в MySQL и вывод в консоль
    private static void readInt() {
        String tableName = Lab3.getTableName();
        System.out.println("Введите несколько чисел \n(для завершения введите 'q'):");
        while (true) {
            System.out.print("число: ");
            String input = scanner.next();

            // проверка на выход из программы
            if (input.equalsIgnoreCase("q")) {
                System.out.println(" - Считывание завершено -");
                return;
            }

            int number;
            // пытаемся преобразовать введенную строку в целое число
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Если введенная строка не является целым числом
                System.out.println("Ошибка: '" + input + "' не является целым числом!");
                continue;
            }
            // проверка на четность
            if (number % 2 == 0) {
                System.out.println("число  " + number + " - целое число, четное");
            } else {
                System.out.println("число  " + number + " - целое число, нечетное");
            }
            // запись в MySQL
            db.insertValue(number, tableName);
        }
    }

    // взаимодействие с пользователем
    private static void interaction() {
        // concatenation block:
        String choices = """ 
                
                1.  Вывести все таблицы из MySQL.
                2.  Создать таблицу в MySQL.
                3.  C клавиатуры задать несколько чисел.
                    Проверка числа на целостность и четность.
                    (если введено не целое число или вообще не число, то будет сообщение об ошибке).
                4.  Сохранить данные из MySQL в Excel и вывести на экран.
                5.  Выйти из программы
                :\s""";

        System.out.print(choices);
        String choice = scanner.next();  // номер выбранного пункта меню
        switch (choice) {
            case "1": // Вывести все таблицы из MySQL
                db.printTableNames();
                break;
            case "2": // Создать таблицу в MySQL.
                db.createTable(Lab1.getTableName(), "int");
                break;
            case "3": // считывание, проверка, печать и запись чисел
                Lab3.readInt();
                break;
            case "4": // Сохранить данные из MySQL в Excel и вывести на экран.
                db.toExcelAndPrint();
                break;
            case "5": // Выход из программы
                return;
            default:
                System.out.println(wrongChoiceMsg);
        }
        Lab3.interaction();
    }

    // установление взаимодействия с бд и начало работы с пользователем
    public static void main(String[] args) {
        db = new DBInteraction("lab3");  // установление взаимодействия с бд
        db.setFilePath("C:\\Users\\Antonova Alena\\Downloads\\lab3.xlsx");  // xlsx файл для сохранения данных
        Lab3.interaction();  // начинаем работать с пользователем
    }
}