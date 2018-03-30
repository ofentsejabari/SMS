package studentmanagement;

import com.jfoenix.controls.JFXPopup;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.PopupButton;
import entry.SMS;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author JABARI
 */
public class ExportMenu extends JFXPopup{

    public ExportMenu(Node owner) {
        
        VBox container = new VBox();
        container.setSpacing(5);
        
        setPopupContent(container);
        setAutoHide(true);
        show(owner, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.LEFT);
                
        PopupButton pdf = new PopupButton("Portable Document Format(PDF)", SMS.getIcon("PDF_96px.png", 24));
        pdf.setPrefWidth(240);
        pdf.setOnAction((ActionEvent event) -> {
            hide();
        });
        
        
        PopupButton xls = new PopupButton("Excel Document (xls)", SMS.getIcon("XLS_96px.png", 24));
        xls.setPrefWidth(240);
        xls.setOnAction((ActionEvent event) -> {
            hide();
        });
        
        
        PopupButton txt = new PopupButton("Plain Text File (txt)", SMS.getIcon("TXT_96px.png", 24));
        txt.setPrefWidth(240);
        txt.setOnAction((ActionEvent event) -> {
            hide();
        });
        
        
        
        
        container.getChildren().addAll(new HBox(SMS.getIcon("FAQ_96px.png")), pdf, xls, txt);
    }
       
}
