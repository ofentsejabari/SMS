package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.AutoCompleteComboBoxListener;
import entry.CCValidator;
import entry.HSpacer;
import entry.SMS;
import entry.ToolTip;
import static entry.control.MainUIFXMLController.PARENT_STACK_PANE;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import mysqldriver.AdminQuery;
import static entry.SMS.getGraphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author ofentse
 */
public class UpdateStudentProfile extends JFXDialog{
    
    public static Student student = null;
    private static File picture;
    

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
        
        
        
        //-- Profile Picture ---------------------------------------------------
        
        
        Image place_holder = new Image(SMS.class.getResourceAsStream("icons/u9.png"));
        
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
        
        
        VBox con = new VBox(studentProfilePic, update);
        con.setSpacing(10);
        con.setAlignment(Pos.TOP_CENTER);
        con.setPadding(new Insets(10, 20, 10, 20));
        con.getStyleClass().add("profile-view");
        
        pane.setLeft(con);
        
        //-- End Profile Picture -----------------------------------------------
        
        //----------------------------------------------------------------------
        GridPane contentGrid = new GridPane();
        //contentGrid.getStyleClass().add("container");
        contentGrid.setStyle("-fx-padding:10;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(10);
        
        contentGrid.add(SMS.setBorderContainer(getStudentDetails(), "Student Details"), 0, 0);
        contentGrid.add(SMS.setBorderContainer(getStudentContactDetails(), "Student Contacts"), 1, 0);
        
        contentGrid.add(SMS.setBorderContainer(getParentDetails(), "Parent/Guardian Details"), 2, 0);
        contentGrid.add(SMS.setBorderContainer(getParentContactDetails(), "Parent/Guardian Contacts"), 3, 0);
        //----------------------------------------------------------------------
        
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save"));
        save.setOnAction((ActionEvent event) -> {
            
            /*if(!"".equals(name.getText().trim())){
                
                if(stream != null){
                    stream.setDescrption(name.getText().trim());
                    
                    if(AdminQuery.updateStream(stream, true)){
                        new DialogUI("Stream details has been updated successfully",
                        DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();
                        streamClassesController.sws.restart();
                        close();
                    }else{
                        new DialogUI("Exception occurred while trying to update stream details",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();
                    }
                }else{
                
                    Stream strm = new Stream("0", name.getText().trim());
                    
                    if(!AdminQuery.isStreamExists(strm)){
                        
                        if(AdminQuery.updateStream(strm, false)){
                            new DialogUI("New stream has been added successfully",
                            DialogUI.SUCCESS_NOTIF, PARENT_STACK_PANE, this).show();

                            streamClassesController.sws.restart();
                            close();
                        }else{
                            new DialogUI("Exception occurred while trying to add new stream",
                            DialogUI.ERROR_NOTIF, stackPane, null).show();
                        }
                    }else{
                           new DialogUI("Stream already exists...",
                            DialogUI.ERROR_NOTIF, stackPane, null).show();
                       }
                }
            }else{
                name.validate();
                new DialogUI( "Ensure that mandatory field are filled up... ",
                    DialogUI.ERROR_NOTIF, stackPane, null).show();
            }*/
        });  
            
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.getStyleClass().add("secondary-toolbar");
        pane.setBottom(footer);
        
        contentGrid.add(footer, 0, 1, 4, 1);
        
        pane.setCenter(contentGrid);
        
        if(student != null){
            title.setText("Update Student");
        }
        
        
        root.getChildren().add(pane);
            
        setDialogContainer(PARENT_STACK_PANE);
        setContent(root);
        setOverlayClose(false);
        show();
        
    }
    
