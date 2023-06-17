/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointments;
import Model.BookedAppointments;
import Model.PersistenceManager;
import Model.Users;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class BookedAppointsPageController implements Initializable {
    
    @FXML
    private AnchorPane StageContainer;
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
    private AnchorPane PageContainer;
    @FXML
    private TableColumn<BookedAppointments, Integer> id;
    @FXML
    private TableColumn<BookedAppointments, String> appt_id;
    @FXML
    private TableColumn<BookedAppointments, String> pat_id;
    @FXML
    private TableColumn<BookedAppointments, String> status;
    @FXML
    private TableColumn<BookedAppointments, String> doc_comment;
    @FXML
    private TableView<BookedAppointments> bookedApptsTV;
    @FXML
    private TextField commentTF;
    @FXML
    private TextField searchNameTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id.setCellValueFactory(new PropertyValueFactory("id"));
        appt_id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        pat_id.setCellValueFactory(new PropertyValueFactory("userId"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        doc_comment.setCellValueFactory(new PropertyValueFactory("doctorComment"));
        
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
    }
    
    @FXML
    private void LogoutAccount(ActionEvent event) throws IOException {
        View.ViewManager.closeHomePage();
        View.ViewManager.openLoginPage();
        
    }
    
    @FXML
    private void refreshTable(ActionEvent event) {
        bookedApptsTV.getItems().clear();
        List< BookedAppointments> appts = BookedAppointments.getBookedAppointments();
        bookedApptsTV.setItems(FXCollections.observableArrayList(appts));
        
    }
    
    @FXML
    private void switchToLightMode(ActionEvent event) {
    }
    
    @FXML
    private void switchToDarkMode(ActionEvent event) {
    }
    
    @FXML
    private void searchAppointByFirstName(ActionEvent event) {
        if (!searchNameTextField.getText().isEmpty()) {
            List<BookedAppointments> bookedAppointmentsByFirstName = BookedAppointments.getBookedAppointmentsByFirstName(searchNameTextField.getText());
            bookedApptsTV.getItems().clear();
            bookedApptsTV.setItems(FXCollections.observableArrayList(bookedAppointmentsByFirstName));
        }
        
    }
    
    @FXML
    private void GoBackToAppointsPage(ActionEvent event) throws IOException {
        View.ViewManager.homePage.changeSceneToAppointPage();
    }
    
    @FXML
    private void sendComment(ActionEvent event) throws Exception {
        boolean isEdited = false;
        if (bookedApptsTV.getSelectionModel().getSelectedItem() != null) {
            if (!commentTF.getText().isEmpty()) {
                BookedAppointments Bappointment = bookedApptsTV.getSelectionModel().getSelectedItem();
                Bappointment.setStatus("finished");
                Bappointment.setDoctorComment(commentTF.getText());
                BookedAppointmentsJpaController controller = new BookedAppointmentsJpaController(PersistenceManager.getInstance().getEntityManagerFactory());
                try {
                    
                    controller.edit(Bappointment);
                    isEdited = true;
                } catch (Exception e) {
                    isEdited = false;
                }
                
                if (isEdited) {
                    Swal.ShowSuccessAlert("Modified");
                } else {
                    Swal.ShowErrorAlert();
                }
            }
        } else {
            Swal.showWarningAlert("No selected Record", "Select A record then comment dumahh");
        }
        
    }
    
}
