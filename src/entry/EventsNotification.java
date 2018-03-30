package entry;

import com.jfoenix.controls.JFXPopup;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.controlsfx.tools.Borders;

/**
 *
 * @author JABARI
 */
public class EventsNotification extends JFXPopup{

    public EventsNotification(Node owner) {
        
        VBox container = new VBox();
        container.setSpacing(5);
        
        setPopupContent(container);
        setAutoHide(true);
        show(owner, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.LEFT);
        
        Label title = new Label("Upcoming Events");
        title.getStyleClass().add("title-label");
        
        HBox hdr = new HBox(SMS.getGraphics(MaterialDesignIcon.BELL_RING, "text-blue", 32), title);
        hdr.setSpacing(20);
        hdr.setAlignment(Pos.CENTER_LEFT);
        hdr.setPadding(new Insets(5));
        
        container.getChildren().addAll(hdr);
        
        String []colors = {"#FFD700", "#32CD32", "#66CDAA", "#40E0D0", "#FF00FF", "#FF6347"};
        
        for(int i=0;i<=5;i++){
            //-- get a random color --
            
            Label ms = new Label("This is an event to be dispalyed ....");
            ms.setWrapText(true);

            Text icon = SMS.getGraphics(MaterialDesignIcon.INFORMATION, "text-success", 24);

            Label name = new Label("O.Jabari");
            name.getStyleClass().add("event-owner");

            HBox contn = new HBox(icon, ms, new HSpacer(), name);
            contn.setAlignment(Pos.CENTER);
            contn.setSpacing(10);
            contn.setPrefWidth(340);

            //-- Set style --
            contn.getStyleClass().add("notif-container");
            ms.getStyleClass().add("notif-label");

            Node node = Borders.wrap(contn)
                    .lineBorder().innerPadding(0)
                    .thickness(1, 1, 1, 2)
                    .radius(5).outerPadding(2, 10, 2, 10)
                    .color(Color.web("#EAEAEA"), Color.web("#EAEAEA"),
                           Color.web("#EAEAEA"), Color.web(colors[new Random().nextInt(colors.length)]))
                    .buildAll();
            HBox.setHgrow(node, Priority.ALWAYS);
            

            container.getChildren().add(node);
            
        }
        
        
    }
       
}
