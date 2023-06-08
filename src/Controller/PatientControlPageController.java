/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.PersistenceManager;
import Model.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PatientControlPageController implements Initializable {

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
    private Button AddUserPatientBtn;
    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private static Label headerLabel;

     private Users userData;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

   public static void editHeader(String str){
   headerLabel.setText(str);
   }
    @FXML
    private void GoToManagementTab(ActionEvent event) {
        View.ViewManager.homePage.changeSceneToHomePage();

    }

    @FXML
    private void GoToAppointTab(ActionEvent event) {
        View.ViewManager.homePage.changeSceneToAppointPage();
    }

    @FXML
    private void GoToAccountPage(ActionEvent event) {
        View.ViewManager.homePage.changeSceneToAccountPage();
    }

    @FXML
    private void LogoutAccount(ActionEvent event) {
    }

    @FXML
    private void switchToLightMode(ActionEvent event) {
    }

    @FXML
    private void switchToDarkMode(ActionEvent event) {
    }

    @FXML
    private void CancelAddPatient() {
        if (Swal.showConfirmAlert("You have unsaved changes that will be reverted,proceed?")) {
            clearInputs();
        View.ViewManager.homePage.changeSceneToHomePage();
        }
        

    }

    @FXML
    private void AddPatientAction(ActionEvent event) {
        Users user = new Users();
        user.setFirstname(firstNameTF.getText());
        user.setLastname(lastNameTF.getText());
        user.setAge(Integer.parseInt(ageTF.getText()));
        user.setEmail(emailTF.getText());
        user.setPhone(phoneTF.getText());

        user.setGender("male"); //Changing  it Later
        user.setRole("patient");

        boolean accepted = PersistenceManager.getInstance().persistEntity(user);
        if (accepted) {
            Swal.ShowSuccessAlert("created");
            clearInputs();
        } else {
            Swal.ShowErrorAlert();
        }
    }

    public void clearInputs() {
        firstNameTF.setText("");
        lastNameTF.setText("");
        phoneTF.setText("");
        ageTF.setText("");
        emailTF.setText("");
    }

    void setUserData(Users usertoEdit) {
       this.userData = usertoEdit;
    }

}
