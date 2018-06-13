/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import static entry.SMS.getGraphics;
import entry.ToolTip;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mysqldriver.InventoryQuery;

/**
 *
 * @author MOILE
 */
public class AddBookCopy extends JFXDialog{
    
    private final StackPane stackPane;
    public String studentID = "",filter2 = "";
    public static JFXDialog dialog;
    JFXComboBox<String> location,invoiceNo,supplier;
    JFXTextField serialNo, edition,copyNo,cost;
    Label cpLabel;
    
    public AddBookCopy(String book_id) {
        
        dialog=this;
        //-- Parent Container --------------------------------------------------
        StackPane stack = new StackPane();
        BorderPane container = new BorderPane();
        stack.getChildren().add(container);
        
        //-- Toolbar -----------------------------------------------------------
        HBox toolBar = new HBox();
        toolBar.getStyleClass().add("screen-decoration");
        
        JFXButton btn_close = new JFXButton("", getGraphics(MaterialDesignIcon.WINDOW_CLOSE, "close", 20));
        btn_close.getStyleClass().add("window-close");
        btn_close.setOnAction((ActionEvent event) -> {
            close();
        });
        
        if(book_id.equals("")){
             cpLabel = new Label("Add Book Copy");
        }
        else {
            String bName= InventoryQuery.getBooksById(book_id).getName();
            cpLabel = new Label("Add "+bName+" Copy");
        }
        
        
        cpLabel.getStyleClass().add("window-title");
        toolBar.getChildren().addAll(cpLabel,new HSpacer(), btn_close);
        container.setTop(toolBar);
        
        //---------------------- END SCREEN DECORATION -------------------------
        
        BorderPane bp = new BorderPane();
        this.studentID =  book_id;
        
        bp.getStyleClass().add("container");
        stackPane = new StackPane();
        stackPane.setStyle("-fx-padding: 10 5 0 5;");
        
        VBox form = new VBox();
        form.setPadding(new Insets(15));
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(20);
        gridPane.setHgap(10);
        
        serialNo = new JFXTextField();
        serialNo.setPromptText("Serial Number");
        serialNo.setLabelFloat(true);
        
        edition= new JFXTextField();
        edition.setPromptText("Edition");
        edition.setLabelFloat(true);
        
        location= new JFXComboBox();
        ObservableList<String> statuses= FXCollections.observableArrayList();
        statuses.addAll("In Storage","Allocated","Overdue Return");
        location.prefWidthProperty().bind(this.widthProperty());
        location.setItems(statuses);
        location.setPromptText("Status");
        location.setLabelFloat(true);
        
        copyNo= new JFXTextField();
        copyNo.setPromptText("Copy No.");
        copyNo.setLabelFloat(true);
        
        invoiceNo = new JFXComboBox();
        invoiceNo.setPromptText("Invoice");
        invoiceNo.prefWidthProperty().bind(this.widthProperty());
        invoiceNo.setLabelFloat(true);
        
        supplier = new JFXComboBox();
        ObservableList<String> item = InventoryQuery.getSupplierNames();
        supplier.setItems(item);
        supplier.prefWidthProperty().bind(this.widthProperty());
        supplier.setPromptText("Supplier Name");
        supplier.setLabelFloat(true);
                
        cost = new JFXTextField();
        cost.setPromptText("Cost");
        cost.setLabelFloat(true);
                
        gridPane.add(serialNo, 0, 0);
        gridPane.add(edition, 1, 0);
        gridPane.add(location, 0, 1);
        gridPane.add(copyNo, 1, 1);
        gridPane.add(invoiceNo, 0, 2);
        gridPane.add(supplier, 1, 2);
        gridPane.add(cost, 0, 3);
        
        form.getChildren().addAll(gridPane);
        form.setSpacing(20);
        
           
          //-- Validate and save the form  -------------------------------------
            JFXButton save = new JFXButton("Save");
            save.getStyleClass().add("dark-blue");
            save.setTooltip(new ToolTip("Save "));
            save.setOnAction((ActionEvent event) -> {
           
           
            });
            
        HBox toolbar1 = new HBox();
        toolbar1.getStyleClass().add("secondary-toolbar");
        toolbar1.getChildren().add(save);
        
        bp.setCenter(form);
        bp.setBottom(toolbar1);
        container.setCenter(bp);
        //-- Set JFXDialog view  -----------------------------------------------
        setDialogContainer(SMS.MAIN_UI);
        setTransitionType(JFXDialog.DialogTransition.CENTER);
        setContent(stack);
        setOverlayClose(true);
        stack.setPrefSize(500, 300);
        show();
        
    }
    
    
}
