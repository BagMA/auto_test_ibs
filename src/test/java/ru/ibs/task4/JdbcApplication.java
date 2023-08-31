package ru.ibs.task4;

import org.junit.jupiter.api.*;
import com.zaxxer.hikari.HikariDataSource;
import ru.ibs.ConfProperties;

import java.sql.*;
public class JdbcApplication {

    private static Connection connection;
    private static HikariDataSource dataSource;
    String fruit = ConfProperties.getProperty("fruit");
    String type = ConfProperties.getProperty("type");
    String id = ConfProperties.getProperty("id");
    String exotic = ConfProperties.getProperty("exotic");
    String sqlShowFood = "SELECT * FROM FOOD";

    @Test
    public void testAddFood() {

        String sql = "INSERT INTO food (FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, fruit);
            stmt.setString(3, type);
            stmt.setString(4, exotic);
            stmt.executeUpdate();
            System.out.println(fruit + " добавлен");
            try(Statement st = connection.createStatement()) {

                try (ResultSet resultSet1 = st.executeQuery("SELECT * FROM FOOD WHERE FOOD_ID = " + id)) {
                    String name = null;
                    Integer id = null;
                    String type = null;
                    Integer exotic = null;
                    while (resultSet1.next()) {
                        id = resultSet1.getInt(1);
                        name = resultSet1.getString(2);
                        type = resultSet1.getString(3);
                        exotic = resultSet1.getInt(4);
                    }
                    Assertions.assertEquals(fruit, name, "не " + fruit);
                    Assertions.assertEquals(this.type, type, "не " + this.type);
                    Assertions.assertEquals(1, exotic, "(экзотичность) не " + this.exotic);
                    Assertions.assertEquals(5, id, "(id) не " + this.id);
                }
            }

            Assertions.assertEquals(5, MethodsCrud.showFood(connection, sqlShowFood).size(),
                    "в списке нет " + id + " элемента");
            Assertions.assertFalse(MethodsCrud.deleteFood(id, connection, sqlShowFood).contains(fruit),
                    "содержит " + fruit);
            System.out.println(fruit + " удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void setUp() throws SQLException {

        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConfProperties.getProperty("urlBd"));
        dataSource.setUsername(ConfProperties.getProperty("username"));
        dataSource.setPassword(ConfProperties.getProperty("password"));
        connection = dataSource.getConnection();
    }

    @AfterAll
    public static void tearDown() throws SQLException {

        dataSource.close();
        connection.close();
    }

}
