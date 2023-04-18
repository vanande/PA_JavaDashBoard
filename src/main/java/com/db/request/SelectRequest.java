package com.db.request;


import com.db.init.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class SelectRequest {


    /**
     * @param table The table to select from
     * @param columns The columns to select
     * @param whereClause The where clause to use
     * @return ResultSet containing the results
     * @throws SQLException
     * @Example
     * <p>ResultSet rs = select("Prestataire", new String[]{"name", "id"}, "id = 1");</p>
     * <p>while (rs.next()) {</p>
     * <p>String name = rs.getString("name");</p>
     * <p>int id = rs.getInt("id");</p>
     * <p>System.out.printf("%s, %d\n", name, id);</p>
     * <p>}</p>
     */
    public static ResultSet select(String table, String[] columns, String whereClause) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");

        if (columns.length == 0) {
            sb.append("*");
        } else if (columns.length == 1) {
            sb.append(columns[0]);
        } else {
            for (int i = 0; i < columns.length; i++) {
                sb.append(columns[i]);
                if (i != columns.length - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(" FROM ").append(table);
        if (whereClause != null) {
            sb.append(" WHERE ").append(whereClause);
        }

        System.out.println(sb);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
