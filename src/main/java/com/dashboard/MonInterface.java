package com.dashboard;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MonInterface extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Créer les boutons
        Button bouton1 = new Button("Option 1");
        Button bouton2 = new Button("Option 2");
        Button bouton3 = new Button("Option 3");
        Button bouton4 = new Button("Option 4");

        // Définir les actions à effectuer lorsqu'on clique sur les boutons
        bouton1.setOnAction(e -> afficherGrapheVierge());
        bouton2.setOnAction(e -> afficherGrapheVierge());
        bouton3.setOnAction(e -> afficherGrapheVierge());
        bouton4.setOnAction(e -> System.exit(0));

        // Créer une boîte verticale pour contenir les boutons
        VBox boite = new VBox();
        boite.setAlignment(Pos.CENTER);
        boite.setPadding(new Insets(10));
        boite.setSpacing(10);
        boite.getChildren().addAll(bouton1, bouton2, bouton3, bouton4);

        // Créer la scène et ajouter la boîte de boutons
        Scene scene = new Scene(boite, 300, 200);

        // Afficher la fenêtre
        primaryStage.setTitle("Mon Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void afficherGrapheVierge() {
        // Code pour afficher le graphe vierge
        System.out.println("Affichage du graphe vierge...");
    }
}
