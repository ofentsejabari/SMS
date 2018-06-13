package entry;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author ofentse
 */
public class JFXAlert extends Alert{
    public static int INFORMATION = 0, SUCCESS = 1, ERROR = 2, WARNING = 3, CONFIRMATION = 4;
    
    
    public JFXAlert(int alertType, String title, String contentText) {
        super(AlertType.CONFIRMATION);
        
        switch(alertType){
            case 0:
                setAlertType(AlertType.INFORMATION);
                break;
            case 1:
                setAlertType(AlertType.INFORMATION);
                setGraphic(SMS.getIcon("success.png", 48));
                break;
            case 2:
                setAlertType(AlertType.ERROR);
                break;
            case 3:
                setAlertType(AlertType.WARNING);
                break;
            case 4:
                setAlertType(AlertType.CONFIRMATION);
                break;    
            
        }
        
        
        setHeaderText(null);
        setTitle(title);
        setContentText(contentText);
        
        
        DialogPane dialogPane = getDialogPane();
        dialogPane.getStyleClass().add("dialog-pane");
                    
        //-- Scene --
        Scene scene = dialogPane.getScene();
        scene.getStylesheets().add(SMS.class.getResource("css/style.css").toExternalForm());
                    
        //-- Get the Stage.
        Stage stage = (Stage) scene.getWindow();
                                        
        //-- Add a custom icon.
        stage.getIcons().add(new Image(SMS.class.getResourceAsStream("icons/1_studentsw.png")));

        showAndWait();
        
        
    }
    
}
