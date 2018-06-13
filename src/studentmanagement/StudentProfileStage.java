package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
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
import javafx.beans.value.ObservableValue;
import static entry.SMS.getGraphics;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import mysqldriver.AdminQuery;
import schooladministration.Subject;

/**
 *
 * @author ofentse
 */
public class StudentProfileStage extends JFXDialog{
    
    public static Student student = null;
    private static File picture;
    
    private JFXTextArea p_postalAddress, p_physicalAddress;
    private JFXTextField p_fname, p_mname, p_lname, p_identity,
                     p_email, p_telephone, p_cellphone, p_relationship, 
                     p_occupation, p_educationLevel;
    public JFXComboBox<String> parents;
    
    private Guardian selectedParent = null;
    private FlowPane subjectsPane;
    
    public StudentProfileStage(Student stdnt){
        
        student = stdnt;
        
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
        
        
        //-- Profile Picture ---------------------------------------------------
        Image place_holder = new Image(SMS.class.getResourceAsStream("icons/"+((student.getGender().equalsIgnoreCase("Female"))?"u9":"u10")+".png"));
        
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
        id.setPromptText("Student ID");
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
        assignedClass.setPromptText("Assigned Class");
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
        enrollDate.setPromptText("Enrolment Date");
        enrollDate.setLabelFloat(true);
        enrollDate.setPrefWidth(180);
        enrollDate.setDisable(true);
        grid.add(enrollDate, 0, 6);
        
        
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.TOP_CENTER);
        leftVBox.getStyleClass().add("profile-view");
        leftVBox.getChildren().addAll(con, fullname, grid);
        
        pane.setLeft(leftVBox);
        
        if(student != null){
            title.setText("Student Profile - "+student.getStudentID());
            
            fullname.setText(student.getFullNameWithInitials());
            id.setText(student.getStudentID());
            email.setText(student.getEmail());
            assignedClass.setText(student.getClassID());
            gender.setText(student.getGender());
            status.setText(student.getStatus());
            dob.setText(student.getDob());
            enrollDate.setText(student.getEnrollDate());
            
        }
        
        //-- End Profile Picture -----------------------------------------------
        
        BorderPane content = new BorderPane();
        content.setStyle("-fx-padding:10;");
        
        JFXTabPane jfxtp = new JFXTabPane();
        jfxtp.getStyleClass().add("jfx-tab-flatpane");
        Tab studentProfile = new Tab("Student Details");
        studentProfile.setContent(getStudentDetails());
        
        Tab assessment = new Tab("Student Assessments");
        Tab finance = new Tab("Finance");
        Tab parentProfile = new Tab("Parent Details");
        parentProfile.setContent(getParentDetails());
        Tab socialWelfare = new Tab("Social Welfare Support");
        Tab specialNeeds = new Tab("Special Needs");
        
