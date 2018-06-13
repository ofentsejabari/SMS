package entry;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author ofentse
 */
public class DialogUI extends JFXDialog{
    
    //-- Nitification types --
    public static int ERROR_NOTIF = 1, SUCCESS_NOTIF = 2, INFORMATION_NOTIF = 3;
    
    public DialogUI(String notif, int type, StackPane dialogContainer, JFXDialog jfxd){
        
        BorderPane container = new BorderPane();
        
        Label notification = new Label(notif);
        notification.setWrapText(true);
        
        HBox content = new HBox(20);
        content.setAlignment(Pos.CENTER_LEFT);
        content.getStyleClass().add("container");
        content.setStyle("-fx-padding:10");
                
        container.setCenter(content);
        container.getStyleClass().add("notification-dialog");
        
        switch(type){
            case 1:
                content.getChildren().addAll(SMS.getIcon("warning.png", 64), notification);
                //container.setLeft(new VBox(new HSpacer(), SMS.getIcon("warning.png", 64), new VSpacer()));
                setOverlayClose(false);
                break;
                
            case 2:
                //container.setLeft(new VBox(new HSpacer(), SMS.getIcon("success.png", 64), new VSpacer()));
                content.getChildren().addAll(SMS.getIcon("success.png", 64), notification);
                setOverlayClose(true);
                break;    
            
            case 3:
                //container.setLeft(new VBox(new HSpacer(), SMS.getIcon("information.png", 64), new VSpacer()));
                content.getChildren().addAll(SMS.getIcon("information.png", 64), notification);
                setOverlayClose(true);
                break;
        }
        
        //-- footer ------------------------------------------------------------
        
        JFXButton ok = new JFXButton("OK");
        ok.getStyleClass().add("dark-blue");
        ok.setOnAction((ActionEvent event) -> {
            close();
        });
        
        
        
        HBox footer = new HBox(ok);
        footer.setStyle("-fx-padding: 2");
        footer.setAlignment(Pos.CENTER);
        container.setBottom(footer);
        
        if(jfxd != null){
            jfxd.close();
        }
        
        setDialogContainer(dialogContainer);
        setContent(container);
        container.setPrefSize(400, 140);
        show();
        
    }
    
}
