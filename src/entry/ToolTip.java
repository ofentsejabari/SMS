
package entry;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.controlsfx.tools.Borders;

/**
 *
 * @author ofentse
 */
public class ToolTip extends Tooltip{
    

    public ToolTip(String tip, int prefWidth, int prefHeight) {
        
        
        Label ms = new Label(tip);
        ms.setWrapText(true);
        
        Text icon = SMS.getGraphics(MaterialDesignIcon.HELP_CIRCLE, "text-info", 24);
        
        
        HBox container = new HBox(icon, ms);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPrefWidth(prefWidth);
        container.setPrefHeight(prefHeight);
        container.setSpacing(10);
        
        //-- Set style --
        container.getStyleClass().add("tooltip-container");
        ms.getStyleClass().add("tooltip-label");
        
        Node node = Borders.wrap(container)
                .lineBorder().innerPadding(0)
                .thickness(1, 1, 1, 2)
                .radius(5).outerPadding(2, 10, 2, 10)
                .color(Color.web("#ffffff"), Color.web("#ffffff"),
                       Color.web("#ffffff"), Color.web("#00BFFF"))
                .buildAll();
        HBox.setHgrow(node, Priority.ALWAYS);
        
        setGraphic(node);
        
    }
    
     public ToolTip(String tip, int prefWidth, int prefHeight, Text icon) {
        
        
        Label ms = new Label(tip);
        ms.setWrapText(true);
        
        
        HBox container = new HBox(icon, ms);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPrefWidth(prefWidth);
        container.setPrefHeight(prefHeight);
        container.setSpacing(10);
        
        //-- Set style --
        container.getStyleClass().add("tooltip-container");
        ms.getStyleClass().add("tooltip-label");
        
        Node node = Borders.wrap(container)
                .lineBorder().innerPadding(0)
                .thickness(1, 1, 1, 2)
                .radius(5).outerPadding(2, 10, 2, 10)
                .color(Color.web("#ffffff"), Color.web("#ffffff"),
                       Color.web("#ffffff"), Color.web("#00BFFF"))
                .buildAll();
        HBox.setHgrow(node, Priority.ALWAYS);
        
        setGraphic(node);
        
    }
    
    public ToolTip(String tip) {
        
        
        Label ms = new Label(tip);
        ms.setWrapText(true);
        
        Text icon = SMS.getGraphics(MaterialDesignIcon.HELP_CIRCLE, "text-info", 24);
        
        
        HBox container = new HBox(icon, ms);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPrefWidth(150);
        container.setPrefHeight(30);
        container.setSpacing(10);
        
        //-- Set style --
        container.getStyleClass().add("tooltip-container");
        ms.getStyleClass().add("tooltip-label");
        
        Node node = Borders.wrap(container)
                .lineBorder().innerPadding(0)
                .thickness(1, 1, 1, 2)
                .radius(5).outerPadding(2, 10, 2, 10)
                .color(Color.web("#ffffff"), Color.web("#ffffff"),
                       Color.web("#ffffff"), Color.web("#00BFFF"))
                .buildAll();
        HBox.setHgrow(node, Priority.ALWAYS);
        
        setGraphic(node);
        
    }
    
    
}
