package entry;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.utils.MaterialDesignIconFactory;
import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.utils.OctIconFactory;
import java.time.LocalDate;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mysqldriver.MySQLHander;
import org.controlsfx.tools.Borders;

/**
 *
 * @author ofentse
 */
public class SMS extends Application {
    
    private double initX, initY;
    public static Stage parentUI;
    public static MySQLHander dbHandler;
    public static StackPane MAIN_UI;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        dbHandler = new MySQLHander();
        
        parentUI = stage;
        parentUI.setTitle("School Management System");
        
        MAIN_UI = new StackPane();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/entry/view/MainUIFXML.fxml"));
        Parent root = loader.load();
        
        MAIN_UI.getChildren().add(root);
        
        Scene scene = new Scene(MAIN_UI, Color.TRANSPARENT);
        scene.getStylesheets().add(SMS.class.getResource("css/style.css").toExternalForm());
        parentUI.setScene(scene);
        parentUI.show();
        
        MAIN_UI.setOnMousePressed((MouseEvent me) -> {
            initX = me.getScreenX() - parentUI.getX();
            initY = me.getScreenY() - parentUI.getY();
        });
 
        MAIN_UI.setOnMouseDragged((MouseEvent me) -> {
            parentUI.setX(me.getScreenX() - initX);
            parentUI.setY(me.getScreenY() - initY);
        });
        
        parentUI.setOnCloseRequest((WindowEvent event) -> {
            parentUI.close();
            Footer.serverMonitor.stop();
            System.exit(0);
        });
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static VBox setBorderContainer(Node content , String title) {
        VBox conDetails = new VBox();
        
        if(title != null){
            conDetails.getChildren().addAll(Borders.wrap(content)
                .lineBorder()
                .title(title)
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(5)
                .radius(5)
                .color(Color.web("#cfd8dc"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());
        }else{
            conDetails.getChildren().addAll(Borders.wrap(content)
                .lineBorder()
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(5)
                .radius(5)
                .color(Color.web("#cfd8dc"), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());
        }
        
        return conDetails;
    }
    
    
    public static VBox setBorderContainer(Node content, String title, String color) {
        VBox conDetails = new VBox();
                
        if(title != null){
            conDetails.getChildren().addAll(Borders.wrap(content)
                .lineBorder()
                .title(title)
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(5)
                .radius(5)
                .color(Color.web(color), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());
        }else{
            conDetails.getChildren().addAll(Borders.wrap(content)
                .lineBorder()
                .thickness(2, 1, 1, 1)
                .innerPadding(0)
                .outerPadding(5)
                .radius(5)
                .color(Color.web(color), Color.web("#EAEAEA"),
                       Color.web("#EAEAEA"), Color.web("#EAEAEA"))
                .buildAll());
        }
        
        return conDetails;
    }
    
    /**
     */
    public static ImageView getIcon(String iconName){
        ImageView img = new ImageView(new Image(SMS.class.getResourceAsStream("icons/"+iconName)));
        return img;
    }
    
    public static ImageView getIcon(String iconName, int height){
        ImageView img = new ImageView(new Image(SMS.class.getResourceAsStream("icons/"+iconName)));
        img.setFitHeight(height);
        img.setPreserveRatio(true);
        return img;
    }
    
    public static Text getGraphics(MaterialDesignIcon icon, String colorStyle, int height){
        Text graphic;
        if(height <= 0){
            graphic = MaterialDesignIconFactory.get().createIcon(icon);
        }else{
            graphic = MaterialDesignIconFactory.get().createIcon(icon, ""+height);
        }
        graphic.getStyleClass().add(colorStyle);
        return graphic;
    }
    
    public static Text getGraphics(OctIcon icon, String colorStyle, int height){
        Text graphic;
        if(height <= 0){
            graphic = OctIconFactory.get().createIcon(icon);
        }else{
            graphic = OctIconFactory.get().createIcon(icon, ""+height);
        }
        graphic.getStyleClass().add(colorStyle);
        return graphic;
    }
    
    public static Text getGraphics(FontAwesomeIcon icon, String colorStyle, int height){
        Text graphic;
        if(height <= 0){
            graphic = FontAwesomeIconFactory.get().createIcon(icon);
        }else{
            graphic = FontAwesomeIconFactory.get().createIcon(icon, ""+height);
        }
        graphic.getStyleClass().add(colorStyle);
        return graphic;
    }
    
    
    public static LocalDate getLocalDate(String dt){
        try {
            if(dt == null){
                return null; }

            if(!"".equals(dt)){
                String[] date = dt.split("-");
                int year = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                LocalDate localdate = LocalDate.of(year, month, day);

                return localdate;
            }
            return null;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    
    public static VBox setDataNotAvailablePlaceholder(){
        
        Label lb = new Label("No data available at the moment");
        lb.setStyle("-fx-text-fill:orangered; -fx-font-family:'Segoe UI Light';-fx-font-size:22");
        
        Label lb2 = new Label("No data recorded at the moment\n"
                + "(Try to refresh)");
        lb2.setStyle("-fx-text-fill: #515151 ;-fx-font-family:'Segoe UI Semibold'");
        
        VBox con = new VBox(SMS.getIcon("preloader_9.gif"), lb, lb2);
        con.setSpacing(5);
        con.setAlignment(Pos.CENTER);
        
        return con;
    }
    
    public static VBox setNoConnectionPlaceholder(){
        
        Label lb = new Label("No network connection");
        lb.setStyle("-fx-text-fill:orangered; -fx-font-family:'Segoe UI Light';-fx-font-size:22");
        
        Label lb2 = new Label("");
        lb2.setStyle("-fx-text-fill: #515151 ;-fx-font-family:'Segoe UI Semibold'");
        
        VBox con = new VBox(SMS.getIcon("preloader_1.gif"), lb, lb2);
        con.setSpacing(5);
        con.setAlignment(Pos.CENTER);
        
        return con;
    }
    
    
    //Set selected node to a content holder
//    public static void setNode(StackPane stackPane, Node node) {
//        stackPane.getChildren().clear();
//        stackPane.getChildren().add((Node) node);
//
//        FadeTransition ft = new FadeTransition(Duration.millis(1500));
//        ft.setNode(node);
//        ft.setFromValue(0.1);
//        ft.setToValue(1);
//        ft.setCycleCount(1);
//        ft.setAutoReverse(false);
//        ft.play();
//    }
    
    
    public static String generateDBID(){
       
        Date date = new Date();
        String str = String.format("%tc",date);
        String st[] = str.split(" ");
        String tym[] = st[3].split(":");
        
        return(st[2]+""+tym[0]+""+tym[1]+""+tym[2]);
    }
    
}
