package messanger;

import com.jfoenix.controls.JFXListView;
import entry.SMS;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import messanger.control.ChatsController;
import messanger.control.ListItemController;

/**
 *
 * @author ofentse
 */
public class MessangerManagement extends BorderPane{
    
    private JFXListView<AnchorPane> mainMenu;
    public static StackPane MESSANGER_MAN_STACK;
    ChatsController chatsController = null;
            
    public MessangerManagement() {
        
        getStyleClass().add("container");
        
        //-- Student Management Menu --
        mainMenu = new JFXListView<>();
        mainMenu.getStyleClass().add("contact_list");
        
        MESSANGER_MAN_STACK = new StackPane();
    
        try {
            for (int i = 0; i < 10; i++) {
                
                //-- 
                FXMLLoader listItemloader = new FXMLLoader(SMS.class.getResource("/messanger/view/listItem.fxml"));
                AnchorPane contact = listItemloader.load();
                mainMenu.getItems().add(contact);
                
                ListItemController  listItemController = listItemloader.getController();
                listItemController.setText("Contact Name--" + i);
                
                //-- 
                FXMLLoader chatLoader = new FXMLLoader(SMS.class.getResource("/messanger/view/chat.fxml"));
                AnchorPane chat = chatLoader.load();
                MESSANGER_MAN_STACK.getChildren().add(chat);
                chatsController = chatLoader.getController();
                chatsController.update("Contact Name--"+ i);

                
                contact.setOnMouseClicked((MouseEvent event) -> {
                    chat.toFront();
                });
                
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        BorderPane bp = new BorderPane(new Label("Welcome to chat"));
        bp.setStyle("-fx-background-color:gray");
        
        MESSANGER_MAN_STACK.getChildren().add(bp);
        
        setLeft(mainMenu);
                
        setCenter(MESSANGER_MAN_STACK);
        
    }
    
}
