package entry;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author jabari
 */
public class PopupButton extends Button{
    
    public PopupButton(String label, Node img) {
        
        getStyleClass().add("popupbutton");
        setPrefSize(250, 30);
        
        HBox grap = new HBox(new Label(label), new HSpacer(), img);
        grap.setAlignment(Pos.CENTER);
        grap.prefWidthProperty().bind(widthProperty());
        
        setGraphic(grap);
        setContentDisplay(ContentDisplay.RIGHT);
    }
    
    
    public PopupButton(String label, ImageView defaultIcon, ImageView hoverIcon) {
        
        getStyleClass().add("popupbutton");
        setPrefSize(250, 30);
        
        Label graphic = new Label();
        graphic.setGraphic(defaultIcon);
        
        HBox grap = new HBox(new Label(label), new HSpacer(), graphic);
        grap.setAlignment(Pos.CENTER);
        grap.prefWidthProperty().bind(widthProperty());
        
        setGraphic(grap);
        setContentDisplay(ContentDisplay.RIGHT);
        
        setOnMouseEntered((MouseEvent event) -> {
            graphic.setGraphic(hoverIcon);
            
        });
        
        setOnMouseExited((MouseEvent event) -> {
            graphic.setGraphic(defaultIcon);
            
        });
    }
    
    public PopupButton(String label) {
        
        getStyleClass().add("popupbutton");
        setPrefSize(250, 30);
        setText(label);
    }

}
