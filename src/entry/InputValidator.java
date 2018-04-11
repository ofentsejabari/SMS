package entry;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author ofentse
 */
public class InputValidator {
    
    //-- List of mandatory text Fields to validate --
    ArrayList<JFXTextField> inputField;
    ArrayList<JFXTextArea> inputArea;
    
    public InputValidator(){
        
        inputField = new ArrayList<>();
        inputArea = new ArrayList<>();
        
    }
    
    public void addField(JFXTextField tf){
        inputField.add(tf);
    }
    
    public void addField(JFXTextArea tf){
        inputArea.add(tf);
    }
    
    /**
     * 
     * @return 
     */
    public boolean isValid(){
    
        for(TextField tf: inputField){
            if(tf.getText().trim().equalsIgnoreCase("")){
                return false;
            }
        }
        
        for(TextArea tf: inputArea){
            if(tf.getText().trim().equalsIgnoreCase("")){
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * 
     * @return 
     */
    public boolean validate(){
    
        for(JFXTextField tf: inputField){
            tf.validate();
        }
        
        for(JFXTextArea tf: inputArea){
            tf.validate();
        }
        
        return true;
    }
    
}
