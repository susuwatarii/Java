import java.util.Scanner;


public class Lab4 {
    protected static DBInteraction  db; //  для базы данных с которой работаем (lab1)
    protected static final String wrongChoiceMsg = "Выбранного пункта меню не существует!";
    protected static final Scanner scanner = new Scanner(System.in);
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

    private static void printAndSave(String str, String tableName) {
        System.out.println(str);
        db.insertValue(str, tableName);
    }

    // Ввести две строки с клавиатуры, результат сохранить в MySQL с последующим выводом в консоль.
    protected static void enterStrings() {
        System.out.print("Строка 1: ");
        scanner.nextLine();
        str1 = scanner.nextLine();
        System.out.print("Строка 2: ");
        str2 = scanner.nextLine();
        if (Lab4.validateStrings()) {
            String tableName3 = Lab4.getTableName();
            Lab4.printAndSave(str1, tableName3);
            Lab4.printAndSave(str2, tableName3);
        }
    }

    // проверка длины введенных строк, если слишком короткие ->
    private static boolean validateStrings() {
        if (str1.length() < 50 || str2.length() < 50) {
            System.out.println("Ошибка! Строки не удовлетворяют условию \"не менее 50 символов каждая\"");
            str1 = null;
            str2 = null;
            return false;
        }
        return true;
    }

    // Поиск подстроки (вводится с клавиатуры) и определение: заканчивается ли строка данной подстрокой.
    private static void findSubstring() {
        if (Lab4.StringsAreEntered()) {
            System.out.print("Введите подстроку:");
            scanner.nextLine();
            String substring = scanner.nextLine();
            String tableName = Lab4.getTableName();

            // индексы вхождений подстроки
            System.out.println("Индексы начал вхождений подстроки:");
            int index1 = str1.indexOf(substring);
            int index2 = str2.indexOf(substring);
            System.out.println("В строке 1");
            if (index1 != -1) {
                Lab4.printAndSave(Integer.toString(index1), tableName);
            } else {
                System.out.println("Подстрока НЕ найдена в строке 1");
            }
            System.out.println("В строке 2");
            if (index2 != -1) {
                Lab4.printAndSave(Integer.toString(index2), tableName);
            } else {
                System.out.println("Подстрока НЕ найдена в строке 2");
            }

            // заканчиваются ли строки подстрокой
            System.out.println("В строке 1");
            if (str1.endsWith(substring)) {
                Lab4.printAndSave("заканчивается " + substring, tableName);
            } else {
                Lab4.printAndSave("не заканчивается подстрокой " + substring, tableName);
            }
            System.out.println("В строке 2");
            if (str2.endsWith(substring)) {
                Lab4.printAndSave("заканчивается " + substring, tableName);
            } else {
                Lab4.printAndSave("не заканчивается подстрокой " + substring, tableName);
            }
        }
    }

    private static void toLowerAndUpperCase() {
        if (Lab4.StringsAreEntered()) {
            String tableName3 = Lab4.getTableName();
            String newStr1 = str1.toLowerCase();
            String newStr2 = str2.toLowerCase();
            String newStr3 = str1.toUpperCase();
            String newStr4 = str2.toUpperCase();
            Lab4.printAndSave(newStr1, tableName3);
            Lab4.printAndSave(newStr2, tableName3);
            Lab4.printAndSave(newStr3, tableName3);
            Lab4.printAndSave(newStr4, tableName3);
        }
    }

    // получить подстроку по индексам, индексы с 0, [startIndex, endIndex)
    private static void getSubString() {
        if (Lab4.StringsAreEntered()) {
            int start;
            int end;
            try {
                System.out.print("Начало подстроки: ");
                start = scanner.nextInt();
                System.out.print("Конец  подстроки: ");
                end = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Некорректные значения для индексов! ");
                return;
            }
            String tableName = Lab4.getTableName();
            try {
                String substr1 = str1.substring(start, end);
                System.out.println("подстрока в строке 1: ");
                Lab4.printAndSave(substr1, tableName);
            } catch (Exception e) {
                System.out.println("Не получилось взять подстроку из строки 1");
            }
            try {
                String substr2 = str2.substring(start, end);
                System.out.println("подстрока в строке 2: ");
                Lab4.printAndSave(substr2, tableName);
            } catch (Exception e) {
                System.out.println("Не получилось взять подстроку из строки 2");
            }
        }
    }

    // взаимодействие с пользователем
    protected static void interaction() {
        // concatenation block:
        String choices = """ 
                
                1. Вывести все таблицы из MySQL.
                2. Создать таблицу в MySQL.
                3. Ввести две строки с клавиатуры (не менее 50 символов каждая),
                   результат сохранить в MySQL с последующим выводом в консоль.
                4. Возвращение подстроки по индексам, результат сохранить в MySQL с последующим выводом в консоль.
                   Индексы с 0, [startIndex, endIndex)
                5. Перевод строк в верхний и нижний регистры, результат сохранить в MySQL с последующим выводом
                   в консоль.
                6. Поиск подстроки (вводится с клавиатуры) и определение: заканчивается
                   ли строка данной подстрокой.
                7. Сохранить данные из MySQL в Excel и вывести на экран.
                8. Выйти из программы
                :\s""";

        System.out.print(choices);
        String choice = scanner.next();  // номер выбранного пункта меню
        switch (choice) {
            case "1": // Вывести все таблицы из MySQL
                db.printTableNames();
                break;
            case "2": // Создать таблицу в MySQL.
                db.createTable(Lab4.getTableName(), "varchar(255)");
                break;
            case "3": // Ввести две строки с клавиатуры (> 50 каждая), результат сохранить в MySQL с последующим выводом в консоль
                Lab4.enterStrings();
                break;
            case "4": // Возвращение подстроки по индексам, результат сохранить в MySQL с последующим выводом консоль.
                Lab4.getSubString();
                break;
            case "5": // Перевод строк в верхний регистр, результат сохранить в MySQL с последующим выводом в консоль.
                Lab4.toLowerAndUpperCase();
                break;
            case "6": // Поиск подстроки (вводится с клавиатуры) и определение: заканчивается ли строка данной подстрокой.
                Lab4.findSubstring();
                break;
            case "7": // Сохранить данные из MySQL в Excel и вывести на экран.
                db.toExcelAndPrint();
                break;
            case "8": // Выход из программы
                return;
            default:
                System.out.println(wrongChoiceMsg);
        }
        Lab4.interaction();
    }

    // установление взаимодействия с бд и начало работы с пользователем
    public static void main(String[] args) {
        db = new DBInteraction("lab4");  // установление взаимодействия с бд
        if (!db.connected)
            return;
        db.setFilePath("C:\\Users\\Antonova Alena\\Downloads\\lab4.xlsx");  // xlsx файл для сохранения данных
        Lab4.interaction();  // начинаем работать с пользователем
    }
}
