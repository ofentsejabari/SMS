package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

/**
 *
 * @author ofentse
 */
public class SchoolInformation extends BorderPane{

    private static JFXTextField schoolHead;

    public SchoolInfoService dds;
    
    private final JFXTextField name, website, tel, fax, email;
    private final JFXTextArea postalAddress, physicalAddress;
    private final Label schoolName;
    
    public static JFXListView<Label> schools_listView;
    
    School selectedSchool = null;
    public static int selectedIndex = 0;

    public SchoolInformation(){
        
        getStyleClass().addAll("container");
        
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getStyleClass().addAll("jfx-tab-flatpane");
        Tab basic  = new Tab("School Information");
        
        setCenter(tabPane);
        
        tabPane.getTabs().add(basic);
        
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 5, 5, 5));
        basic.setContent(pane);
        
        dds = new SchoolInfoService();
        
        BorderPane content = new BorderPane();
        content.setPadding(new Insets(0, 0, 0, 5));
        pane.setCenter(content);
                
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        content.setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Add School");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            new UpdateSchoolDialog(null);
        });
        
        JFXButton refresh = new JFXButton("Refresh");
        refresh.getStyleClass().add("jfx-tool-button");
        refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        refresh.setOnAction((ActionEvent event) -> {
            updateSchoolListView();
        });
        
        JFXButton edit = new JFXButton("Edit School");
        edit.getStyleClass().add("jfx-tool-button");
        edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        edit.setOnAction((ActionEvent event) -> {
            new UpdateSchoolDialog(selectedSchool);
        });
        
        schoolName = new Label();
        schoolName.getStyleClass().add("title-label");
        toolbar.getChildren().addAll(schoolName, new HSpacer(),refresh, edit, btn_add);
        
        schools_listView = new JFXListView<>();
        schools_listView.getStyleClass().add("jfx-custom-list");
        
        
        BorderPane lftPane = new BorderPane(schools_listView);
        
        Label allSc = new Label("Schools");
        allSc.getStyleClass().add("title-label");
        HBox tbar = new HBox(allSc, new HSpacer());
        tbar.getStyleClass().add("secondary-toolbar");
        lftPane.setTop(tbar);
        
        pane.setLeft(lftPane);
        //--
        
        GridPane contactDetails = new GridPane();
        contactDetails.getStyleClass().add("container");
        contactDetails.setStyle("-fx-padding:25 10 10 10;");
        contactDetails.setVgap(20);
        contactDetails.setHgap(10);
        
        name = new JFXTextField();
        name.setPromptText("School Name");
        name.prefWidthProperty().bind(contactDetails.widthProperty());
        name.setLabelFloat(true);
        contactDetails.add(name, 0, 0);
        name.setDisable(true);
        
        website = new JFXTextField();
        website.setPromptText("Web Site");
        website.prefWidthProperty().bind(contactDetails.widthProperty());
        website.setLabelFloat(true);
        contactDetails.add(website, 0, 1);
        website.setDisable(true);
        
        tel = new JFXTextField();
        tel.setPromptText("Telephone");
        tel.prefWidthProperty().bind(contactDetails.widthProperty());
        tel.setLabelFloat(true);
        contactDetails.add(tel, 0, 2);
        tel.setDisable(true);
        
        fax = new JFXTextField();
        fax.setPromptText("Fax");
        fax.prefWidthProperty().bind(contactDetails.widthProperty());
        fax.setLabelFloat(true);
        contactDetails.add(fax, 0, 3);
        fax.setDisable(true);
        
        email = new JFXTextField();
        email.setPromptText("Email");
        email.prefWidthProperty().bind(contactDetails.widthProperty());
        email.setLabelFloat(true);
        contactDetails.add(email, 0, 4);
        email.setDisable(true);
        
        postalAddress = new JFXTextArea();
        postalAddress.setPromptText("Postal Address");
        postalAddress.prefWidthProperty().bind(contactDetails.widthProperty());
        postalAddress.setLabelFloat(true);
        postalAddress.setPrefRowCount(3);
        contactDetails.add(postalAddress, 0, 5);
        postalAddress.setDisable(true);
        
        physicalAddress = new JFXTextArea();
        physicalAddress.setPromptText("Physical Address");
        physicalAddress.prefWidthProperty().bind(contactDetails.widthProperty());
        physicalAddress.setLabelFloat(true);
        physicalAddress.setPrefRowCount(3);
        contactDetails.add(physicalAddress, 0, 6);
        physicalAddress.setDisable(true);
        
        HBox con = new HBox(contactDetails);
        con.setPadding(new Insets(20, 0, 0, 0));
        
        content.setCenter(SMS.setBorderContainer(contactDetails, "School Contact Details")); 
        
        content.setRight(SMS.setBorderContainer(otherDetails(), "Other Details"));
        
        
        schools_listView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try {
                selectedSchool = SMS.dbHandler.getSchoolByName(schools_listView.getSelectionModel().getSelectedItem().getText());
                
                schoolName.setText(selectedSchool.getSchoolName());
                name.setText(selectedSchool.getSchoolName());
                website.setText(selectedSchool.getWebsite());
                tel.setText(selectedSchool.getTel());
                fax.setText(selectedSchool.getFax());
                email.setText(selectedSchool.getEmail());
                postalAddress.setText(selectedSchool.getPostalAddress());
                physicalAddress.setText(selectedSchool.getPhysicalAddress());
                
                selectedIndex = newValue.intValue();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
                
        dds.start();
        dds.restart();
        
        updateSchoolListView();
    }
    
    
    public static VBox otherDetails(){
        VBox con = new VBox();
        con.getStyleClass().add("container");
        GridPane other = new GridPane();
        other.setStyle("-fx-padding:25 10 10 10;");
        other.setVgap(20);
        other.setHgap(10);
        
        schoolHead = new JFXTextField();
        schoolHead.setPromptText("School Head");
        schoolHead.setPrefWidth(300);
        schoolHead.setLabelFloat(true);
        other.add(schoolHead, 0, 0);
        schoolHead.setDisable(true);
        
        con.getChildren().add(other);
        
        return con;
    }
    
    
    public static void updateSchoolListView(){
        ObservableList<String> ssn = SMS.dbHandler.getSchoolNames();
        ObservableList<Label> data = FXCollections.observableArrayList();
        
        for(String dt: ssn){
            data.add(new Label(dt));
        }
        schools_listView.setItems(data);
        schools_listView.getSelectionModel().select(selectedIndex);
    }
    
    
    public class SchoolInfoWork extends Task<Integer> {       
        @Override 
        protected Integer call() throws Exception {
            
            Platform.runLater(() -> {
                
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