        jfxtp.getTabs().addAll(studentProfile, parentProfile, assessment, finance, specialNeeds, socialWelfare);
        
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
    
    
    public  BorderPane getParentDetails(){
        
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        bp.setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add Guardian");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
             new UpdateGaurdianProfile(null, student.getStudentID());
        });
        
        JFXButton btn_edit = new JFXButton("Update");
        btn_edit.getStyleClass().add("jfx-tool-button");
        btn_edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_CONVERT, "icon-default", 22));
        btn_edit.setOnAction((ActionEvent event) -> {
            
            new UpdateGaurdianProfile(selectedParent, student.getStudentID());
        });
        
        parents = new JFXComboBox<>(SMS.dbHandler.getStudentGaurdians(student.getStudentID()));
        parents.setPromptText("Select Parent");
        parents.setPrefWidth(220);
        parents.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            //-- Get details of the selected parent --
            selectedParent = SMS.dbHandler.getGaurdianByName(newValue);
            
            p_postalAddress.setText(selectedParent.getPostalAddress());
            p_physicalAddress.setText(selectedParent.getPhysicalAddress());
            p_fname.setText(selectedParent.getFirstName());
            p_mname.setText(selectedParent.getMiddleName());
            p_relationship.setText(selectedParent.getRelation());
            p_lname.setText(selectedParent.getLastName());
            p_identity.setText(selectedParent.getIdentity());
            p_email.setText(selectedParent.getEmail());
            p_telephone.setText(selectedParent.getTelephone());
            p_cellphone.setText(selectedParent.getCellphone());
            p_occupation.setText(selectedParent.getOccupation());
            p_educationLevel.setText(selectedParent.getEducation());
            
        });
        
        toolbar.getChildren().addAll(parents, new HSpacer(), btn_edit, btn_add);
        
        GridPane parentDetails = new GridPane();
        parentDetails.getStyleClass().add("container");
        parentDetails.setStyle("-fx-padding:25 20;");
        parentDetails.setVgap(20);
        parentDetails.setHgap(20);
        
        p_fname = new JFXTextField();
        p_fname.setPromptText("First Name");
        p_fname.setLabelFloat(true);
        p_fname.setPrefWidth(350);
        p_fname.setDisable(true);
        parentDetails.add(p_fname, 0, 0);
        
        p_mname = new JFXTextField();
        p_mname.setPromptText("Middle Name");
        p_mname.setLabelFloat(true);
        p_mname.setDisable(true);
        parentDetails.add(p_mname, 1, 0);
        
        p_lname = new JFXTextField();
        p_lname.setPromptText("Surname");
        p_lname.setLabelFloat(true);
        p_lname.setDisable(true);
        parentDetails.add(p_lname, 0, 1);
        
        p_identity = new JFXTextField();
        p_identity.setPromptText("Omang/ Passport Number");
        p_identity.setLabelFloat(true);
        p_identity.setDisable(true);
        parentDetails.add(p_identity, 1, 1);
        
        p_educationLevel = new JFXTextField();
        p_educationLevel.setPromptText("Education Level Attained");
        p_educationLevel.setLabelFloat(true);
        p_educationLevel.setPrefWidth(350);
        p_educationLevel.setDisable(true);
        parentDetails.add(p_educationLevel, 0, 2);   
        
        p_occupation = new JFXTextField();
        p_occupation.setPromptText("Occupation");
        p_occupation.setLabelFloat(true);
        p_occupation.setPrefWidth(350);
        p_occupation.setDisable(true);
        parentDetails.add(p_occupation, 1, 2);
        
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:25 20;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(20);
        
        p_email = new JFXTextField();
        p_email.setPromptText("Email Address");
        p_email.setDisable(true);
        p_email.setLabelFloat(true);
        contactDetails.add(p_email, 0, 0);
        
        p_telephone = new JFXTextField();
        p_telephone.setPromptText("Telephone");
        p_telephone.setDisable(true);
        p_telephone.setLabelFloat(true);
        contactDetails.add(p_telephone, 1, 0);
        
        
        p_cellphone = new JFXTextField();
        p_cellphone.setPromptText("Cellphone");
        p_cellphone.setLabelFloat(true);
        p_cellphone.setDisable(true);
        contactDetails.add(p_cellphone, 0, 1);
        
        p_relationship = new JFXTextField();
        p_relationship.setPromptText("Relationship");
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
        contactDetails.add(p_postalAddress, 0, 2);
        
        
        p_physicalAddress = new JFXTextArea();
        p_physicalAddress.setPromptText("Physical Address");
        p_physicalAddress.setLabelFloat(true);
        p_physicalAddress.setPrefWidth(350);
        p_physicalAddress.setPrefRowCount(3);
        p_physicalAddress.setDisable(true);
        contactDetails.add(p_physicalAddress, 1, 2);
        
        VBox con = new VBox(SMS.setBorderContainer(parentDetails, "Personal Details"),
                         SMS.setBorderContainer(contactDetails, "Contacts Details"));
        con.setPadding(new Insets(10, 0, 0, 0));
        bp.setCenter(con);
        
        if(parents.getItems() != null && !parents.getItems().isEmpty()){parents.setValue(parents.getItems().get(0));}
        
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
        
        getSubject();
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
    
    
    public void getSubject(){
        
        ObservableList<Subject> optional = AdminQuery.getOptionalSubjectFor(student.getStudentID());
        ObservableList<Subject> core = AdminQuery.getStreamSubjects(AdminQuery.getClassByID(student.getClassID()).getStreamID(), 1);
        
        subjectsPane.getChildren().clear();
        
        for(Subject subject: core){
            JFXButton cl = new JFXButton(subject.getDescription());
            cl.getStyleClass().addAll("success-label");
            subjectsPane.getChildren().add(cl);
        } 
        
        for(Subject subject: optional){
            Label cl = new Label(subject.getDescription());
            cl.getStyleClass().addAll("warning-label");
            subjectsPane.getChildren().add(cl);
        } 
    }
}
