package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.awt.*;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.sql.*;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.userModels;

/**
 * Created by gedeagas on 22/11/17.
 */

public class loginController {
    @FXML private javafx.scene.control.TextField username;
    @FXML private javafx.scene.control.TextField password;

    @FXML
    private javafx.scene.control.Button loginButton;

    @FXML
    private void btn1handle(javafx.event.ActionEvent event) {
        String lUsername = username.getText();
        String lPassword = password.getText();

        userModels user = new userModels(lUsername,lPassword);
        String hasil = user.login();
        if(hasil != "Logged In"){
            System.out.println("ERROR");

        } else {
            try {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scene/Home.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage2 = new Stage();
                stage2.setScene(new Scene(root1));
                stage2.show();

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {


    }


}
