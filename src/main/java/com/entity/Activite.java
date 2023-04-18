package com.entity;

import com.db.init.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Activite {
    private int id;
    private String nom;
    private int prix;
    private List<Option> options;

    public Activite(int idActivite) {
        this.id = idActivite;
        try {
            fetchFromDatabase(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchFromDatabase(int id) throws SQLException {
        String query = "SELECT a.prix AS activite_prix, o.nom, o.prix, o.idOption " +
                "FROM activite a " +
                "LEFT JOIN `option` o ON o.idActivite = a.idActivite " +
                "WHERE a.idActivite = ?";

        try (Connection conn = DatabaseConnection.getConnection()){
             PreparedStatement ps = conn.prepareStatement(query);
             ps.setInt(id, this.id);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                this.prix = rs.getInt("activite_prix");

                if (this.options == null) {
                    this.options = new ArrayList<>();
                }
                String optionNom = rs.getString("nom");
                float optionPrix = rs.getFloat("prix");
                int optionId = rs.getInt("idOption");

                Option option = new Option(optionId, optionNom, optionPrix);
                this.options.add(option);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int SumOfOptions() {
        int sum = 0;
        for (Option option : this.options) {
            sum += option.getPrix();
        }
        return sum;
    }

    public Activite(String nom, int id, int prix) {
        this.nom = nom;
        this.id = id;
        this.prix = prix;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void addOption(Option option) {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }
        this.options.add(option);
    }
}
