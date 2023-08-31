package ru.ibs.task4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MethodsCrud {

    public static List<String> deleteFood(String id, Connection connection, String sqlShowFood) throws SQLException {
        String sql = "DELETE FROM food WHERE food_id = "+id ;
        List<String> names = new ArrayList<>();
        try (Statement ps  = connection.createStatement()) {
            ps.executeUpdate(sql);
            try (ResultSet resultSet3 = ps.executeQuery(sqlShowFood)) {
                while (resultSet3.next()) {
                    String name = resultSet3.getString(2);
                    names.add(name);
                }
            }
        }
        return names;
    }

    public static List<String> showFood(Connection connection, String sqlShowFood) throws SQLException {
        List<String> names = new ArrayList<>();
        try (PreparedStatement ps  = connection.prepareStatement(sqlShowFood)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    names.add(resultSet.getString(2));
                    System.out.println(id + ". " + "\t" + name);
                }
            }
        }
        return names;
    }

}
