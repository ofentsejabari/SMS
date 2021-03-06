package entry.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.octicons.OctIcon;
import employeemanagement.HumanResourceManagement;
import entry.Footer;
import entry.SMS;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import studentmanagement.StudentManagement;
import inventorymanagement.InventoryManagement;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import schooladministration.SchoolAdministartion;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import entry.ToolTip;
import eventcalendar.JBEventCalendar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.tools.Borders;
import static entry.SMS.getIcon;

/**
 * 
 * @author ofentse
 */
public class MainUIFXMLController implements Initializable {

    @FXML
    private Circle profile_picture;
    
    @FXML
    private BorderPane parentContainer;
    
    @FXML    
    private JFXButton btn_toolbar_help, btn_toolbar_about, drawer;
    
    @FXML    
    private StackPane drawerStackPane;
    
    @FXML
    private HBox footer;
     
    public Image imageHolder;
    public static StackPane PARENT_STACK_PANE;
    
    AnchorPane drawerContent;
    
    public static BorderPane studentManagement, employeeManagement, admin;
                             //inventoryManagement,//, messangerUI;
    public static JFXDrawersStack drawerStack;
    public static JFXDrawer jFXDrawer;
    
    public static BorderPane dashboard;
    
    public JFXTabPane jFXTabPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        drawer.setText(""); 
        btn_toolbar_help.setGraphic(SMS.getGraphics(OctIcon.QUESTION, "text-white", 20));
        
        drawer.setGraphic(SMS.getGraphics(MaterialDesignIcon.ARROW_RIGHT_BOLD, "text-info", 18));   
        
        //-- Update profile picture --
        imageHolder = getIcon("user.png").getImage();
        profile_picture.setFill(new ImagePattern(imageHolder));
        
        //-- Modules (Student Management, Inventory, ) --
        studentManagement   = new StudentManagement();
        employeeManagement  = new HumanResourceManagement();
        //inventoryManagement = new InventoryManagement();
        admin               = new SchoolAdministartion();
        //messangerUI         = new MessangerManagement();
        
        FlowPane fpane = new FlowPane(Orientation.HORIZONTAL);
        fpane.setPadding(new Insets(20));
        fpane.setAlignment(Pos.CENTER_LEFT);
        fpane.setHgap(10);
        fpane.setVgap(10);
        
        dashboard = new BorderPane(fpane);
        
        //-- Event calendar --
        JBEventCalendar calendar = new JBEventCalendar();
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(Borders.wrap(calendar)
                .lineBorder()
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .radius(5)
                .color(Color.web("#F0582F"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());

        HBox.setHgrow(vBox, Priority.ALWAYS);
        dashboard.setRight(vBox);
        
        //-- End event calendar --
        
        try {
            drawerContent = FXMLLoader.load(getClass().getResource("/entry/view/NavigationDrawer.fxml"));
            //configDrawerContent = FXMLLoader.load(getClass().getResource("/entry/view/ConfigurationNavigationDrawer.fxml"));
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        jFXTabPane = new JFXTabPane();
        Tab db = new Tab("Dashboard", dashboard);
        db.setGraphic(SMS.getGraphics(MaterialDesignIcon.ARCHIVE, "text-indigo", 24));
        
        Tab emlm = new Tab("Employees", employeeManagement);
        emlm.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "text-indigo", 24));
        emlm.setTooltip(new ToolTip("", 300, 100,
                SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "text-indigo", 54)));
        
        Tab stdm = new Tab("Students", studentManagement);
        stdm.setGraphic(SMS.getGraphics(FontAwesomeIcon.GRADUATION_CAP, "text-indigo", 24));
        
//        Tab invm = new Tab("Inventory", inventoryManagement);
//        invm.setGraphic(SMS.getGraphics(MaterialDesignIcon.ARCHIVE, "text-indigo", 24));
        
        Tab lbm = new Tab("Administration", admin);
        lbm.setGraphic(SMS.getGraphics(FontAwesomeIcon.GEARS, "text-indigo", 24));
        
        Tab finm = new Tab("Finance", null);
        finm.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "text-indigo", 24));
        
//        Tab messanger = new Tab("Messanger", messangerUI);
//        messanger.setGraphic(SMS.getGraphics(MaterialDesignIcon.MESSAGE_BULLETED, "text-indigo", 24));
        
        Tab timetable = new Tab("Timetable", null);
        timetable.setGraphic(SMS.getGraphics(MaterialDesignIcon.CALENDAR_CLOCK, "text-indigo", 24));
        
        jFXTabPane.getTabs().addAll(db,  lbm, stdm, emlm, finm, timetable);//, messanger);

        PARENT_STACK_PANE = new StackPane(jFXTabPane);
        
        parentContainer.setCenter(PARENT_STACK_PANE);
        
        drawerStack = new JFXDrawersStack();
        drawerStack.setContent(parentContainer);
        
        jFXDrawer = new JFXDrawer();
        
        jFXDrawer.setDirection(JFXDrawer.DrawerDirection.LEFT);
        jFXDrawer.setDefaultDrawerSize(200);
        jFXDrawer.setSidePane(drawerContent);
        jFXDrawer.setOverLayVisible(true);
        jFXDrawer.setResizableOnDrag(false);
        
        drawer.setOnAction((ActionEvent event) -> {
            drawerStack.toggle(jFXDrawer);
        });
        
        drawerStackPane.getChildren().addAll(drawerStack);
        
        footer.getChildren().add(new Footer());
        
    }  
    
    public StackPane getParentStackPane(){
        return drawerStackPane;
    }
}
