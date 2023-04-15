package com.db.request;


import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectRequestTest {
    public static void main(String[] args) {
        String[] columns = {"name", "id"};

        try {
            ResultSet resultSet = SelectRequest.select("users", columns);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                System.out.printf("%s, %d\n", name, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
