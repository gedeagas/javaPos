package controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import models.salesModel;
import service.Database;
import service.vGlobal;

import java.math.BigDecimal;

/**
 * Created by gedeagas on 23/11/17.
 */
public class salesController {
    @FXML
    public TableView<models.salesModel> mainTable;

    @FXML private TableColumn namaBarang;
    @FXML private TableColumn harga;
    @FXML private Label totalBelanja;
    @FXML private Button tambahButton;
    @FXML private TextField searchText;
    @FXML private TableColumn eanBarang;
    @FXML private Label totalBayar;
    @FXML private Button id100;
    @FXML private Button id50;
    @FXML private Button id20;
    @FXML private Button id10;
    @FXML private Button id5;
    @FXML private Button id2;
    @FXML private Button complete1;



    private long holderTotalBayar = 0;
    private long holderTotalBelanja = 0;



    public ObservableList<models.salesModel> list = FXCollections.observableArrayList ();

    String musicFile = "src/assets/cr.mp3";     // For example

    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    private String formater(long input){
        java.util.Currency idr = java.util.Currency.getInstance("IDR");
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.GERMANY);
        format.setCurrency(idr);

        return format.format(input);

    }

    public void initialize() {



        namaBarang.setCellValueFactory(new PropertyValueFactory("namaBarang"));
        harga.setCellValueFactory(new PropertyValueFactory("hargaBarang"));
        eanBarang.setCellValueFactory(new PropertyValueFactory("eanBarang"));

        mainTable.setItems(list);
        //Test.Anjay();

        totalBayar.setText(formater(holderTotalBayar));


        list.addListener(new ListChangeListener<salesModel>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends salesModel> c) {
                long total = (long) 0;

                for (salesModel item : list){
                    total += item.getHargaBarang();

                }
                java.util.Currency idr = java.util.Currency.getInstance("IDR");
                java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.GERMANY);
                format.setCurrency(idr);
                holderTotalBelanja = total;
                System.out.println(format.format(total));
                totalBelanja.setText(format.format(total));

            }

        });

        id100.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                holderTotalBayar += 100000;
                totalBayar.setText(formater(holderTotalBayar));


            }

        });

        id50.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                holderTotalBayar += 50000;
                totalBayar.setText(formater(holderTotalBayar));

            }

        });

        id20.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                holderTotalBayar += 20000;
                totalBayar.setText(formater(holderTotalBayar));

            }

        });


        id10.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                holderTotalBayar += 10000;
                totalBayar.setText(formater(holderTotalBayar));

            }

        });

        id5.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                holderTotalBayar += 5000;
                totalBayar.setText(formater(holderTotalBayar));

            }

        });

        id2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                holderTotalBayar += 2000;
                totalBayar.setText(formater(holderTotalBayar));

            }

        });

        complete1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                if(holderTotalBayar != 0 && holderTotalBelanja != 0){
                    if(holderTotalBayar>=holderTotalBelanja){

                        Database db = new Database();
                        boolean hasil = db.insertSales(holderTotalBelanja);
                        if(hasil){
                            long kembalian = holderTotalBayar-holderTotalBelanja;
                            mediaPlayer.play();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Terima Kasih");
                            alert.setHeaderText("Total Kembalian");
                            alert.setContentText(formater(kembalian));
                            alert.showAndWait();

                            try {
                                Stage stage = (Stage) complete1.getScene().getWindow();
                                stage.close();
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Maaf");
                        alert.setHeaderText("Uang Bayar Tidak Mencukupi");
                        alert.setContentText("Mohon coba lagi");

                        alert.showAndWait();

                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Maaf");
                    alert.setHeaderText("Tidak ada belaja yang terinput");
                    alert.setContentText("Mohon coba lagi");

                    alert.showAndWait();



                }

            }

        });

        tambahButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                try {


                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/Search.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();

                    searchController x = (searchController) fxmlLoader.getController();
                    vGlobal.searchXQuery = searchText.getText();
                    x.list = list;

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