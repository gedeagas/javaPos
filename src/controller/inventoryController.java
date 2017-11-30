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
import models.inventoryModels;
import service.Database;

/**
 * Created by gedeagas on 23/11/17.
 */
public class inventoryController {
    @FXML TabPane theTab;
    @FXML
    TableView<models.inventoryModels> productTable;
    @FXML
    TableColumn tbEan;
    @FXML
    TableColumn tbName;
    @FXML
    TableColumn tbPrice;
    @FXML
    TableColumn tbAction;
    @FXML
    TableColumn tbDelete;

    @FXML private Button btnInsert;
    @FXML private TextField inName;
    @FXML private TextField inEan;
    @FXML private TextField inPrice;
    @FXML private TextField upName;
    @FXML private TextField upEan;
    @FXML private TextField upPrice;
    @FXML private Button btnUpdate;

    private int theId;


    public ObservableList<inventoryModels> list = FXCollections.observableArrayList ();

    public void reloadTable() {
        service.Database db = new Database();
        db.getAllRecords();

        list = db.inventoryQuery();

        productTable.setItems(list);
    }
    public void initialize() {
        tbEan.setCellValueFactory(new PropertyValueFactory("eanBarang"));
        tbName.setCellValueFactory(new PropertyValueFactory("namaBarang"));
        tbPrice.setCellValueFactory(new PropertyValueFactory("hargaBarang"));
        tbAction.setCellFactory(new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new inventoryController.updateButton("he");
            }
        });
        tbDelete.setCellFactory(new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new inventoryController.deleteButton("he");
            }
        });



        service.Database db = new Database();
        db.getAllRecords();

        list = db.inventoryQuery();

        productTable.setItems(list);

        btnInsert.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                Database db = new Database();
                String name = inName.getText();
                int ean = Integer.parseInt(inEan.getText());
                long harga =  Long.parseLong(inPrice.getText());

                boolean hasil = db.insertInventory(name,ean,harga);
                if(hasil){
                    reloadTable();
                } else {

                }

            }

        });

        btnUpdate.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                Database db = new Database();
                String name = upName.getText();
                int ean = Integer.parseInt(upEan.getText());
                long harga =  Long.parseLong(upPrice.getText());

                boolean hasil = db.updateInventory(theId, name,harga,ean);
                if(hasil){
                    reloadTable();
                } else {

                }

            }

        });
    }

    private class deleteButton extends TableCell<Disposer.Record, Boolean> {


        private Button cellButton;

        deleteButton(String text){
            cellButton = new Button();
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    models.inventoryModels record = (models.inventoryModels) getTableRow().getItem();
                    Database db = new Database();
                    boolean result = db.deleteInventory(record.getIdBarang());
                    if(result){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Sukses");
                        alert.setHeaderText(record.getNamaBarang() + " Berhasil Di Hapus");

                        alert.showAndWait();
                        reloadTable();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Terjadi Galat");
                        alert.setContentText("Mohon coba lagi");

                        alert.showAndWait();

                    }

                }
            });
        }
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                cellButton.setText("Delete");
                setGraphic(cellButton);
            } else { // you must always do the following in cell subclasses:
                setGraphic(null);
            }
        }
    }

    private class updateButton extends TableCell<Disposer.Record, Boolean> {


        private Button cellButton;

        updateButton(String text){
            cellButton = new Button();
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    models.inventoryModels record = (models.inventoryModels) getTableRow().getItem();
                    upName.setText(record.getNamaBarang());
                    upEan.setText(String.valueOf(record.getEanBarang()));
                    upPrice.setText(String.valueOf(record.getHargaBarang()));
                    theTab.getSelectionModel().select(1);
                    theId = record.getIdBarang();

                }
            });
        }
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                cellButton.setText("Update");
                setGraphic(cellButton);
            } else { // you must always do the following in cell subclasses:
                setGraphic(null);
            }
        }
    }
}
