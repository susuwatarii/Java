import java.util.Scanner;


public class Lab2 {  // скорее всего буду наследовать: дописать новые методы, скрыть (вместо переопределения) interaction()
    private static DBInteraction  db; //  для базы данных с которой работаем (lab1)
    private static final String wrongChoiceMsg = "Выбранного пункта меню не существует!";
    private static final Scanner scanner = new Scanner(System.in);
    private static String str1;
    private static String str2;

    private static String getTableName() {
        System.out.println("Название таблицы в которою нужно сохранить результат: ");
        return scanner.next();
    }

    private static boolean StringsAreEntered() {
        if (str1 != null && str2 != null) {
            return true;
        }
        System.out.println("Строки не заданы! ");
        return false;
    }

    private static void printAndSave(String str, String tableName) {
        System.out.println(str);
        db.insertValue(str, tableName);
    }

    // Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в консоль.
    private static void enterStrings() {
        System.out.print("Строка 1: ");
        scanner.nextLine();
        str1 = scanner.nextLine();
        System.out.print("Строка 2: ");
        str2 = scanner.nextLine();
        String tableName3 = Lab2.getTableName();
        Lab2.printAndSave(str1, tableName3);
        Lab2.printAndSave(str2, tableName3);
    }

    // Подсчитать размер ранее введенных строк, результат сохранить в MySQL с последующим выводом в консоль.
    private static void lenStrings() {
        if (Lab2.StringsAreEntered()) {
            String len1 = String.valueOf(str1.length());
            String len2 = String.valueOf(str2.length());
            String tableName4 = Lab2.getTableName();
            Lab2.printAndSave(len1, tableName4);
            Lab2.printAndSave(len2, tableName4);
        }
    }

    // Объединить две строки в единое целое, результат сохранить в MySQL с последующим выводом в консоль.
    private static void concatStrings() {
        if (Lab2.StringsAreEntered()) {
            String res = str1 + str2 ;
            Lab2.printAndSave(res, Lab2.getTableName());
        }
    }

    // Сравнить две ранее введенные строки, результат сохранить в MySQL с последующим выводом в консоль.
    private static void compStrings() {
        if (Lab2.StringsAreEntered()) {
            String res2;
            if (str1.equals(str2)) {
                res2 = "Строки равны";
            } else {
                res2 = "Строки не равны";
            }
            Lab2.printAndSave(res2, Lab2.getTableName());
        }
    }

    // взаимодействие с пользователем
    private static void interaction() {
        // concatenation block:
        String choices = """ 
                
                1. Вывести все таблицы из MySQL.
                2. Создать таблицу в MySQL.
                3. Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в консоль.
                4. Подсчитать размер ранее введенных строк, результат сохранить в MySQL с последующим выводом в консоль.
                5. Объединить две строки в единое целое, результат сохранить в MySQL с последующим выводом в консоль.
                6. Сравнить две ранее введенные строки, результат сохранить в MySQL с последующим выводом в консоль.
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
                db.createTable(Lab2.getTableName(), "varchar(255)");
                break;
            case "3": // Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в консоль.
                Lab2.enterStrings();
                break;
            case "4": // Подсчитать размер ранее введенных строк, результат сохранить в MySQL с последующим выводом в консоль.
                Lab2.lenStrings();
                break;
            case "5": // Объединить две строки в единое целое, результат сохранить в MySQL с последующим выводом в консоль.
                Lab2.concatStrings();
                break;
            case "6": // Сравнить две ранее введенные строки, результат сохранить в MySQL с последующим выводом в консоль.
                Lab2.compStrings();
                break;
            case "7": // Сохранить данные из MySQL в Excel и вывести на экран.
                db.toExcelAndPrint();
                break;
            case "8": // Выход из программы
                return;
            default:
                System.out.println(wrongChoiceMsg);
        }
        Lab2.interaction();
    }

    // установление взаимодействия с бд и начало работы с пользователем
    public static void main(String[] args) {
        db = new DBInteraction("lab2");  // установление взаимодействия с бд
        if (!db.connected)
            return;
        db.setFilePath("C:\\Users\\Antonova Alena\\Downloads\\lab2.xlsx");  // xlsx файл для сохранения данных
        Lab2.interaction();  // начинаем работать с пользователем
    }
}