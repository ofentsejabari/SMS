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
    ArrayList<JFXComboBox<String>> combo_box; 
    ArrayList<JFXDatePicker> date_picker;
    
    public InputValidator(){
        
        inputField = new ArrayList<>();
        inputArea = new ArrayList<>();
        
        combo_box = new ArrayList<>();
        date_picker = new ArrayList<>();
    }
    
    public void addField(JFXTextField tf){
        inputField.add(tf);
    }
    
    public void addField(JFXTextArea tf){
        inputArea.add(tf);
    }
    
    public void addField(JFXComboBox tf){
        combo_box.add(tf);
    }
    
    public void addField(JFXDatePicker tf){
        date_picker.add(tf);
    }
    
    /**
     * 
     * @return 
     */
    public boolean isValid(){
    
        for(TextField tf: inputField){
            if(tf.getText().equalsIgnoreCase("")){
                return false;
            }
        }
        
        for(TextArea tf: inputArea){
            if(tf.getText().equalsIgnoreCase("")){
                return false;
            }
        }
        
        for(ComboBox tf: combo_box){
            if(tf.getValue() == null || tf.getValue().toString().equalsIgnoreCase("")){
                return false;
            }
        }
        
        for(DatePicker tf: date_picker){
            if(tf.getValue() == null || tf.getValue().toString().equalsIgnoreCase("")){
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
        
//        for(JFXComboBox tf: combo_box){
//            tf.validate();
//        }
//        
//        for(DatePicker tf: date_picker){
//            tf.validate();
//        }
        
        
        return true;
    }
    
}
