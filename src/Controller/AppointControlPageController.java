/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Model.Appointments;
import Model.PersistenceManager;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AppointControlPageController implements Initializable {

    public static Appointments AppointmentData;
    @FXML
    private Button managementBtn;
    @FXML
    private Button AppointsBtn;
    @FXML
    private Button AccBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Button lightModeBtn;
    @FXML
    private Button darkModeBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label headerLabel;
    @FXML
    private Button AddAppointBtn;
    @FXML
    private TextField DateTF;
    @FXML
    private TextField timeTF;
    @FXML
    private TextField DayTF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (AppointmentData != null) {

            DayTF.setText(AppointmentData.getAppointmentDay());
            DateTF.setText(String.valueOf(AppointmentData.getAppointmentDate()));
            timeTF.setText(String.valueOf(AppointmentData.getAppointmentTime()));
        }

    }

    @FXML
    private void GoToManagementTab(ActionEvent event) {
    }

    @FXML
    private void GoToAppointTab(ActionEvent event) {
    }

    @FXML
    private void GoToAccountPage(ActionEvent event) {
    }

    @FXML
    private void LogoutAccount(ActionEvent event) throws IOException {
        View.ViewManager.closeHomePage();
        View.ViewManager.openLoginPage();
    }

    @FXML
    private void switchToLightMode(ActionEvent event) {
    }

    @FXML
    private void switchToDarkMode(ActionEvent event) {
    }

    @FXML
    private void AddAppointAction(ActionEvent event)
            throws ParseException, IOException, NonexistentEntityException, Exception {

        boolean isDone = false;
        if (AppointmentData == null) {

            Appointments appointment = new Appointments();

            appointment.setAppointmentDay(DayTF.getText());
            appointment.setAppointmentDate(DateTF.getText());
            appointment.setAppointmentTime(timeTF.getText());
            appointment.setStatus("free");
            isDone = PersistenceManager.getInstance().persistEntity(appointment);
            if (isDone == true) {
                Swal.ShowSuccessAlert("Created");
                clearInputs();
                View.ViewManager.homePage.changeSceneToAppointPage();

            } else {
                Swal.ShowErrorAlert();
            }
        } else {

            AppointmentData.setAppointmentDay(DayTF.getText());
            AppointmentData.setAppointmentDate(DateTF.getText());
            AppointmentData.setAppointmentTime(timeTF.getText());
            AppointmentData.setStatus("free");
            AppointmentsJpaController controller
                    = new AppointmentsJpaController(PersistenceManager.getInstance().getEntityManagerFactory());

            try {
                controller.edit(AppointmentData);
                isDone = true;
            } catch (Exception e) {
                isDone = false;
            }
            if (isDone == true) {
                Swal.ShowSuccessAlert("Updated");
            } else {
                Swal.ShowErrorAlert();
            }

        }
    }

    @FXML
    private void CancelAddAppoint(ActionEvent event) throws IOException {
        if (Swal.showConfirmAlert("You have unsaved changes that will be ignored,proceed?")) {
            clearInputs();
            View.ViewManager.homePage.changeSceneToAppointPage();
        }
    }

    public void clearInputs() {
        DateTF.setText("");
        DayTF.setText("");
        timeTF.setText("");

    }
}
