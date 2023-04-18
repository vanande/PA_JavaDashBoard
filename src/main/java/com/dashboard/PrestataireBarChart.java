package com.dashboard;

import com.entity.Prestataire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class PrestataireBarChart extends Application {

    @Override
    public void start(Stage stage) {

        // Creating the axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Prestataire");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total Prix Payé (en euro)");

        XYChart.Series<String, Number> data = new XYChart.Series<>();

        // Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Total Prix Payé par Prestataire");

        // Collecting the data
        try {
            List<Prestataire> prestataires = Prestataire.getPrestatairesStats();

            for (Prestataire prestataire : prestataires) {
                System.out.println("Nom du prestataire : " + prestataire.getNomPrestataire());
                System.out.println("Metier du prestataire : " + prestataire.getMetierPrestataire());
                System.out.println("Nombre d'engagements : " + prestataire.getNombreEngagements());
                System.out.println("Total prix payé : " + prestataire.getTotalPrixPaye());
                String prestataireNameAndMetier = prestataire.getNomPrestataire() + " - " + prestataire.getMetierPrestataire();
                data.getData().add(new XYChart.Data<>(prestataireNameAndMetier, Double.parseDouble(prestataire.getTotalPrixPaye())));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get prestataires stats.");
            e.printStackTrace();
        }

        // Adding data to the Bar chart
        barChart.getData().add(data);

        // Creating a VBox to hold the Bar chart
        VBox vbox = new VBox(barChart);

        // Creating a Scene
        Scene scene = new Scene(vbox, 800, 600);

        // Setting the Scene
        stage.setScene(scene);
        stage.setTitle("Bar Chart Prestataire");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
