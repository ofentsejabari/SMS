/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagement;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import static employeemanagement.EmployeeManagementView.employeeListWork;
import entry.HSpacer;
import entry.SMS;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author MOILE
 */
public class EmployeeDesignationsView extends BorderPane{
    
    JFXButton add,btn_export,btn_refresh;
    
    public EmployeeDesignationsView(){
    
        setPadding(new Insets(10));
        
        add = new JFXButton("Add");
        add.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_PLUS,"icon-default", 22));
        add.getStyleClass().add("jfx-tool-button");
        
        btn_export = new JFXButton("Export");
        btn_export.getStyleClass().add("jfx-tool-button");
        btn_export.setGraphic(SMS.getGraphics(MaterialDesignIcon.EXPORT, "icon-default", 24));
        btn_export.setOnAction((ActionEvent event) -> {
            
        });
        
        btn_refresh = new JFXButton("Refresh");
        btn_refresh.getStyleClass().add("jfx-tool-button");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            
        });
        
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        toolbar.getChildren().addAll(new HSpacer(),add,btn_export,btn_refresh);
        setTop(toolbar);
        
        
        
    
    }
    
}
