package View;

import java.io.IOException;

public class ViewManager {

    public static userLoginPage userLoginPage;
    public static HomePage homePage;
    public static PatientHomePage patientHomePage;
    

    private ViewManager() {
    }
//    

    public static void openPatientHomePage() throws IOException {
        if (patientHomePage == null) {
            patientHomePage = new PatientHomePage();
        }
        patientHomePage.show();
    }

    public static void closePatientHomePage() throws IOException {
        if (patientHomePage != null) {
            patientHomePage.close();
        }

    }

    public static void openLoginPage() throws IOException {
        if (userLoginPage == null) {
            userLoginPage = new userLoginPage();
        }
        userLoginPage.show();
    }

    public static void closeLoginPage() {
        if (userLoginPage != null) {
            userLoginPage.close();
        }
    }

    public static void openHomePage() throws IOException {
        if (homePage == null) {
            homePage = new HomePage();
        }
        homePage.show();

    }

    public static void closeHomePage() {
        if (homePage != null) {
            homePage.close();
        }
    }

}
