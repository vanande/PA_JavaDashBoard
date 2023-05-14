package com.dashboard;

import com.entity.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class DashboardApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button1 = new Button("Top Iencli");
        Button button2 = new Button("Pie Chart");
        Button button3 = new Button("Presta Bar Chart");
        Button button4 = new Button("Mat Bar Chart");

        VBox vbox = new VBox(button1, button2, button3, button4);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);


        Scene scene = new Scene(vbox, 320, 240);
        primaryStage.setTitle("Vertical Buttons");
        primaryStage.setScene(scene);
        primaryStage.show();

        button1.setOnAction(event -> {
            ListClient listClient = new ListClient();
            try {
                listClient.InitAllClient();
            } catch (Exception e) {
                e.printStackTrace();
            }

            float total = 0;
            for (Client c : listClient.getListClient()) {
            System.out.println(c.getName());
            System.out.println(c.getTotal_spent());
            total += c.getTotal_spent();
            }

            Stage pieChartStage = new Stage();
            pieChartStage.setTitle("Top client Pie Chart");
            pieChartStage.initModality(Modality.APPLICATION_MODAL);

            PieChart pieChart = new PieChart();

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Client c : listClient.getListClient()) {
                Float percent = (float) ((c.getTotal_spent() / total) * 100);
                System.out.println("Total total : " + total);
                System.out.println("Total spent : " + c.getTotal_spent());
                System.out.println("Percent : " + c.getTotal_spent() / total);
                pieChartData.add(new PieChart.Data(c.getName() + " : " + String.format("%.1f", percent) + "%", c.getTotal_spent()));
            }

            pieChart.setData(pieChartData);

            // Create a back button to return to the previous window
            backButton(pieChartStage, pieChart);


        });

        button2.setOnAction(event -> {
            int tb_id = 1 ;

            if (false) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter the Team Building ID : ");
                tb_id = sc.nextInt();
            }

            Stage pieChartStage = new Stage();
            pieChartStage.setTitle("Team Building " + tb_id + " Pie Chart");
            pieChartStage.initModality(Modality.APPLICATION_MODAL);

            TeamBuilding tb = new TeamBuilding(tb_id);
            int totalPrice = tb.getTotalPrice();
            System.out.println(totalPrice);

            PieChart pieChart = new PieChart();


            // Add data to the pie chart
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            Map<String, Integer> map = new HashMap<>();
            Map<String, Integer> option_map = new HashMap<>();

            for (Activite a : tb.getActivities()) {
                if (map.containsKey(a.getNom())) {
                    map.put(a.getNom(), map.get(a.getNom()) + a.getPrix());
                } else {
                    map.put(a.getNom(), a.getPrix());
                }
                for (Option o : a.getOptions()) {
                    if (option_map.containsKey(o.getNom())) {
                        option_map.put(o.getNom(), (int) (option_map.get(o.getNom()) + o.getPrix()));
                    } else {
                        option_map.put(o.getNom(), (int) o.getPrix());
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey() + " " + entry.getValue() + "$", entry.getValue()));
            }
            for (Map.Entry<String, Integer> entry : option_map.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey() + " : " + entry.getValue()+ "$", entry.getValue()));
            }

            pieChart.setData(pieChartData);
            backButton(pieChartStage, pieChart);

        });

        button3.setOnAction(event -> {
            PrestataireBarChart prestataireBarChartApp = new PrestataireBarChart();
            prestataireBarChartApp.start(primaryStage);
        });

        button4.setOnAction(event -> {
            MaterielBarChart materielBarChartApp = new MaterielBarChart();
            materielBarChartApp.start(primaryStage);
        });


    }

    private static void backButton(Stage pieChartStage, PieChart pieChart) {
        // Create a back button to return to the previous window
        Button backButton = new Button("Back");
        backButton.setOnAction(backEvent -> pieChartStage.close());

        // Add the pie chart and the back button to a new stack pane
        StackPane pieChartPane = new StackPane();
        pieChartPane.getChildren().addAll(pieChart, backButton);
        StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);

        // Add the stack pane to the scene and display the scene
        Scene pieChartScene = new Scene(pieChartPane, 700, 700);
        pieChartStage.setScene(pieChartScene);
        pieChartStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}