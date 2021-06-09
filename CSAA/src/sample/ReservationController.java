package sample;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ReservationController implements Initializable {

    ObservableList<ReservationDTO> listR;
    ReservationDTO selectedReservation;
    boolean editCheck = false;

    @FXML
    private TabPane tabs;

    @FXML
    private Tab tab_new, tab_edit, tab_table;

    @FXML
    private TableView<ReservationDTO> table_reservations;

    @FXML
    private TableColumn<ReservationDTO, Integer> col_id;

    @FXML
    private TableColumn<ReservationDTO, String> col_name, col_phone, col_spz, col_time, col_date;

    @FXML
    private TextField tf_name, tf_phone, tf_spz, tf_name_edit, tf_phone_edit, tf_spz_edit;

    @FXML
    private ChoiceBox<String> timeChoiceBox, timeChoiceBox_edit;

    @FXML
    private DatePicker dp_date, dp_date_edit;

    @FXML
    private Button createButton, editButton;

    @FXML
    private Label errorMessage;

    @FXML
    private void createReservation() throws InterruptedException {
        try{
            ReservationProvider.CreateReservation(tf_name.getText(), tf_phone.getText(), tf_spz.getText(), dp_date, timeChoiceBox.getValue());
            SetCreateFieldToEmpty();
            refreshReservations();
            tabs.getSelectionModel().select(tab_table);
        }
        catch (RuntimeException ex){
            ShowMessage("V tento den, v tomto časovém termínu je příliš mnoho rezervací, zvolte prosím jiný den/čas.");
        }
    }

    private void ShowMessage(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(5)
        );
        visiblePause.setOnFinished(
                event -> HideErrorMessage()
        );
        visiblePause.play();
    }

    private void SetCreateFieldToEmpty() {
        tf_name.setText("");
        tf_phone.setText("");
        tf_spz.setText("");
        dp_date.setValue(null);
        timeChoiceBox.setValue(null);
        HideErrorMessage();
    }

    private void HideErrorMessage() {
        errorMessage.setText("ErrorMessage");
        errorMessage.setVisible(false);
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
            timeChoiceBox_edit.getItems().add(TimeIndex.hours.get(i));
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

    @FXML
    public void checkCreate(){
        if(!tf_name.getText().equals("") && !tf_phone.getText().equals("") && !tf_spz.getText().equals("") && dp_date.getValue() != null && timeChoiceBox.getValue()!= null){
            createButton.setDisable(false);
            createButton.setVisible(true);
        }
        else{
            createButton.setDisable(true);
            createButton.setVisible(false);
        }
    }

    @FXML
    public void setSelectedReservationToNull(){ // TOTO BY SE MELO ZAVOLAT I KDYZ SE VYBERE NEJAKY JINY TAB NEZ 'UPRAVIT' ale to nevim jak :D
        if(selectedReservation != null){
            selectedReservation = null;
            tab_edit.setDisable(true);
            editCheck = false;
        }
    }

    @FXML
    public void getSelectedReservation() throws ParseException {
        selectedReservation = table_reservations.getSelectionModel().getSelectedItem();
        var dateFromReservation = new SimpleDateFormat("yyyy-MM-dd").parse(selectedReservation.getDate());
        if(selectedReservation != null && dateFromReservation.after(new Date())){
            tab_edit.setDisable(false);
        }
        else{
            tab_edit.setDisable(true);
        }
    }


    @FXML
    public void fillEditFields(){
        tf_name_edit.setText(selectedReservation.getName());
        tf_phone_edit.setText(selectedReservation.getPhone());
        tf_spz_edit.setText(selectedReservation.getSpz());
        dp_date_edit.setValue(LocalDate.parse(selectedReservation.getDate()));
        timeChoiceBox_edit.setValue(timeChoiceBox_edit.getItems().get(TimeIndex.GetIndex(selectedReservation.getTime())));
        editCheck = true;
    }

    @FXML
    public void checkEdit(){
        if(!tf_name_edit.getText().equals("") && !tf_phone_edit.getText().equals("") && !tf_spz_edit.getText().equals("") && dp_date_edit.getValue() != null && timeChoiceBox_edit.getValue()!= null && editCheck){
            editButton.setDisable(false);
            editButton.setVisible(true);
        }
        else{
            editButton.setDisable(true);
            editButton.setVisible(false);
        }
    }

    @FXML
    public void editReservation(){
        try{
            ReservationProvider.EditReservation(selectedReservation.getId() ,tf_name_edit.getText(), tf_phone_edit.getText(), tf_spz_edit.getText(), dp_date_edit, timeChoiceBox_edit.getValue());
            refreshReservations();
            tabs.getSelectionModel().select(tab_table);
            setSelectedReservationToNull();
        }
        catch (RuntimeException ex){
            ShowMessage("V tento den, v tomto časovém termínu je příliš mnoho rezervací, zvolte prosím jiný den/čas.");
        }
    }
}
