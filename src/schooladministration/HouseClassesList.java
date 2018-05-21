package schooladministration;

import com.jfoenix.controls.JFXTextField;
import entry.CustomTableColumn;
import entry.CustomTableView;
import entry.ProgressIndicator;
import entry.SMS;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import mysqldriver.AdminQuery;
import static schooladministration.SchoolAdministartion.houseController;

/**
 *
 * @author ofentse
 */
public class HouseClassesList extends BorderPane{
    
    public static CustomTableView<ISchoolClass> table;
    public HouseClassWorkService hcws;
    public HouseStreamWorkService hsws;
    
    
    private final JFXTextField name;
    private final JFXTextField hod;
    private final PieChart strength;

    public HouseClassesList() {
        
        setPadding(new Insets(10));
        getStyleClass().add("container");
        hcws = new HouseClassWorkService();
        hsws = new HouseStreamWorkService();
                
        table = new CustomTableView<>();
        
        CustomTableColumn cn = new CustomTableColumn("");
        cn.setPercentWidth(5);
        cn.setCellValueFactory(new PropertyValueFactory<>("schoolID"));
        cn.setCellFactory(TextFieldTableCell.forTableColumn());
        cn.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
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
        
        CustomTableColumn className = new CustomTableColumn("CLASS NAME");
        className.setPercentWidth(94.9);
        className.setCellValueFactory(new PropertyValueFactory<>("name"));
        className.setCellFactory(TextFieldTableCell.forTableColumn());
        className.setCellFactory(new Callback<TableColumn<String, String>, TableCell<String, String>>() {
            @Override 
            public TableCell<String, String> call(TableColumn<String, String> clientID) {
                return new TableCell<String, String>() {
                    
                    @Override 
                    public void updateItem(final String ID, boolean empty) {
                        super.updateItem(ID, empty);
                        Hyperlink description = new Hyperlink("");
                        description.getStyleClass().add("tableLink");
                        
                        if(!empty){
                            description.setText(ID);
                            setGraphic(description);                           
                            description.setOnAction((ActionEvent event) -> {
                                   
                            });
                        }else{ setGraphic(null); }
                    }
                };
            }
        });

        
        table.getTableView().getColumns().addAll(cn, className);
        VBox.setVgrow(table, Priority.ALWAYS);
        
        ProgressIndicator pi = new ProgressIndicator("Loading class data", "If network connection is very slow,"
                                                   + " this might take some few more seconds.");
        
        pi.visibleProperty().bind(hcws.runningProperty());
        table.getTableView().itemsProperty().bind(hcws.valueProperty());
                
        GridPane contentGrid = new GridPane();
        contentGrid.getStyleClass().add("container");
        contentGrid.setStyle("-fx-padding:25 5 5 5;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(10);
        
        name = new JFXTextField();
        name.setPromptText("HOUSE NAME");
        name.prefWidthProperty().bind(contentGrid.widthProperty());
        name.setLabelFloat(true);
        contentGrid.add(name, 0, 0);
        name.setDisable(true);
                
        hod = new JFXTextField();
        hod.setPromptText("HEAD OF HOUSE (HOH)");
        hod.prefWidthProperty().bind(contentGrid.widthProperty());
        hod.setLabelFloat(true);
        contentGrid.add(hod, 1, 0);
        hod.setDisable(true);
        
        
        //--
        StackPane stackPane = new StackPane(table, pi);
        stackPane.getStyleClass().add("container");
        contentGrid.add(SMS.setBorderContainer(stackPane, null, "#F0582F"), 1, 1);
        //--
        
        strength = new PieChart();
        strength.setLabelLineLength(5);
        
        VBox stBox = new VBox(strength);
        stBox.getStyleClass().add("container");
        
        contentGrid.add(SMS.setBorderContainer(stBox, null, "#F0582F"), 0, 1);
        
        setCenter(contentGrid);
        
        hcws.start();
        hsws.start();
        
        hcws.restart();
        hcws.restart();
        
    }
    
    
    
    
    public class HouseClassListWork extends Task<ObservableList<ISchoolClass>> {       
        @Override 
        protected ObservableList<ISchoolClass> call() throws Exception {
            
            ObservableList<ISchoolClass> data; 
          
            if(houseController.selectedHouse != null){
                data = AdminQuery.getHouseClassList(houseController.selectedHouse.getID());
            }else{ 
                data = FXCollections.observableArrayList();
            }
            
            for (int i = 0; i < data.size(); i++) {
                data.get(i).setSchoolID(i+1+"");
            }         
            return data;
        }
    }

    public class HouseClassWorkService extends Service<ObservableList<ISchoolClass>> {

        @Override
        protected Task createTask() {
            return new HouseClassListWork();
        }
    }
   
    
    
    public class HouseStreamWork extends Task<Integer> {       
        @Override 
        protected Integer call() throws Exception {
            Platform.runLater(() -> {
            
                try{
                    ObservableList<Stream> all = AdminQuery.getStreams();

                    name.setText(houseController.selectedHouse.getHouseName());
                    hod.setText(SMS.dbHandler.getEmployeeByID(houseController.selectedHouse.getHOH()).getFullNameWithInitials());

                    ObservableList<PieChart.Data>  data = FXCollections.observableArrayList();

                    for(Stream stream: all){
                        int sz = AdminQuery.getHouseClassList(houseController.selectedHouse.getID(),
                                stream.getStreamID()).size();
                        data.add(new PieChart.Data(stream.getDescription()+" - "+sz, sz));
                    }

                    strength.setData(data);

                }catch(Exception ex){}
            });
            
            
            return 1;
        }
    }
    
    public class HouseStreamWorkService extends Service<Integer> {

        @Override
        protected Task createTask() {
            return new HouseStreamWork();
        }
    }
    
}
