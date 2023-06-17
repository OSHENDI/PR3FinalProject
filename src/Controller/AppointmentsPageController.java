/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Model.Appointments;
import Model.PersistenceManager;
import java.io.IOException;
import java.net.URL;

import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AppointmentsPageController implements Initializable {

    @FXML
    private Button AppointsBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Button lightModeBtn;
    @FXML
    private Button darkModeBtn;
    @FXML
    private AnchorPane StageContainer;
    @FXML
    private AnchorPane PageContainer;
    @FXML
    private Button managementBtn;
    @FXML
    private Button AccBtn;
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
    private TableView<Appointments> AppointsTV;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Settings.getInstance().setDarkModeEnabled(HomePageController.getThemeStatus());
        boolean isdark = Settings.getInstance().isDarkModeEnabled();
        if (isdark == true) {
            switchToDarkMode();
        }
        id.setCellValueFactory(new PropertyValueFactory("id"));
        apptDate.setCellValueFactory(new PropertyValueFactory("appointmentDate"));
        apptDay.setCellValueFactory(new PropertyValueFactory("appointmentDay"));
        apptTime.setCellValueFactory(new PropertyValueFactory("appointmentTime"));
        status.setCellValueFactory(new PropertyValueFactory("status"));

    }

    @FXML
    private void GoToManagementTab(ActionEvent event) {
        View.ViewManager.homePage.changeSceneToHomePage();
    }

    @FXML
    private void GoToAppointTab(ActionEvent event) {
    }

    @FXML
    private void GoToAccountPage(ActionEvent event) {
        View.ViewManager.homePage.changeSceneToAccountPage();
    }

    @FXML
    private void LogoutAccount(ActionEvent event) throws IOException {
        View.ViewManager.closeHomePage();
        View.ViewManager.openLoginPage();
    }

    @FXML
    private void switchToLightMode() {
        lightModeBtn.getStyleClass().add("activetheme");
        darkModeBtn.getStyleClass().remove("activetheme");
        PageContainer.getStyleClass().remove("darkmode");
        StageContainer.getStyleClass().remove("dark-bg-theme");
    }

    @FXML
    private void switchToDarkMode() {
        lightModeBtn.getStyleClass().remove("activetheme");
        PageContainer.getStyleClass().add("darkmode");
        darkModeBtn.getStyleClass().add("activetheme");
        StageContainer.getStyleClass().add("dark-bg-theme");
    }


    @FXML
    private void goToBookedAppoints(ActionEvent event) throws IOException {
        View.ViewManager.homePage.changeSceneToBookedAppointsPage();
    }

    private void GoBackToAppointsPage(ActionEvent event) throws IOException {
        View.ViewManager.homePage.changeSceneToAppointPage();
    }

    @FXML
    private void createNewAppointmentAction(ActionEvent event) throws IOException {
        View.ViewManager.homePage.changeScenetoAppointControlPage();

    }

    @FXML
    private void showFreeAppoints(ActionEvent event) {
        AppointsTV.getItems().clear();
        AppointsTV.setItems(FXCollections.observableArrayList(Appointments.getAppointments()));
    }

    @FXML
    private void updateAppoint(ActionEvent event) throws IOException {
        if (AppointsTV.getSelectionModel().getSelectedItem() != null) {
            AppointControlPageController.AppointmentData = AppointsTV.getSelectionModel().getSelectedItem();
            View.ViewManager.homePage.changeScenetoAppointControlPage();
              View.HomePage.AppointControlPage = null;
        } else {
            
            Swal.showWarningAlert("no record Selected", "select a record to continue!");
        }
    }

    @FXML
    private void deleteAppoint(ActionEvent event) throws IllegalOrphanException, NonexistentEntityException {
        
        if(AppointsTV.getSelectionModel().getSelectedItem() != null){
            Integer selectedItemtoDelete = AppointsTV.getSelectionModel().getSelectedItem().getId();
            AppointmentsJpaController controller = new AppointmentsJpaController(PersistenceManager.getInstance().getEntityManagerFactory());
            
            controller.destroy(selectedItemtoDelete);
            Swal.ShowSuccessAlert("Deleted");
        }
        
    }

}
