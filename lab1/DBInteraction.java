import java.sql.*;
import java.util.Scanner;


// void или возвращать статус
// не статик так как можно будет подключаться к разным бд
public class DBInteraction {
    protected boolean connected = false; // получилось ли подключиться к бд
    protected Connection connection;
    protected String dbName;
    protected static Scanner scanner = new Scanner(System.in);

    public DBInteraction(String dbName) {
        this.dbName = dbName;
        this.connectDB(this.dbName);
    }

    // или можно сделать в public
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

    // для строковых данных
    // создание таблицы с заданным именем
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
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columnNames[0] + " " + dataType + " PRIMARY KEY";
            for (int i = 1; i < columnNames.length; i++) {
                sql = sql + ", " + columnNames[i] + " " + dataType;
            }
            sql += ")";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Таблица не создана!\n" + e.getMessage());
            return;
        }
        System.out.println("Таблица создана  или уже существует");
    }

    // добавление Строки в таблицу
    public void insertValue(String strValue) {
        if (!connected) {
            System.out.println("База данных " + dbName + " не подключена!");
            return;
        }
        System.out.println("Enter the name of your table");
        String tableName = scanner.nextLine();

        try {
            String sql = "INSERT INTO " + tableName + " VALUES (?)"; // ? - заполнитель, подставляются данные
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, strValue); // индексация параметров с 1
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при внесении данных!\n" + e.getMessage());
            return;
        }
        System.out.println("Данные успешно внесены в таблицу");
    }

    // байтовый тип данных - целые числа от -128 до 127 (включительно), поэтому в бд записываем как integer
    // добавление Целого числа в таблицу
    public void insertValue(int intValue) {
        if (!connected) {
            System.out.println("База данных " + dbName + " не подключена!");
            return;
        }
        System.out.println("Enter the name of your table");
        String tableName = scanner.nextLine();

        try {
            String sql = "INSERT INTO " + tableName + " VALUES (?)"; // ? - заполнитель, подставляются данные
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, intValue); // индексация параметров с 1
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при внесении данных!\n" + e.getMessage());
            return;
        }
        System.out.println("Данные успешно внесены в таблицу");
    }

    // добавление Вещественного числа в таблицу
    public void insertValue(float floatValue) {
        if (!connected) {
            System.out.println("База данных " + dbName + " не подключена!");
            return;
        }
        System.out.println("Enter the name of your table");
        String tableName = scanner.nextLine();

        try {
            String sql = "INSERT INTO " + tableName + " VALUES (?)"; // ? - заполнитель, подставляются данные
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, floatValue); // индексация параметров с 1
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при внесении данных!\n" + e.getMessage());
            return;
        }
        System.out.println("Данные успешно внесены в таблицу");
    }

    // вывести все таблицы базы данных
    // нужны ли данные таблиц?, навеерное нет
    //   ---------------------------------------------- разобраться
    public void printTables() {
        if (!connected) {
            System.out.println("База данных " + dbName + " не подключена!");
            return;
        }
        // Получаем объект DatabaseMetaData
        try {
            System.out.println("Список таблиц:");
            DatabaseMetaData metaData = connection.getMetaData();
            // Получаем список таблиц
            String[] types = {"TABLE"};
            ResultSet tables = metaData.getTables(null, null, "%", types);
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    */

    public static void main(String[] args) {  // psv   throws вместо try catch
        DBInteraction db1 = new DBInteraction("dbname");
        db1.printTables();

    }
}