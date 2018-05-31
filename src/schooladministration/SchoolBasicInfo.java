package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.dbHandler;
import static entry.SMS.getIcon;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author ofentse
 */
public class SchoolBasicInfo extends BorderPane{

    public SchoolInfoService dds;
    
    private final JFXTextField name, website, tel, fax, email;
    private final JFXTextArea postalAddress, physicalAddress;
    private final ImageView schoolLogo;

    public SchoolBasicInfo(){
        
        dds = new SchoolInfoService();
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Update");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateSchoolDialog(dbHandler.getSchoolByID());
        });
        
        toolbar.getChildren().addAll(new HSpacer(), btn_add);
        
        //--
        GridPane contentGrid = new GridPane();
        contentGrid.getStyleClass().add("container");
        contentGrid.setStyle("-fx-padding:25 5 10 5;");
        contentGrid.setVgap(25);
        contentGrid.setHgap(10);
        
        GridPane basicInfo = new GridPane();
        basicInfo.getStyleClass().add("container");
        basicInfo.setStyle("-fx-padding:25 5 5 5;");
        basicInfo.setVgap(20);
        basicInfo.setHgap(10);
        
        name = new JFXTextField();
        name.setPromptText("School Name");
        name.prefWidthProperty().bind(basicInfo.widthProperty());
        name.setLabelFloat(true);
        basicInfo.add(name, 0, 0);
        name.setDisable(true);
        
        website = new JFXTextField();
        website.setPromptText("Web Site");
        website.prefWidthProperty().bind(basicInfo.widthProperty());
        website.setLabelFloat(true);
        basicInfo.add(website, 0, 1);
        website.setDisable(true);
        
        
        tel = new JFXTextField();
        tel.setPromptText("Telephone");
        tel.prefWidthProperty().bind(basicInfo.widthProperty());
        tel.setLabelFloat(true);
        basicInfo.add(tel, 0, 2);
        tel.setDisable(true);
        
        
        fax = new JFXTextField();
        fax.setPromptText("Fax");
        fax.prefWidthProperty().bind(basicInfo.widthProperty());
        fax.setLabelFloat(true);
        basicInfo.add(fax, 0, 3);
        fax.setDisable(true);
        
        email = new JFXTextField();
        email.setPromptText("Email");
        email.prefWidthProperty().bind(basicInfo.widthProperty());
        email.setLabelFloat(true);
        basicInfo.add(email, 0, 4);
        email.setDisable(true);
        
        postalAddress = new JFXTextArea();
        postalAddress.setPromptText("Postal Address");
        postalAddress.prefWidthProperty().bind(basicInfo.widthProperty());
        postalAddress.setLabelFloat(true);
        postalAddress.setPrefRowCount(3);
        basicInfo.add(postalAddress, 0, 5);
        postalAddress.setDisable(true);
        
        physicalAddress = new JFXTextArea();
        physicalAddress.setPromptText("Physical Address");
        physicalAddress.prefWidthProperty().bind(basicInfo.widthProperty());
        physicalAddress.setLabelFloat(true);
        physicalAddress.setPrefRowCount(3);
        basicInfo.add(physicalAddress, 0, 6);
        physicalAddress.setDisable(true);
                
//        contentGrid.add(SMS.setBorderContainer(basicInfo, null), 0, 0);
        
        //--
        
        GridPane logo = new GridPane();
        logo.getStyleClass().add("container");
        logo.setStyle("-fx-padding:5;");
        logo.setVgap(20);
        logo.setHgap(10);
                
        Label lg = new Label("School Logo");
        lg.getStyleClass().add("title-label");
        
        HBox toolb = new HBox(lg, new HSpacer(), new Button("Edit"));
        toolb.prefWidthProperty().bind(logo.widthProperty());
        toolb.getStyleClass().add("title-toolbar");
        
        logo.add(toolb, 0, 0);
        
        schoolLogo = getIcon("information.png", 100);
        logo.add(schoolLogo, 0, 1);
        
        //contentGrid.add(SMS.setBorderContainer(logo, null), 1, 0);
        
        VBox n1 = SMS.setBorderContainer(logo, null);
        HBox.setHgrow(n1, Priority.ALWAYS);
        //logo.setStyle("-fx-border-color:red;");
        
        VBox n2 = SMS.setBorderContainer(basicInfo, null);
        HBox.setHgrow(n2, Priority.ALWAYS);
        //basicInfo.setStyle("-fx-border-color:red;");
        
        setCenter(new HBox(n2, n1)); 
        
        dds.start();
        dds.restart();
    }
    
    
    public class SchoolInfoWork extends Task<Integer> {       
        @Override 
        protected Integer call() throws Exception {
            
            Platform.runLater(() -> {
                School school = dbHandler.getSchoolByID();
                
                name.setText(school.getSchoolName());
                website.setText(school.getWebsite());
                tel.setText(school.getTel());
                fax.setText(school.getFax());
                email.setText(school.getEmail());
                postalAddress.setText(school.getPostalAddress());
                physicalAddress.setText(school.getPhysicalAddress());
            });
            
            return 1;
        }
    }

    
    public class SchoolInfoService extends Service<Integer> {

        @Override
        protected Task createTask() {
            return new SchoolInfoWork();
        }
    }
   
}
