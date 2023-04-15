package com.dashboard;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
        Button button2 = new Button("Button 2");
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
