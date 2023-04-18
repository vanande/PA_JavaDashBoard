package com.dashboard;

import com.entity.Materiel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class MaterielBarChart extends Application {

    @Override
    public void start(Stage stage) {

        // Creating the axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Matériel");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Nombre d'engagements");

        XYChart.Series<String, Number> data = new XYChart.Series<>();

        // Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Nombre d'engagements par matériel");

        // Collecting the data
        try {
            List<Materiel> materiels = Materiel.getMaterielStats();

            for (Materiel materiel : materiels) {
                System.out.println("Nom du matériel : " + materiel.getNomMateriel());
                System.out.println("Nombre d'engagements : " + materiel.getNombreLocations());
                data.getData().add(new XYChart.Data<>(materiel.getNomMateriel(), materiel.getNombreLocations()));
            }

        } catch (SQLException e) {
            System.out.println("Failed to get materiels stats.");
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
        stage.setTitle("Bar Chart Matériel");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
