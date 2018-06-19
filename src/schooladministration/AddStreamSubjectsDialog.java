package schooladministration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.DialogUI;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.getGraphics;
import entry.ToolTip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import mysqldriver.AdminQuery;
import org.controlsfx.control.ListSelectionView;
import static schooladministration.SchoolAdministartion.streamClassesController;

/**
 *
 * @author jabari
 */
public class AddStreamSubjectsDialog extends JFXDialog{

    private final ListSelectionView<String> view;
    public Stream stream;
    ObservableList<Subject> all = null;
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public AddStreamSubjectsDialog(Stream stream) {
        
        this.stream = stream;
                    
        //-- Parent Container --
        StackPane stackPane = new StackPane();
        BorderPane container = new BorderPane();
        stackPane.getChildren().add(container);
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        Label title = new Label("Stream Subjects");
        title.getStyleClass().add("window-title");
        
        toolBar.getChildren().addAll(title, new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        JFXButton save = new JFXButton("Save");
        save.getStyleClass().add("dark-blue");
        save.setTooltip(new ToolTip("Save Changes"));
        
                
        view = new ListSelectionView<String>();
        view.setTargetHeader(new Label("Selected Subjects"));
        view.setTargetFooter(new HBox(new HSpacer(), save));
        
        view.setSourceHeader(new Label("Available Subjects"));
        
        container.setCenter(view);
        
        //-- Validate and save the form  ---------------------------------------
        save.setOnAction((ActionEvent event) -> {
            
            if(!view.getTargetItems().isEmpty()){
                
                ObservableList<String> subjectIDs = FXCollections.observableArrayList();
            
                for(Subject subj: all){
                    if(view.getTargetItems().contains(subj.getDescription())){
                        subjectIDs.add(subj.getSubjectID());
                    }
                }
            
                if(AdminQuery.deleteSubjectStream(stream.getStreamID())){
                
                    if(AdminQuery.addStreamSubject(subjectIDs, stream.getStreamID())){
                        
                        new DialogUI("Stream subjects has been updated successfully",
                        DialogUI.SUCCESS_NOTIF, SchoolAdministartion.ADMIN_MAN_STACK, this).show();
                        
                        streamClassesController.subjectList.subjectWorkService.restart();
                        close();
                    }else{
                        new DialogUI("Exception occurred while trying to update stream subjects",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();
                    }
                    
                }else{
                    new DialogUI("Exception occurred while trying to update stream subjects",
                        DialogUI.ERROR_NOTIF, stackPane, null).show();
                }
            }else{
                new DialogUI( "No subjects selected for this stream.",
                    DialogUI.ERROR_NOTIF, stackPane, null).show();
            }
            
        });
        
        //-- footer ------------------------------------------------------------
        HBox footer = new HBox(save);
        footer.setAlignment(Pos.CENTER);
        footer.getStyleClass().add("primary-toolbar");
        container.setBottom(footer);

        //-- Set jfxdialog view  -----------------------------------------------
        setDialogContainer(SchoolAdministartion.ADMIN_MAN_STACK);
        setContent(stackPane);
        setOverlayClose(false);
        stackPane.setPrefSize(500, 400);
        show();
        
        updateList();
    }
    
    
    public void updateList(){
        
        ObservableList<String> source = FXCollections.observableArrayList();
        ObservableList<String> target = FXCollections.observableArrayList();
        
        all = AdminQuery.getSubjectList();
        ObservableList<Subject> streamClasses = AdminQuery.getStreamSubjectsList(stream.getStreamID());
        
        for(Subject sbj: streamClasses){target.add(sbj.getDescription());}
        
        for(Subject sbj: all){
            if(!target.contains(sbj.getDescription())){
                source.add(sbj.getDescription());
            }
        }
        
        view.getSourceItems().addAll(source);
        view.getTargetItems().addAll(target);
        
    }
}
