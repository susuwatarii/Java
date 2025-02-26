import java.util.InputMismatchException;
import java.util.Scanner;


public class Lab1 { // не буду наследовать, возможно буду использовать некоторые методы
    private static DBInteraction  db; //  для базы данных с которой работаем (lab1)
    private static final String wrongChoiceMsg = "Выбранного пункта меню не существует!";
    private static final Scanner scanner = new Scanner(System.in);

    private static String getTableName() {
        System.out.println("Название таблицы в которою нужно сохранить результат: ");
        return scanner.next();
    }

    private static void printAndSave(byte number, String tableName) {
        System.out.println(number);
        db.insertValue(number, tableName);
    }

    private static void printAndSave(int number, String tableName) {
        System.out.println(number);
        db.insertValue(number, tableName);
    }

    private static void printAndSave(float number, String tableName) {
        System.out.println(number);
        db.insertValue(number, tableName);
    }

    // проверка: введены ли байты, заполнение массива из двух байтов
    private static boolean validNumbers(byte[] byteArr){
        try {
            System.out.print("a: ");
            byteArr[0] = scanner.nextByte();
            if (byteArr.length == 2) {
                System.out.print("b: ");
                byteArr[1] = scanner.nextByte();
            }
        } catch (InputMismatchException e){
            scanner.nextLine(); // если не считалось число, то его нужно считать
            System.out.println("Ошибка " + e.getMessage());
            return false;
        }
        return true;
    }
    // проверка: введены ли вещественные числа, заполнение массива из двух float
    private static boolean validNumbers(float[] floatArr){
        try {
            System.out.print("a: ");
            floatArr[0] = scanner.nextFloat();
            if (floatArr.length == 2) {
                System.out.print("b: ");
                floatArr[1] = scanner.nextFloat();
            }
        } catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Ошибка " + e.getMessage());
            return false;
        }
        return true;
    }
    // проверка: введены ли целые числа, заполнение массива из двух int
    private static boolean validNumbers(int[] intArr){
        try {
            System.out.print("a: ");
            intArr[0] = scanner.nextInt();
            if (intArr.length == 2) {
                System.out.print("b: ");
                intArr[1] = scanner.nextInt();
            }
        } catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Ошибка " + e.getMessage());
            return false;
        }
        return true;
    }

    // выбор типа данных и математические операции над числами:
    private static void sum(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Lab1.validNumbers(byteAb)) {  // заполнение массива и проверка на корректность введенных данных
                    byteRes = (byte) (byteAb[0] + byteAb[1]);
                    Lab1.printAndSave(byteRes, Lab1.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Lab1.validNumbers(intAb)) {
                    intRes = intAb[0] + intAb[1];
                    Lab1.printAndSave(intRes, Lab1.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Lab1.validNumbers(floatAb)) {
                    floatRes = floatAb[0] + floatAb[1];
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    private static void subt(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Lab1.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] - byteAb[1]);
                    Lab1.printAndSave(byteRes, Lab1.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Lab1.validNumbers(intAb)) {
                    intRes = intAb[0] - intAb[1];
                    Lab1.printAndSave(intRes, Lab1.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Lab1.validNumbers(floatAb)) {
                    floatRes = floatAb[0] - floatAb[1];
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    private static void mult(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Lab1.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] * byteAb[1]);
                    Lab1.printAndSave(byteRes, Lab1.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Lab1.validNumbers(intAb)) {
                    intRes = intAb[0] * intAb[1];
                    Lab1.printAndSave(intRes, Lab1.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Lab1.validNumbers(floatAb)) {
                    floatRes = floatAb[0] * floatAb[1];
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    private static void div(String type){
        //1.Byte 2.Integer 3.Float
        float floatRes;
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                if (Lab1.validNumbers(byteAb)) {
                    if (byteAb[1] == 0) {
                        System.out.println("На ноль делить нельзя! ");
                        return;
                    }
                    floatRes = (float)byteAb[0] / (float)byteAb[1];
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                if (Lab1.validNumbers(intAb)) {
                    if (intAb[1] == 0) {
                        System.out.println("На ноль делить нельзя! ");
                        return;
                    }
                    floatRes = (float)intAb[0] / (float)intAb[1];
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                if (Lab1.validNumbers(floatAb)) {
                    if (floatAb[1] == 0) {
                        System.out.println("На ноль делить нельзя! ");
                        return;
                    }
                    floatRes = floatAb[0] / floatAb[1];
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    private static void mod(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Lab1.validNumbers(byteAb)) {
                    if (byteAb[1] == 0) {
                        System.out.println("На ноль делить нельзя! ");
                        return;
                    }
                    byteRes = (byte) (byteAb[0] % byteAb[1]);
                    Lab1.printAndSave(byteRes, Lab1.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Lab1.validNumbers(intAb)) {
                    if (intAb[1] == 0) {
                        System.out.println("На ноль делить нельзя! ");
                        return;
                    }
                    intRes = intAb[0] % intAb[1];
                    Lab1.printAndSave(intRes, Lab1.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Lab1.validNumbers(floatAb)) {
                    if (floatAb[1] == 0) {
                        System.out.println("На ноль делить нельзя! ");
                        return;
                    }
                    floatRes = floatAb[0] % floatAb[1];
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    private static void abs(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteA = {0};
                byte byteRes;
                if (Lab1.validNumbers(byteA)) {
                    byteRes = (byte) Math.abs(byteA[0]);
                    Lab1.printAndSave(byteRes, Lab1.getTableName());
                }
                break;
            case "2":
                int[] intA = {0};
                int intRes;
                if (Lab1.validNumbers(intA)) {
                    intRes = Math.abs(intA[0]);
                    Lab1.printAndSave(intRes, Lab1.getTableName());
                }
                break;
            case "3":
                float[] floatA = {0};
                float floatRes;
                if (Lab1.validNumbers(floatA)) {
                    floatRes = Math.abs(floatA[0]);
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    private static void pow(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Lab1.validNumbers(byteAb)) {
                    byteRes = (byte) (Math.pow(byteAb[0], byteAb[1]));
                    Lab1.printAndSave(byteRes, Lab1.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Lab1.validNumbers(intAb)) {
                    intRes = (int) (Math.pow(intAb[0], intAb[1]));
                    Lab1.printAndSave(intRes, Lab1.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Lab1.validNumbers(floatAb)) {
                    floatRes = (float) (Math.pow(floatAb[0], floatAb[1]));
                    Lab1.printAndSave(floatRes, Lab1.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }

    // взаимодействие с пользователем
    private static void interaction() {
        // concatenation block:
        String choices = """ 
                
                1. Вывести все таблицы из MySQL.
                2.  Создать таблицу в MySQL.
                3.  Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                4.  Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль.
                5.  Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                6.  Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.
                    - ответ типа float
                7.  Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим выводом в консоль.
                8.  Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в консоль.
                9.  Возведение числа в степень, результат сохранить в MySQL с последующим выводом в консоль.
                10. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран
                11. Выйти из программы
                :\s""";

        String types = "1. Byte\n2. Integer\n3. Float (через запятую)\n: ";
        String type; // номер выбранного типа данных

        System.out.print(choices);
        String choice = scanner.next();  // номер выбранного пункта меню
        switch (choice) {
            case "1": // Вывести все таблицы из MySQL
                db.printTableNames();
                break;
            case "2": // Создать таблицу в MySQL.
                System.out.println("Тип столбца, например:\n1. int\n2. float\n3. varchar(255)");
                String dataType = scanner.next();
                switch (dataType) {
                    case "1":
                        db.createTable(Lab1.getTableName(), "int");
                        break;
                    case "2":
                        db.createTable(Lab1.getTableName(), "float");
                        break;
                    case "3":
                        db.createTable(Lab1.getTableName(), "varchar(255)");
                        break;
                    default:
                        System.out.println(Lab1.wrongChoiceMsg);
                }
                break;
            case "3": // Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na + b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Lab1.sum(type);
                break;
            case "4": // Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль
                System.out.println("\na - b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Lab1.subt(type);
                break;
            case "5": // Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na * b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Lab1.mult(type);
                break;
            case "6": // Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na / b  (результат типа float)");
                System.out.print(types);
                type = scanner.next();
                Lab1.div(type);
                break;
            case "7": // Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na % b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Lab1.mod(type);
                break;
            case "8": // Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\n|a| (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Lab1.abs(type);
                break;
            case "9": // Возведение числа в степень, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na^b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Lab1.pow(type);
                break;
            case "10": // Сохранить данные из MySQL в Excel и вывести на экран.
                db.toExcelAndPrint();
                break;
            case "11": // Выход из программы
                return;
            default:
                System.out.println(wrongChoiceMsg);
        }
        Lab1.interaction();
    }

    // установление взаимодействия с бд и начало работы с пользователем
    public static void main(String[] args) {
        db = new DBInteraction("lab1");  // установление взаимодействия с бд
        if (!db.connected)
            return;
        db.setFilePath("C:\\Users\\Antonova Alena\\Downloads\\lab1.xlsx");  // xlsx файл для сохранения данных
        Lab1.interaction();  // начинаем работать с пользователем
    }
}