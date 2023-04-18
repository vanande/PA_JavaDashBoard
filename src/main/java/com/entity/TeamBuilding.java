package com.entity;

import com.db.init.DatabaseConnection;
import com.db.request.SelectRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamBuilding {
    private int id;
    private String title;
    private int totalPrice;
    private List<Activite> activities;

    public TeamBuilding(int id) {
        this.id = id;
        try {
            ResultSet rs = SelectRequest.select("team_building", new String[]{"titre"}, "idTeam_Building = " + this.id);

            while (rs.next()) {
                this.title = rs.getString("titre");
            }
            rs.close();
            ResultSet rs2 = SelectRequest.select("teambuilding_activite", new String[]{"prix_total", "idActivite"}, "idTeam_Building = " + this.id);
            while (rs2.next()) {
                if (this.activities == null) {
                    this.activities = new ArrayList<>();
                }
                this.totalPrice += rs2.getInt("prix_total");
                this.activities.add(new Activite(rs2.getInt("idActivite")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void setTotalPrice() {
        this.totalPrice = 0;
        for (Activite activity : this.activities) {
            this.totalPrice += activity.SumOfOptions() + activity.getPrix();
        }
    }

    public List<Activite> getActivities() {
        return activities;
    }

    public void setActivities(List<Activite> activities) {
        this.activities = activities;
    }
}
