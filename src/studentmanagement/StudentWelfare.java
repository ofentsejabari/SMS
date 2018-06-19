package studentmanagement;

import com.jfoenix.controls.JFXTabPane;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ofentse
 */
public class StudentWelfare extends BorderPane{
    public StudentWelfare(){
        getStyleClass().add("container");
        setPadding(new Insets(10));
        
        JFXTabPane jfxtp = new JFXTabPane();
        jfxtp.getStyleClass().add("jfx-tab-flatpane");
        
        Tab ssn = new Tab("Special Needs");
        ssn.setContent(new StudentSpecialNeeds());
        
        Tab sws = new Tab("Social Welfare support");
        sws.setContent(new SocialWelfareSupport());
        
        Tab swsa = new Tab("Social Welfare Aid");
        swsa.setContent(new SocialWelfareAid());
        
        jfxtp.getTabs().addAll(ssn, sws, swsa);
        setCenter(jfxtp);
    }
    
}
