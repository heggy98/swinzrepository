package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableView<Reservation> table_reservations;

    @FXML
    private TableColumn<Reservation, Integer> col_id;

    @FXML
    private TableColumn<Reservation, String> col_name;

    @FXML
    private TableColumn<Reservation, String> col_phone;

    @FXML
    private TableColumn<Reservation, String> col_spz;

    @FXML
    private TableColumn<Reservation, String> col_time;

    @FXML
    private TableColumn<Reservation, Date> col_date;

    @FXML
    private void buttonUlozitAction(ActionEvent event) {
        System.out.println("tak to máš uložený no :)");
    }

    ObservableList<Reservation> listR;

    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        col_id.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<Reservation,String>("name"));
        col_phone.setCellValueFactory(new PropertyValueFactory<Reservation,String>("phone"));
        col_date.setCellValueFactory(new PropertyValueFactory<Reservation, Date>("id"));
        col_time.setCellValueFactory(new PropertyValueFactory<Reservation,String>("id"));
        col_spz.setCellValueFactory(new PropertyValueFactory<Reservation,String>("spz"));

        listR = Database_Access.getReservations();
        table_reservations.setItems(listR);
    }

}
