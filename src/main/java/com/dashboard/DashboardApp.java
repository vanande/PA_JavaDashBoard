package com.dashboard;

import com.entity.TeamBuilding;
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

public class DashboardApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Pie Chart");
        Button button3 = new Button("Button 3");
        Button button4 = new Button("Button 4");

        VBox vbox = new VBox(button1, button2, button3, button4);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);


        Scene scene = new Scene(vbox, 320, 240);
        primaryStage.setTitle("Vertical Buttons");
        primaryStage.setScene(scene);
        primaryStage.show();

        button1.setOnAction(event -> {
            // Create a new window
            Stage circleStage = new Stage();
            circleStage.setTitle("Circle Window");
            circleStage.initModality(Modality.APPLICATION_MODAL); // Block input events to other windows
            StackPane stackPane = new StackPane();
            Circle circle = new Circle(50, Color.BLUE);
            stackPane.getChildren().add(circle);
            Scene circleScene = new Scene(stackPane, 200, 200);
            circleStage.setScene(circleScene);
            circleStage.show();

            // Create a back button to return to the previous window
            Button backButton = new Button("Back");
            backButton.setOnAction(backEvent -> circleStage.close());
            stackPane.getChildren().add(backButton);
            StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);
        });

        button2.setOnAction(event -> {
            int tb_id = 1;

            // Create a new window
            Stage pieChartStage = new Stage();
            pieChartStage.setTitle("Pie Chart Window");
            pieChartStage.initModality(Modality.APPLICATION_MODAL);
            StackPane stackPane = new StackPane();

            // Do the math
            TeamBuilding tb = new TeamBuilding(tb_id);
            int totalPrice = tb.getTotalPrice();
            System.out.println(totalPrice);



            // Create a pie chart
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Activités", 80),
                            new PieChart.Data("Prestataire", 19),
                            new PieChart.Data("Autre", 1)
                    );
            PieChart pieChart = new PieChart(pieChartData);
            pieChart.setTitle("Répartition des activités");

            // Set the colors of the pie chart
            pieChartData.get(0).getNode().setStyle("-fx-pie-color: green;");
            pieChartData.get(1).getNode().setStyle("-fx-pie-color: blue;");
            pieChartData.get(2).getNode().setStyle("-fx-pie-color: red;");

            // Create a back button to return to the previous window
            Button backButton = new Button("Back");
            backButton.setOnAction(backEvent -> pieChartStage.close());

            // Add the pie chart and the back button to a new stack pane
            StackPane pieChartPane = new StackPane();
            pieChartPane.getChildren().addAll(pieChart, backButton);
            StackPane.setAlignment(backButton, Pos.BOTTOM_RIGHT);

            // Add the stack pane to the scene and display the scene
            Scene pieChartScene = new Scene(pieChartPane, 500, 500);
            pieChartStage.setScene(pieChartScene);
            pieChartStage.show();

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
