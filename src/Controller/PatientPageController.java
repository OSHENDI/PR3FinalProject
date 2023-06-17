/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Model.Appointments;
import Model.BookedAppointments;
import Model.PersistenceManager;
import Model.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PatientPageController implements Initializable {
    
    public static Users current_User;
    
    public static String BookedType;
    @FXML
    private AnchorPane StageContainer;
    @FXML
    private Button AppointsBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private AnchorPane PageContainer;
    @FXML
    private Button bookFreeBtn;
    @FXML
    private Label userLabel;
    @FXML
    private TableView<Appointments> patientTV;
    @FXML
    private TableColumn<Appointments, Integer> id;
    @FXML
    private TableColumn<Appointments, String> apptDate;
    @FXML
    private TableColumn<Appointments, String> apptDay;
    @FXML
    private TableColumn<Appointments, String> apptTime;
    @FXML
    private TableColumn<Appointments, String> status;
    @FXML
    private TableColumn<Appointments, String> comment;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText(current_User.getFirstname() + " " + current_User.getLastname());
        id.setCellValueFactory(new PropertyValueFactory("id"));
        apptDate.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        apptDay.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        apptTime.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
//        comment.setCellValueFactory(new PropertyValueFactory<>("doc_comment"));

    }
    
    @FXML
    private void LogoutAccount(ActionEvent event) throws IOException {
        System.out.println("Logging out");
        current_User = null;
        View.ViewManager.closePatientHomePage();
        View.ViewManager.openLoginPage();
    }
    
    @FXML
    private void showAllFreeAppointments() {
        patientTV.getItems().clear();
        patientTV.setItems(FXCollections.observableArrayList(Appointments.getAppointments()));
        
    }
    
    @FXML
    private void showAllBookedFinishedAppointment(ActionEvent event) throws IOException {
        BookedType = "finished";
        PatientBookedPageController.current_User = PatientPageController.current_User;
        View.ViewManager.patientHomePage.changeSceneToBookedAppts();
    }
    
    @FXML
    private void showAllBookedWaitingAppointment(ActionEvent event) throws IOException {
        BookedType = "waiting";
        PatientBookedPageController.current_User = PatientPageController.current_User;
        View.ViewManager.patientHomePage.changeSceneToBookedAppts();
    }
    
    @FXML
    private void GoToAppointTab(ActionEvent event) {
    }
    
    @FXML
    private void bookFreeAppoint(ActionEvent event) throws NonexistentEntityException, Exception {
        if (patientTV.getSelectionModel().getSelectedItem() != null) {
            Appointments freeAppoint = patientTV.getSelectionModel().getSelectedItem();
            freeAppoint.setStatus("booked");
            AppointmentsJpaController aptcontroller = new AppointmentsJpaController(PersistenceManager.getInstance().getEntityManagerFactory());
            
            aptcontroller.edit(freeAppoint);
            
            BookedAppointments booked = new BookedAppointments();
            booked.setUserId(current_User);
            booked.setAppointmentId(freeAppoint);
            booked.setStatus("waiting");
            PersistenceManager.getInstance().persistEntity(booked);
            showAllFreeAppointments();
        }
    }
    
}
