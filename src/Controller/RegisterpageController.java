
package Controller;

import Model.PersistenceManager;
import Model.Users;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


/**
 * FXML Controller class
 *
 * @author hp
 */
public class RegisterpageController implements Initializable {

    @FXML
    private Button LoginTab;
    @FXML
    private Button registerTab;
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton maleRB;
    @FXML
    private RadioButton femaleRB;
    @FXML
    private Button registerPatientBtn;
    @FXML
    private TextField patientFname;
    @FXML
    private TextField patientLname;
    @FXML
    private TextField patientEmail;
    @FXML
    private TextField patientPhone;
    @FXML
    private TextField patientAge;
    ArrayList<String> patientInfo;
    @FXML
    private TextField PatientPassword;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       toggleGroup = new ToggleGroup();
       maleRB.setToggleGroup(toggleGroup);
       femaleRB.setToggleGroup(toggleGroup);
       maleRB.setSelected(true);
       
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
    private void RegisterPatientAction(ActionEvent event) {
        fillInputs();
      if(validateForm() == true){
          Users user = new Users();
          user.setFirstname(patientInfo.get(0));
          user.setLastname(patientInfo.get(1));
          user.setAge(Integer.parseInt(patientInfo.get(2)));
          user.setEmail(patientInfo.get(3));
          user.setPhone(patientInfo.get(4));
          user.setGender(patientInfo.get(5));
          user.setRole(patientInfo.get(6));
          user.setPassword(patientInfo.get(7));
          
          boolean isadded =  PersistenceManager.getInstance().persistEntity(user);
          if(isadded){
          Swal.ShowSuccessAlert("Added Successfully!");
          patientInfo.clear();
          
          }else{
          Swal.ShowErrorAlert();
          patientInfo.clear();
          }
      }else{
          patientInfo.clear();
       Swal.showWarningAlert("some inputs are empty", "Please fill in the required Fields!");
      }
        
    }
    
    private void fillInputs(){
     patientInfo = new ArrayList(); 
        patientInfo.add(patientFname.getText().trim());// firstname , lasstname , age, email,phone,gender,role
        patientInfo.add(patientLname.getText().trim());
        patientInfo.add(patientAge.getText());
        patientInfo.add(patientEmail.getText().trim());
        patientInfo.add(patientPhone.getText().trim());
        String gender = maleRB.isSelected()?maleRB.getText():femaleRB.getText();
        patientInfo.add(gender);
        patientInfo.add("patient");
        patientInfo.add(PatientPassword.getText());
    }
   private boolean validateForm(){
        return patientInfo.stream().noneMatch(string -> (string.isEmpty()));
    }
    
}
