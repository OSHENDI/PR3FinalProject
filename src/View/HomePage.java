package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePage extends Stage {

    private Scene homepageScene;
    private Scene AppointmentsScene;
    private Scene AccountScene;
    public static Scene PatientControlPage;
    Scene bookedAppointsScene;
   public static Scene AppointControlPage;

    public HomePage() throws IOException {

//         FXMLLoader appointloader = new FXMLLoader(getClass().getResource("/View/userFXML/AppointmentsPage.fxml"));
//        Parent appointparent = appointloader.load();
//        AppointmentsScene = new Scene(appointparent);
//        this.setScene(AppointmentsScene);
//        this.setTitle("Appointments Page");
//        setResizable(false);
//         FXMLLoader accountpLoader = new FXMLLoader(getClass().getResource("/View/userFXML/AccountPage.fxml"));
//        Parent accountparent = accountpLoader.load();
//        AccountScene = new Scene(accountparent);
//        this.setScene(AccountScene);
//        this.setTitle("Account Info Page");
//        setResizable(false);
        FXMLLoader PatientpLoader = new FXMLLoader(getClass().getResource("/View/userFXML/PatientControlPage.fxml"));
        Parent Patientparent = PatientpLoader.load();
        PatientControlPage = new Scene(Patientparent);
        this.setScene(PatientControlPage);
        this.setTitle("Patient Control Page");
        setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/userFXML/HomePage.fxml"));
        Parent parent = loader.load();
        homepageScene = new Scene(parent);
        this.setScene(homepageScene);
        this.setTitle("Home Page");
        setResizable(false);
    }

    public void changeSceneToAppointPage() throws IOException {
        if (AppointmentsScene == null) {
            FXMLLoader appointloader = new FXMLLoader(getClass().getResource("/View/userFXML/AppointmentsPage.fxml"));
            Parent appointparent = appointloader.load();
            AppointmentsScene = new Scene(appointparent);
            this.setTitle("Appointments Page");
            setResizable(false);
            this.setScene(AppointmentsScene);
        } else {
            this.setScene(AppointmentsScene);
        }
    }

    public void changeSceneToBookedAppointsPage() throws IOException {
        if (bookedAppointsScene == null) {
            FXMLLoader appointloader = new FXMLLoader(getClass().getResource("/View/userFXML/BookedAppointsPage.fxml"));
            Parent appointparent = appointloader.load();
            bookedAppointsScene = new Scene(appointparent);
            this.setTitle("booked Appointments Page");
            setResizable(false);
        }

        this.setScene(bookedAppointsScene);
    }

    public void changeSceneToHomePage() {
        this.setScene(homepageScene);
    }

    public void changeSceneToPatientControlPage() throws IOException {
        if (PatientControlPage == null) {
            FXMLLoader PatientpLoader = new FXMLLoader(getClass().getResource("/View/userFXML/PatientControlPage.fxml"));
            Parent Patientparent = PatientpLoader.load();
            PatientControlPage = new Scene(Patientparent);
            this.setTitle("Patient Control Page");
            setResizable(false);
        }
        this.setScene(PatientControlPage);
    }

    public void changeScenetoAppointControlPage() throws IOException {
        if (AppointControlPage == null) {
            FXMLLoader PatientpLoader = new FXMLLoader(getClass().getResource("/View/userFXML/AppointControlPage.fxml"));
            Parent apptParent = PatientpLoader.load();
            AppointControlPage = new Scene(apptParent);
            this.setTitle("Appointment Control Page");
            setResizable(false);
        }
        this.setScene(AppointControlPage);

    }

    public void changeSceneToAccountPage() {
// this.setScene(AccountScene);
    }

}
