/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.getGraphics;
import static entry.SMS.setDataNotAvailablePlaceholder;
import entry.ToolTip;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.InventoryQuery;

/**
 *
 * @author MOILE
 */
public class AllocateResourceDialog extends JFXDialog{
    
    private final StackPane stackPane;
    public String studentID = "",filter2 = "";
    
    
    public AllocateResourceDialog(String studentID) {
        
        
        //-- Parent Container --------------------------------------------------
        StackPane stack = new StackPane();
        BorderPane container = new BorderPane();
        stack.getChildren().add(container);
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        //Label title = new Label("Database Configuration", getGraphics(MaterialDesignIcon.SERVER_NETWORK, "", 32));
        //title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //---------------------- END SCREEN DECORATION -------------------------
        
        BorderPane bp = new BorderPane();
        this.studentID = studentID;
        
        bp.getStyleClass().add("container");
        stackPane = new StackPane();
        stackPane.setStyle("-fx-padding: 10 5 0 5;");
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        bp.setTop(toolbar);
        
        JFXComboBox<String> resource = new  JFXComboBox<>(InventoryQuery.getInventoryType());
        resource.setPromptText("Asset Type");
        resource.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter2 = newValue;
         });
        
        toolbar.getChildren().add(resource);
           
          //-- Validate and save the form  -------------------------------------
            JFXButton save = new JFXButton("Update Configuration");
            save.getStyleClass().add("dark-blue");
            save.setTooltip(new ToolTip("Save Department"));
            save.setOnAction((ActionEvent event) -> {
           
           
            });
        

        //-- Set JFXDialog view  -----------------------------------------------
        setDialogContainer(SMS.MAIN_UI);
        setTransitionType(JFXDialog.DialogTransition.CENTER);
        setContent(stack);
        setOverlayClose(true);
        stack.setPrefSize(300, 200);
        show();
        
    }
    
    
}
