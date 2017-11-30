package controller;

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
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login is denied");
            alert.setContentText("Your username or password is wrong");

            alert.showAndWait();

        } else {
            try {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/Home.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();



                Stage stage2 = new Stage();
                Scene scene = new Scene(root1);
                scene.setFill(null);


                stage2.initStyle(StageStyle.UNDECORATED);

                stage2.setScene(scene);

                homeController controller = fxmlLoader.getController();
                controller.setStage(stage2);
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
