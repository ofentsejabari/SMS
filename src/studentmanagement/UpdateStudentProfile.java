package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.AutoCompleteComboBoxListener;
import entry.CCValidator;
import entry.HSpacer;
import entry.JFXAlert;
import entry.SMS;
import entry.ToolTip;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mysqldriver.AdminQuery;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.util.Date;
import static studentmanagement.StudentManagement.studentEnrolmentController;
import static entry.SMS.getGraphics;

/**
 *
 * @author ofentse
 */
public class UpdateStudentProfile extends JFXDialog{
    
    public  Student student = null;
    private static File picture;
    
    private JFXTextArea p_postalAddress, p_physicalAddress;
    private JFXTextField p_fname, p_mname, p_lname, p_identity,
                       p_email, p_telephone, p_cellphone, p_relationship;
    private JFXComboBox<String> p_occupation, s_citizenship, p_educationLevel;
    
    private JFXTextArea s_postalAddress, s_physicalAddress;
    private JFXTextField s_fname, s_mname, s_lname, s_email;
    private JFXComboBox<String> s_assignedClass, s_lastSchool, s_pslGrade;
    private JFXDatePicker s_dob, s_enrollmentDate;
    private JFXRadioButton s_male, s_female;
    
    //-- 
    private String id = generateStudentID();
    
