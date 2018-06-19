package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import entry.AutoCompleteComboBoxListener;
import entry.HSpacer;
import entry.SMS;
import entry.ToolTip;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.DialogUI;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ObservableValue;
import mysqldriver.EmployeeQuery;
import static entry.SMS.getGraphics;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import mysqldriver.AdminQuery;
import static entry.SMS.getGraphics;

/**
 *
 * @author jabari
 */
public class AddMemberDialog extends JFXDialog{

    private JFXComboBox<String> member;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AddMemberDialog(String activityID) {
                    
        //-- Parent Container --
        StackPane stackPane = new StackPane();
        BorderPane container = new BorderPane();
        stackPane.getChildren().add(container);
        
        //-- Create form fields and add to content container ------------------- 
        GridPane contentGrid = new GridPane();
        contentGrid.getStyleClass().add("container");
        contentGrid.setStyle("-fx-padding:25 5 15 20;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(2);
        
        HBox con = new HBox(10);
        con.setAlignment(Pos.CENTER_LEFT);
        ToggleGroup tg = new ToggleGroup();
        
        JFXRadioButton teacher = new JFXRadioButton("Teacher/ Staff");
        RadioButton student = new JFXRadioButton("Student");
        
        teacher.setToggleGroup(tg);
        student.setToggleGroup(tg);
        tg.selectToggle(student);
        
        con.getChildren().addAll(student, teacher);
        contentGrid.add(con, 0, 0);
            
        member = new JFXComboBox<>(SMS.dbHandler.getStudentsNameList());
        member.setPromptText("Student Name");
        member.setLabelFloat(true);
        member.setPrefWidth(360);
        new AutoCompleteComboBoxListener(member);
        contentGrid.add(member, 0, 1);   
        
        teacher.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(newValue){
                member.setPromptText("Teacher/ Staff Name");
                member.setItems(EmployeeQuery.getEmployeeNameList());
            }else{
                member.setPromptText("Student Name"); 
                member.setItems(SMS.dbHandler.getStudentsNameList());
            }
        });
        
        container.setCenter(SMS.setBorderContainer(contentGrid, null));
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Members");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save changes"));
        save.setOnAction((ActionEvent event) -> {
            
            if(member.getValue() != null && !"".equals(member.getValue())){
                
                String memberID = "", type = "";
                if(student.isSelected()){
                    memberID = SMS.dbHandler.getStudentByName(member.getValue()).getStudentID();
                    type = "Student";
                }else{
                    memberID = EmployeeQuery.getEmployeeByName(member.getValue()).getEmployeeID();
                    type = "Staff";
                }
                
                ActivityMember ac = new ActivityMember("0", memberID, activityID, type);
                
                if(!AdminQuery.isActivityMemberExist(ac)){
                    
                    if(AdminQuery.addActivityMember(ac)){
                            new DialogUI("Activity member has been added successfully",
                                        DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();
                            SchoolAdministartion.extraCurriculaController.extraCurriculaMembers.ams.restart();
                            close();
                    }else{
                        new DialogUI("Exception occurred while trying to add activity member.",
                                    DialogUI.ERROR_NOTIF, stackPane, null).show();
                    }
                }else{
                    new DialogUI("Sport/ club member already exists !!!",
                                DialogUI.ERROR_NOTIF, stackPane, null).show();
                }
            
            }else{
                new DialogUI( "Ensure that mandatory field are filled up... ",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();
            }
                           
        });
        
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        container.setBottom(footer);

        //-- Set jfxdialog view  -----------------------------------------------
        setDialogContainer(SchoolAdministartion.ADMIN_MAN_STACK);
        setContent(stackPane);
        setOverlayClose(false);
        //stackPane.setPrefSize(400, 200);
        show();
        
    }
    
    
}
