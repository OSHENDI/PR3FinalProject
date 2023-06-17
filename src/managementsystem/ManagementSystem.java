
package managementsystem;

import java.io.IOException;
import javafx.application.Application;

import javafx.stage.Stage;


public class ManagementSystem extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        View.ViewManager.openLoginPage();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
