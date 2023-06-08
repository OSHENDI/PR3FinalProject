package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Model.PersistenceManager;
import Model.Users;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class HomePageController implements Initializable {

    @FXML
    private Button AppointsBtn;
    @FXML
    private Button managementBtn;
    @FXML
    private Button AccBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private Button lightModeBtn;
    @FXML
    private Button darkModeBtn;
    @FXML
    private Button AddPatientBtn;
    @FXML
    private TableColumn<Users, Integer> idCol;
    @FXML
    private TableColumn<Users, String> firstNameCol;
    @FXML
    private TableColumn<Users, String> LastNameCol;
    @FXML
    private TableColumn<Users, String> AgeCol;
    @FXML
    private TableColumn<Users, String> genderCol;
    @FXML
    private TableColumn<Users, String> phonenoCol;
    @FXML
    private TableColumn<Users, String> emailCol;
    @FXML
    private Button showPatientsBtn;
    @FXML
    private TableView<Users> userTableView;
    @FXML
    private Button DeleteUserBtn;
    @FXML
    private TextField searchPrompt;
    @FXML
    private Button searchBtn;
    @FXML
    private Button updateUserBtn;
    @FXML
    private AnchorPane PageContainer;
    @FXML
    private AnchorPane StageContainer;
    
     public static boolean isDarkEnabled;
  
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        LastNameCol.setCellValueFactory(new PropertyValueFactory("lastname"));
        AgeCol.setCellValueFactory(new PropertyValueFactory("age"));
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        phonenoCol.setCellValueFactory(new PropertyValueFactory("phone"));

    }

    @FXML
    private void GoToAppointTab(ActionEvent event) {
        View.ViewManager.homePage.changeSceneToAppointPage();
    }

    @FXML
    private void GoToManagementTab(ActionEvent event) {
//        View.ViewManager.homePage.changeSceneToHomePage();
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
    private void switchToLightMode(ActionEvent event) {
        isDarkEnabled = false;
        Settings.getInstance().setDarkModeEnabled(isDarkEnabled);
        lightModeBtn.getStyleClass().add("activetheme");
        darkModeBtn.getStyleClass().remove("activetheme");
        PageContainer.getStyleClass().remove("darkmode");
        StageContainer.getStyleClass().remove("dark-bg-theme");
    }

    @FXML
    private void switchToDarkMode(ActionEvent event) {
        isDarkEnabled = true;
        Settings.getInstance().setDarkModeEnabled(isDarkEnabled);
        
        PageContainer.getStyleClass().add("darkmode");
        lightModeBtn.getStyleClass().remove("activetheme");
        darkModeBtn.getStyleClass().add("activetheme");
        StageContainer.getStyleClass().add("dark-bg-theme");
    }

    @FXML
    private void GoToAddPatient() {
        View.ViewManager.homePage.changeSceneToPatientControlPage();

    }

    @FXML
    private void ShowAllPatients(ActionEvent event) {
        List<Users> usersList = Users.getUsersList();
        userTableView.setItems(FXCollections.observableArrayList(usersList));

    }

    @FXML
    private void DelUserAction(ActionEvent event) throws IllegalOrphanException, NonexistentEntityException {
        if (userTableView.getSelectionModel().getSelectedItem() != null) {
            if (Swal.showConfirmAlert("You are about to delete this account, proceed?")) {
                UsersJpaController usercontroller
                        = new UsersJpaController(PersistenceManager.getInstance().getEntityManagerFactory());
                usercontroller.destroy(userTableView.getSelectionModel().getSelectedItem().getId());
                Swal.ShowSuccessAlert("Deleted");
                userTableView.getItems().remove(userTableView.getSelectionModel().getSelectedItem());
            }

        }
    }

    @FXML
    private void SearchAction(ActionEvent event) {

        if (!searchPrompt.getText().equals("") && searchPrompt.getText() != null) {
            EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();
            try {
                TypedQuery<Users> query = entityManager.createNamedQuery("Users.findByFirstname", Users.class);
                query.setParameter("firstname", searchPrompt.getText());
                List<Users> results = query.getResultList();
                userTableView.getItems().clear();
                userTableView.setItems(FXCollections.observableArrayList(results));

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                entityManager.close();
            }
        } else {
            Swal.showWarningAlert("Search field is Empty!","you have to enter a search term first");
        }
    }

    @FXML
    private void GoToUpdateScene(ActionEvent event) throws IOException {
           Users usertoEdit = userTableView.getSelectionModel().getSelectedItem();
        if(usertoEdit!= null){
//          GoToAddPatient();
        }
    }
    
  static public boolean getThemeStatus(){
   return  isDarkEnabled;
   }

}
