package ru.ibs.task4.spring;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodRowMapper implements RowMapper<Food> {
    @Override
    public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
        Food food = new Food();
        food.setId(rs.getInt("food_id"));
        food.setName(rs.getString("food_name"));
        food.setType(rs.getString("food_type"));
        food.setExotic(rs.getInt("food_exotic"));
        return food;
    }
}
