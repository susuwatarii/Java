import java.util.InputMismatchException;
import java.util.Scanner;


public class Strings {
    protected static DBInteraction  db; //  для базы данных с которой работаем (lab1)
    protected static String wrongChoiceMsg = "Выбранного пункта меню не существует!";
    protected static Scanner scanner = new Scanner(System.in);
    protected static String str1;
    protected static String str2;

    protected static String getTableName() {
        System.out.println("Название таблицы в которою нужно сохранить результат: ");
        return scanner.next();
    }

    protected static boolean StringsAreEntered() {
        if (str1 != null && str2 != null) {
            return true;
        }
        System.out.println("Строки не заданы! ");
        return false;
    }

    // Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в консоль.
    protected static void enterStrings() {
        System.out.print("Строка 1: ");
        str1 = scanner.next();
        System.out.print("Строка 2: ");
        str2 = scanner.next();
        String tableName3 = Strings.getTableName();
        db.insertValue(str1, tableName3);
        db.insertValue(str2, tableName3);
        System.out.println(str1 + "\n" + str2);
    }

    // Подсчитать размер ранее введенных строк, результат сохранить в MySQL с последующим выводом в консоль.
    protected static void lenStrings() {
        if (Strings.StringsAreEntered()) {
            String len1 = String.valueOf(str1.length());
            String len2 = String.valueOf(str2.length());
            String tableName4 = Strings.getTableName();
            db.insertValue(len1, tableName4);
            db.insertValue(len2, tableName4);
            System.out.println(str1 + ": " + len1 + "\n" + str2 + ": " + len1);
        }
    }

    // Объединить две строки в единое целое, результат сохранить в MySQL с последующим выводом в консоль.
    protected static void concatStrings() {
        if (Strings.StringsAreEntered()) {
            String res = str1 + str2 ;
            db.insertValue(res, Strings.getTableName());
            System.out.println(res);
        }
    }

    // Сравнить две ранее введенные строки, результат сохранить в MySQL с последующим выводом в консоль.
    protected static void compStrings() {
        if (Strings.StringsAreEntered()) {
            String res2;
            if (str1.equals(str2)) {
                res2 = "Строки равны";
            } else {
                res2 = "Строки не равны";
            }
            db.insertValue(res2, Strings.getTableName());
            System.out.println(res2);
        }
    }

    // взаимодействие с пользователем
    protected static void interaction() {
        // concatenation block:
        String choices = """ 
                
                1. Вывести все таблицы из MySQL.
                2. Создать таблицу в MySQL.
                3. Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в
                консоль.
                4. Подсчитать размер ранее введенных строк, результат сохранить в MySQL с последующим
                выводом в консоль.
                5. Объединить две строки в единое целое, результат сохранить в MySQL с последующим выводом
                в консоль.
                6. Сравнить две ранее введенные строки, результат сохранить в MySQL с последующим выводом
                в консоль.
                7. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран.
                8. Выйти из программы
                :\s""";

        System.out.print(choices);
        String choice = scanner.next();  // номер выбранного пункта меню
        switch (choice) {
            case "1": // Вывести все таблицы из MySQL
                db.printTableNames();
                break;
            case "2": // Создать таблицу в MySQL.
                db.createTable(Strings.getTableName());
                break;
            case "3": // Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в консоль.
                Strings.enterStrings();
                break;
            case "4": // Подсчитать размер ранее введенных строк, результат сохранить в MySQL с последующим выводом в консоль.
                Strings.lenStrings();
                break;
            case "5": // Объединить две строки в единое целое, результат сохранить в MySQL с последующим выводом в консоль.
                Strings.concatStrings();
                break;
            case "6": // Сравнить две ранее введенные строки, результат сохранить в MySQL с последующим выводом в консоль.
                Strings.compStrings();
                break;
            case "7": // Сохранить данные из MySQL в Excel и вывести на экран.
                db.toExcelAndPrint();
                break;
            case "8": // Выход из программы
                return;
            default:
                System.out.println(wrongChoiceMsg);
        }
        Strings.interaction();
    }

    // установление взаимодействия с бд и начало работы с пользователем
    public static void main(String[] args) {
        db = new DBInteraction("lab2");  // установление взаимодействия с бд
        db.setFilePath("C:\\Users\\Antonova Alena\\Downloads\\lab2.xlsx");  // xlsx файл для сохранения данных
        Strings.interaction();  // начинаем работать с пользователем
    }
}