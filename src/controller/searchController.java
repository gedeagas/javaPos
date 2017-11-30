package controller;

import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import models.categoryModels;
import models.salesModel;
import service.Database;
import service.vGlobal;

/**
 * Created by gedeagas on 27/11/17.
 */

public class searchController {
    @FXML
    TableView<salesModel> searchTable;
    @FXML
    TableColumn ean;
    @FXML
    TableColumn name;
    @FXML
    TableColumn price;
    @FXML
    TableColumn action;


    public ObservableList<salesModel> list;
    public static String searchString = "";

    public ObservableList<salesModel> listSearch = FXCollections.observableArrayList ();

    public void initialize() {
        ean.setCellValueFactory(new PropertyValueFactory("searchEan"));
        name.setCellValueFactory(new PropertyValueFactory("searchName"));
        price.setCellValueFactory(new PropertyValueFactory("searchPrice"));

        action.setCellFactory(new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new searchController.ButtonCell("he");
            }
        });

        service.Database db = new Database();
        System.out.println(vGlobal.searchXQuery);
        listSearch = db.searchSales("");

        searchTable.setItems(listSearch);



    }

    //Define the button cell
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {


        private Button cellButton;

        ButtonCell(String text){
            cellButton = new Button();
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    models.searchModels record = (models.searchModels) getTableRow().getItem();
                    System.out.println(record.getSearchName());
                    System.out.println(record.getSearchId());
                    list.add(new salesModel(record.getSearchId(),record.getSearchName(),record.getSearchPrice(),record.getSearchEan()));



                }
            });
        }
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                cellButton.setText("Tambah");
                setGraphic(cellButton);
            } else { // you must always do the following in cell subclasses:
                setGraphic(null);
            }
        }
    }
}
