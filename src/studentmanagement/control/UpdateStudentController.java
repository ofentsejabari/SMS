package studentmanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CCValidator;
import entry.DialogUI;
import entry.InputValidator;
import entry.SMS;
import static entry.SMS.dbHandler;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.tools.Borders;
import studentmanagement.Student;
import studentmanagement.StudentProfileStage;
import mysqldriver.AdminQuery;
import studentmanagement.SParent;
import static entry.SMS.getGraphics;
import static entry.SMS.getIcon;
import javafx.application.Platform;
import static entry.SMS.getGraphics;
import static entry.SMS.getIcon;
import entry.ToolTip;
import java.util.Date;
import javafx.scene.layout.GridPane;
import static entry.SMS.getGraphics;
import static entry.SMS.getIcon;
import static entry.SMS.getGraphics;
import static entry.SMS.getIcon;


/**
 * FXML Controller class
 *
 * @author ofentse
 */
public class UpdateStudentController implements Initializable {

    public JFXDatePicker dob, enrollmentDate;

    @FXML
    private HBox container;

    @FXML
    private JFXComboBox<String> lastSchool;

    @FXML
    private VBox parentContacts;

    @FXML
    private VBox background_process;

    @FXML
    private JFXTextField plname;

    @FXML
    private JFXComboBox<String> education;

    @FXML
    private JFXComboBox<String> occupation;

    @FXML
    private JFXTextField parentEmail;

    @FXML
    private VBox studentDetails;

    @FXML
    private VBox studentContact;

    @FXML
    private JFXTextArea parent_physicalAddress;

    @FXML
    private JFXTextArea student_postalAddress;

    @FXML
    private JFXTextField mname;

    @FXML
    private Label title;

    @FXML
    private JFXCheckBox specialNeeds;

    @FXML
    private VBox parentDetails;

    @FXML
    private JFXTextArea parent_postalAddress;

    @FXML
    private JFXComboBox<String> classAllocated;

    @FXML
    private JFXTextField studentEmail;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXComboBox<String> club;

    @FXML
    private JFXTextField cellphone;

    @FXML
    private JFXRadioButton female;

    @FXML
    private JFXComboBox<String> relationship;

    @FXML
    private JFXButton btn_toolbar_close;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXTextArea student_physicalAddress;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXComboBox<String> sportCode;

    @FXML
    private JFXComboBox<String> citizenship;

    @FXML
    private JFXComboBox<String> pslegrade;

    @FXML
    private JFXCheckBox socialSupport;

    @FXML
    private JFXTextField telephone;

    @FXML
    private Circle profile_picture;

    @FXML
    private StackPane stackPane;

    @FXML
    private JFXComboBox<String> homeVillage;

    @FXML
    private JFXTextField pfname;

    @FXML
    private JFXTextField identity;

    @FXML
    private JFXButton btn_cancel;

    @FXML
    private CustomTextField enrolmentID;

    @FXML
    private JFXRadioButton male;
    
    
    private Image imageHolder;
    private ProfileUpdateService uls;
    
