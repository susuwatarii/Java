import java.sql.*;
import java.util.Scanner;


public class DBInteraction {
    protected boolean connected = false; // получилось ли подключиться к бд  //  !!!!!!!!!!!  создать метод выдающий оштбку
    protected Connection connection;
    protected String dbName;
    protected static Scanner scanner = new Scanner(System.in);

    // почему нет типа данных???
    public DBInteraction(String dbName) {
        this.dbName = dbName;
        this.connectDB(this.dbName);
    }

    // подключение к бд
    protected void connectDB(String dbName) {
        String dbNameUrl = "jdbc:mysql://localhost:3306/" + dbName; // url для подключения дб MySQl java data base connectivity, 3306 - port name
        // с точки зрения безопасности не выносить пароль и логин в переменные
        try{
            connection = DriverManager.getConnection(dbNameUrl, "root", "1234");
            System.out.println("успешное подключение к бд\n");
            connected = true;
        } catch (SQLException e) {
            System.out.println("ошибка подключения к бд!\n" + e.getMessage() + "\n");
            connected = false;
        }
    }

    // проверка на подключение к дб, если нет - сообщение об этом
    protected boolean connectionCheck() {
        if (!connected) {
            System.out.println("База данных " + dbName + " не подключена!");
            return false;
        }
        return true;
    }

    // создание таблицы с заданным именем
    // id генерируется автоматически, второй столбец имеет имя result
    public void createTable() {
        if (this.connectionCheck()) {
            try {
                System.out.println("Enter the name of your table");
                String tableName = scanner.nextLine();
                System.out.println("Тип столбца, например:\ninteger, varchar(255), float");
                String dataType = scanner.nextLine();

                // создаем sql запрос
                String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT PRIMARY KEY AUTO_INCREMENT, result " + dataType + ")";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Таблица не создана!\n" + e.getMessage());
                return;
            }
            System.out.println("Таблица создана  или уже существует");
        }
    }

    // байтовый тип данных - целые числа от -128 до 127 (включительно), поэтому в бд записываем как integer
    // добавление Целого числа в таблицу
    public void insertValue(int intValue) {
        if (this.connectionCheck()) {
            System.out.println("Enter the name of your table");
            String tableName = scanner.nextLine();

            try {
                String sql = "INSERT INTO " + tableName + " (result) VALUES (?)"; // ? - заполнитель, подставляются данные
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, intValue); // индексация параметров с 1
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при внесении данных!\n" + e.getMessage());
                return;
            }
            System.out.println("Данные успешно внесены в таблицу");
        }
    }
    // добавление Вещественного числа в таблицу
    public void insertValue(float floatValue) {
        if (this.connectionCheck()) {
            System.out.println("Enter the name of your table");
            String tableName = scanner.nextLine();

            try {
                String sql = "INSERT INTO " + tableName + " (result) VALUES (?)"; // ? - заполнитель, подставляются данные
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setFloat(1, floatValue); // индексация параметров с 1
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при внесении данных!\n" + e.getMessage());
                return;
            }
            System.out.println("Данные успешно внесены в таблицу");
        }
    }
    // добавление Строки в таблицу
    public void insertValue(String strValue) {
        if (this.connectionCheck()) {
            System.out.println("Enter the name of your table");
            String tableName = scanner.nextLine();

            try {
                String sql = "INSERT INTO " + tableName + " (result) VALUES (?)"; // ? - заполнитель, подставляются данные
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, strValue); // индексация параметров с 1
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при внесении данных!\n" + e.getMessage());
                return;
            }
            System.out.println("Данные успешно внесены в таблицу");
        }
    }

    // вывод в консоль всех таблицы базы данных
    //   ---------------------------------------------- разобраться
    public void printTableNames() {
        if (this.connectionCheck()) {
            // Получаем объект DatabaseMetaData
            try {
                System.out.println("Список таблиц:");
                DatabaseMetaData metaData = connection.getMetaData();
                // Получаем список таблиц
                String[] types = {"TABLE"};
                // connection.getCatalog() - имя бд
                ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", types);
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println(tableName);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }



    public void printAllData() {
        if (this.connectionCheck()) {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
                String[] types = {"TABLE"};
                ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", types);
                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println("\n -- " + tableName + " -- ");
                    String sql = "SELECT * FROM " + tableName;
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery(sql);
                    ResultSetMetaData tableMetaData = resultSet.getMetaData();
                    int columnCount = tableMetaData.getColumnCount();

                    // вывод названия колонок
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(tableMetaData.getColumnName(i) + ";  "); // Adjust width for readability
                    }
                    System.out.println();

                    // вывод типа колонок
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(tableMetaData.getColumnTypeName(i) + ";  "); // Adjust width for readability
                    }
                    System.out.println();

                    // вывод данных колонок
                    while (resultSet.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSet.getString(i) + ";  "); // Use getString for generic handling
                        }
                        System.out.println();
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /*
    // добавление Строковых данных в таблицу
    public void InsertValues(String[] strValue) {
        if (connected) {
            System.out.println("Enter the name of your table");
            String tableName = scanner.nextLine();
            System.out.println("Названия столбцов через пробел");
            String columnNames = scanner.nextLine();

            try {
                String sql = "INSERT INTO " + tableName + " (" + columnNames.replace(" ",", ") + ") VALUES (?, ?, ?)"; // ? - заполнитель, подставляются данные
                PreparedStatement statement = connection.prepareStatement(sql);
                for (int i = 1; i <= columnNames.split(" ").length; i++) {
                    statement.setString(i, strValue[i-1]); // индексация параметров с 1
                }
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при внесении данных!\n" + e.getMessage());
                return;
            }
            System.out.println("Данные успешно внесены в таблицу");
        } else {
            System.out.println("База данных " + dbName + " не подключена!");
        }

    }

    */  // добавление  в таблицу строки с данными строкового типа

    /*
    public void createTable() {
        if (!connected) {
            System.out.println("База данных " + dbName + " не подключена!");
            return;
        }
        try {
            System.out.println("Enter the name of your table");
            String tableName = scanner.nextLine();
            System.out.println("Тип столбцов, например:\ninteger, varchar(255), float");
            String dataType = scanner.nextLine();
            System.out.println("Названия столбцов через пробел \nПервый столбец будет создан PRIMARY KEY");
            String[] columnNames = scanner.nextLine().split(" ");

            // создаем sql запрос
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT PRIMARY KEY AUTO_INCREMENT" ;
            for (int i = 0; i < columnNames.length; i++) {
                sql = sql + ", " + columnNames[i] + " " + dataType;
            }
            sql += ")";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Таблица не создана!\n" + e.getMessage());
            return;
        }
        System.out.println("Таблица создана  или уже существует");
    }*/ // создание таблицы с заданным именем и типом данных для всех столбцов

    // потом грохнуть метод, не нужен
    public static void main(String[] args) {  // psv   throws вместо try catch
        DBInteraction db1 = new DBInteraction("lab1");
        db1.printAllData();

    }
}