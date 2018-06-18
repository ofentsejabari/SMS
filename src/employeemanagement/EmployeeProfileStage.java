package employeemanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CCValidator;
import entry.HSpacer;
import entry.SMS;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
import static entry.SMS.getGraphics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import mysqldriver.EmployeeQuery;

/**
 *
 * @author ofentse
 */
public class EmployeeProfileStage extends JFXDialog{
    
    public static EmployeeModel employee = null;
    private static File picture;
    
    private JFXTextArea p_postalAddress, p_physicalAddress;
    private JFXTextField p_fname, p_mname, p_lname, p_identity,
                     p_email, p_telephone, p_cellphone, 
                    p_educationLevel;
    private JFXComboBox<String> p_relationship;
    
    
    
    private FlowPane subjectsPane;
    
    public EmployeeProfileStage(EmployeeModel  emp){
        
        employee = emp;
        
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
        
        Label title = new Label("Add Employee");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        pane.setTop(toolBar);
        
        //-- End Screen Decoration ---------------------------------------------
        
        
        //-- Profile Picture ---------------------------------------------------
        Image place_holder = new Image(SMS.class.getResourceAsStream("icons/"+((employee.getGender().equalsIgnoreCase("Female"))?"u9":"u10")+".png"));
        
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
        
        studentProfilePic.setOnMousePressed((MouseEvent event) -> {
            
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
        
        VBox con = new VBox(studentProfilePic);
        con.setAlignment(Pos.CENTER);
        con.setPadding(new Insets(10));
        con.getStyleClass().add("profile-container");

        Label fullname = new Label();
        fullname.getStyleClass().add("title-label");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-padding:20 0 0 0;");
        grid.setVgap(15);
        grid.setHgap(5);
        
        JFXTextField id = new JFXTextField();
        id.setPromptText("Employee ID");
        id.setLabelFloat(true);
        id.setPrefWidth(180);
        id.setDisable(true);
        grid.add(id, 0, 0);
        
        JFXTextField email = new JFXTextField();
        email.setPromptText("Email");
        email.setLabelFloat(true);
        email.setPrefWidth(180);
        email.setDisable(true);
        grid.add(email, 0, 1);
        
        JFXTextField assignedClass = new JFXTextField();
        assignedClass.setPromptText("Base Class");
        assignedClass.setLabelFloat(true);
        assignedClass.setPrefWidth(180);
        assignedClass.setDisable(true);
        grid.add(assignedClass, 0, 2);
        
        JFXTextField gender = new JFXTextField();
        gender.setPromptText("Gender");
        gender.setLabelFloat(true);
        gender.setPrefWidth(180);
        gender.setDisable(true);
        grid.add(gender, 0, 3);
        
        JFXTextField status = new JFXTextField();
        status.setPromptText("Status");
        status.setLabelFloat(true);
        status.setPrefWidth(180);
        status.setDisable(true);
        grid.add(status, 0, 4);
        
        JFXTextField dob = new JFXTextField();
        dob.setPromptText("DOB");
        dob.setLabelFloat(true);
        dob.setPrefWidth(180);
        dob.setDisable(true);
        grid.add(dob, 0, 5);
        
        JFXTextField enrollDate = new JFXTextField();
        enrollDate.setPromptText("Employement Date");
        enrollDate.setLabelFloat(true);
        enrollDate.setPrefWidth(180);
        enrollDate.setDisable(true);
        grid.add(enrollDate, 0, 6);
        
        
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.TOP_CENTER);
        leftVBox.getStyleClass().add("profile-view");
        leftVBox.getChildren().addAll(con, fullname, grid);
        
        pane.setLeft(leftVBox);
        
        if(employee != null){
            title.setText("Employee Profile - "+employee.getEmployeeID());
            
            fullname.setText(employee.getFullNameWithInitials());
            id.setText(employee.getEmployeeID());
            email.setText(employee.getEmail());
            gender.setText(employee.getGender());
            dob.setText(employee.getDob());
            enrollDate.setText(employee.getEnrollDate());
            
        }
        
        //-- End Profile Picture -----------------------------------------------
        
        BorderPane content = new BorderPane();
        content.setStyle("-fx-padding:10;");
        
        JFXTabPane jfxtp = new JFXTabPane();
        jfxtp.getStyleClass().add("jfx-tab-flatpane");
        Tab empProfile = new Tab("Employee Details");
        
        Tab nextKin = new Tab("Next Of Kin");
        nextKin.setContent(getNextOfKinDetails(employee));
        Tab appraisal = new Tab("Employee Apraisal");
        
        jfxtp.getTabs().addAll(empProfile,nextKin,appraisal);
        
        content.setCenter(jfxtp);
        //----------------------------------------------------------------------
        pane.setCenter(content);
        
        root.getChildren().add(pane);
        root.setPrefSize(1000, 520); 
        
        setDialogContainer(PARENT_STACK_PANE);
        setContent(root);
        setOverlayClose(false);
        show();
        
    }
    
   
    public  BorderPane getNextOfKinDetails(EmployeeModel emp){
        
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10));
       
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        bp.setTop(toolbar);
        NextOfKin nextofKin = EmployeeQuery.getNextOfKin(emp.getEmployeeID());
        
        
        JFXButton btn_edit = new JFXButton("Update");
        btn_edit.getStyleClass().add("jfx-tool-button");
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_CONVERT, "icon-default", 22));
        btn_edit.setOnAction((ActionEvent event) -> {
            new AddEmployeeStage(emp);
        });
        
        toolbar.getChildren().addAll( new HSpacer(), btn_edit);
        
        GridPane empDetails = new GridPane();
        empDetails.getStyleClass().add("container");
        empDetails.setStyle("-fx-padding:25 20;");
        empDetails.setVgap(20);
        empDetails.setHgap(20);
        
        p_fname = new JFXTextField();
        p_fname.setPromptText("First Name");
        p_fname.setLabelFloat(true);
        p_fname.setPrefWidth(350);
        p_fname.setDisable(true);
        empDetails.add(p_fname, 0, 0);
        
        
        
        p_lname = new JFXTextField();
        p_lname.setPromptText("Surname");
        p_lname.setLabelFloat(true);
        p_lname.setDisable(true);
        empDetails.add(p_lname, 0, 1);
        
        p_identity = new JFXTextField();
        p_identity.setPromptText("Omang/ Passport Number");
        p_identity.setLabelFloat(true);
        p_identity.setPrefWidth(350);
        p_identity.setDisable(true);
        empDetails.add(p_identity, 1, 0);
        
        
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:25 20;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(20);
        
        p_email = new JFXTextField();
        p_email.setPromptText("Email Address");
        p_email.setDisable(true);
        CCValidator.setFieldValidator(p_email, "Required");
        p_email.setLabelFloat(true);
        contactDetails.add(p_email, 0, 0);
        
        p_telephone = new JFXTextField();
        p_telephone.setPromptText("Telephone");
        p_telephone.setDisable(true);
        CCValidator.setFieldValidator(p_telephone, "Required");
        p_telephone.setLabelFloat(true);
        contactDetails.add(p_telephone, 1, 0);
        
        
        p_cellphone = new JFXTextField();
        p_cellphone.setPromptText("Cellphone");
        p_cellphone.setLabelFloat(true);
        CCValidator.setFieldValidator(p_cellphone, "Required");
        p_cellphone.setDisable(true);
        contactDetails.add(p_cellphone, 0, 1);
        
        p_relationship = new JFXComboBox();
        p_relationship.setPromptText("Relationship");
        ObservableList<String> item= FXCollections.observableArrayList();
        item.addAll("Mother","Father","Sibling","Spouse","Uncle","Aunt","GrandParent","Son","Daughter","Other");
        p_relationship.setItems(item);
        p_relationship.setLabelFloat(true);
        p_relationship.setPrefWidth(350);
        p_relationship.setDisable(true);
        contactDetails.add(p_relationship, 1, 1);
        
        p_postalAddress = new JFXTextArea();
        p_postalAddress.setPromptText("Postal Address");
        p_postalAddress.setLabelFloat(true);
        p_postalAddress.setPrefWidth(350);
        p_postalAddress.setPrefRowCount(3);
        p_postalAddress.setDisable(true);
        CCValidator.setFieldValidator(p_postalAddress, "Required");
        contactDetails.add(p_postalAddress, 0, 2);
        
        
        p_physicalAddress = new JFXTextArea();
        p_physicalAddress.setPromptText("Physical Address");
        p_physicalAddress.setLabelFloat(true);
        p_physicalAddress.setPrefWidth(350);
        p_physicalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(p_physicalAddress, "Required");
        p_physicalAddress.setDisable(true);
        contactDetails.add(p_physicalAddress, 1, 2);
        
         
        VBox con = new VBox(SMS.setBorderContainer(empDetails, "Personal Details"),
                         SMS.setBorderContainer(contactDetails, "Contacts Details"));
        con.setPadding(new Insets(10, 0, 0, 0));
        bp.setCenter(con);
        
        if(nextofKin!= null){
            
                p_postalAddress.setText(nextofKin.getPostalAddress());
                p_physicalAddress.setText(nextofKin.getPhysicalAddress());
                p_fname.setText(nextofKin.getFirstName());
                p_lname.setText(nextofKin.getSurname());
                p_identity.setText(nextofKin.getOmang());
                p_email.setText(nextofKin.getEmail());
                p_telephone.setText(nextofKin.getTelephone());
                p_cellphone.setText(nextofKin.getCellphone());
                p_relationship.setValue(nextofKin.getRelationship());
                
        
        }
        
        return bp;
    }
     
    public  BorderPane getStudentDetails(){
        
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10));
        
        subjectsPane = new FlowPane();
        subjectsPane.setOrientation(Orientation.HORIZONTAL);
        subjectsPane.setPadding(new Insets(20));
        subjectsPane.setHgap(10);
        subjectsPane.setVgap(10);
        subjectsPane.getStyleClass().add("container");
        
        //----------------------------------------------------------------------
        GridPane sports = new GridPane();
        sports.setStyle("-fx-padding:25 20;");
        sports.setVgap(20);
        sports.setHgap(20);
        
        //----------------------------------------------------------------------
        GridPane clubs = new GridPane();
        clubs.setStyle("-fx-padding:25 20;");
        clubs.setVgap(20);
        clubs.setHgap(20);
        
        
        VBox con = new VBox(SMS.setBorderContainer(subjectsPane, "Subjects"),
                            SMS.setBorderContainer(clubs, "Clubs"),
                            SMS.setBorderContainer(clubs, "Sports"));
        con.setPadding(new Insets(10, 0, 0, 0));
        bp.setCenter(con);
        
        return bp;
    }
    
    
    public void getSubjects(){
        
//        ObservableList<Subject> optional = AdminQuery.getOptionalSubjectFor(employee.getStudentID());
//        ObservableList<Subject> core = AdminQuery.getStreamSubjects(AdminQuery.getClassByID(employee.getClassID()).getStreamID(), 1);
//        
//        subjectsPane.getChildren().clear();
//        
//        for(Subject subject: core){
//            JFXButton cl = new JFXButton(subject.getDescription());
//            cl.getStyleClass().addAll("success-label");
//            subjectsPane.getChildren().add(cl);
//        } 
//        
//        for(Subject subject: optional){
//            Label cl = new Label(subject.getDescription());
//            cl.getStyleClass().addAll("warning-label");
//            subjectsPane.getChildren().add(cl);
        } 

    private boolean isValidateInputs(){
        
        if(p_fname.getText().trim().equals("") || p_lname.getText().trim().equals("") ||
            p_identity.getText().trim().equals("") || p_email.getText().trim().equals("") ||
            p_identity.getText().trim().equals("") || p_telephone.getText().trim().equals("") ||
            p_cellphone.getText().trim().equals("") || p_postalAddress.getText().trim().equals("") ||
            p_physicalAddress.getText().trim().equals("") ){
            
            p_fname.validate(); p_lname.validate(); p_identity.validate(); p_email.validate();
            p_telephone.validate(); p_cellphone.validate(); p_postalAddress.validate();
            p_physicalAddress.validate(); ;
            
            return false;
        }
        return true;
    }

    public void activateButtons(boolean value){

       p_postalAddress.setDisable(value);
       p_physicalAddress.setDisable(value);
       p_fname.setDisable(value);
       p_lname.setDisable(value);
       p_identity.setDisable(value);
       p_email.setDisable(value);
       p_telephone.setDisable(value);
       p_cellphone.setDisable(value);
       p_relationship.setDisable(value); 
    }
}
