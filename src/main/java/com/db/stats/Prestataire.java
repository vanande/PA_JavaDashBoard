package com.db.stats;

import com.db.init.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Prestataire {

    private String nomPrestataire;
    private String metierPrestataire;
    private int nombreEngagements;
    private int totalPrixPaye;

    public Prestataire(String nomPrestataire, int nombreEngagements, int totalPrixPaye, String metierPrestataire) {
        this.nomPrestataire = nomPrestataire;
        this.nombreEngagements = nombreEngagements;
        this.totalPrixPaye = totalPrixPaye;
        this.metierPrestataire = metierPrestataire;
    }

    public static List<Prestataire> getPrestatairesStats() throws SQLException {
        List<Prestataire> prestataires = new ArrayList<>();

        String query = "SELECT p.nom,p.metier, COUNT(*) AS nombre_engagements, SUM(tb.prix_total) AS total_prix_paye " +
                "FROM prestataire p " +
                "JOIN engage e ON e.idPRESTATAIRE = p.idPRESTATAIRE " +
                "JOIN teambuilding_activite tb ON e.idTEAM_BUILDING = tb.TEAM_BUILDING_idTEAM_BUILDING " +
                "GROUP BY p.idPRESTATAIRE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nomPrestataire = rs.getString("nom");
                String metierPrestataire = rs.getString("metier");
                int nombreEngagements = rs.getInt("nombre_engagements");
                int totalPrixPaye = rs.getInt("total_prix_paye");

                prestataires.add(new Prestataire(nomPrestataire, nombreEngagements, totalPrixPaye, metierPrestataire));
            }
        }

        return prestataires;
    }

    public String getNomPrestataire() {
        return nomPrestataire;
    }

    public String getNombreEngagements() {
        return String.valueOf(nombreEngagements);
    }

    public String getTotalPrixPaye() {
        return String.valueOf(totalPrixPaye);
    }

    public String getMetierPrestataire() {
        return metierPrestataire;
    }
}
