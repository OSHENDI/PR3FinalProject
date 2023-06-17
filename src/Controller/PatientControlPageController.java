/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Model.PersistenceManager;
import Model.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

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

    public static Users userData;

    public ToggleGroup toggleGroup;
    @FXML
    private RadioButton malerb;
    @FXML
    private RadioButton femalerb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        toggleGroup = new ToggleGroup();
        malerb.setToggleGroup(toggleGroup);
        femalerb.setToggleGroup(toggleGroup);

        if (userData != null) {

            firstNameTF.setText(userData.getFirstname());
            lastNameTF.setText(userData.getLastname());
            emailTF.setText(userData.getEmail());
            ageTF.setText(userData.getAge() + "");
            phoneTF.setText(userData.getPhone());
            if (userData.getGender().equals("male")) {
                malerb.setSelected(true);
            } else {
                femalerb.setSelected(true);
            }

        } else {
            clearInputs();

        }

    }

    @FXML
    private void GoToManagementTab(ActionEvent event) {
        View.ViewManager.homePage.changeSceneToHomePage();

    }

    @FXML
    private void GoToAppointTab(ActionEvent event) throws IOException {
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
    private void AddPatientAction() throws NonexistentEntityException, Exception {
        boolean accepted = false;
        if (userData == null) {
            Users user = new Users();
            user.setFirstname(firstNameTF.getText());
            user.setLastname(lastNameTF.getText());
            user.setAge(Integer.parseInt(ageTF.getText()));
            user.setEmail(emailTF.getText());
            user.setPhone(phoneTF.getText());

            user.setGender(malerb.isSelected() ? "male" : "female"); // if male radio btn selected => male ... else female
            user.setRole("patient");
            accepted = PersistenceManager.getInstance().persistEntity(user);
            if (accepted) {
                Swal.ShowSuccessAlert("created");
                clearInputs();
            } else {
                Swal.ShowErrorAlert();
            }

        } else {

            UsersJpaController controller = new UsersJpaController(PersistenceManager.
                    getInstance().getEntityManagerFactory());

            userData.setFirstname(firstNameTF.getText());
            userData.setLastname(lastNameTF.getText());
            userData.setAge(Integer.parseInt(ageTF.getText()));
            userData.setEmail(emailTF.getText());
            userData.setPhone(phoneTF.getText());
            userData.setGender(malerb.isSelected() ? "male" : "female");

            try {
                controller.edit(userData);
                accepted = true;
            } catch (Exception e) {
                accepted = false;
                e.printStackTrace();
            }

            if (accepted) {
                Swal.ShowSuccessAlert("Updated");
                View.ViewManager.homePage.changeSceneToHomePage();
            } else {
                Swal.ShowErrorAlert();
            }
            userData = null;
        }

    }

    public void clearInputs() {
        firstNameTF.setText("");
        lastNameTF.setText("");
        phoneTF.setText("");
        ageTF.setText("");
        emailTF.setText("");
    }

}
