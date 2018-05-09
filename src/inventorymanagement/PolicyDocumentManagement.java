/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.HSpacer;
import entry.ProgressIndicator;
import entry.SMS;
import static entry.SMS.setDataNotAvailablePlaceholder;
import static inventorymanagement.control.FacilitiesController.facilitiesList;
import static inventorymanagement.control.InventoryListController.filter;
import static inventorymanagement.control.PolicyDocumentController.policyList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.InventoryQuery;

/**
 *
 * @author MOILE
 */
public class PolicyDocumentManagement extends BorderPane{

    public static CustomTableView<PolicyDocument> policyDocTable;
    public static PolicyWorkService policyWork;
    private final StackPane stackPane;
    
    public PolicyDocumentManagement() {
        
        policyWork = new PolicyWorkService();
    
        getStyleClass().add("container");
        stackPane = new StackPane();
        
        setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.UPLOAD, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
          //  new AddPolicyDocument().show();
        });
        
        JFXButton btn_refresh = new JFXButton("Refresh");
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            policyWork.restart();
           // new DialogUI("ahgjagjcas as ", DialogUI.ERROR_NOTIF, stackPane).show();
        });
        
        
        
        
        toolbar.getChildren().addAll(new HSpacer(), btn_refresh, btn_add);
        
        /*
            CREATE SUPPLIER TABLE
        */
        policyDocTable = new CustomTableView<>();
        
        CustomTableColumn policyDocID = new CustomTableColumn("#");
        policyDocID.setPercentWidth(4.9);
        policyDocID.setCellValueFactory(new PropertyValueFactory<>("policyDocID"));
        policyDocID.setCellFactory(TextFieldTableCell.forTableColumn());
        policyDocID.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn policyDocName = new CustomTableColumn("POLICY DOCUMENT");
        policyDocName.setPercentWidth(75.1);
        policyDocName.setCellValueFactory(new PropertyValueFactory<>("policyDocName"));
        policyDocName.setCellFactory(TextFieldTableCell.forTableColumn());
        policyDocName.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        });
        
        CustomTableColumn policyDocDate = new CustomTableColumn("UPLOAD DATE");
        policyDocDate.setPercentWidth(10);
        policyDocDate.setCellValueFactory(new PropertyValueFactory<>("policyDocDate"));
        policyDocDate.setCellFactory(TextFieldTableCell.forTableColumn());
        policyDocDate.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        }); 
                
        CustomTableColumn policyDocControls= new CustomTableColumn("CONTROLS");
        policyDocControls.setPercentWidth(10);
        policyDocControls.setCellFactory(TextFieldTableCell.forTableColumn());
        policyDocControls.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        
                        if(!empty){
                            setGraphic(new Label(ID));
                        }else{ setGraphic(null); }
                    }
                };
            }
        }); 
        
       
        
        policyDocTable.getTableView().getColumns().addAll(policyDocID,policyDocName,policyDocDate,policyDocControls);
        VBox.setVgrow(policyDocTable, Priority.ALWAYS);
        
        //-- SET DATA
        policyDocTable.getTableView().setItems(InventoryQuery.policiesList());
        
        
        VBox ph = setDataNotAvailablePlaceholder();
        policyDocTable.getTableView().setPlaceholder(ph);
        
        ProgressIndicator pi = new ProgressIndicator("Loading users data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(policyWork.runningProperty());
        policyDocTable.getTableView().itemsProperty().bind(policyWork.valueProperty());
        
        stackPane.getChildren().addAll(pi,policyDocTable);
        setCenter(stackPane);
        
        policyWork.start();
        policyWork.restart();
        
    }
    
    public class PolicyWork extends Task<ObservableList<PolicyDocument>> {       
        @Override 
        protected ObservableList<PolicyDocument> call() throws Exception {
            
            Platform.runLater(() -> {               
                policyDocTable.getTableView().setPlaceholder(new VBox());
            });
            policyList  =  InventoryQuery.policiesList();
            
            for(int i=0;i<policyList.size();i++){
                policyList.get(i).setPolicyDocID(i+1+"");
            }
                        
            Platform.runLater(() -> {  
                //count.setText(studentList.size()+" Student(s)");
                policyDocTable.getTableView().setPlaceholder(setDataNotAvailablePlaceholder());
            });

            return policyList;
        } 
    }

    public class PolicyWorkService extends Service<ObservableList<PolicyDocument>> {

        @Override
        protected Task createTask() {
            return new PolicyWork();
        }
    }
    
}
