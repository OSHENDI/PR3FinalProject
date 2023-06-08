
package Controller;


import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class Swal {
    
    public static void ShowErrorAlert(){
     Alert InsertionErrorAlert = new Alert(Alert.AlertType.ERROR);
            InsertionErrorAlert.setTitle("Data Insertion Failed!");
            InsertionErrorAlert.setContentText("Something is wrong with your inputs");
            InsertionErrorAlert.showAndWait();
    }
    
     public static void ShowSuccessAlert(String state){
     Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Data "+state+" Successfully!");
            successAlert.setContentText("this Account has been "+state+" Successfully");
            successAlert.showAndWait();
    }
     public static void showWarningAlert(String title,String contentText){
      Alert successAlert = new Alert(Alert.AlertType.WARNING);
            successAlert.setTitle(title);
            successAlert.setContentText(contentText);
            successAlert.showAndWait();
     
     }
     
    public static boolean showConfirmAlert(String innerMessage) {
    AtomicBoolean isOkClicked = new AtomicBoolean(false);
    
    Alert successAlert = new Alert(Alert.AlertType.CONFIRMATION);
    successAlert.setTitle("Are you Sure?");
    successAlert.setContentText(innerMessage);
    successAlert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.OK) {
            isOkClicked.set(true);
        } 
    });

    return isOkClicked.get();
}


     
     
}
