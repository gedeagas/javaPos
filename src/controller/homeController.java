package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.vGlobal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by gedeagas on 22/11/17.
 */
public class homeController {
    @FXML private Label home_name;
    @FXML private Button favHome;
    @FXML private Button  favSettings;
    @FXML private Button  favAbout;
    @FXML private Pane salesButton;
    @FXML private Pane inventoryButton;
    @FXML private Pane invButton;
    @FXML private HBox homebox;
    @FXML private Label todayDate;

    @FXML private Label todayJam;
    Parent root;

    private double xOffset = 0;
    private double yOffset = 0;

    private Stage myStage;
    public void setStage(Stage stage) {
        myStage = stage;
    }

    public void initialize() {
        String name = vGlobal.name;
        home_name.setText(name);

        Date xdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
        String dayOfWeek = dateFormat.format(xdate);
        Calendar startDate = Calendar.getInstance();
        int day= startDate.get(Calendar.DAY_OF_MONTH);
        int year= startDate.getWeekYear();
        String month = startDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        todayDate.setText(dayOfWeek + ", ");
        todayJam.setText(String.valueOf(day) + " " + month + " " + String.valueOf(year));


        homebox.setOnMousePressed(event -> {
            xOffset = myStage.getX() - event.getScreenX();
            yOffset = myStage.getY()- event.getScreenY();
        });

        homebox.setOnMouseDragged(event -> {
            myStage.setX(event.getScreenX() + xOffset);
            myStage.setY(event.getScreenY() + yOffset);
        });

        favHome.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent> () {

            @Override
            public void handle(javafx.scene.input.MouseEvent t) {
                favHome.setStyle(
                        "-fx-background-color:  linear-gradient(to right, #00c6ff, #0072ff);" +
                                "-fx-text-fill: white;"

                );

            }
        });



        favHome.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent> () {

            @Override
            public void handle(javafx.scene.input.MouseEvent t) {
                favHome.setStyle("-fx-background-color:transparent;");
            }
        });

        favSettings.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent> () {

            @Override
            public void handle(javafx.scene.input.MouseEvent t) {
                favSettings.setStyle(
                        "-fx-background-color:  linear-gradient(to right, #00c6ff, #0072ff);" +
                                "-fx-text-fill: white;"

                );

            }
        });

        favSettings.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent> () {

            @Override
            public void handle(javafx.scene.input.MouseEvent t) {
                favSettings.setStyle("-fx-background-color:transparent;");
            }
        });

        favAbout.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent> () {

            @Override
            public void handle(javafx.scene.input.MouseEvent t) {
                favAbout.setStyle(
                        "-fx-background-color:  linear-gradient(to right, #00c6ff, #0072ff);" +
                                "-fx-text-fill: white;"
                );

            }
        });

        favAbout.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent> () {

            @Override
            public void handle(javafx.scene.input.MouseEvent t) {
                favAbout.setStyle("-fx-background-color:transparent;");
            }
        });

        salesButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                try {


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/Sales.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage2 = new Stage();
                    Scene scene = new Scene(root1);
                    scene.setFill(null);

                    stage2.setScene(scene);
                    stage2.show();

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }

        });

        invButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                try {


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/Inventory.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage2 = new Stage();
                    Scene scene = new Scene(root1);
                    scene.setFill(null);

                    stage2.setScene(scene);
                    stage2.show();

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }

        });

        inventoryButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/Category.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage2 = new Stage();
                    Scene scene = new Scene(root1);
                    scene.setFill(null);

                    stage2.setScene(scene);
                    stage2.show();

                } catch(Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }




}