    public UpdateStudentProfile(Student stdnt){
        this.student = stdnt;
        
        StackPane root = new StackPane();
        BorderPane pane = new BorderPane();
        
        //-- Screen Decoration -------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Enroll Student");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        pane.setTop(toolBar);
        
        //-- End Screen Decoration ---------------------------------------------
        
        Image place_holder = new Image(SMS.class.getResourceAsStream("icons/u10.png"));
        
        Circle studentProfilePic = new Circle(45);
        studentProfilePic.setTranslateX(0);
        studentProfilePic.setTranslateY(0);
        studentProfilePic.setCenterX(30);
        studentProfilePic.setCenterY(30);
        studentProfilePic.setEffect(new DropShadow(3, Color.web("#EAEAEA")));
        studentProfilePic.setStroke(Color.web("#cfd8dc"));
        studentProfilePic.setStrokeWidth(1);
        studentProfilePic.setFill(Color.web("#EAEAEA"));
        studentProfilePic.setFill(new ImagePattern(place_holder));
        if(student != null){
            studentProfilePic.setFill(new ImagePattern(new Image(SMS.class.getResourceAsStream("icons/"+((student.getGender().equalsIgnoreCase("Female"))?"u9":"u10")+".png"))));
        }
        
        JFXButton update = new JFXButton("Update", getGraphics(MaterialDesignIcon.UPLOAD, "text-white", 18));
        update.getStyleClass().add("dark-green");
        update.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open image file");
            fileChooser.getExtensionFilters().addAll(
              new FileChooser.ExtensionFilter("Image Files", "*.png; *.jpg; *.jpeg; *.gif")
            );
            
            picture = fileChooser.showOpenDialog(null);
            if (picture != null) {
                try {
                    BufferedImage bufferedImage = ImageIO.read(picture);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
          
                    studentProfilePic.setFill(new ImagePattern(image));
                } catch (IOException ex) {

                }
            } 
        });
        
        Label studentID = new Label(id);
        studentID.getStyleClass().add("title-label");
        
        
        VBox con = new VBox(studentProfilePic, update, studentID);
        con.setSpacing(10);
        con.setAlignment(Pos.TOP_CENTER);
        con.setPadding(new Insets(10, 20, 10, 20));
        con.getStyleClass().add("profile-view");
        
        pane.setLeft(con);
        
        //-- End Profile Picture -----------------------------------------------
        GridPane contentGrid = new GridPane();
        contentGrid.setStyle("-fx-padding:10;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(10);
        
        contentGrid.add(SMS.setBorderContainer(getStudentDetails(), "Student Details"), 0, 0);
        contentGrid.add(SMS.setBorderContainer(getStudentContactDetails(), "Student Contacts"), 0, 1);
        
        contentGrid.add(SMS.setBorderContainer(getParentDetails(), "Parent/Guardian Details"), 0, 2);
        contentGrid.add(SMS.setBorderContainer(getParentContactDetails(), "Parent/Guardian Contacts"), 0, 3);
        //----------------------------------------------------------------------
        
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save"));
        save.setOnAction((ActionEvent event) -> {
            
            saveChanges();
            
        });  
            
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        pane.setBottom(footer);
        
        pane.setPrefSize(860, 700);
        pane.setCenter(new ScrollPane(contentGrid));
        if(student != null){
            title.setText("Update Student - "+student.getStudentID());
            
            studentID.setText(student.getStudentID());
            
            s_postalAddress.setText(student.getPostalAddress());
            s_physicalAddress.setText(student.getPhysicalAddress());
            s_fname.setText(student.getFirstName());
            s_mname.setText(student.getMiddleName());
            s_lname.setText(student.getLastName());
            s_email.setText(student.getEmail());
            s_assignedClass.setValue(AdminQuery.getClassByID(student.getClassID()).getName());
            s_lastSchool.setValue(student.getLastSchoolAttended());
            s_pslGrade.setValue(student.getPslegrade());
            s_dob.setValue(SMS.getLocalDate(student.getDob()));
            s_enrollmentDate.setValue(SMS.getLocalDate(student.getEnrollDate()));
            
            if(student.getGender().equalsIgnoreCase("Male")){
                s_male.setSelected(true);
            }else{
                s_female.setSelected(true);
            }
            
            //------------------------------------------------------------------
            Guardian guardian = SMS.dbHandler.getGaurdianByStudentID(student.getStudentID());
            p_fname.setText(guardian.getFirstName());
            p_lname.setText(guardian.getLastName());
            p_identity.setText(guardian.getIdentity());
            p_mname.setText(guardian.getMiddleName());
            p_relationship.setText(guardian.getRelation());
            p_email.setText(guardian.getEmail());
            p_telephone.setText(guardian.getTelephone());
            p_cellphone.setText(guardian.getCellphone());
            p_postalAddress.setText(guardian.getPostalAddress());
            p_physicalAddress.setText(guardian.getPhysicalAddress());
            p_educationLevel.setValue(guardian.getEducation());
            p_occupation.setValue(guardian.getOccupation());
        }
        
        root.getChildren().add(pane);
            
        setDialogContainer(PARENT_STACK_PANE);
        setContent(root);
        setOverlayClose(false);
        show();
        
    }

    
    
    private void saveChanges() {
        
        if(student != null){ //-- update --
            
            if(isValidateInputs()){
                //-- Create student and parent models --
                student.setCitizenship((s_citizenship.getValue() != null)? s_citizenship.getValue():"");
                student.setPostalAddress((s_postalAddress.getText() != null)? s_postalAddress.getText():"");
                student.setPhysicalAddress((s_physicalAddress.getText() != null)? s_physicalAddress.getText():"");
                student.setClassID((s_assignedClass.getValue() != null)? AdminQuery.getClassByName(s_assignedClass.getValue()).getClassID():"");
                student.setPslegrade((s_pslGrade.getValue() != null)? s_pslGrade.getValue():"");
                student.setDob((s_dob.getValue() != null)? s_dob.getValue().toString():"");
                student.setEnrollDate((s_enrollmentDate.getValue() != null)? s_enrollmentDate.getValue().toString():"");
                student.setFirstName(s_fname.getText().trim());
                student.setMiddleName(s_mname.getText().trim());
                student.setLastName(s_lname.getText().trim());
                student.setEmail(s_email.getText().trim());
                student.setGender(s_male.isSelected()?"Male":"Female");
                
                Guardian parent = SMS.dbHandler.getGaurdianByStudentID(student.getStudentID());
                
                parent.setFirstName(p_fname.getText().trim());
                parent.setLastName(p_lname.getText().trim());
                parent.setIdentity(p_identity.getText().trim());
                parent.setEmail(p_email.getText().trim());
                parent.setPostalAddress(p_postalAddress.getText().trim());
                parent.setPhysicalAddress(p_physicalAddress.getText().trim());
                parent.setTelephone(p_telephone.getText().trim());
                parent.setCellphone(p_cellphone.getText().trim());
                parent.setOccupation((p_occupation.getValue() != null)? p_occupation.getValue():"");
                parent.setEducation((p_educationLevel.getValue() != null)? p_educationLevel.getValue():"");
                
                if(SMS.dbHandler.updateStudentProfile(student, parent, true)){
                    new JFXAlert(JFXAlert.SUCCESS, "Update Successful",
                            " Student details has been updated successfully");
                    
                    studentEnrolmentController.enrollUI.studentListWork.restart();
                }else{
                    new JFXAlert(JFXAlert.ERROR, "Update Failed",
                            "An error encountered while trying to update student details");
                }
            }else{
                new JFXAlert(JFXAlert.ERROR, "Input Capture Error",
                        " Please ensure that required fields are captured, before trying to update"
                                + " student information");
            }
        }else{
            //-- create new --
            if(isValidateInputs()){
                
                Student std = new Student();
                std.setStudentID(id);
                std.setCitizenship((s_citizenship.getValue() != null)? s_citizenship.getValue():"");
                std.setPostalAddress((s_postalAddress.getText() != null)? s_postalAddress.getText():"");
                std.setPhysicalAddress((s_physicalAddress.getText() != null)? s_physicalAddress.getText():"");
                std.setClassID((s_assignedClass.getValue() != null)? AdminQuery.getClassByName(s_assignedClass.getValue()).getClassID():"");
                std.setPslegrade((s_pslGrade.getValue() != null)? s_pslGrade.getValue():"");
                std.setDob((s_dob.getValue() != null)? s_dob.getValue().toString():"");
                std.setEnrollDate((s_enrollmentDate.getValue() != null)? s_enrollmentDate.getValue().toString():"");
                std.setFirstName(s_fname.getText().trim());
                std.setMiddleName(s_mname.getText().trim());
                std.setLastName(s_lname.getText().trim());
                std.setEmail(s_email.getText().trim());
                std.setGender(s_male.isSelected()?"Male":"Female");
                std.setStatus("Active");
                std.setSchoolID("20000");
                
                
                Guardian parent = new Guardian();
                parent.setStudentID(std.getStudentID());
                parent.setFirstName(p_fname.getText().trim());
                parent.setRelation(p_relationship.getText().trim());
                parent.setLastName(p_lname.getText().trim());
                parent.setIdentity(p_identity.getText().trim());
                parent.setEmail(p_email.getText().trim());
                parent.setPostalAddress(p_postalAddress.getText().trim());
                parent.setPhysicalAddress(p_physicalAddress.getText().trim());
                parent.setTelephone(p_telephone.getText().trim());
                parent.setCellphone(p_cellphone.getText().trim());
                parent.setOccupation((p_occupation.getValue() != null)? p_occupation.getValue():"");
                parent.setEducation((p_educationLevel.getValue() != null)? p_educationLevel.getValue():"");
                
                if(SMS.dbHandler.updateStudentProfile(std, parent, false)){
                    new JFXAlert(JFXAlert.SUCCESS, "Update Successful",
                            " Student enrolled successfully");
                    close();
                    studentEnrolmentController.enrollUI.studentListWork.restart();
                    
                }else{
                    new JFXAlert(JFXAlert.ERROR, "Update Failed",
                            "An error encountered while trying to enroll student.");
                }
            }else{
                
                new JFXAlert(JFXAlert.ERROR, "Input Capture Error",
                        "Please ensure that required fields are captured,"
                                + " before trying to add new student information to the system.");
                
            }
        }
    }
    
    /**
     * if one of the mandatory fields is empty
     * @return false, true otherwise.
     */
    private boolean isValidateInputs(){
        
        if(p_fname.getText().trim().equals("") || p_lname.getText().trim().equals("") ||
            p_identity.getText().trim().equals("") || p_email.getText().trim().equals("") ||
            p_identity.getText().trim().equals("") || p_telephone.getText().trim().equals("") ||
            p_cellphone.getText().trim().equals("") || p_postalAddress.getText().trim().equals("") ||
            p_physicalAddress.getText().trim().equals("") || s_postalAddress.getText().trim().equals("") ||
            s_physicalAddress.getText().trim().equals("") || s_fname.getText().trim().equals("") ||
            s_lname.getText().trim().equals("")){
            
            p_fname.validate(); p_lname.validate(); p_identity.validate(); p_email.validate();
            p_telephone.validate(); p_cellphone.validate(); p_postalAddress.validate();
            p_physicalAddress.validate(); 
            
            s_postalAddress.validate(); s_physicalAddress.validate();
            s_fname.validate(); s_lname.validate();
            
            return false;
        }
        return true;
    }
    
    private GridPane getParentContactDetails(){
    
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:25 20;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(20);
        
        p_email = new JFXTextField();
        p_email.setPromptText("Email Address");
        CCValidator.setFieldValidator(p_email, "Required");
        p_email.setLabelFloat(true);
        p_email.setPrefWidth(280);
        contactDetails.add(p_email, 0, 0);
        
        p_telephone = new JFXTextField();
        p_telephone.setPromptText("Telephone");
        CCValidator.setFieldValidator(p_telephone, "Required");
        p_telephone.setLabelFloat(true);
        p_telephone.setPrefWidth(280);
        contactDetails.add(p_telephone, 1, 0);
        
        
        p_cellphone = new JFXTextField();
        p_cellphone.setPromptText("Cellphone");
        p_cellphone.setLabelFloat(true);
        p_cellphone.setPrefWidth(280);
        CCValidator.setFieldValidator(p_cellphone, "Required");
        contactDetails.add(p_cellphone, 0, 1);
        
        p_relationship = new JFXTextField();
        p_relationship.setPromptText("Relationship");
        p_relationship.setLabelFloat(true);
        p_relationship.setPrefWidth(280);
        CCValidator.setFieldValidator(p_relationship, "Required");
        contactDetails.add(p_relationship, 1, 1);
        
        p_postalAddress = new JFXTextArea();
        p_postalAddress.setPromptText("Postal Address");
        p_postalAddress.setLabelFloat(true);
        p_postalAddress.setPrefWidth(280);
        p_postalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(p_postalAddress, "Required");
        contactDetails.add(p_postalAddress, 0, 2);
        
        
        p_physicalAddress = new JFXTextArea();
        p_physicalAddress.setPromptText("Physical Address");
        p_physicalAddress.setLabelFloat(true);
        p_physicalAddress.setPrefWidth(280);
        p_physicalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(p_physicalAddress, "Required");
        contactDetails.add(p_physicalAddress, 1, 2);
        
        return contactDetails;
    }
    
    private GridPane getParentDetails(){
    
        GridPane parentDetails = new GridPane();
        parentDetails.getStyleClass().add("container");
        parentDetails.setStyle("-fx-padding:25 20;");
        parentDetails.setVgap(20);
        parentDetails.setHgap(20);
        
        p_fname = new JFXTextField();
        p_fname.setPromptText("First Name");
        p_fname.setLabelFloat(true);
        p_fname.setPrefWidth(280);
        CCValidator.setFieldValidator(p_fname, "Required");
        parentDetails.add(p_fname, 0, 0);
        
        p_mname = new JFXTextField();
        p_mname.setPromptText("Middle Name");
        p_mname.setLabelFloat(true);
        parentDetails.add(p_mname, 1, 0);
        
        p_lname = new JFXTextField();
        p_lname.setPromptText("Surname");
        p_lname.setLabelFloat(true);
        CCValidator.setFieldValidator(p_lname, "Required");
        parentDetails.add(p_lname, 0, 1);
        
        p_identity = new JFXTextField();
        p_identity.setPromptText("Omang/ Passport Number");
        p_identity.setLabelFloat(true);
        CCValidator.setFieldValidator(p_identity, "Required");
        parentDetails.add(p_identity, 1, 1);
        
        p_educationLevel = new JFXComboBox<>();
        p_educationLevel.setPromptText("Education Level Attained");
        p_educationLevel.setLabelFloat(true);
        p_educationLevel.setPrefWidth(280);
        new AutoCompleteComboBoxListener(p_educationLevel);
        parentDetails.add(p_educationLevel, 0, 2);   
        
        p_occupation = new JFXComboBox<>();
        p_occupation.setPromptText("Occupation");
        p_occupation.setLabelFloat(true);
        p_occupation.setPrefWidth(280);
        new AutoCompleteComboBoxListener(p_occupation);
        parentDetails.add(p_occupation, 1, 2);
        
        return parentDetails;
    }
    
    private GridPane getStudentContactDetails(){
    
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:25 20;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(20);
        
        s_citizenship = new JFXComboBox<>(
                FXCollections.observableArrayList("Botswana", "South Africa",
                "Lesotho", "Zambia", "Zimbaqwe", "Namibia", "Swaziland"));
        
        s_citizenship.setPromptText("Citizenship");
        s_citizenship.setLabelFloat(true);
        s_citizenship.setPrefWidth(280);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(s_citizenship);
        new AutoCompleteComboBoxListener(s_citizenship);
        contactDetails.add(s_citizenship, 0, 0);
        
        s_email = new JFXTextField();
        s_email.setPromptText("Email Address");
        s_email.setLabelFloat(true);
        s_email.setPrefWidth(280);
        contactDetails.add(s_email, 1, 0);
        
        s_postalAddress = new JFXTextArea();
        s_postalAddress.setPromptText("Postal Address");
        s_postalAddress.setLabelFloat(true);
        s_postalAddress.setPrefWidth(280);
        s_postalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(s_postalAddress, "Required");
        contactDetails.add(s_postalAddress, 0, 1);
        
        s_physicalAddress = new JFXTextArea();
        s_physicalAddress.setPromptText("Physical Address");
        s_physicalAddress.setLabelFloat(true);
        s_physicalAddress.setPrefWidth(280);
        s_physicalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(s_physicalAddress, "Required");
        contactDetails.add(s_physicalAddress, 1, 1);
        
        return contactDetails;
    }
    
    private GridPane getStudentDetails(){
    
        GridPane studentDetails = new GridPane();
        studentDetails.getStyleClass().add("container");
        studentDetails.setStyle("-fx-padding:25 20 20 20;");
        studentDetails.setVgap(20);
        studentDetails.setHgap(20);
        
        s_fname = new JFXTextField();
        s_fname.setPromptText("First Name");
        s_fname.setLabelFloat(true);
        s_fname.setPrefWidth(280);
        CCValidator.setFieldValidator(s_fname, "Required");
        studentDetails.add(s_fname, 0, 0);
        
        s_mname = new JFXTextField();
        s_mname.setPromptText("Middle Name");
        s_mname.setLabelFloat(true);
        s_mname.setPrefWidth(280);
        studentDetails.add(s_mname, 1, 0);
        
        s_lname = new JFXTextField();
        s_lname.setPromptText("Surname");
        s_lname.setLabelFloat(true);
        CCValidator.setFieldValidator(s_lname, "Required");
        studentDetails.add(s_lname, 0, 1);
        
        s_assignedClass = new JFXComboBox<>(AdminQuery.getClassNames());
        s_assignedClass.setPromptText("Assigned Class");
        s_assignedClass.setLabelFloat(true);
        s_assignedClass.setPrefWidth(280);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(s_assignedClass);
        new AutoCompleteComboBoxListener(s_assignedClass);
        studentDetails.add(s_assignedClass, 1, 1);   
        
        s_lastSchool = new JFXComboBox<>();
        s_lastSchool.setPromptText("Last School Attended");
        s_lastSchool.setLabelFloat(true);
        s_lastSchool.setPrefWidth(280);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(s_lastSchool);
        new AutoCompleteComboBoxListener(s_lastSchool);
        studentDetails.add(s_lastSchool, 0, 2);
        
        s_pslGrade = new JFXComboBox<>(FXCollections.observableArrayList("A*", "A", "B",
                "C", "D", "E", "U"));
        s_pslGrade.setPromptText("PSLE Grade");
        s_pslGrade.setLabelFloat(true);
        s_pslGrade.setPrefWidth(280);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(s_pslGrade);
        new AutoCompleteComboBoxListener(s_pslGrade);
        studentDetails.add(s_pslGrade, 1, 2);
        
        s_dob = new JFXDatePicker();
        s_dob.setPromptText("Date of birth");
        s_dob.setPrefWidth(280);
        studentDetails.add(s_dob, 0, 3);
        
        s_enrollmentDate = new JFXDatePicker();
        s_enrollmentDate.setPromptText("Enrolment Date");
        s_enrollmentDate.setPrefWidth(280);
        studentDetails.add(s_enrollmentDate, 1, 3);
        
        HBox gender = new HBox(10);
        gender.setAlignment(Pos.CENTER_LEFT);
        s_male = new JFXRadioButton("Male");
        s_female = new JFXRadioButton("Female");
        
        ToggleGroup tg = new ToggleGroup();
        s_male.setToggleGroup(tg);
        s_female.setToggleGroup(tg);
        tg.selectToggle(s_male);
        
        gender.getChildren().addAll(s_male, s_female);
        studentDetails.add(gender, 0, 4, 2, 1);
        
        return studentDetails;
    }
    
    /**
     * 
     * @return 
     */
    public String generateStudentID(){
        
        Date date = new Date();
        String str = String.format("%tc", date);
        String st[] = str.split(" ");
        
        String tym[] = st[3].split(":");
        
        return(st[1].toUpperCase()+""+st[2]+""+st[5].substring(2)+""+tym[0]+""+tym[1]+""+tym[2]);
    }
    
}
