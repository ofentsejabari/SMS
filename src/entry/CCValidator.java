package entry;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import static entry.SMS.getGraphics;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

/**
 *
 * @author ofentse
 */
public class CCValidator {
    
    RequiredFieldValidator rfv;

    public CCValidator() {
        rfv = new RequiredFieldValidator();
    }
    

    public  void setTextFieldValidator(JFXTextField field, String label) {
        
        
        field.getValidators().add(rfv);
        rfv.setMessage("");
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
                                             Boolean oldValue, Boolean newValue) -> {
            if(!newValue){field.validate();}
        });
        
        Label ic = new Label(label,getGraphics(MaterialDesignIcon.ALERT, "text-error", 14));
        ic.setContentDisplay(ContentDisplay.RIGHT);
        ic.getStyleClass().add("input-error-label");
        rfv.setIcon(ic);
    }
    
    
    public RequiredFieldValidator getValidator(){
        return rfv;
    }
    
    
    public  void setPasswordFieldValidator(JFXPasswordField field, String label) {
        
        field.getValidators().add(rfv);
        rfv.setMessage("");
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, 
                                             Boolean oldValue, Boolean newValue) -> {
            if(!newValue){field.validate();}
        });
        
        Label ic = new Label(label,getGraphics(MaterialDesignIcon.ALERT, "text-error", 14));
        ic.setContentDisplay(ContentDisplay.RIGHT);
        ic.getStyleClass().add("input-error-label");
        rfv.setIcon(ic);
    }
    
    
    public static void setEmailFieldValidator(JFXTextField field, String label) {
        
        Pattern VALID_EMAIL_ADDRESS_REGEX =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                                             Pattern.CASE_INSENSITIVE);
        
        RequiredFieldValidator rfv = new RequiredFieldValidator();
        field.getValidators().add(rfv);
        rfv.setMessage("");
        
        Label ic = new Label(label,getGraphics(MaterialDesignIcon.ALERT, "text-error", 14));
        ic.setContentDisplay(ContentDisplay.RIGHT);
        ic.getStyleClass().add("input-error-label");
        rfv.setIcon(ic);
        
        
        field.focusedProperty().addListener((ObservableValue<? extends Boolean> observable,
                                             Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                field.validate();
            }else{
            }
        });
        
        
    }
    
    
}
