package schooladministration;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author ofentse
 */
public class DepartmentDetails extends BorderPane{

    public DepartmentDetails() {
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Update Grading Scheme");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateDepartmentDialog(null).show();
        });
        
        
        toolbar.getChildren().addAll(new HSpacer(), btn_add);
        
    }
    
    
    
}
