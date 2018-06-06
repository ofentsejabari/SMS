/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import inventorymanagement.AddSupplierStage;
import inventorymanagement.AssetAllocationList;
import inventorymanagement.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mysqldriver.InventoryQuery;

/**
 * FXML Controller class
 *
 * @author MOILE
 */
public class SupplierInformationController implements Initializable {

    @FXML
    private JFXButton btn_info,searchIcon,searchButton, btn_refresh, btn_cancel,btn_update;
    @FXML
    private JFXButton btn_edit;
    
    ObservableList<String> items =null; 
    
    AssetAllocationList statusItem = null;
    
    @FXML
    private JFXTextField companyName;
    @FXML
    private JFXTextField companyTel;
    @FXML
    private JFXTextField companyCell;
    @FXML
    private JFXTextField companyFax;
    @FXML
    private JFXTextField companyEmail;
    @FXML
    private JFXTextArea companyPostal;
    @FXML
    private JFXTextArea companyPhysical;
    
    
    @FXML
    private VBox vboxId;        
    @FXML
    private JFXTextField searchField;
    
    @FXML
    private JFXListView<String> supplier_ListView;
    
    @FXML
    private VBox searchArea,listview;
    
    
    @FXML
    private Label totalSuppliers;
    
    boolean flag= true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       activateButtons(false);
        searchIcon.setGraphic(SMS.getGraphics(FontAwesomeIcon.SEARCH, "icon-default", 17));
        searchButton.setGraphic(SMS.getGraphics(FontAwesomeIcon.SEARCH, "icon-default", 17));
        listview.getChildren().remove(searchArea);
        
        searchIcon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if(listview.getChildren().contains(searchArea))
               {
                   listview.getChildren().remove(searchArea);
               }
               else{
                   listview.getChildren().add(1,searchArea);
               }
            }
        });
        
        items =InventoryQuery.getSupplierNames();
        totalSuppliers.setText(""+items.size());
        supplier_ListView.setItems(items);
        
        supplier_ListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           
            if (newValue != null){
                        Supplier sp = InventoryQuery.getSupplierByName(newValue);
                        companyName.setText(sp.getSupplierName());
                        companyTel.setText(sp.getSupplierPhone());
                        companyPostal.setText(sp.getSupplierPostal());
                        companyCell.setText(sp.getSupplierCell());
                        companyEmail.setText(sp.getSupplierEmail());
                        companyFax.setText(sp.getSupplierFax());
                        companyPhysical.setText(sp.getSupplierPhysical());
            } 
            else{
                        companyName.setText("");
                        companyTel.setText("");
                        companyPostal.setText("");
                        companyCell.setText("");
                        companyEmail.setText("");
                        companyFax.setText("");
                        companyPhysical.setText("");
            
            }
            
        });
        
        btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
        btn_refresh.setOnAction((ActionEvent event) -> {
            items =InventoryQuery.getSupplierNames();
            supplier_ListView.setItems(items);
        });
        
        searchField.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldVal,
                Object newVal) {
              search((String) oldVal, (String) newVal);
            }
        });
         
        btn_info.setGraphic(SMS.getGraphics(FontAwesomeIcon.INFO, "icon-default", 20));
        btn_info.setOnAction((ActionEvent event) -> {
            //new AddSupplierStage().show();
        });
        
        btn_edit.setGraphic(SMS.getGraphics(FontAwesomeIcon.EDIT, "icon-default", 24));
        btn_edit.setOnAction((ActionEvent event) -> {
            activateButtons(flag);
            //new AddSupplierStage().show();
        });
    }    
     public void search(String oldVal, String newVal) {
       // System.out.print("here");
        if (oldVal != null && (newVal.length() < oldVal.length())) {
          supplier_ListView.setItems(items);
          
        }
        String value = newVal.toUpperCase();
        ObservableList<String> subentries = FXCollections.observableArrayList();
        int count=0;
        for (String entry : supplier_ListView.getItems()) {
            boolean match = true;
           String entryText = entry;
            
            if (!entryText.toUpperCase().contains(value)) {
                //System.out.println(entryText.toUpperCase()+"--"+value);
              match = false;
            }
            if (match) {
              count++;
              subentries.add(entryText);
            }
        }
        totalSuppliers.setText(""+count);
        supplier_ListView.setItems(subentries);
  }
    
    public void textField(String cName){
        System.out.println(cName);
        companyName.setText(cName);
    }
    
    public void activateButtons(boolean flag){
        System.out.println(flag);
        btn_update.setVisible(flag);
        btn_cancel.setVisible(flag);
        
        companyName.setEditable(flag);
        companyName.setDisable(!flag);
        companyPostal.setEditable(flag);
        companyPostal.setDisable(!flag);
        companyCell.setEditable(flag);
        companyCell.setDisable(!flag);
        companyEmail.setEditable(flag);
        companyEmail.setDisable(!flag);
        companyFax.setEditable(flag);
        companyFax.setDisable(!flag);
        companyPhysical.setEditable(flag);
        companyPhysical.setDisable(!flag);
        companyTel.setEditable(flag);
        companyTel.setDisable(!flag);
        this.flag=!flag;
        
    }
    
    public void setFilter(String filter){
        statusItem.filter = filter;
        statusItem.studentAllocationWork.restart();
    }
    
}
