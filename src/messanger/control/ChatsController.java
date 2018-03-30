package messanger.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.tools.Borders;

/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class ChatsController implements Initializable {

    @FXML
    private ScrollPane chats;
    
    @FXML
    private JFXButton send, btn_add, btn_remove, btn_exit_group, btn_refresh;
    
    @FXML
    private JFXTextArea message;
    
    @FXML
    private Label name;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //-- Create some sample messages --
        
        VBox chatContainer = new VBox();
        
        for (int i = 0; i < 10; i++) {
             chatContainer.getChildren().add(formatMessage("This is a message kjhwbe wu we uwgweyg \nweggugwueg uwe"));
        }
        
        chatContainer.getChildren().add(formatPost("This is a message kjhwbe wu we uwgweyg \nweggugwueg uwe"));
        chatContainer.getChildren().add(formatPost("Another postbjsbsjb jsdjsjdbjsd"));
        
        btn_refresh.setText("");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "text-secondary", 18));
        
        btn_add.setText("");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_PLUS, "text-secondary", 18));
        
        btn_remove.setText("");
        btn_remove.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_MINUS, "text-secondary", 18));
        
        btn_exit_group.setText("");
        btn_exit_group.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_REMOVE, "text-secondary", 18));
        
        send.setText("");
        send.setGraphic(SMS.getGraphics(FontAwesomeIcon.SEND, "text-white", 18));
        send.setOnAction((ActionEvent event) -> {
            if(!message.getText().equalsIgnoreCase("")){
                chatContainer.getChildren().add(formatPost(message.getText()));
                message.setText("");
            }
        });
        
        chats.setContent(chatContainer);
    } 
    
    public void update(String txt){
        name.setText(txt);
    }
    
    public HBox formatMessage(String messg){
    
        String colors[] = {"#0AB6F5","#DC143C","#4B0082","#FF7F24","#66CD00","#EE4000"}; 
        Random rnd = new Random();
        
        Label lb = new Label(messg);
        lb.setWrapText(true);
        HBox message = new HBox(lb);
        
        HBox con = new HBox(Borders.wrap(message)
                .lineBorder()
                .thickness(1, 1, 1, 2)
                .innerPadding(10)
                .outerPadding(10, 10, 10, 20)
                .radius(5, 5, 5, 5)
                .color(Color.web("#EAEAEA"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web(colors[rnd.nextInt(colors.length-1)]))
                .buildAll());
        
        con.setAlignment(Pos.CENTER_LEFT);
        con.prefWidthProperty().bind(chats.widthProperty().subtract(20));
        con.setStyle("-fx-padding:0");
        return  con;
    }
    
    
    public HBox formatPost(String messg){
        
        Label lb = new Label(messg);
        lb.setWrapText(true);
        HBox message = new HBox(lb);
        
        HBox con = new HBox(Borders.wrap(message)
                .lineBorder()
                .thickness(1, 2, 1, 1)
                .innerPadding(10)
                .outerPadding(10, 20, 10, 10)
                .radius(5, 5, 5, 5)
                .color(Color.web("#EAEAEA"), Color.web("#FF7F24"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());
        
        con.setAlignment(Pos.CENTER_RIGHT);
        con.prefWidthProperty().bind(chats.widthProperty().subtract(20));
        con.setStyle("-fx-padding:0");
        return  con;
    }
    
}
