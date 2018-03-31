package studentmanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entry.CCValidator;
import entry.DialogUI;
import entry.InputValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import static entry.SMS.getIcon;
import entry.VSpacer;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlsfx.tools.Borders;
import static studentmanagement.control.StudentEnrolmentController.profileStage;


/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class StudentProfileController implements Initializable {

    
    public JFXDatePicker dob;

    @FXML
    private HBox container;

    @FXML
    private VBox background_process;

    @FXML
    private JFXComboBox<String> pob;

    @FXML
    private JFXTextField plname;

    @FXML
    private JFXComboBox<String> language;

    @FXML
    private JFXTextField mname;

    @FXML
    private JFXTextField cell;

    @FXML
    private JFXTextField pemail;

    @FXML
    private JFXComboBox<String> classAllocated;

    @FXML
    private JFXTextField lname;


    @FXML
    private JFXTextArea physicalAddress;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXButton btn_toolbar_close;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField ptel;

    @FXML
    private JFXComboBox<String> prelationship;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField pomang;

    @FXML
    private JFXButton edit;

    @FXML
    private Circle profile_picture;

    @FXML
    private JFXComboBox<String> poccupation;

    @FXML
    private JFXComboBox<String> homeVillage;

    @FXML
    private JFXTextField pfname;

    @FXML
    private JFXCheckBox enable_editing;

    @FXML
    private JFXTextArea postalAddress;

    @FXML
    private JFXComboBox<String> nationality;

    @FXML
    private VBox optionalSubjects;

    @FXML
    private JFXComboBox<String> peducation;

    @FXML
    private JFXButton btn_cancel;

    @FXML
    private VBox personalDetails, parentDetails, classDetails, contactDetails;

    @FXML
    private JFXRadioButton male;
    
    @FXML
    private StackPane stackPane;
    
    private Image imageHolder;
    private ProfileUpdateService uls;
    
    InputValidator iv = new InputValidator();
    
    /**
     * Initializes the controller class.
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        disableEditing();
        container.getChildren().clear();
       
        
        //-- Student contact details -------------------------------------------
        
        VBox persDetails = new VBox();
        persDetails.getChildren().addAll(Borders.wrap(personalDetails)
                .lineBorder()
                .title("Persornal Details")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#FFC300"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(persDetails, Priority.ALWAYS);
        
        
        //-- Student contact details -------------------------------------------
        
        VBox conDetails = new VBox();
        conDetails.getChildren().addAll(Borders.wrap(contactDetails)
                .lineBorder()
                .title("Contact Details")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#72D800"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(conDetails, Priority.ALWAYS);
        
        //-- Student contact details -------------------------------------------
        
        VBox clsDetails = new VBox();
        clsDetails.getChildren().addAll(Borders.wrap(classDetails)
                .lineBorder()
                .title("Subjects - Class Allocation")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#FFC300"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(clsDetails, Priority.ALWAYS);
        
        //-- Student contact details -------------------------------------------
        
        VBox parDetails = new VBox();
        parDetails.getChildren().addAll(Borders.wrap(parentDetails)
                .lineBorder()
                .title("Parent/Gaurdian Details")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#72D800"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(parDetails, Priority.ALWAYS);
        
        
        container.getChildren().addAll(persDetails, conDetails, clsDetails, parDetails);
        
        uls = new ProfileUpdateService();
        background_process.visibleProperty().bind(uls.runningProperty());
        uls.start();
        
        
        //-- Update profile picture --
        imageHolder = getIcon("user.png").getImage();
        profile_picture.setFill(new ImagePattern(imageHolder));
        
        
        dob = new JFXDatePicker();
        dob.setPromptText("Date Of Birth");
        personalDetails.getChildren().add(6, dob);
        
        
        //-- edit --
        edit.setText("");
        edit.setOnAction((ActionEvent event) -> {
            //-- Open file chooser dialog --
            System.err.println("Edit profile picture");
        });
        
        //-- Toggle editing of input field through a check box --
        enable_editing.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(newValue){
                enableEditing();
            }else{
                disableEditing();
            }
        });
        
        //-- Add Radio buttons to a toggle group to allow one selection at a time --
        
        ToggleGroup tg = new ToggleGroup();
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);
        tg.selectToggle(male);
        
        
        
        
        CCValidator.setTextFieldValidator(fname, "Name required");
        CCValidator.setTextFieldValidator(lname, "Surname required");
        CCValidator.setTextFieldValidator(pemail, "Cell Phone"); 
        CCValidator.setTextFieldValidator(pfname, "First name required"); 
        CCValidator.setTextFieldValidator(plname, "Last name required"); 
        CCValidator.setTextFieldValidator(ptel, "Cell/ Tel # required"); 
        CCValidator.setTextFieldValidator(pomang, "Omang required");
        
        iv.addField(fname);
        iv.addField(lname);
        iv.addField(pfname);
        iv.addField(plname);
        iv.addField(pemail);
        iv.addField(ptel);
        iv.addField(pomang);
             
        
        btn_update.setOnAction((ActionEvent event) -> {
            
            if(iv.isValid()){
                
                //--  save the form --
                DialogUI succ = new DialogUI("Good to go!!!", DialogUI.SUCCESS_NOTIF, stackPane);
                succ.DIALOG_CONTROLLER.setBTNYESControl(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        background_process.toFront();
                        succ.close();
                    }
                }, "Continue");
                succ.show();
            }else{
                //-- show alert --
                iv.validate();
                DialogUI err = new DialogUI("Please ensure that all mandotory fields are filled up before saving changes.",
                        DialogUI.ERROR_NOTIF, stackPane);
                err.DIALOG_CONTROLLER.setBTNNOControl(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        background_process.toFront();
                        err.close();
                    }
                }, "Fix Errors");
                err.show();
            } 
        });
       
    }
            
    
    public void enableEditing(){
        edit.setVisible(true);              
        btn_update.setVisible(true);
        
        plname.setEditable(true);
        mname.setEditable(true);
        cell.setEditable(true);
        pemail.setEditable(true);
        lname.setEditable(true);
        email.setEditable(true);
        ptel.setEditable(true);
        fname.setEditable(true);
        pomang.setEditable(true);
        pfname.setEditable(true);
    }
    
    public void disableEditing(){
        
        edit.setVisible(false);
        btn_update.setVisible(false);
        
        plname.setEditable(false);
        mname.setEditable(false);
        cell.setEditable(false);
        pemail.setEditable(false);
        lname.setEditable(false);
        email.setEditable(false);
        ptel.setEditable(false);
        fname.setEditable(false);
        pomang.setEditable(false);
        pfname.setEditable(false);
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        
        if(event.getSource().equals(btn_toolbar_close)){
            
            //-- Close student profile window --
            profileStage.close();
            
        }else if(event.getSource().equals(btn_cancel)){
            
            //-- Cancel Changes and disable editing -- 
            enable_editing.setSelected(false);
            
        }else if(event.getSource().equals(btn_update)){
            
            if(iv.isValid()){
            
                //--  save the form --
                System.out.println("Good to go!!!");
                
            }else{
                //-- show alert --
                
                System.out.println("Mandatory fields not filled up");
            }
        }
    }
    
    
    public class ProfileUpdateTask extends Task<Void> {       
        @Override 
        protected Void call() throws Exception{
           
            try{
                //-- Sleep for a few seconds
               
                
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            
            return null;
        }
    }

    public class ProfileUpdateService extends Service<Void> {
        @Override
        protected Task createTask() {
            return new ProfileUpdateTask();
        }
    }

}