    private GridPane getParentContactDetails(){
    
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:25 10 10 10;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(10);
        
        JFXTextField email = new JFXTextField();
        email.setPromptText("Email Address");
        CCValidator.setFieldValidator(email, "Required");
        email.setLabelFloat(true);
        email.setPrefWidth(210);
        contactDetails.add(email, 0, 0);
        
        JFXTextField telephone = new JFXTextField();
        telephone.setPromptText("Telephone");
        CCValidator.setFieldValidator(telephone, "Required");
        telephone.setLabelFloat(true);
        telephone.setPrefWidth(210);
        contactDetails.add(telephone, 0, 1);
        
        
        JFXTextField cellphone = new JFXTextField();
        cellphone.setPromptText("Cellphone");
        cellphone.setLabelFloat(true);
        cellphone.setPrefWidth(210);
        CCValidator.setFieldValidator(cellphone, "Required");
        contactDetails.add(cellphone, 0, 2);
        
        JFXTextArea ppostalAddress = new JFXTextArea();
        ppostalAddress.setPromptText("Postal Address");
        ppostalAddress.setLabelFloat(true);
        ppostalAddress.setPrefWidth(210);
        ppostalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(ppostalAddress, "Required");
        contactDetails.add(ppostalAddress, 0, 3);
        
        
        JFXTextArea pphysicalAddress = new JFXTextArea();
        pphysicalAddress.setPromptText("Physical Address");
        pphysicalAddress.setLabelFloat(true);
        pphysicalAddress.setPrefWidth(210);
        pphysicalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(pphysicalAddress, "Required");
        contactDetails.add(pphysicalAddress, 0, 5);
        
        
        return contactDetails;
    }
    
    private GridPane getParentDetails(){
    
        GridPane parentDetails = new GridPane();
        parentDetails.getStyleClass().add("container");
        parentDetails.setStyle("-fx-padding:25 10 10 10;");
        parentDetails.setVgap(20);
        parentDetails.setHgap(10);
        
        JFXTextField fname = new JFXTextField();
        fname.setPromptText("First Name");
        fname.setLabelFloat(true);
        fname.setPrefWidth(210);
        CCValidator.setFieldValidator(fname, "Required");
        parentDetails.add(fname, 0, 0);
        
        JFXTextField mname = new JFXTextField();
        mname.setPromptText("Middle Name");
        mname.setLabelFloat(true);
        parentDetails.add(mname, 0, 1);
        
        
        JFXTextField lname = new JFXTextField();
        lname.setPromptText("Surname");
        lname.setLabelFloat(true);
        CCValidator.setFieldValidator(lname, "Required");
        parentDetails.add(lname, 0, 2);
        
        
        JFXTextField identity = new JFXTextField();
        identity.setPromptText("Omang/ Passport Number");
        identity.setLabelFloat(true);
        CCValidator.setFieldValidator(identity, "Required");
        parentDetails.add(identity, 0, 3);
        
        JFXComboBox educationLevel = new JFXComboBox<>();
        educationLevel.setPromptText("Education Level Attained");
        educationLevel.setLabelFloat(true);
        educationLevel.setPrefWidth(210);
        new AutoCompleteComboBoxListener(educationLevel);
        parentDetails.add(educationLevel, 0, 4);   
        
        JFXComboBox occupation = new JFXComboBox<>();
        occupation.setPromptText("Occupation");
        occupation.setLabelFloat(true);
        occupation.setPrefWidth(210);
        new AutoCompleteComboBoxListener(occupation);
        parentDetails.add(occupation, 0, 5);
        
        return parentDetails;
    }
    
    private GridPane getStudentContactDetails(){
    
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:10 10 10 10;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(10);
        
        JFXComboBox citizenship = new JFXComboBox<>(
                FXCollections.observableArrayList("Botswana", "South Africa",
                "Lesotho", "Zambia", "Zimbaqwe", "Namibia", "Swaziland"));
        
        citizenship.setPromptText("Citizenship");
        citizenship.setLabelFloat(true);
        citizenship.setPrefWidth(210);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(citizenship);
        new AutoCompleteComboBoxListener(citizenship);
        
        
        JFXTextField email = new JFXTextField();
        email.setPromptText("Email Address");
        email.setLabelFloat(true);
        email.setPrefWidth(210);
        contactDetails.add(email, 0, 1);
        
        JFXTextArea spostalAddress = new JFXTextArea();
        spostalAddress.setPromptText("Postal Address");
        spostalAddress.setLabelFloat(true);
        spostalAddress.setPrefWidth(210);
        spostalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(spostalAddress, "Required");
        contactDetails.add(spostalAddress, 0, 2);
        
        
        JFXTextArea sphysicalAddress = new JFXTextArea();
        sphysicalAddress.setPromptText("Physical Address");
        sphysicalAddress.setLabelFloat(true);
        sphysicalAddress.setPrefWidth(210);
        sphysicalAddress.setPrefRowCount(3);
        CCValidator.setFieldValidator(sphysicalAddress, "Required");
        contactDetails.add(sphysicalAddress, 0, 3);
           
        JFXTabPane jfxtp = new JFXTabPane();
        jfxtp.getStyleClass().addAll("jfx-tab-flatpane", "jfx-tab-flatpaneUI");
        
        Tab sen = new Tab("SEN");
        
        
        JFXTextArea sposa = new JFXTextArea();
        sposa.setPromptText("Special Education Need");
        sposa.setLabelFloat(true);
        sposa.setPrefWidth(200);
        sposa.setPrefRowCount(3);
        
        VBox vb1 = new VBox(sposa);
        vb1.setStyle("-fx-padding: 20 5 5 5;");
        
        sen.setContent(vb1);
        
        Tab sws = new Tab("SWS");
        
        JFXTextArea sphya = new JFXTextArea();
        sphya.setPromptText("Social Welfare Support");
        sphya.setLabelFloat(true);
        sphya.setPrefWidth(200);
        sphya.setPrefRowCount(3);
        
        VBox vb = new VBox(sphya);
        vb.setStyle("-fx-padding: 20 5 20 5;");
        
        sws.setContent(vb);
        
        jfxtp.getTabs().addAll(sen, sws);
        
        contactDetails.add(jfxtp,  0, 0);
        contactDetails.add(citizenship, 0, 4);
       
        return contactDetails;
    }
    
