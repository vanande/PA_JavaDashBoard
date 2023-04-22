package com.entity;

import com.db.init.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TeamBuilding {
    private int id;
    private String title;
    private int totalPrice;
    private List<Activite> activities;

    public TeamBuilding(int id){
        this.id = id;
        try{
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT list_option.nom AS o_nom, list_option.prix AS o_prix, list_activite.nom AS a_nom, activite.prix AS a_prix, team_building.titre AS tb_titre\n" +
                    "FROM team_building\n" +
                    "INNER JOIN teambuilding_activite ON team_building.idTEAM_BUILDING = teambuilding_activite.idTEAM_BUILDING\n" +
                    "INNER JOIN activite ON teambuilding_activite.idActivite = activite.idActivite\n" +
                    "INNER JOIN list_activite ON activite.idlist_activite = list_activite.idlist_activite\n" +
                    "INNER JOIN list_option ON list_option.idlist_activite = list_activite.idlist_activite\n" +
                    "WHERE team_building.idTEAM_BUILDING = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Activite> activities = new ArrayList<>();
            List<Option> options = new ArrayList<>();
            while (rs.next()){
                this.title = rs.getString("tb_titre");
                String nomActivite = rs.getString("a_nom");
                int prixActivite = rs.getInt("a_prix");
                String nomOption = rs.getString("o_nom");
                int prixOption = rs.getInt("o_prix");
                options.add(new Option(nomOption, prixOption));
                activities.add(new Activite(nomActivite, prixActivite, options));
            }

            this.activities = activities;

            this.totalPrice = 0;
            for (Activite a : activities){
                for (Option o : a.getOptions()){
                    System.out.println(o.getPrix() + o.getNom());
                    this.totalPrice += o.getPrix();
                }
                System.out.println(a.getPrix() + a.getNom());
                this.totalPrice += a.getPrix();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public TeamBuilding(int id, String title, int totalPrice, List<Activite> activities) {
        this.id = id;
        this.title = title;
        this.totalPrice = totalPrice;
        this.activities = activities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Activite> getActivities() {
        return this.activities;
    }

    public void setActivities(List<Activite> activities) {
        this.activities = activities;
    }
}
