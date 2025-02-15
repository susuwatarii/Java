import java.util.InputMismatchException;
import java.util.Scanner;

// разобраться с break;

public class Main {
    protected static DBInteraction  db;
    protected static Scanner scanner = new Scanner(System.in);

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

    protected static void sum(String type){
        System.out.println("a + b");
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};  // чтобы не ругался на инициализацию
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] + byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes);
                }
                break;
            case "2":
                int[] intAb = {0, 0};  // чтобы не ругался на инициализацию
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] + intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes);
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] + floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
        }
    }
    protected static void subt(String type){
        System.out.println("a - b");
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};  // чтобы не ругался на инициализацию
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] - byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes);
                }
                break;
            case "2":
                int[] intAb = {0, 0};  // чтобы не ругался на инициализацию
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] - intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes);
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] - floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
        }
    }
    protected static void mult(String type){
        System.out.println("a * b");
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};  // чтобы не ругался на инициализацию
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] * byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes);
                }
                break;
            case "2":
                int[] intAb = {0, 0};  // чтобы не ругался на инициализацию
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] * intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes);
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] * floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
        }
    }
    protected static void div(String type){
        System.out.println("a / b  (результат float)");
        float floatRes;
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};  // чтобы не ругался на инициализацию
                if (Main.validNumbers(byteAb)) {
                    floatRes = (float)byteAb[0] / (float)byteAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
            case "2":
                int[] intAb = {0, 0};  // чтобы не ругался на инициализацию
                if (Main.validNumbers(intAb)) {
                    floatRes = (float)intAb[0] / (float)intAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] / floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
        }
    }
    protected static void mod(String type){
        System.out.println("a % b");
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};  // чтобы не ругался на инициализацию
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) (byteAb[0] % byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes);
                }
                break;
            case "2":
                int[] intAb = {0, 0};  // чтобы не ругался на инициализацию
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = intAb[0] % intAb[1];
                    System.out.println(intRes);
                    db.insertValue(intRes);
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = floatAb[0] % floatAb[1];
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
        }
    }
    protected static void myAbs(String type){
        System.out.println("|a|");
        switch (type) {
            case "1":
                byte[] byteA = {0};  // чтобы не ругался на инициализацию
                byte byteRes;
                if (Main.validNumbers(byteA)) {
                    byteRes = (byte) Math.abs(byteA[0]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes);
                }
                break;
            case "2":
                int[] intA = {0};  // чтобы не ругался на инициализацию
                int intRes;
                if (Main.validNumbers(intA)) {
                    intRes = Math.abs(intA[0]);
                    System.out.println(intRes);
                    db.insertValue(intRes);
                }
                break;
            case "3":
                float[] floatA = {0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Main.validNumbers(floatA)) {
                    floatRes = Math.abs(floatA[0]);
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
        }
    }
    protected static void myPow(String type){
        System.out.println("a^b");
        switch (type) {
            case "1":
                byte[] byteAb = {0, 0};  // чтобы не ругался на инициализацию
                byte byteRes;
                if (Main.validNumbers(byteAb)) {
                    byteRes = (byte) Math.pow(byteAb[0], byteAb[1]);
                    System.out.println(byteRes);
                    db.insertValue(byteRes);
                }
                break;
            case "2":
                int[] intAb = {0, 0};  // чтобы не ругался на инициализацию
                int intRes;
                if (Main.validNumbers(intAb)) {
                    intRes = (int) Math.pow(intAb[0], intAb[1]);
                    System.out.println(intRes);
                    db.insertValue(intRes);
                }
                break;
            case "3":
                float[] floatAb = {0, 0};  // чтобы не ругался на инициализацию
                float floatRes;
                if (Main.validNumbers(floatAb)) {
                    floatRes = (float) Math.pow(floatAb[0], floatAb[1]);
                    System.out.println(floatRes);
                    db.insertValue(floatRes);
                }
                break;
        }
    }


    protected static void interaction() {
        // взаимодействие с пользователем, выбор действия из меню
        String choices = "\n1. Вывести все таблицы из MySQL.\n" +
                "2.  Создать таблицу в MySQL.\n" +
                "3.  Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "4.  Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "5.  Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "6.  Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "7.  Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "8.  Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "9.  Возведение числа в степень, результат сохранить в MySQL с последующим выводом в консоль.\n" +
                "10. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран\n" +
                "11. Выйти из программы\n" +
                ": ";

        String types = "1. Byte\n2. Integer\n3. Float (через запятую)";

        System.out.print(choices);  // это же не printf?
        int choice = -1;  // -1 когда выбор пункта из меню неправильный(невозможный)
        String type; // тип данных для работы
        try{
            choice = scanner.nextInt();
        } catch (InputMismatchException e){
            scanner.nextLine();
            System.out.println("Выбранного пункта меню не существует!");
        }

        switch (choice) {
            case 1: // Вывести все таблицы из MySQL
                db.printTables();
                break;
            case 2: // Создать таблицу в MySQL.
                db.createTable();
                break;
            case 3: // Сложение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println(types);
                type = scanner.next();
                Main.sum(type);
                break;
            case 4: // Вычитание чисел, результат сохранить в MySQL с последующим выводом в консоль
                System.out.println(types);
                type = scanner.next();
                Main.subt(type);
                break;
            case 5: // Умножение чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println(types);
                type = scanner.next();
                Main.mult(type);
                break;
            case 6: // Деление чисел, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println(types);
                type = scanner.next();
                Main.div(type);
                break;
            case 7: // Деление чисел по модулю (остаток), результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println(types);
                type = scanner.next();
                Main.mod(type);
                break;
            case 8: // Возведение числа в модуль, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println(types);
                type = scanner.next();
                Main.myAbs(type);
                break;
            case 9: // Возведение числа в степень, результат сохранить в MySQL с последующим выводом в консоль.
                System.out.println(types);
                type = scanner.next();
                Main.myPow(type);
                break;
            case 10: // Сохранить данные из MySQL в Excel и вывести на экран.

                break;
            case 11: // Выход из программы
                return;
        }
        Main.interaction();
    }

    public static void main(String[] args) {
        db = new DBInteraction("lab1");  // подключаемся к базе данных
        Main.interaction();  // начинаем работать с пользователем
    }
}