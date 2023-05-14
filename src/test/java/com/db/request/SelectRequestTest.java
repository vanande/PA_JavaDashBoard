package com.db.request;


import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectRequestTest {
    public static void main(String[] args) {
        String[] columns = {"nom", "idPRESTATAIRE"};

        try {
            ResultSet resultSet = SelectRequest.select("prestataire", columns, "1");
            while (resultSet.next()) {
                String name = resultSet.getString("nom");
                int id = resultSet.getInt("idPRESTATAIRE");
                System.out.printf("%s, %d\n", name, id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
