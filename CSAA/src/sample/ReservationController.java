package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    ObservableList<ReservationDTO> listR;

    @FXML
    private TableView<ReservationDTO> table_reservations;

    @FXML
    private TableColumn<ReservationDTO, Integer> col_id;

    @FXML
    private TableColumn<ReservationDTO, String> col_name;

    @FXML
    private TableColumn<ReservationDTO, String> col_phone;

    @FXML
    private TableColumn<ReservationDTO, String> col_spz;

    @FXML
    private TableColumn<ReservationDTO, String> col_time;

    @FXML
    private TableColumn<ReservationDTO, String> col_date;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_phone;

    @FXML
    private TextField tf_spz;

    @FXML
    private ChoiceBox<String> timeChoiceBox;

    @FXML
    private DatePicker dp_date;

    @FXML
    private void createReservation() {
        ReservationProvider.CreateReservation(tf_name.getText(), tf_phone.getText(), tf_spz.getText(), dp_date, timeChoiceBox.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeTimeChoiceBox();
        initializeTable();
        refreshReservations();
    }

    private void initializeTimeChoiceBox(){
        for(int i = 0; i < TimeIndex.hours.size(); i++){
            timeChoiceBox.getItems().add(TimeIndex.hours.get(i));
        }
    }

    private void initializeTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_spz.setCellValueFactory(new PropertyValueFactory<>("spz"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void refreshReservations(){
        listR = ReservationProvider.GetAllReservations();
        table_reservations.setItems(listR);
    }

}
