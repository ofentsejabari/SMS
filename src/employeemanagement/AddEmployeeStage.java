/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagement;

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
import static entry.SMS.getGraphics;
import entry.ToolTip;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import mysqldriver.AdminQuery;
import mysqldriver.EmployeeQuery;
import studentmanagement.Guardian;
import studentmanagement.Student;
import static studentmanagement.StudentManagement.studentEnrolmentController;

/**
 *
 * @author MOILE
 */
public class AddEmployeeStage extends JFXDialog{

    
    public  EmployeeModel employee = null;
    private static File picture;
    
    private JFXTextArea p_postalAddress, p_physicalAddress;
    private JFXTextField p_fname, p_mname, p_lname, p_identity,
                       p_email, p_telephone, p_cellphone, p_relationship;
    private JFXComboBox<String> p_occupation, s_citizenship, p_educationLevel;
    
    private JFXTextArea e_postalAddress, e_physicalAddress;
    private JFXTextField e_fname, e_mname, e_lname, e_email;
    private JFXComboBox<String>   e_designation;
    private JFXDatePicker e_dob, e_employement;
    private JFXRadioButton e_male, e_female;
    
    //-- 
    private String id = generateEmployeeID();
    
    public AddEmployeeStage(EmployeeModel emp){
        this.employee = emp;
        
        StackPane root = new StackPane();
        BorderPane pane = new BorderPane();
        
        BorderPane centerPane = new BorderPane();
        centerPane.setCenter(pane);
        
        //-- Screen Decoration -------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Add Employee");
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
        
        if(employee != null){
            studentProfilePic.setFill(new ImagePattern(new Image(SMS.class.getResourceAsStream("icons/"+((employee.getGender().equalsIgnoreCase("Female"))?"u9":"u10")+".png"))));
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
        
        pane.setRight(con);
        
        //-- End Profile Picture -----------------------------------------------
        GridPane contentGrid = new GridPane();
        contentGrid.setStyle("-fx-padding:10;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(10);
        
        contentGrid.add(SMS.setBorderContainer(getEmployeeDetails(), "Employee Details"), 0, 0);
        contentGrid.add(SMS.setBorderContainer(getEmployeeContactDetails(), "Employee Contacts"), 0, 1);
        
        contentGrid.add(SMS.setBorderContainer(getNextOfKinDetails(), "Next Of Kin Details"), 0, 2);
        contentGrid.add(SMS.setBorderContainer(getNextOfKinContactDetails(), "Next Of Kin Contacts"), 0, 3);
        //----------------------------------------------------------------------
        centerPane.setCenter(new ScrollPane(contentGrid));
        
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save"));
        save.setOnAction((ActionEvent event) -> {
            saveChanges();
        });  
        
        pane.setCenter(centerPane);
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        centerPane.setBottom(footer);
                
        pane.setPrefSize(870, 700);
        if(employee != null){
            title.setText("Update Student - "+employee.getEmployeeID() );
            
            studentID.setText(employee.getEmployeeID());
            
            e_postalAddress.setText(employee.getPostalAddress());
            e_physicalAddress.setText(employee.getPhysicalAddress());
            e_fname.setText(employee.getFirstName());
            e_mname.setText(employee.getMiddleName());
            e_lname.setText(employee.getLastName());
            e_email.setText(employee.getEmail());
            e_designation.setValue(employee.getDesignation());
            e_dob.setValue(SMS.getLocalDate(employee.getDob()));
            
            if(employee.getGender().equalsIgnoreCase("Male")){
                e_male.setSelected(true);
            }else{
                e_female.setSelected(true);
            }
            
            //------------------------------------------------------------------
            NextOfKin kin = EmployeeQuery.getNextOfKin(employee.getEmployeeID());
            p_fname.setText(kin.getFirstName().get());
            p_lname.setText(kin.getFirstName().get());
            p_identity.setText(kin.getFirstName().get());
            p_mname.setText(kin.getFirstName().get());
            p_relationship.setText(kin.getFirstName().get());
            p_email.setText(kin.getFirstName().get());
            p_telephone.setText(kin.getFirstName().get());
            p_cellphone.setText(kin.getFirstName().get());
            p_postalAddress.setText(kin.getFirstName().get());
            p_physicalAddress.setText(kin.getFirstName().get());
            p_educationLevel.setValue(kin.getFirstName().get());
            p_occupation.setValue(kin.getFirstName().get());
        }
        
        root.getChildren().add(pane);
            
        setDialogContainer(PARENT_STACK_PANE);
        setContent(root);
        setOverlayClose(false);
        show();
        
    }

    
    
    private void saveChanges() {
        if(employee != null){ //-- update --
            
            if(isValidateInputs()){
                //-- Create student and parent models --
                employee.setNationality((s_citizenship.getValue() != null)? s_citizenship.getValue():"");
                employee.setPostalAddress((e_postalAddress.getText() != null)? e_postalAddress.getText():"");
                employee.setPhysicalAddress((e_physicalAddress.getText() != null)? e_physicalAddress.getText():"");
                employee.setDob((e_dob.getValue() != null)? e_dob.getValue().toString():"");
                employee.setEnrollDate((e_employement.getValue() != null)? e_employement.getValue().toString():"");
                employee.setFirstName(e_fname.getText().trim());
                employee.setMiddleName(e_mname.getText().trim());
                employee.setLastName(e_lname.getText().trim());
                employee.setEmail(e_email.getText().trim());
                employee.setGender(e_male.isSelected()?"Male":"Female");
                
                NextOfKin kin = EmployeeQuery.getNextOfKin(employee.getEmployeeID());
                
//                kin.setFirstName(p_fname.getText().trim());
//                kin.setSurname(p_lname.getText().trim());
//                kin.setOmang(p_identity.getText().trim());
//                kin.setEmail(p_email.getText().trim());
//                kin.setPostalAddress(p_postalAddress.getText().trim());
//                kin.setPhysicalAddress(p_physicalAddress.getText().trim());
//                kin.setTelephone(p_telephone.getText().trim());
//                kin.setCellphone(p_cellphone.getText().trim());
//                
//                if(SMS.dbHandler.updateStudentProfile(employee, kin, true)){
//                    new JFXAlert(JFXAlert.SUCCESS, "Update Successful",
//                            " Student details has been updated successfully");
//                    
//                    studentEnrolmentController.enrollUI.studentListWork.restart();
//                }else{
//                    new JFXAlert(JFXAlert.ERROR, "Update Failed",
//                            "An error encountered while trying to update student details");
//                }
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
                std.setPostalAddress((e_postalAddress.getText() != null)? e_postalAddress.getText():"");
                std.setPhysicalAddress((e_physicalAddress.getText() != null)? e_physicalAddress.getText():"");
                std.setPslegrade((e_designation.getValue() != null)? e_designation.getValue():"");
                std.setDob((e_dob.getValue() != null)? e_dob.getValue().toString():"");
                std.setEnrollDate((e_employement.getValue() != null)? e_employement.getValue().toString():"");
                std.setFirstName(e_fname.getText().trim());
                std.setMiddleName(e_mname.getText().trim());
                std.setLastName(e_lname.getText().trim());
                std.setEmail(e_email.getText().trim());
                std.setGender(e_male.isSelected()?"Male":"Female");
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
            p_physicalAddress.getText().trim().equals("") || e_postalAddress.getText().trim().equals("") ||
            e_physicalAddress.getText().trim().equals("") || e_fname.getText().trim().equals("") ||
            e_lname.getText().trim().equals("")){
            
            p_fname.validate(); p_lname.validate(); p_identity.validate(); p_email.validate();
            p_telephone.validate(); p_cellphone.validate(); p_postalAddress.validate();
            p_physicalAddress.validate(); 
            
            e_postalAddress.validate(); e_physicalAddress.validate();
            e_fname.validate(); e_lname.validate();
            
            return false;
        }
        return true;
    }
    
    private GridPane getNextOfKinContactDetails(){
    
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
    
    private GridPane getNextOfKinDetails(){
    
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
    
    private GridPane getEmployeeContactDetails(){
    
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
        
        e_email = new JFXTextField();
        e_email.setPromptText("Email Address");
        e_email.setLabelFloat(true);
        e_email.setPrefWidth(280);
        contactDetails.add(e_email, 1, 0);
        
        e_postalAddress = new JFXTextArea();
        e_postalAddress.setPromptText("Postal Address");
        e_postalAddress.setLabelFloat(true);
        e_postalAddress.setPrefWidth(280);
        e_postalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(e_postalAddress, "Required");
        contactDetails.add(e_postalAddress, 0, 1);
        
        e_physicalAddress = new JFXTextArea();
        e_physicalAddress.setPromptText("Physical Address");
        e_physicalAddress.setLabelFloat(true);
        e_physicalAddress.setPrefWidth(280);
        e_physicalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(e_physicalAddress, "Required");
        contactDetails.add(e_physicalAddress, 1, 1);
        
        return contactDetails;
    }
    
    private GridPane getEmployeeDetails(){
    
        GridPane studentDetails = new GridPane();
        studentDetails.getStyleClass().add("container");
        studentDetails.setStyle("-fx-padding:25 20 20 20;");
        studentDetails.setVgap(20);
        studentDetails.setHgap(20);
        
        e_fname = new JFXTextField();
        e_fname.setPromptText("First Name");
        e_fname.setLabelFloat(true);
        e_fname.setPrefWidth(280);
        CCValidator.setFieldValidator(e_fname, "Required");
        studentDetails.add(e_fname, 0, 0);
        
        e_mname = new JFXTextField();
        e_mname.setPromptText("Middle Name");
        e_mname.setLabelFloat(true);
        e_mname.setPrefWidth(280);
        studentDetails.add(e_mname, 1, 0);
        
        e_lname = new JFXTextField();
        e_lname.setPromptText("Surname");
        e_lname.setLabelFloat(true);
        CCValidator.setFieldValidator(e_lname, "Required");
        studentDetails.add(e_lname, 0, 1);
        
        
        e_designation = new JFXComboBox<>(FXCollections.observableArrayList("A*", "A", "B",
                "C", "D", "E", "U"));
        e_designation.setPromptText("PSLE Grade");
        e_designation.setLabelFloat(true);
        e_designation.setPrefWidth(280);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(e_designation);
        new AutoCompleteComboBoxListener(e_designation);
        studentDetails.add(e_designation, 1, 2);
        
        e_dob = new JFXDatePicker();
        e_dob.setPromptText("Date of birth");
        e_dob.setPrefWidth(280);
        studentDetails.add(e_dob, 0, 3);
        
        e_employement = new JFXDatePicker();
        e_employement.setPromptText("Enrolment Date");
        e_employement.setPrefWidth(280);
        studentDetails.add(e_employement, 1, 3);
        
        HBox gender = new HBox(10);
        gender.setAlignment(Pos.CENTER_LEFT);
        e_male = new JFXRadioButton("Male");
        e_female = new JFXRadioButton("Female");
        
        ToggleGroup tg = new ToggleGroup();
        e_male.setToggleGroup(tg);
        e_female.setToggleGroup(tg);
        tg.selectToggle(e_male);
        
        gender.getChildren().addAll(e_male, e_female);
        studentDetails.add(gender, 0, 4, 2, 1);
        
        return studentDetails;
    }
    
    /**
     * 
     * @return 
     */
    public String generateEmployeeID(){
        
        Date date = new Date();
        String str = String.format("%tc", date);
        String st[] = str.split(" ");
        
        String tym[] = st[3].split(":");
        
        return(st[1].toUpperCase()+""+st[2]+""+st[5].substring(2)+""+tym[0]+""+tym[1]+""+tym[2]);
    }
    
}
