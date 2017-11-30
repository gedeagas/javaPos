package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import service.Database;

import java.sql.*;

/**
 * Created by gedeagas on 22/11/17.
 */
public class Login extends Application {

    private double xOffset = 0;
    private double yOffset = 0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/scene/Login.fxml"));



        root.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });



        primaryStage.setTitle("Filkom Pos");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.show();
        start();

    }

    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("HEHEH");
    }

    private void start(){
        Database newDb = new Database();
        newDb.getAllRecords();

    }


}
