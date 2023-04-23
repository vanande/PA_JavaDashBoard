package com.entity;
import com.db.init.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListClient {
    private List<Client> listClient;


    public void InitAllClient() {
        try{
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT \n" +
                    "  client.nom AS client_nom,\n" +
                    "  SUM(activite.prix) + SUM(list_option.prix) AS prix_total\n" +
                    "FROM \n" +
                    "  client\n" +
                    "  INNER JOIN team_building ON client.idCLIENT = team_building.idClient\n" +
                    "  INNER JOIN teambuilding_activite ON team_building.idTEAM_BUILDING = teambuilding_activite.idTEAM_BUILDING\n" +
                    "  INNER JOIN activite ON teambuilding_activite.idActivite = activite.idActivite\n" +
                    "  INNER JOIN list_activite ON activite.idlist_activite = list_activite.idlist_activite\n" +
                    "  INNER JOIN `option` ON activite.idActivite = `option`.idActivite\n" +
                    "  INNER JOIN list_option ON `option`.idlist_option = list_option.idlist_option\n" +
                    "GROUP BY \n" +
                    "  client.idCLIENT;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String nomClient = rs.getString("client_nom");
                int prixTotal = rs.getInt("prix_total");
                if (listClient == null){
                    listClient = new ArrayList<>();
                }
                listClient.add(new Client(nomClient, prixTotal));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getListClient() {
        return listClient;
    }

}
