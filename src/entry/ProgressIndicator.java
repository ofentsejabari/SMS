package entry;

import com.jfoenix.controls.JFXSpinner;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author ofentse
 */
public class ProgressIndicator extends BorderPane{

    public ProgressIndicator(String headerLabel, String label){
        
        Region veil = new Region();
        veil.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8)");
        
        Label lb = new Label(headerLabel);
        lb.setStyle("-fx-text-fill:orangered; -fx-font: 18 'Segoe UI Light'");
        
        Label lb2 = new Label(label);
        lb2.setStyle("-fx-text-fill: #515151 ;-fx-font-weight:bold;");
        
        JFXSpinner spinner = new JFXSpinner();
        
        VBox con = new VBox(lb, lb2, spinner);//SchoolAdmin.getIcon("preloader_1.gif", 94));
        con.setSpacing(5);
        con.setAlignment(Pos.CENTER);
        
        setCenter(con);
    }
}
