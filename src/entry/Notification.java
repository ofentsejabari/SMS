package entry;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.event.ActionEvent;
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
 * @author ofentse
 */
public class Notification extends HBox{
    
    public static int SUCCESS_TYPE = 1, WARNING_TYPE = 2, INFORMATION_TYPE = 3; 
    public static JFXButton close;

    public Notification(String message, int type, VBox parent) {
        
        
        Label ms = new Label(message);
        ms.setWrapText(true);
        
        Text icon = SMS.getGraphics(MaterialDesignIcon.INFORMATION, "text-success", 24);
        
        
        close = new JFXButton("",SMS.getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "text-error", 16));
        close.setTooltip(new ToolTip("Close notification"));
        close.getStyleClass().add("jfx-close-button");
        close.setOnAction((ActionEvent event) -> {
            parent.getChildren().remove(1);
        });
        
        HBox container = new HBox(icon, ms, new HSpacer(), new VBox(close, new VSpacer()));
        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        
        //-- Set style --
        container.getStyleClass().add("notif-container");
        ms.getStyleClass().add("notif-label");
        
        Node node = Borders.wrap(container)
                .lineBorder().innerPadding(0)
                .thickness(1, 1, 1, 3)
                .radius(5).outerPadding(2, 10, 2, 10)
                .color(Color.web("#EAEAEA"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#32CD32"))
                .buildAll();
        HBox.setHgrow(node, Priority.ALWAYS);
        
        getChildren().add(node);
        
    }
    
    
}
