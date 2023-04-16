package com.db.stats;

import com.db.init.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Materiel {

    private String nomMateriel;
    private int nombreLocations;

    public Materiel(String nomMateriel, int nombreLocations) {
        this.nomMateriel = nomMateriel;
        this.nombreLocations = nombreLocations;
    }

    public static List<Materiel> getMaterielStats() throws SQLException {
        List<Materiel> materiels = new ArrayList<>();

        String query = "SELECT m.nom, COUNT(*) AS nombre_locations " +
                "FROM materiel m " +
                "JOIN loue l ON l.idMATERIEL = m.idMATERIEL " +
                "GROUP BY m.idMATERIEL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nomMateriel = rs.getString("nom");
                int nombreLocations = rs.getInt("nombre_locations");

                materiels.add(new Materiel(nomMateriel, nombreLocations));
            }
        }

        return materiels;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    public Integer getNombreLocations() {
        return nombreLocations;
    }
}
