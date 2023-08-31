package ru.ibs.task4.spring;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.ibs.ConfProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcSpring {

    private static Connection connection;
    private static HikariDataSource dataSource;
    private static JdbcTemplate jdbcTemplate;
    String fruit = ConfProperties.getProperty("fruit");
    String type = ConfProperties.getProperty("type");
    String id = ConfProperties.getProperty("id");
    String exotic = ConfProperties.getProperty("exotic");

    @BeforeAll
    public static void setUp() throws SQLException {
        dataSource = (HikariDataSource) getPooledDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        connection = dataSource.getConnection();
    }

    @AfterAll
    public static void tearDown() throws SQLException {

        dataSource.close();
        connection.close();
    }

    private static DataSource getPooledDataSource() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConfProperties.getProperty("urlBd"));
        dataSource.setUsername(ConfProperties.getProperty("username"));
        dataSource.setPassword(ConfProperties.getProperty("password"));

        return dataSource;
    }

    @Test
    public void testAddFoodSpring() {
        String sql = "INSERT INTO food (FOOD_ID, FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, id, fruit, type, exotic);
        System.out.println(fruit + " добавлен");
        String sql2 = "SELECT * FROM FOOD WHERE FOOD_ID = ?";
//        jdbcTemplate.query(sql2, (rs, rowNum) -> {
//            int id = rs.getInt("food_id");
//            String name = rs.getString("food_name");
//            String type = rs.getString("food_type");
//            int exotic = rs.getInt("food_exotic");
//
//        });
        jdbcTemplate.queryForRowSet(sql2, new FoodRowMapper(),  5);
    }

}
