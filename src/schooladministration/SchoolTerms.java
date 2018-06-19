package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ofentse
 */
public class SchoolTerms extends BorderPane{

    public static JFXListView<Label> term_listView;
    
    Term selectedTerm = null;
    public static int selectedIndex = 0;
    private final JFXTextField name, year, start, end;
    
    public SchoolTerms() {
        
        getStyleClass().addAll("container");
        setPadding(new Insets(15, 5, 5, 5));
        
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getStyleClass().addAll("jfx-tab-flatpane");
        Tab basic  = new Tab("School Terms");
        
        setCenter(tabPane);
        
        tabPane.getTabs().add(basic);
        
        BorderPane tabContent = new BorderPane();
        tabContent.setPadding(new Insets(10));
        basic.setContent(tabContent);
        
        BorderPane centerContainer = new BorderPane();
        centerContainer.setPadding(new Insets(0, 5, 0, 5));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
                
        JFXButton refresh = new JFXButton("Refresh");
        refresh.getStyleClass().add("jfx-tool-button");
        refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        refresh.setOnAction((ActionEvent event) -> {
            updateTermListView();
        });
        
        JFXButton edit = new JFXButton("Edit Term");
        edit.getStyleClass().add("jfx-tool-button");
        edit.setGraphic(SMS.getGraphics(MaterialDesignIcon.PENCIL_BOX_OUTLINE, "icon-default", 24));
        edit.setOnAction((ActionEvent event) -> {
            //new UpdateSWAidDialog(selectedTerm);
        });
        
        JFXButton btn_add = new JFXButton("Add Term");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            //new UpdateSWAidDialog(null);
        });
        
        Label title = new Label();
        title.getStyleClass().add("title-label");
        
        toolbar.getChildren().addAll(title, new HSpacer(), refresh, edit, btn_add);
               
        term_listView = new JFXListView<>();
        term_listView.getStyleClass().add("jfx-custom-list");
        
        GridPane contentGrid = new GridPane();
        contentGrid.getStyleClass().add("container");
        contentGrid.setAlignment(Pos.CENTER);
        contentGrid.setStyle("-fx-padding:20 5");
        contentGrid.setVgap(20);
        contentGrid.setHgap(20);
        
        name = new JFXTextField();
        name.prefWidthProperty().bind(contentGrid.widthProperty().subtract(25));
        name.setPromptText("Term Name");
        name.setLabelFloat(true);
        name.setDisable(true);
        contentGrid.add(name, 0, 0, 3, 1);
        
        start = new JFXTextField();
        start.setPromptText("Start Date");
        start.setLabelFloat(true);
        start.setDisable(true);
        contentGrid.add(start, 0, 1);
        
        end = new JFXTextField();
        end.setPromptText("End Date");
        end.setLabelFloat(true);
        end.setDisable(true);
        contentGrid.add(end, 1, 1);
        
        year = new JFXTextField();
        year.setPromptText("Academic Year");
        year.setLabelFloat(true);
        year.setDisable(true);
        contentGrid.add(year, 2, 1);
        
        centerContainer.setCenter(SMS.setBorderContainer(contentGrid, "Term Details"));
        centerContainer.setTop(toolbar);
        
        tabContent.setCenter(centerContainer);
        
        HBox tbar = new HBox();
        tbar.getStyleClass().add("secondary-toolbar");
        
        Label terms = new Label("Terms");
        terms.getStyleClass().add("title-label");
        
        tbar.getChildren().addAll(terms, new HSpacer());
        
        BorderPane lPane = new BorderPane(term_listView);
        lPane.setTop(tbar);
        
        tabContent.setLeft(lPane);
        
        
        term_listView.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            try {
                selectedTerm = SMS.dbHandler.getTermByName(term_listView.getSelectionModel().getSelectedItem().getText());
                title.setText(term_listView.getSelectionModel().getSelectedItem().getText());
                
                name.setText(selectedTerm.getDescription());
                start.setText(selectedTerm.getStart());
                end.setText(selectedTerm.getEnd());
                year.setText(selectedTerm.getYear());
                
                selectedIndex = newValue.intValue();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        updateTermListView();
    }
    
    
    public static void updateTermListView(){
        ObservableList<String> ssn = SMS.dbHandler.getTermNameList();
        ObservableList<Label> data = FXCollections.observableArrayList();
        
        for(String dt: ssn){
            data.add(new Label(dt));
        }
        term_listView.setItems(data);
        term_listView.getSelectionModel().select(selectedIndex);
    }
 
}