    InputValidator iv = new InputValidator();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        container.getChildren().clear();
        btn_toolbar_close.setGraphic(getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_toolbar_close.getStyleClass().add("window-close");
        
        //-- Student contact details -------------------------------------------
        
        VBox persDetails = new VBox();
        persDetails.getChildren().addAll(Borders.wrap(studentDetails)
                .lineBorder()
                .title("Student Details")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#cfd8dc"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(persDetails, Priority.ALWAYS);
        
        //-- Other  details -------------------------------------------
        
        VBox conDetails = new VBox();
        conDetails.getChildren().addAll(Borders.wrap(studentContact)
                .lineBorder()
                .title("Other Details")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#cfd8dc"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(conDetails, Priority.ALWAYS);
        
        //-- Parent contact details -------------------------------------------
        
        VBox clsDetails = new VBox();
        clsDetails.getChildren().addAll(Borders.wrap(parentDetails)
                .lineBorder()
                .title("Parent/Gaurdian Details")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#cfd8dc"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(clsDetails, Priority.ALWAYS);
        
        //-- Student contact details -------------------------------------------
        
        VBox parDetails = new VBox();
        parDetails.getChildren().addAll(Borders.wrap(parentContacts)
                .lineBorder()
                .title("Parent/Gaurdian Contacts")
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(15, 10, 10, 10)
                .radius(5)
                .color(Color.web("#cfd8dc"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA")).buildAll());

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
        studentDetails.getChildren().add(5, dob);
        
        enrollmentDate = new JFXDatePicker();
        enrollmentDate.setPromptText("Enrolment Date");
        
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        
        Label db = new Label("DOB:");
        db.setTooltip(new ToolTip("Date of Birth"));
        grid.add(db, 0, 1);
        grid.add(dob, 1, 1);
        
        Label enrd = new Label("ENRD");
        enrd.setTooltip(new ToolTip("Enrollment Date"));
        grid.add(enrd, 0, 0);
        
        grid.add(enrollmentDate, 1, 0);
        
        studentDetails.getChildren().add(6, grid);
        
        
        JFXButton id = new JFXButton("STUDENT ID");
        id.getStyleClass().add("dark-green");
        id.setStyle("-fx-padding:4;");
        id.setPrefWidth(50);
        
        enrolmentID.setLeft(id);
        enrolmentID.setEditable(false);
        
        //-- Add Radio buttons to a toggle group to allow one selection at a time --
        
        ToggleGroup tg = new ToggleGroup();
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);
        tg.selectToggle(male);
        
        //-- Add validators ----------------------------------------------------
        CCValidator.setFieldValidator(fname, "Name required");
        CCValidator.setFieldValidator(lname, "Surname required");
        CCValidator.setFieldValidator(studentEmail, "Email required"); 
        
        CCValidator.setFieldValidator(pfname, "Name required"); 
        CCValidator.setFieldValidator(plname, "Last name required"); 
        CCValidator.setFieldValidator(telephone, "Telephone # required"); 
        CCValidator.setFieldValidator(cellphone, "Telephone # required");
        CCValidator.setFieldValidator(identity, "Identity required");
        
        CCValidator.setFieldValidator(parent_physicalAddress, "Physical Address required");
        CCValidator.setFieldValidator(parent_postalAddress, "Postal Address required");
        
        iv.addField(fname);
        iv.addField(lname);
        iv.addField(pfname);
        iv.addField(plname);
        iv.addField(studentEmail);
        
        iv.addField(student_physicalAddress);
        iv.addField(student_postalAddress);
        
        iv.addField(parentEmail);
        iv.addField(telephone);
        iv.addField(cellphone);
        iv.addField(identity);
        iv.addField(parent_physicalAddress);
        iv.addField(parent_postalAddress);
        
        enrolmentID.setText(generateStudentID());
        
             
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
    
    /**
     * Generate a student ID using current time stamp. This will ensure uniqueness
     * of the ID`s generated.
     * @return ID(e.g Jul2417202320 )
     */
    public String generateStudentID(){
        
        Date date = new Date();
        String str = String.format("%tc",date);
        String st[] = str.split(" ");
        String tym[] = st[3].split(":");
        
        return(st[1].toUpperCase()+""+st[2]+""+st[5].substring(2)+""+tym[0]+""+tym[1]+""+tym[2]);
    }
    
    /**
     * Generate parent ID
     * @return ( e.g 2417202320 )
     */
    public String generateParentID(){
        
        Date date = new Date();
        String str = String.format("%tc",date);
        String st[] = str.split(" ");
        String tym[] = st[3].split(":");
        
        return(st[2]+""+st[5].substring(2)+""+tym[0]+""+tym[1]+""+tym[2]);
    } 
    
    /**
     * Get reference to close button --
     * @return 
     */
    public JFXButton getCloseButton(){
        return btn_toolbar_close;
    }
    
    /**
     * Update input fields is for previewing or update.
     * @param student 
     */
    public void updateStudentDetails(Student student){
        
        title.setText(student.getFullName().toUpperCase());
            enrolmentID.setText(student.getStudentID());

            fname.setText(student.getFirstName());
            mname.setText(student.getMiddleName());
            lname.setText(student.getLastName());

            citizenship.setValue(student.getCitizenship());
            dob.setValue(SMS.getLocalDate(student.getDob()));
            enrollmentDate.setValue(SMS.getLocalDate(student.getEnrollDate()));

            //-- 1 for male and 2 for female --
            male.setSelected("MALE".equalsIgnoreCase(student.getGender()));
            female.setSelected("FEMALE".equalsIgnoreCase(student.getGender()));

            studentEmail.setText(student.getEmail());
            classAllocated.setValue(AdminQuery.getClassByID(student.getClassID()).getName());
            student_postalAddress.setText(student.getPostalAddress());
            student_physicalAddress.setText(student.getPhysicalAddress());

            pslegrade.setValue(student.getPslegrade());
            studentEmail.setText(student.getEmail());

            specialNeeds.setSelected(student.getSpecialNeed().equalsIgnoreCase("1"));
            socialSupport.setSelected(student.getSocialWalfare().equalsIgnoreCase("1"));

            sportCode.setValue(student.getSportCode());
            club.setValue(student.getClub());

            //-- Set parent details --
            SParent sparent = dbHandler.getParentByID(student.getParentID());
            pfname.setText(sparent.getFirstName()); 
            plname.setText(sparent.getLastName());
            occupation.setValue(sparent.getOccupation());
            identity.setText(sparent.getIdentity());
            education.setValue(sparent.getEducation());
            cellphone.setText(sparent.getCellphone());
            parentEmail.setText(sparent.getEmail()); 
            telephone.setText(sparent.getTelephone());
            parent_postalAddress.setText(sparent.getPostalAddress());
            parent_physicalAddress.setText(sparent.getPhysicalAddress());
            relationship.setValue(sparent.getRelation());
        
    }
       
    public void enableEditing(){  
        
        btn_update.setVisible(true);
        plname.setEditable(true);
        mname.setEditable(true);
        cellphone.setEditable(true);
        parentEmail.setEditable(true);
        lname.setEditable(true);
        telephone.setEditable(true);
        fname.setEditable(true);
        identity.setEditable(true);
        pfname.setEditable(true);
    }
    
    public void disableEditing(){
        
        btn_update.setVisible(false);
        plname.setEditable(false);
        mname.setEditable(false);
        cellphone.setEditable(false);
        parentEmail.setEditable(false);
        lname.setEditable(false);
        studentEmail.setEditable(false);
        telephone.setEditable(false);
        fname.setEditable(false);
        identity.setEditable(false);
        pfname.setEditable(false);
    }
    
    public class ProfileUpdateTask extends Task<Void> {       
        @Override 
        protected Void call() throws Exception{
           
            try{
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
