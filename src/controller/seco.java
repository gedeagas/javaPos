package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import models.salesModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gedeagas on 23/11/17.
 */
public class seco implements Initializable {
    public ObservableList<salesModel> list;
    public String aha = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("heeh");
    }

    @FXML
    private void onPress(ActionEvent event) throws Exception {
        list.add(new salesModel(2,"Shit",(long) 2000000,2));
        System.out.println(aha);
    }

}