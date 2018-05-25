package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entry.DialogUI;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.getGraphics;
import entry.ToolTip;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import entry.CCValidator;
import javafx.scene.layout.GridPane;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import mysqldriver.DBConfig;

/**
 *
 * @author jabari
 */
public class SystemConfigDialog extends JFXDialog{

    private JFXTextField dbHost, dbName, username;
    private JFXPasswordField password;    
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public SystemConfigDialog() {
        
        //-- Parent Container --
        StackPane stackPane = new StackPane();
        BorderPane container = new BorderPane();
        stackPane.getChildren().add(container);
        
        //-- Create form fields and add to content container ------------------- 
        GridPane contentGrid = new GridPane();
        contentGrid.getStyleClass().add("gridpane");
        contentGrid.setVgap(20);
        contentGrid.setHgap(2);
        
        dbHost = new JFXTextField();
        dbHost.setPromptText("Database Host");
        dbHost.setPrefWidth(360);
        dbHost.setLabelFloat(true);
        contentGrid.add(dbHost, 0, 0);
        CCValidator.setFieldValidator(dbHost, "Database host required.");
        
        
        dbName = new JFXTextField();
        dbName.setPromptText("Database Name");
        dbName.setPrefWidth(360);
        dbName.setLabelFloat(true);
        contentGrid.add(dbName, 0, 1);
        CCValidator.setFieldValidator(dbName, "Database name required.");
        
        
        username = new JFXTextField();
        username.setPromptText("Username");
        username.setPrefWidth(360);
        username.setLabelFloat(true);
        contentGrid.add(username, 0, 2);
        CCValidator.setFieldValidator(username, "Username required.");
        
        
        password = new JFXPasswordField();
        password.setPromptText("Password");
        password.setPrefWidth(360);
        password.setLabelFloat(true);
        contentGrid.add(password, 0, 3);
               
        container.setCenter(SMS.setBorderContainer(contentGrid, null));
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Database Configuration", getGraphics(MaterialDesignIcon.SERVER_NETWORK, "", 32));
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        DBConfig config = DBConfig.deserialiseObject();
        dbHost.setText(config.getDBHost());
        dbName.setText(config.getDBName());
        username.setText(config.getDBUserName());
        password.setText(config.getDBPassword());
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Update Configuration");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save Department"));
        save.setOnAction((ActionEvent event) -> {
            
            if(!dbHost.getText().trim().equals("")& 
                    !dbName.getText().trim().equals("")&
                    !username.getText().trim().equals("")){

                        config.setDBHost(dbHost.getText().trim());
                        config.setDBName(dbName.getText().trim());
                        config.setDBUserName(username.getText().trim());
                        config.setDBPassword(password.getText().trim());

                        //-- Save configuration file with update details --
                        DBConfig.serializeObject(config);

                        new DialogUI("Database configuration details has been updated successfully. "
                                + "Restart the application for changes to effect.",
                            DialogUI.SUCCESS_NOTIF, SMS.MAIN_UI, this).show(); 
            }else{
                dbHost.validate();
                dbName.validate();
                username.validate();

                new DialogUI( "Ensure that mandatory field are filled up... ",
                    DialogUI.ERROR_NOTIF, stackPane, null).show();
            }
            
        });
        
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        container.setBottom(footer);

        //-- Set JFXDialog view  -----------------------------------------------
        setDialogContainer(SMS.MAIN_UI);
        setTransitionType(JFXDialog.DialogTransition.LEFT);
        setContent(stackPane);
        setOverlayClose(true);
        stackPane.setPrefSize(400, 300);
        show();
        
    }
}
