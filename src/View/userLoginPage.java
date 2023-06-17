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
public class userLoginPage extends Stage {

    private Scene userLoginPage;
    private Scene userRegScene;

    public userLoginPage() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/userFXML/loginpage.fxml"));
        Parent parent = loader.load();
        userLoginPage = new Scene(parent);
        this.setScene(userLoginPage);
        this.setTitle("Patient Login Page");
        setResizable(false);
        
          FXMLLoader userRegloader = new FXMLLoader(getClass().getResource("/View/userFXML/registerpage.fxml"));
        Parent usersceneloader = userRegloader.load();
        userRegScene = new Scene(usersceneloader);
        this.setTitle("Patient Register Page");
        setResizable(false);

    }
    
   public void changeSceneToRegisterPage(){
    this.setScene(userRegScene);
    }
    
    public void ChangeSceneToLoginPage(){
    this.setScene(userLoginPage);
    }

}
