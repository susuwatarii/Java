import org.apache.poi.ss.usermodel.Cell;  // библиотеки для работы с Excel
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


// взаимодействие с выбранной базой данных
public class DBInteraction {  //  будет наследование, если возникнет потребность расширить функционал
    public boolean connected = false; // получилось ли подключиться к бд
    protected Connection connection;
    protected final String dbName;
    protected static Scanner scanner = new Scanner(System.in);
    private String filePath; // файл excel куда сохраняются данные

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public DBInteraction(String dbName) {
        this.dbName = dbName;
        this.connectDB(this.dbName);
    }

    // создание базы данных если не существует
    private static void createDatabaseIfNotExists(String dbName) {
        // Формируем URL для подключения без указания конкретной базы данных
        String rootUrl = "jdbc:mysql://localhost:3306/?serverTimezone=UTC";
        try (Connection tempConnection = DriverManager.getConnection(rootUrl, "root", "1234");
             Statement stmt = tempConnection.createStatement()) {
            // Проверяем наличие базы данных через INFORMATION_SCHEMA
            String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + dbName + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                stmt.executeUpdate("CREATE DATABASE " + dbName);
                System.out.println("База данных " + dbName + " создана.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при проверке/создании базы данных: " + e.getMessage());
        }
    }

    // подключение к бд
    protected void connectDB(String dbName) {
        String dbNameUrl = "jdbc:mysql://localhost:3306/" + dbName;
        try {
            // создаем бд если не существует
            createDatabaseIfNotExists(dbName); // Проверяем существование БД. Если нет, создаем
            // Подключаемся к базе данных
            connection = DriverManager.getConnection(dbNameUrl, "root", "1234");
            System.out.println("успешное подключение к базе данных\n");
            connected = true;
        } catch (SQLException e) {
            System.out.println("ошибка подключения к базе данных!\n" + e.getMessage());
            connected = false;
        }
    }

    // проверка на подключение к дб
    protected boolean connectionCheck() {
        if (!connected) {
            System.out.println("База данных " + dbName + " не подключена!");
            return false;
        }
        return true;
    }

    // создание таблицы с заданным именем; id генерируется автоматически, второй столбец имеет имя result
    public void createTable(String tableName, String dataType) {
        if (this.connectionCheck()) {
            try {
                // создаем sql запрос
                String sql = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (id INT PRIMARY KEY AUTO_INCREMENT, result " + dataType + ")";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Таблица не создана!\n" + e.getMessage());
                return;
            }
            System.out.println("Таблица создана или уже существует");
        }
    }

    // байтовый тип данных - целые числа от -128 до 127 (включительно), поэтому в бд записываем как integer
    // добавление Целого числа в таблицу
    public void insertValue(int intValue, String tableName) {
        if (this.connectionCheck()) {
            try {
                String sql = "INSERT INTO `" + tableName + "` (result) VALUES (?)"; // ? - заполнитель, подставляются данные
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
    public void insertValue(float floatValue, String tableName) {
        if (this.connectionCheck()) {
            try {
                String sql = "INSERT INTO `" + tableName + "` (result) VALUES (?)"; // ? - заполнитель, подставляются данные
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
    public void insertValue(String strValue, String tableName) {
        if (this.connectionCheck()) {
            try {
                String sql = "INSERT INTO `" + tableName + "` (result) VALUES (?)"; // ? - заполнитель, подставляются данные
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

    // вывод в консоль всех таблиц бд
    public void printTableNames() {
        if (this.connectionCheck()) {
            // Получаем объект DatabaseMetaData
            System.out.println("База данных: " + this.dbName);
            try {
                System.out.println("Список таблиц:");
                DatabaseMetaData metaData = connection.getMetaData();
                // Получаем список таблиц
                String[] types = {"TABLE"};
                // connection.getCatalog() - имя бд
                ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", types);
                while (tables.next()) {
                    // печать названий таблиц
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println(tableName);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // выгрузить таблицы с данными в excel и вывести их в консоль
    public void toExcelAndPrint() {
        if (this.connectionCheck()) {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
                String[] types = {"TABLE"};
                ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", types);

                // Создаем Excel workbook
                Workbook workbook = new XSSFWorkbook();

                while (tables.next()) {
                    // получение данных таблицы
                    String tableName = tables.getString("TABLE_NAME");
                    System.out.println("\n -- " + tableName + " -- ");
                    String sql = "SELECT * FROM `" + tableName + "`";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery(sql);
                    ResultSetMetaData tableMetaData = resultSet.getMetaData();
                    int columnCount = tableMetaData.getColumnCount();

                    // создание страницы для таблицы
                    Sheet sheet = workbook.createSheet(tableName);

                    // выводим названия колонок в консоль
                    // записываем заголовки столбцов в Excel
                    Row headerRow = sheet.createRow(0);
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(tableMetaData.getColumnName(i) + ";  ");
                        Cell cell = headerRow.createCell(i - 1);
                        cell.setCellValue(tableMetaData.getColumnName(i));
                    }
                    System.out.println();

                    // выводим типы колонок в консоль
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(tableMetaData.getColumnTypeName(i) + ";  ");
                    }
                    System.out.println();

                    // выводим данные колонок в консоль
                    // записываем данные (из ResultSet) в Excel
                    int rowNum = 1;
                    while (resultSet.next()) {
                        Row row = sheet.createRow(rowNum++);
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(resultSet.getString(i) + ";  ");
                            Cell cell = row.createCell(i - 1);
                            cell.setCellValue(resultSet.getString(i));
                        }
                        System.out.println();
                    }

                    // Записываем workbook в файл
                    try {
                        FileOutputStream outputStream = new FileOutputStream(this.filePath);
                        workbook.write(outputStream);
                        System.out.println("Таблица сохранена в " + this.getFilePath());
                    } catch (IOException e) {
                        System.out.println("Возникли проблемы с сохранением таблицы " + tableName  + " в файл  " + this.getFilePath());
                        System.out.println(e.getMessage());
                    }
                }
                workbook.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
