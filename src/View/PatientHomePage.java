/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class PatientHomePage extends Stage {

    public static Scene patientScene;
    public static Scene PatientBookedScene;

    public PatientHomePage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/userFXML/Patientpage.fxml"));
        Parent parent = loader.load();
        patientScene = new Scene(parent);
        this.setScene(patientScene);
        this.setTitle("Patient Home Page");
        setResizable(false);
    }

    public void changeSceneToBookedAppts() throws IOException {
        if (PatientBookedScene == null) {
            FXMLLoader loadPatBooked = new FXMLLoader(getClass().getResource("/View/userFXML/PatientBookedPage.fxml"));
            Parent patParent = loadPatBooked.load();
            PatientBookedScene = new Scene(patParent);
            this.setTitle("Patient Booked Appointments");
            setResizable(false);
            this.setScene(PatientBookedScene);
        } else {
            this.setScene(PatientBookedScene);
        }
    }

    public void changeSceneToPatientScreen() {
        this.setScene(patientScene);
    }
}
