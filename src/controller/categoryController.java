package controller;

import com.sun.prism.impl.Disposer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.categoryModels;
import models.salesModel;
import service.Database;

import javax.xml.soap.Text;


/**
 * Created by gedeagas on 23/11/17.
 */
public class categoryController {
    @FXML
    TableView<categoryModels> categoryTable;
    @FXML
    TableColumn tableCategory;
    @FXML
    TableColumn tableId;
    @FXML
    Button addNew;
    @FXML
    TextField textFieldId;
    @FXML
    TableColumn deleteButton;

    public ObservableList<categoryModels> list = FXCollections.observableArrayList ();

    public void initialize() {
        tableId.setCellValueFactory(new PropertyValueFactory("categoryId"));
        deleteButton.setCellFactory(new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell("he");
            }
        });
        tableCategory.setCellValueFactory(new PropertyValueFactory("categoryName"));

        service.Database db = new Database();
        db.getAllRecords();

        list = db.queryCategory();

        categoryTable.setItems(list);

        addNew.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                service.Database db = new Database();
                Boolean result = db.insertCategory(textFieldId.getText());
                if(result){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucsess");
                    alert.setHeaderText("Insert is sucess");
                    alert.setContentText("1 Row is added");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Insert is denied");
                    alert.setContentText("Your insert is failed");
                }

                list=db.queryCategory();
                categoryTable.setItems(list);


            }
        });

    }
    //Define the button cell
    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {


        private Button cellButton;

        ButtonCell(String text){
            cellButton = new Button();
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    models.categoryModels record = (models.categoryModels) getTableRow().getItem();
                    service.Database db = new Database();
                    boolean result = db.deleteCategory(record.getCategoryId());
                    if(result){
                        list=db.queryCategory();
                        categoryTable.setItems(list);
                    } else {
                        System.out.println("error");
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


}
