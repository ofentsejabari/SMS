package entry.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.octicons.OctIcon;
import employeemanagement.HumanResourceManagement;
import entry.EventsNotification;
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
import messanger.MessangerManagement;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import static entry.SMS.getIcon;
import entry.ToolTip;
import eventcalendar.JBEventCalendar;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.controlsfx.tools.Borders;

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
    private JFXButton btn_toolbar_help, drawer;
    
    @FXML    
    private StackPane drawerStackPane;
    
    @FXML
    private HBox footer;
     
    public Image imageHolder;
    public static StackPane PARENT_STACK_PANE;
    
    AnchorPane drawerContent;
    
    
    public static BorderPane studentManagement, employeeManagement, 
                             inventoryManagement, admin, messangerUI;
    public static JFXDrawersStack drawerStack;
    public static JFXDrawer jFXDrawer;
    
    public static BorderPane dashboard;
    
    public JFXTabPane jFXTabPane;
    
    @FXML
    private JFXButton btn_toolbar_badge1, btn_toolbar_badge2;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        drawer.setText(""); 
        btn_toolbar_help.setGraphic(SMS.getGraphics(OctIcon.QUESTION, "text-white", 20));
        
        drawer.setGraphic(SMS.getGraphics(MaterialDesignIcon.ARROW_RIGHT_BOLD, "text-white", 20));
        
        btn_toolbar_badge1.setGraphic(SMS.getGraphics(MaterialDesignIcon.COMMENT_MULTIPLE_OUTLINE,
                                      "text-white", 20));
        btn_toolbar_badge1.setText("");
        
        btn_toolbar_badge2.setGraphic(SMS.getGraphics(FontAwesomeIcon.BELL_ALT, "text-white", 20));
        btn_toolbar_badge2.setText("");
        btn_toolbar_badge2.setOnAction((ActionEvent event) -> {
            new EventsNotification(btn_toolbar_badge2);
        });
        
        
        
        
        
        //-- Update profile picture --
        imageHolder = getIcon("user.png").getImage();
        profile_picture.setFill(new ImagePattern(imageHolder));
        
        //-- Modules (Student Management, Inventory, ) --
        studentManagement   = new StudentManagement();
        employeeManagement  = new HumanResourceManagement();
        inventoryManagement = new InventoryManagement();
        admin               = new SchoolAdministartion();
        messangerUI         = new MessangerManagement();
        
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
                //.title("Event Calendar")
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
            AnchorPane pane  = FXMLLoader.load(getClass().getResource("/entry/view/Tile.fxml"));
            fpane.getChildren().add(pane);
            AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/entry/view/Tile_1.fxml"));
            fpane.getChildren().add(pane1);
            AnchorPane pane2 = FXMLLoader.load(getClass().getResource("/entry/view/Tile_2.fxml"));
            fpane.getChildren().add(pane2);
            AnchorPane pane3 = FXMLLoader.load(getClass().getResource("/entry/view/Tile_3.fxml"));
            fpane.getChildren().add(pane3);

            drawerContent = FXMLLoader.load(getClass().getResource("/entry/view/NavigationDrawer.fxml"));
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        jFXTabPane = new JFXTabPane();
        Tab db          = new Tab("Dashboard", dashboard);
        db.setGraphic(SMS.getGraphics(MaterialDesignIcon.ARCHIVE, "text-indigo", 24));
        
        Tab emlm        = new Tab("Employees", employeeManagement);
        emlm.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "text-indigo", 24));
        emlm.setTooltip(new ToolTip("ihs chisd b isubshdihisdhif sdis df"
                + "sd fhsiodhfhsoihsodifh sdhohsdf "
                + "sdf sohdf iohso foishofho shdfsho sd sdoifhos dfoihs fsf"
                + "sd fhosifho sodfhos dfs f", 300, 100,
                SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "text-indigo", 54)));
        
        
        Tab stdm        = new Tab("Students", studentManagement);
        stdm.setGraphic(SMS.getGraphics(FontAwesomeIcon.GRADUATION_CAP, "text-indigo", 24));
        
        Tab invm        = new Tab("Inventory", inventoryManagement);
        invm.setGraphic(SMS.getGraphics(MaterialDesignIcon.ARCHIVE, "text-indigo", 24));
        
        Tab lbm         = new Tab("Administration", admin);
        lbm.setGraphic(SMS.getGraphics(FontAwesomeIcon.GEARS, "text-indigo", 24));
        
        Tab finm        = new Tab("Finance", null);
        finm.setGraphic(SMS.getGraphics(MaterialDesignIcon.ACCOUNT_NETWORK, "text-indigo", 24));
        
        Tab messanger   = new Tab("Messanger", messangerUI);
        messanger.setGraphic(SMS.getGraphics(MaterialDesignIcon.MESSAGE_BULLETED, "text-indigo", 24));
        
        Tab timetable   = new Tab("Timetable", messangerUI);
        timetable.setGraphic(SMS.getGraphics(MaterialDesignIcon.CALENDAR_CLOCK, "text-indigo", 24));
        
        jFXTabPane.getTabs().addAll(db,  lbm, stdm, emlm, invm, finm, timetable, messanger);
        
        
        
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
        
        drawerStackPane.getChildren().add(drawerStack);
        
        
        footer.getChildren().add(new Footer());
        
//        badge.getStyleClass().add("icons-badge");
        
//        Gauge gauge = GaugeBuilder.create().skinType(Gauge.SkinType.SLIM)
//                          .barColor(Color.RED)
//                          .decimals(0)
//                          .maxValue(100).value(20)
//                          .unit("TOTAL STUDENTS")
//                          .build();
//        
//        Gauge gauge1 = GaugeBuilder.create().skinType(Gauge.SkinType.DASHBOARD)
//                          .barColor(Color.GREEN)
//                          .decimals(0)
//                          .maxValue(100)
//                          .unit("UNIT").value(50)
//                          .build();
//        gauge1.setAnimated(true);
//        gauge1.setAnimationDuration(1000);
//        
//        students.getChildren().addAll(gauge, gauge1);
        
    }   
    
     
}
