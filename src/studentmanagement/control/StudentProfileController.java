package studentmanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entry.CCValidator;
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
import static studentmanagement.control.StudentEnrolmentController.profileStage;


/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class StudentProfileController implements Initializable {

    
    public JFXDatePicker dob;

    @FXML
    private JFXComboBox<String> pob;

    @FXML
    private JFXTextField plname;

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
    private JFXComboBox<String> prelationship, language;

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
    private JFXComboBox<String> peducation;

    @FXML
    private JFXButton btn_cancel;

    @FXML
    private VBox personalDetails, background_process;

    @FXML
    private JFXRadioButton male;
    
    private Image imageHolder;
    private ProfileUpdateService uls;
    
    InputValidator iv = new InputValidator();
    CCValidator ccv = new CCValidator();
    
    /**
     * Initializes the controller class.
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        disableEditing();
        
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
        
        
        
        
        ccv.setTextFieldValidator(fname, "Name required");
        ccv.setTextFieldValidator(lname, "Last name required");
//        ccv.setTextFieldValidator(nationality, "Name required");
//        ccv.setTextFieldValidator(lname, "mName required");
        
        
        iv.addField(fname);
        iv.addField(mname);
        
        
        
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
