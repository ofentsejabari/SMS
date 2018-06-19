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
import javafx.collections.ObservableList;
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
import mysqldriver.EmployeeQuery;

/**
 *
 * @author MOILE
 */
public class AddEmployeeStage extends JFXDialog{

    
    public  EmployeeModel employee = null;
    private static File picture;
    
    private JFXTextArea p_postalAddress, p_physicalAddress;
    private JFXTextField p_fname, p_mname, p_lname, p_identity,
                       p_email, p_telephone, p_cellphone;
    private JFXComboBox<String> e_citizenship, p_relationship;
    
    private JFXTextArea e_postalAddress, e_physicalAddress;
    private JFXTextField e_fname, e_mname, e_lname, e_email;
    private JFXComboBox<String>   e_designation;
    private JFXDatePicker e_dob, e_employement;
    private JFXRadioButton e_male, e_female;
    
    //-- 
    private String id = generateEmployeeID();
    
    public AddEmployeeStage(EmployeeModel emp){
        this.employee = emp;
        //
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
        
        Image place_holder = new Image(SMS.class.getResourceAsStream("icons/u2.png"));
        
        Circle employeeProfilePic = new Circle(45);
        employeeProfilePic.setTranslateX(0);
        employeeProfilePic.setTranslateY(0);
        employeeProfilePic.setCenterX(30);
        employeeProfilePic.setCenterY(30);
        employeeProfilePic.setEffect(new DropShadow(3, Color.web("#EAEAEA")));
        employeeProfilePic.setStroke(Color.web("#cfd8dc"));
        employeeProfilePic.setStrokeWidth(1);
        employeeProfilePic.setFill(Color.web("#EAEAEA"));
        employeeProfilePic.setFill(new ImagePattern(place_holder));
        
        if(employee != null){
            employeeProfilePic.setFill(new ImagePattern(new Image(SMS.class.getResourceAsStream("icons/"+((employee.getGender().equalsIgnoreCase("Female"))?"u3":"u6")+".png"))));
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
          
                    employeeProfilePic.setFill(new ImagePattern(image));
                } catch (IOException ex) {

                }
            } 
        });
        
        Label employeeId = new Label(id);
        employeeId.getStyleClass().add("title-label");
        
        VBox con = new VBox(employeeProfilePic, update, employeeId);
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
            title.setText("Update Employee - "+employee.getEmployeeID() );
            
            employeeId.setText(employee.getEmployeeID());
            
            e_postalAddress.setText(employee.getPostalAddress());
            e_physicalAddress.setText(employee.getPhysicalAddress());
            e_fname.setText(employee.getFirstName());
            e_mname.setText(employee.getMiddleName());
            e_lname.setText(employee.getLastName());
            e_employement.setValue(SMS.getLocalDate(employee.getEnrollDate()));
            e_email.setText(employee.getEmail());
            e_citizenship.setValue(employee.getNationality());
            e_designation.setValue(EmployeeQuery.getEmployeeDesignation(employee.getDesignation()));
            e_dob.setValue(SMS.getLocalDate(employee.getDob()));
            
            if(employee.getGender().equalsIgnoreCase("Male")){
                e_male.setSelected(true);
            }else{
                e_female.setSelected(true);
            }
            
            //------------------------------------------------------------------
            NextOfKin kin = EmployeeQuery.getNextOfKin(employee.getEmployeeID());
            p_fname.setText(kin.getFirstName());
            p_lname.setText(kin.getSurname());
            p_identity.setText(kin.getOmang());
            p_relationship.setValue(kin.getRelationship());
            p_email.setText(kin.getEmail());
            p_telephone.setText(kin.getTelephone());
            p_cellphone.setText(kin.getCellphone());
            p_postalAddress.setText(kin.getPostalAddress());
            p_physicalAddress.setText(kin.getPhysicalAddress());
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
                //-- Create employeeand next of kin models --
                employee.setNationality((e_citizenship.getValue() != null)? e_citizenship.getValue():"");
                employee.setPostalAddress((e_postalAddress.getText() != null)? e_postalAddress.getText():"");
                employee.setPhysicalAddress((e_physicalAddress.getText() != null)? e_physicalAddress.getText():"");
                employee.setDob((e_dob.getValue() != null)? e_dob.getValue().toString():"");
                employee.setEnrollDate((e_employement.getValue() != null)? e_employement.getValue().toString():"");
                employee.setDesignation(EmployeeQuery.getEmployeeDesignationId(e_designation.getValue()));
                employee.setFirstName(e_fname.getText().trim());
                employee.setMiddleName(e_mname.getText().trim());
                employee.setLastName(e_lname.getText().trim());
                employee.setEmail(e_email.getText().trim());
                employee.setGender(e_male.isSelected()?"Male":"Female");
                
                NextOfKin kin = EmployeeQuery.getNextOfKin(employee.getEmployeeID());
                
                kin.setFirstName(p_fname.getText().trim());
                kin.setSurname(p_lname.getText().trim());
                kin.setOmang(p_identity.getText().trim());
                kin.setEmail(p_email.getText().trim());
                kin.setRelationship(p_relationship.getValue().trim());
                kin.setPostalAddress(p_postalAddress.getText().trim());
                kin.setPhysicalAddress(p_physicalAddress.getText().trim());
                kin.setTelephone(p_telephone.getText().trim());
                kin.setCellphone(p_cellphone.getText().trim());
                
                if(EmployeeQuery.updateEmployeeProfile(employee, kin, true)){
                    new JFXAlert(JFXAlert.SUCCESS, "Update Successful",
                            " Employee details has been updated successfully");
                    
                    EmployeeManagementView.employeeListWork.restart();
                }else{
                    new JFXAlert(JFXAlert.ERROR, "Update Failed",
                            "An error encountered while trying to update Employee details");
                }
            }else{
                new JFXAlert(JFXAlert.ERROR, "Input Capture Error",
                        " Please ensure that required fields are captured, before trying to update"
                                + " employee information");
            }
        }else{
            //-- create new --
            if(isValidateInputs()){
                
                EmployeeModel emp = new EmployeeModel();
                emp.setEmployeeID(id);
                emp.setDesignation(EmployeeQuery.getEmployeeDesignationId(e_designation.getValue()));
                
                emp.setNationality((e_citizenship.getValue() != null)? e_citizenship.getValue():"");
                emp.setPostalAddress((e_postalAddress.getText() != null)? e_postalAddress.getText():"");
                emp.setPhysicalAddress((e_physicalAddress.getText() != null)? e_physicalAddress.getText():"");
                emp.setDob((e_dob.getValue() != null)? e_dob.getValue().toString():"");
                emp.setEnrollDate((e_employement.getValue() != null)? e_employement.getValue().toString():"");
                emp.setFirstName(e_fname.getText().trim());
                emp.setMiddleName(e_mname.getText().trim());
                emp.setLastName(e_lname.getText().trim());
                emp.setEmail(e_email.getText().trim());
                emp.setGender(e_male.isSelected()?"Male":"Female");
                //emp.setSchoolID("20000");
                
                
                NextOfKin next = new NextOfKin();
                next.setEmployeeId(id);
                next.setFirstName(p_fname.getText().trim());
                next.setRelationship(p_relationship.getValue().trim());
                next.setSurname(p_lname.getText().trim());
                next.setOmang(p_identity.getText().trim());
                next.setEmail(p_email.getText().trim());
                next.setPostalAddress(p_postalAddress.getText().trim());
                next.setPhysicalAddress(p_physicalAddress.getText().trim());
                next.setTelephone(p_telephone.getText().trim());
                next.setCellphone(p_cellphone.getText().trim());
                
                if(EmployeeQuery.updateEmployeeProfile(emp, next, false)){
                    new JFXAlert(JFXAlert.SUCCESS, "Update Successful",
                            " Employee added successfully");
                    close();
                    EmployeeManagementView.employeeListWork.restart();
                    
                }else{
                    new JFXAlert(JFXAlert.ERROR, "Update Failed",
                            "An error encountered while trying to enroll employee.");
                }
            }else{
                
                new JFXAlert(JFXAlert.ERROR, "Input Capture Error",
                        "Please ensure that required fields are captured,"
                                + " before trying to add new employee information to the system.");
                
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
        
        p_relationship = new JFXComboBox();
        p_relationship.setPrefWidth(280);
        p_relationship.setPromptText("Relationship");
        p_relationship.setLabelFloat(true);
        ObservableList<String> item= FXCollections.observableArrayList();
        item.addAll("Mother","Father","Sibling","Spouse","Uncle","Aunt","GrandParent","Son","Daughter","Other");
        p_relationship.setItems(item);
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
    
        GridPane employeeDetails = new GridPane();
        employeeDetails.getStyleClass().add("container");
        employeeDetails.setStyle("-fx-padding:25 20;");
        employeeDetails.setVgap(20);
        employeeDetails.setHgap(20);
        
        p_fname = new JFXTextField();
        p_fname.setPromptText("First Name");
        p_fname.setLabelFloat(true);
        p_fname.setPrefWidth(280);
        CCValidator.setFieldValidator(p_fname, "Required");
        employeeDetails.add(p_fname, 0, 0);
        
        p_lname = new JFXTextField();
        p_lname.setPromptText("Surname");
        p_lname.setPrefWidth(280);
        p_lname.setLabelFloat(true);
        CCValidator.setFieldValidator(p_lname, "Required");
        employeeDetails.add(p_lname, 1, 0);
        
        p_identity = new JFXTextField();
        p_identity.setPromptText("Omang/ Passport Number");
        p_identity.setLabelFloat(true);
        CCValidator.setFieldValidator(p_identity, "Required");
        employeeDetails.add(p_identity, 0, 1);
        
        
        return employeeDetails;
    }
    
    private GridPane getEmployeeContactDetails(){
    
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:25 20;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(20);
        
         e_postalAddress = new JFXTextArea();
        e_postalAddress.setPromptText("Postal Address");
        e_postalAddress.setLabelFloat(true);
        e_postalAddress.setPrefWidth(280);
        e_postalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(e_postalAddress, "Required");
        contactDetails.add(e_postalAddress, 0, 0);
        
        e_physicalAddress = new JFXTextArea();
        e_physicalAddress.setPromptText("Physical Address");
        e_physicalAddress.setLabelFloat(true);
        e_physicalAddress.setPrefWidth(280);
        e_physicalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(e_physicalAddress, "Required");
        contactDetails.add(e_physicalAddress, 1, 0);
        
        
        e_email = new JFXTextField();
        e_email.setPromptText("Email Address");
        e_email.setLabelFloat(true);
        e_email.setPrefWidth(280);
        contactDetails.add(e_email, 0, 1);
        return contactDetails;
    }
    
    private GridPane getEmployeeDetails(){
    
        GridPane employeeDetails = new GridPane();
        employeeDetails.getStyleClass().add("container");
        employeeDetails.setStyle("-fx-padding:25 20 20 20;");
        employeeDetails.setVgap(20);
        employeeDetails.setHgap(20);
        
        e_fname = new JFXTextField();
        e_fname.setPromptText("First Name");
        e_fname.setLabelFloat(true);
        e_fname.setPrefWidth(280);
        CCValidator.setFieldValidator(e_fname, "Required");
        employeeDetails.add(e_fname, 0, 0);
        
        e_mname = new JFXTextField();
        e_mname.setPromptText("Middle Name");
        e_mname.setLabelFloat(true);
        e_mname.setPrefWidth(280);
        employeeDetails.add(e_mname, 1, 0);
        
        e_lname = new JFXTextField();
        e_lname.setPromptText("Surname");
        e_lname.setLabelFloat(true);
        CCValidator.setFieldValidator(e_lname, "Required");
        employeeDetails.add(e_lname, 0, 1);
        
        
        
        e_designation = new JFXComboBox<>();
        e_designation.setItems(EmployeeQuery.getEmployeeDesignationList());
        e_designation.setPromptText("Designation");
        e_designation.setLabelFloat(true);
        e_designation.setPrefWidth(280);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(e_designation);
        new AutoCompleteComboBoxListener(e_designation);
        employeeDetails.add(e_designation, 1, 1);
        
        e_dob = new JFXDatePicker();
        e_dob.setPromptText("Date of birth");
        e_dob.setPrefWidth(280);
        employeeDetails.add(e_dob, 0, 2);
        
        e_employement = new JFXDatePicker();
        e_employement.setPromptText("Employment Date");
        e_employement.setPrefWidth(280);
        employeeDetails.add(e_employement, 1, 2);
        
        e_citizenship = new JFXComboBox<>(
                FXCollections.observableArrayList("Botswana", "South Africa",
                "Lesotho", "Zambia", "Zimbaqwe", "Namibia", "Swaziland"));
        
        e_citizenship.setPromptText("Citizenship");
        e_citizenship.setLabelFloat(true);
        e_citizenship.setPrefWidth(280);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(e_citizenship);
        new AutoCompleteComboBoxListener(e_citizenship);
        employeeDetails.add(e_citizenship, 0, 3);
        
        
        HBox gender = new HBox(10);
        gender.setAlignment(Pos.CENTER_LEFT);
        e_male = new JFXRadioButton("Male");
        e_female = new JFXRadioButton("Female");
        
        ToggleGroup tg = new ToggleGroup();
        e_male.setToggleGroup(tg);
        e_female.setToggleGroup(tg);
        tg.selectToggle(e_male);
        
        gender.getChildren().addAll(e_male, e_female);
        employeeDetails.add(gender, 0, 4, 2, 1);
        
        return employeeDetails;
    }
    
    /**
     * 
     * @return 
     */
    public static String generateEmployeeID(){
        
        Date date = new Date();
        String str = String.format("%tc", date);
        String st[] = str.split(" ");
        
        String tym[] = st[3].split(":");
        
        return(st[2]+st[1].toUpperCase()+""+st[5].substring(2)+""+tym[0]+""+tym[1]+""+tym[2]);
    }
    
}
