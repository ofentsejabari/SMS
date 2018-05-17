package entry.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ofentse
 */
public class LoginController implements Initializable {
    
    
    @FXML
    private StackPane progressView;
    @FXML
    private Button btn_close;
    @FXML
    private BorderPane menuView;
    @FXML
    private JFXButton btn_admin;
    @FXML
    private BorderPane loginView;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXCheckBox ck_remember;
    @FXML
    private JFXButton btn_cancel;
    @FXML
    private JFXButton btn_login;
    @FXML
    private Button btn_menu;
        
    UserLoginService uls = null;
    private Stage mainWindow;
    
    
    //MainUIFXMLController MAIN_CONTROLLER;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuView.toFront();
        uls = new UserLoginService();
        uls.start();
        progressView.visibleProperty().bind(uls.runningProperty());
        
        try {
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/entry/view/MainUIFXML.fxml"));
//            Parent root = loader.load();
            //MAIN_CONTROLLER = loader.getController();
            
//            Scene scene = new Scene(root, Color.TRANSPARENT);
//            scene.getStylesheets().add(SMS.class.getResource("css/style.css").toExternalForm());
//            mainWindow = new Stage();
//            mainWindow.setScene(scene);
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

    }
    
    public class UserLoginTask extends Task<Void> {       
        @Override 
        protected Void call() throws Exception{
           
            try{
                //-- Sleep for a few seconds
                // Thread.sleep(400);
                Platform.runLater(() -> {
                    mainWindow.show();
                    SMS.parentUI.hide();
                });
                
                
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
            return null;
        }
    }

    public class UserLoginService extends Service<Void> {
        @Override
        protected Task createTask() {
            return new UserLoginTask();
        }
    }
    
}
