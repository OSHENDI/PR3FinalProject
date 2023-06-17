/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointments;
import Model.BookedAppointments;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PatientBookedPageController implements Initializable {

    public static Users current_User;
    @FXML
    private AnchorPane StageContainer;
    @FXML
    private Button AppointsBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Label userLabel;
    @FXML
    private AnchorPane PageContainer;
    @FXML
    private TableView<BookedAppointments> patientTV;
    @FXML
    private TableColumn<BookedAppointments, Integer> id;
    @FXML
    private TableColumn<BookedAppointments, String> apptID;
    @FXML
    private TableColumn<BookedAppointments, String> userID;
    @FXML
    private TableColumn<BookedAppointments, String> status;
    @FXML
    private TableColumn<BookedAppointments, String> comment;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        id.setCellValueFactory(new PropertyValueFactory("id"));
        apptID.setCellValueFactory(new PropertyValueFactory("appointmentId"));
        userID.setCellValueFactory(new PropertyValueFactory("userId"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        comment.setCellValueFactory(new PropertyValueFactory("doctorComment"));
//        apptID.setCellFactory(column -> new TableCell<BookedAppointments, Appointments>() {
//            @Override
//            protected void updateItem(Appointments item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty || item == null ? "" : String.valueOf(item.getId()));
//            }
//        });
//
//        userID.setCellFactory(column -> new TableCell<BookedAppointments, Users>() {
//            @Override
//            protected void updateItem(Users item, boolean empty) {
//                super.updateItem(item, empty);
//                setText(empty || item == null ? "" : String.valueOf(item.getId()));
//            }
//        });

        patientTV.getItems().clear();
        String BookedType = PatientPageController.BookedType;
        List<BookedAppointments> PatientbookedtypeApts
                = BookedAppointments.getPatientBookedTypeAppointments(current_User, BookedType);

        patientTV.setItems(FXCollections.observableArrayList(PatientbookedtypeApts));
    }

    @FXML
    private void GoToAppointTab(ActionEvent event) {
    }

    @FXML
    private void LogoutAccount(ActionEvent event) throws IOException {
        HomePageController.current_user = null;
        View.ViewManager.closePatientHomePage();
        View.ViewManager.openLoginPage();

    }

    @FXML
    private void goBackToPatientPage(ActionEvent event) {
        View.ViewManager.patientHomePage.changeSceneToPatientScreen();
        View.ViewManager.patientHomePage.PatientBookedScene = null;
    }

}
