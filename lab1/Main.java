import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    protected static DBInteraction  db; //  для базы данных с которой работаем (lab1)
    protected static String wrongChoiceMsg = "Выбранного пункта меню не существует!";
    protected static Scanner scanner = new Scanner(System.in);
    protected static String getTableName() {
        System.out.println("Название таблицы в которою нужно сохранить результат: ");
        return scanner.next();
    }

    // проверка: введены ли байты, заполнение массива из двух байтов
    protected static boolean validNumbers(byte[] byteArr){
        try {
            System.out.print("a: ");
            byteArr[0] = scanner.nextByte();
            if (byteArr.length == 2) {
                System.out.print("b: ");
                byteArr[1] = scanner.nextByte();
            }
        } catch (InputMismatchException e){
            scanner.nextLine(); // если не считалось число, то его нужно считать
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    // проверка: введены ли вещественные числа, заполнение массива из двух float
    protected static boolean validNumbers(float[] floatArr){
        try {
            System.out.print("a: ");
            floatArr[0] = scanner.nextFloat();
            if (floatArr.length == 2) {
                System.out.print("b: ");
                floatArr[1] = scanner.nextFloat();
            }
        } catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    // проверка: введены ли целые числа, заполнение массива из двух int
    protected static boolean validNumbers(int[] intArr){
        try {
            System.out.print("a: ");
            intArr[0] = scanner.nextInt();
            if (intArr.length == 2) {
                System.out.print("b: ");
                intArr[1] = scanner.nextInt();
            }
        } catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    // выбор типа данных и математические операции над числами:
    protected static void sum(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Main.validNumbers(byteAb)) {  // заполнение массива и проверка на корректность введенных данных
                    byteRes = (byte) (byteAb[0] + byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes, Main.getTableName());  // сохранение результата в бд
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] + intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes, Main.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] + floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    protected static void subt(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] - byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes, Main.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] - intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes, Main.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] - floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    protected static void mult(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] * byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes, Main.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] * intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes, Main.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] * floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    protected static void div(String type){
        //1.Byte 2.Integer 3.Float
        float floatRes;
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                if (Main.validNumbers(byteAb)) {
                    floatRes = (float)byteAb[0] / (float)byteAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                if (Main.validNumbers(intAb)) {
                    floatRes = (float)intAb[0] / (float)intAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] / floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    protected static void mod(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] % byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes, Main.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] % intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes, Main.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] % floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    protected static void abs(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteA = {0};
                byte byteRes;
                if (Main.validNumbers(byteA)) {
                    byteRes = (byte) Math.abs(byteA[0]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes, Main.getTableName());
                }
                break;
            case "2":
                int[] intA = {0};
                int intRes;
                if (Main.validNumbers(intA)) {
                    intRes = Math.abs(intA[0]);
                    System.out.println(intRes);
                    db.insertValue(intRes, Main.getTableName());
                }
                break;
            case "3":
                float[] floatA = {0};
                float floatRes;
                if (Main.validNumbers(floatA)) {
                    floatRes = Math.abs(floatA[0]);
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }
    protected static void pow(String type){
        //1.Byte 2.Integer 3.Float
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) Math.pow(byteAb[0], byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes, Main.getTableName());
                }
                break;
            case "2":
                int[] intAb = {0, 0};
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = (int) Math.pow(intAb[0], intAb[1]);
                    System.out.println(intRes);
                    db.insertValue(intRes, Main.getTableName());
                }
                break;
            case "3":
                float[] floatAb = {0, 0};
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = (float) Math.pow(floatAb[0], floatAb[1]);
                    System.out.println(floatRes);
                    db.insertValue(floatRes, Main.getTableName());
                }
                break;
            default:
                System.out.println(wrongChoiceMsg);
        }
    }

    // взаимодействие с пользователем
    protected static void interaction() {
        // concatenation block:
        String choices = """ 
                
                1. Вывести все таблицы из MySQL.
                2.  Создать таблицу в MySQL.
                3.  Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                4.  Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль.
                5.  Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                6.  Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.
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
                db.createTable(Main.getTableName());
                break;
            case "3": // Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na + b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Main.sum(type);
                break;
            case "4": // Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль
                System.out.println("\na - b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Main.subt(type);
                break;
            case "5": // Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na * b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Main.mult(type);
                break;
            case "6": // Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na / b  (результат типа float)");
                System.out.print(types);
                type = scanner.next();
                Main.div(type);
                break;
            case "7": // Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na % b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Main.mod(type);
                break;
            case "8": // Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\n|a| (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Main.abs(type);
                break;
            case "9": // Возведение числа в степень, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println("\na^b  (результат соответствующего типа)");
                System.out.print(types);
                type = scanner.next();
                Main.pow(type);
                break;
            case "10": // Сохранить данные из MySQL в Excel и вывести на экран.
                db.toExcelAndPrint();
                break;
            case "11": // Выход из программы
                return;
            default:
                System.out.println(wrongChoiceMsg);
        }
        Main.interaction();
    }

    // установление взаимодействия с бд и начало работы с пользователем
    public static void main(String[] args) {
        db = new DBInteraction("lab1");  // установление взаимодействия с бд
        db.setFilePath("C:\\Users\\Antonova Alena\\Downloads\\lab1.xlsx");  // xlsx файл для сохранения данных
        Main.interaction();  // начинаем работать с пользователем
    }
}