    private GridPane getStudentDetails(){
    
        GridPane studentDetails = new GridPane();
        studentDetails.getStyleClass().add("container");
        studentDetails.setStyle("-fx-padding:25 10 10 10;");
        studentDetails.setVgap(20);
        studentDetails.setHgap(10);
        
        JFXTextField fname = new JFXTextField();
        fname.setPromptText("First Name");
        fname.setLabelFloat(true);
        fname.setPrefWidth(210);
        CCValidator.setFieldValidator(fname, "Required");
        studentDetails.add(fname, 0, 0);
        
        JFXTextField mname = new JFXTextField();
        mname.setPromptText("Middle Name");
        mname.setLabelFloat(true);
        studentDetails.add(mname, 0, 1);
        
        
        JFXTextField lname = new JFXTextField();
        lname.setPromptText("Surname");
        lname.setLabelFloat(true);
        CCValidator.setFieldValidator(lname, "Required");
        studentDetails.add(lname, 0, 2);
        
        JFXComboBox assignedClass = new JFXComboBox<>(AdminQuery.getClassNames());
        assignedClass.setPromptText("Assigned Class");
        assignedClass.setLabelFloat(true);
        assignedClass.setPrefWidth(210);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(assignedClass);
        new AutoCompleteComboBoxListener(assignedClass);
        studentDetails.add(assignedClass, 0, 3);   
        
        JFXComboBox lastSchool = new JFXComboBox<>();
        lastSchool.setPromptText("Last School Attended");
        lastSchool.setLabelFloat(true);
        lastSchool.setPrefWidth(210);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(lastSchool);
        new AutoCompleteComboBoxListener(lastSchool);
        studentDetails.add(lastSchool, 0, 4);
        
        
        JFXComboBox pslGrade = new JFXComboBox<>(FXCollections.observableArrayList("A*", "A", "B",
                "C", "D", "E", "U"));
        pslGrade.setPromptText("PSLE Grade");
        pslGrade.setLabelFloat(true);
        pslGrade.setPrefWidth(210);
        AutoCompleteComboBoxListener.setAutoCompleteValidator(pslGrade);
        new AutoCompleteComboBoxListener(pslGrade);
        studentDetails.add(pslGrade, 0, 5);
        
        
        JFXDatePicker dob = new JFXDatePicker();
        dob.setPromptText("Date of birth");
        dob.setPrefWidth(210);
        studentDetails.add(dob, 0, 6);
        
        JFXDatePicker enrollmentDate = new JFXDatePicker();
        enrollmentDate.setPromptText("Enrolment Date");
        enrollmentDate.setPrefWidth(210);
        studentDetails.add(enrollmentDate, 0, 7);
        
        HBox gender = new HBox(10);
        gender.setAlignment(Pos.CENTER);
        JFXRadioButton male = new JFXRadioButton("Male");
        JFXRadioButton female = new JFXRadioButton("Female");
        
        ToggleGroup tg = new ToggleGroup();
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);
        tg.selectToggle(male);
        
        gender.getChildren().addAll(male, female);
        studentDetails.add(gender, 0, 8);
        
        return studentDetails;
    }
    
    
}
