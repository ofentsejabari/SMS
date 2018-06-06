package studentmanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.getGraphics;
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

/**
 *
 * @author ofentse
 */
public class StudentProfileStage extends JFXDialog{
    
    public static Student student = null;
    private static File picture;
    

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

        Label fullname = new Label(student.getFullNameWithInitials());
        fullname.getStyleClass().add("title-label");
        
        GridPane grid = new GridPane();
        grid.setStyle("-fx-padding:20 0 0 0;");
        grid.setVgap(15);
        grid.setHgap(5);
        
        JFXTextField id = new JFXTextField(student.getId());
        id.setPromptText("Student ID");
        id.setLabelFloat(true);
        id.setPrefWidth(180);
        id.setDisable(true);
        grid.add(id, 0, 0);
        
        JFXTextField email = new JFXTextField(student.getEmail());
        email.setPromptText("Email");
        email.setLabelFloat(true);
        email.setPrefWidth(180);
        email.setDisable(true);
        grid.add(email, 0, 1);
        
        JFXTextField assignedClass = new JFXTextField(student.getEmail());
        assignedClass.setPromptText("Assigned Class");
        assignedClass.setLabelFloat(true);
        assignedClass.setPrefWidth(180);
        assignedClass.setDisable(true);
        grid.add(assignedClass, 0, 2);
        
        JFXTextField gender = new JFXTextField(student.getGender());
        gender.setPromptText("Gender");
        gender.setLabelFloat(true);
        gender.setPrefWidth(180);
        gender.setDisable(true);
        grid.add(gender, 0, 3);
        
        JFXTextField status = new JFXTextField(student.getStudentID());
        status.setPromptText("Status");
        status.setLabelFloat(true);
        status.setPrefWidth(180);
        status.setDisable(true);
        grid.add(status, 0, 4);
        
        JFXTextField dob = new JFXTextField(student.getStudentID());
        dob.setPromptText("DOB");
        dob.setLabelFloat(true);
        dob.setPrefWidth(180);
        dob.setDisable(true);
        grid.add(dob, 0, 5);
        
        JFXTextField enrollDate = new JFXTextField(student.getStudentID());
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
        
        //-- End Profile Picture -----------------------------------------------
        
        
        
        //----------------------------------------------------------------------
        BorderPane content = new BorderPane();
        content.setStyle("-fx-padding:10;");
        
        JFXTabPane jfxtp = new JFXTabPane();
        jfxtp.getStyleClass().add("jfx-tab-flatpane");
        Tab studentProfile = new Tab("Student Details");
        studentProfile.setContent(null);
        
        Tab assessment = new Tab("Student Assessments");
        Tab finance = new Tab("Finance");
        Tab parentProfile = new Tab("Parent Details");
        Tab socialWelfare = new Tab("Social Welfare Support");
        Tab specialNeeds = new Tab("Special Needs");
        
        jfxtp.getTabs().addAll(studentProfile, parentProfile, assessment, finance, specialNeeds, socialWelfare);
        
        content.setCenter(jfxtp);
        //----------------------------------------------------------------------
        pane.setCenter(content);
        
        if(student != null){
            title.setText("Student Profile");
        }
        
        root.getChildren().add(pane);
            
        setDialogContainer(PARENT_STACK_PANE);
        setContent(root);
        setOverlayClose(false);
        root.setPrefSize(900, 450);
        show();
        
    }
    
     
}
