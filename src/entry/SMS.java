package entry;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.utils.MaterialDesignIconFactory;
import de.jensd.fx.glyphs.octicons.OctIcon;
import de.jensd.fx.glyphs.octicons.utils.OctIconFactory;
import java.io.InputStream;
import java.time.LocalDate;
import javafx.animation.FadeTransition;
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
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import mysqldriver.MySQLHander;

/**
 *
 * @author ofentse
 */
public class SMS extends Application {
    
    private double initX, initY;
    public static Stage parentUI;
    public static MySQLHander dbHandler;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        dbHandler = new MySQLHander();
        
        parentUI = stage;
        parentUI.setTitle("EDU-TECH School Management System");
        Parent root = FXMLLoader.load(getClass().getResource("view/LoginView.fxml"));
        
        
        Scene scene = new Scene(root, Color.TRANSPARENT);
        scene.getStylesheets().add(SMS.class.getResource("css/style.css").toExternalForm());
        parentUI.initStyle(StageStyle.UNDECORATED);
        parentUI.setScene(scene);
        parentUI.show();
        
        root.setOnMousePressed((MouseEvent me) -> {
            initX = me.getScreenX() - parentUI.getX();
            initY = me.getScreenY() - parentUI.getY();
        });
 
        root.setOnMouseDragged((MouseEvent me) -> {
            parentUI.setX(me.getScreenX() - initX);
            parentUI.setY(me.getScreenY() - initY);
        });
        
        
        
        parentUI.setOnCloseRequest((WindowEvent event) -> {
            parentUI.close();
            System.exit(1);
        });
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
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
    
    
    // load the svg file
    InputStream svgFile =  getClass().getResourceAsStream("/afester/javafx/examples/data/Ghostscript_Tiger.svg");
//    SvgLoader loader = new SvgLoader();
//    Group svgImage = loader.loadSvg(svgFile);
//
//    // Scale the image and wrap it in a Group to make the button 
//    // properly scale to the size of the image  
//    svgImage.setScaleX(0.1);
//    svgImage.setScaleY(0.1);
//    Group graphic = new Group(svgImage);

    
    
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
    public static void setNode(StackPane stackPane, Node node) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
}
