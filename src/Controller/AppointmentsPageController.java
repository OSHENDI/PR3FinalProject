/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AppointmentsPageController implements Initializable {

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
    private AnchorPane StageContainer;
    @FXML
    private AnchorPane PageContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("is Dark theme enabled in that scene?: " + Settings.getInstance().isDarkModeEnabled());
        System.out.println("From home controller: "+HomePageController.getThemeStatus());
        
        Settings.getInstance().setDarkModeEnabled(HomePageController.getThemeStatus());
        boolean isdark = Settings.getInstance().isDarkModeEnabled();
        
        System.out.println("is dark by method? "+isdark);
        if (isdark == true) {
            switchToDarkMode();
        }

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

}
