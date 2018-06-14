package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import entry.AutoCompleteComboBoxListener;
import entry.DialogUI;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.dbHandler;
import entry.ToolTip;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import mysqldriver.AdminQuery;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CCValidator;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.scene.layout.GridPane;
import static schooladministration.SchoolAdministartion.streamClassesController;
import static entry.SMS.getGraphics;
import mysqldriver.EmployeeQuery;

/**
 *
 * @author jabari
 */
public class UpdateClassDialog extends JFXDialog{

    private JFXTextField name;
    private JFXComboBox<String> stream, house, teacher;
    
    //private final ValidationSupport vSupport;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public UpdateClassDialog(ISchoolClass isclass) {
                    
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
        
        name = new JFXTextField();
        name.setPromptText("Class Name");
        name.setPrefWidth(360);
        name.setLabelFloat(true);
        contentGrid.add(name, 0, 0);
        
        CCValidator.setFieldValidator(name, "Class name required.");
                
        stream = new JFXComboBox<>(AdminQuery.getStreamNames());
        stream.setPromptText("Class Stream");
        stream.setLabelFloat(true);
        stream.setPrefWidth(360);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(stream);
        new AutoCompleteComboBoxListener(stream);
        contentGrid.add(stream, 0, 1);
        if(streamClassesController.selectedStream != null){
            stream.setValue(streamClassesController.selectedStream.getDescription());
        }        
        
        house = new JFXComboBox<>(AdminQuery.getHouseNames());
        house.setPromptText("Class House");
        house.setLabelFloat(true);
        house.setPrefWidth(360);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(house);
        new AutoCompleteComboBoxListener(house);
        contentGrid.add(house, 0, 2);
        
        
        teacher = new JFXComboBox<>(EmployeeQuery.getEmployeeNameList());
        teacher.setPromptText("Class Teacher");
        teacher.setLabelFloat(true);
        teacher.setPrefWidth(360);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(teacher);
        new AutoCompleteComboBoxListener(teacher);
        contentGrid.add(teacher, 0, 3);
        
        container.setCenter(SMS.setBorderContainer(contentGrid, null));
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Class");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //-- Update form entries  ----------------------------------------------
        
        if(isclass != null){
            
            name.setText(isclass.getName());
            teacher.setValue(EmployeeQuery.getEmployeeByID(isclass.getClassTeacherID()).getFullName());
            
            stream.setValue(AdminQuery.getStreamByID(isclass.getStreamID()).getDescription());
            house.setValue(AdminQuery.getHouseByID(isclass.getHouse()).getHouseName());
            
            title.setText("Update Class");
        }
        
        
        //-- Validate and save the form  ---------------------------------------
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save Department"));
        save.setOnAction((ActionEvent event) -> {
            
            if(!"".equals(name.getText().trim()) 
                    && !stream.getValue().trim().equals("") 
                    && !house.getValue().trim().equals("")){

                    if(isclass != null){
                        ISchoolClass cls = new ISchoolClass(isclass.getClassID(),
                                        name.getText().trim(), 
                                        (teacher.getValue()!= null)?EmployeeQuery.getEmployeeByName(teacher.getValue()).getEmployeeID():"",
                                        (house.getValue() != null)?AdminQuery.getHouseByName(house.getValue()).getID():"",
                                         AdminQuery.getStreamByName(stream.getValue()).getStreamID(),
                                        "");

                        if(AdminQuery.updateClass(cls, true)){

                                new DialogUI("Class details been updated successfully",
                                    DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();
                                streamClassesController.sws.restart();
                                close();

                            }else{
                                new DialogUI("Exception occurred while trying to update class details",
                                DialogUI.ERROR_NOTIF, stackPane, null).show();
                            }
                    }else{

                        ISchoolClass cls = new ISchoolClass(SMS.generateDBID(), name.getText().trim(), 
                                (teacher.getValue()!= null)?EmployeeQuery.getEmployeeByName(teacher.getValue()).getEmployeeID():"",
                                (house.getValue() != null)?AdminQuery.getHouseByName(house.getValue()).getID():"",
                                 AdminQuery.getStreamByName(stream.getValue()).getStreamID(),
                                "");

                        if(!AdminQuery.isClassExists(cls)){
                            if(AdminQuery.updateClass(cls, false)){

                                new DialogUI("Class details has been added successfully",
                                DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, null).show();
                                streamClassesController.sws.restart();
                                close();
                            }else{
                                new DialogUI("Exception occurred while trying to add class details.",
                                DialogUI.ERROR_NOTIF, stackPane, null).show();
                            }

                       }else{
                            new DialogUI("Class name has been used already. Please use a different class name.",
                            DialogUI.ERROR_NOTIF, stackPane, null).show();
                       }
                    }
            }else{
                name.validate();

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
        stackPane.setPrefSize(400, 200);
        show();
        
    }
    
   
}
