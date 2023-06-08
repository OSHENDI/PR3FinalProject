package Controller;

import Model.PersistenceManager;
import Model.Users;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoginpageController implements Initializable {

    @FXML
    private Button LoginTab;
    @FXML
    private Button registerTab;
    @FXML
    private Button loginBtn;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField UsernameorEmailTF;
    @FXML
    private Button logAsAdminBtn;

    static List<Users> ADMINSLIST;
    static List<Users> USERSLIST;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ADMINSLIST = Users.getAdminsList();
        USERSLIST = Users.getUsersList();
    }

    @FXML
    private void goToLoginTab(ActionEvent event) {
        View.ViewManager.userLoginPage.ChangeSceneToLoginPage();
    }

    @FXML
    private void goToRegTab(ActionEvent event) {
        View.ViewManager.userLoginPage.changeSceneToRegisterPage();
    }

    @FXML
    private void triggerLoginAction(ActionEvent event) throws IOException { //Patient Login action

        
//        for (Users users : USERSLIST) {
//            System.out.println(users.getEmail()+" - "+users.getPassword());
//        }
        if (validateLoginDetails(USERSLIST) == true) {
            UsernameorEmailTF.clear();
            passwordTF.clear();
            System.out.println("User is granted access to go to his page!");
        } else {
            System.out.println("Denied User cuz wrong or empty");
        }

//        View.ViewManager.closeLoginPage();
//        View.ViewManager.openPatientHomePage();
    }

    @FXML
    private void LogAsAdminAction(ActionEvent event) throws IOException {

        if (validateLoginDetails(ADMINSLIST) == true) {
            UsernameorEmailTF.clear();
            passwordTF.clear();
            View.ViewManager.closeLoginPage();
            View.ViewManager.openHomePage();

        } else {
            Swal.ShowErrorAlert();

        }

    }

    private boolean validateLoginDetails(List<Users> ALL_USERS_LIST) {
        String username = UsernameorEmailTF.getText().trim();
        String pass = passwordTF.getText().trim();

        if (ALL_USERS_LIST
                .stream()
                .anyMatch
        (users -> ((username.equals(users.getUsername()) || username.equals(users.getEmail()) )
                && pass.equals(users.getPassword())))) {
            return true;
        }
        
        return false;
    }

}
