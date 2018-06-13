package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
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
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import static entry.SMS.getGraphics;
import javafx.scene.layout.VBox;

/**
 *
 * @author ofentse
 */
public class UpdateGaurdianProfile extends JFXDialog{
    
    public Guardian guardian = null;
    
    private JFXTextArea p_postalAddress, p_physicalAddress;
    private JFXTextField p_fname, p_mname, p_lname, p_identity,
                       p_email, p_telephone, p_cellphone, p_relationship;
    private JFXComboBox<String> p_occupation, p_educationLevel;
       
    /**
     * 
     * @param guardian
     * @param studentID - required if you are adding a new guardian to 
     */
    public UpdateGaurdianProfile(Guardian guardian, String studentID){
        this.guardian = guardian;
        
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
        
        Label title = new Label("Add Student Guardian");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        pane.setTop(toolBar);
        
        //----------------------------------------------------------------------
        GridPane contentGrid = new GridPane();
        contentGrid.setStyle("-fx-padding:10;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(10);
        
        contentGrid.add(SMS.setBorderContainer(getParentDetails(), "Persornal Details"), 0, 0);
        contentGrid.add(SMS.setBorderContainer(getParentContactDetails(), "Contact Details"), 0, 1);
        //----------------------------------------------------------------------
        
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save"));
        save.setOnAction((ActionEvent event) -> {
            
            if(guardian != null){ //-- update --
                
                if(isValidateInputs()){
                    
                    guardian.setFirstName(p_fname.getText().trim());
                    guardian.setLastName(p_lname.getText().trim());
                    guardian.setIdentity(p_identity.getText().trim());
                    guardian.setMiddleName(p_mname.getText().trim());
                    guardian.setRelation(p_relationship.getText().trim());
                    guardian.setEmail(p_email.getText().trim());
                    guardian.setTelephone(p_telephone.getText().trim());
                    guardian.setCellphone(p_cellphone.getText().trim());
                    guardian.setPostalAddress(p_postalAddress.getText().trim());
                    guardian.setPhysicalAddress(p_physicalAddress.getText().trim());
                    guardian.setOccupation((p_occupation.getValue() != null)? p_occupation.getValue():"");
                    guardian.setEducation((p_educationLevel.getValue() != null)? p_educationLevel.getValue():"");
                    
                    if(SMS.dbHandler.updateGaurdianProfile(guardian, true)){
                        new JFXAlert(JFXAlert.SUCCESS, "Update Successful", 
                            " Gaurdian details has been updated successfully");
                    }else{
                        new JFXAlert(JFXAlert.ERROR, "Update Failed", 
                            " An error occured while trying to update gaurdian details.");
                    }
                    
                }else{
                    new JFXAlert(JFXAlert.ERROR, "Input Capture Error", 
                            " Please ensure that required fields are captured, before trying to update"
                          + " gaurdian information");
                }
            }else{//-- create new --
                if(isValidateInputs()){
                    Guardian parent = new Guardian();
                    parent.setStudentID(studentID);
                    parent.setFirstName(p_fname.getText().trim());
                    parent.setLastName(p_lname.getText().trim());
                    parent.setMiddleName(p_mname.getText().trim());
                    parent.setRelation(p_relationship.getText().trim());
                    parent.setIdentity(p_identity.getText().trim());
                    parent.setEmail(p_email.getText().trim());
                    parent.setTelephone(p_telephone.getText().trim());
                    parent.setCellphone(p_cellphone.getText().trim());
                    parent.setPostalAddress(p_postalAddress.getText().trim());
                    parent.setPhysicalAddress(p_physicalAddress.getText().trim());
                    parent.setOccupation((p_occupation.getValue() != null)? p_occupation.getValue():"");
                    parent.setEducation((p_educationLevel.getValue() != null)? p_educationLevel.getValue():""); 
                    
                    if(SMS.dbHandler.updateGaurdianProfile(parent, false)){
                        new JFXAlert(JFXAlert.SUCCESS, "Update Successful", 
                            " Gaurdian details has been added successfully");
                        close();
                    }else{
                        new JFXAlert(JFXAlert.ERROR, "Update Failed", 
                            " An error occured while trying to add gaurdian details.");
                    }
                    
                }else{
                    new JFXAlert(JFXAlert.ERROR, "Input Capture Error", 
                            "Please ensure that required fields are captured,"
                          + " before trying to add new gaurdian information to the system.");
                    
                    
                }
            }
            
        });  
            
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        pane.setBottom(footer);
        
        pane.setCenter(contentGrid);
        
        if(guardian != null){
            
            title.setText("Update Gaurdian Details - "+guardian.getIdentity());
            
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
        //root.setPrefSize(650, 350);
            
        setDialogContainer(PARENT_STACK_PANE);
        setContent(root);
        setOverlayClose(false);
        show();
        
    }
    
    
    
    /**
     * if one of the mandatory fields is empty
     * @return false, true otherwise.
     */
    private boolean isValidateInputs(){
        
        if(p_fname.getText().trim().equals("") || p_lname.getText().trim().equals("") ||
            p_email.getText().trim().equals("") || p_identity.getText().trim().equals("") ||
            p_telephone.getText().trim().equals("") || p_cellphone.getText().trim().equals("") || 
            p_postalAddress.getText().trim().equals("") || p_physicalAddress.getText().trim().equals("")){
            
            p_fname.validate(); p_lname.validate(); p_identity.validate(); p_email.validate();
            p_telephone.validate(); p_cellphone.validate(); p_postalAddress.validate();
            p_physicalAddress.validate(); 
            
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
        p_email.setPrefWidth(270);
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
    
}
