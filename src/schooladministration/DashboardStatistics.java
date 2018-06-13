package schooladministration;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;

/**
 *
 * @author ofentse
 */
public class DashboardStatistics extends BorderPane{

    //private final JFXTextField name, hod;
    public DepartmentDetailsService dds;
    
    public PieChart strength, departments;

    public DashboardStatistics() {
        
        dds = new DepartmentDetailsService();
        
        //setPadding(new Insets(10));
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("secondary-toolbar");
        setTop(toolbar);
        
        JFXButton btn_add = new JFXButton("Reload");
        btn_add.getStyleClass().add("jfx-tool-button");
        btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.RELOAD, "icon-default", 24));
        btn_add.setOnAction((ActionEvent event) -> {
            dds.restart();
        });
        
        
        toolbar.getChildren().addAll(new HSpacer(), btn_add);
        
        
        GridPane contentGrid = new GridPane();
        contentGrid.setAlignment(Pos.CENTER);
        contentGrid.getStyleClass().add("container");
        contentGrid.setStyle("-fx-padding:15 10 10 10;");
        contentGrid.setVgap(20);
        contentGrid.setHgap(10);
              
        //-- 
        departments = new PieChart();
        departments.setLabelLineLength(5);
        
        VBox sbBox = new VBox(departments);
        sbBox.getStyleClass().add("container");
        
        contentGrid.add(SMS.setBorderContainer(sbBox, "DEPARTMENTS STRENGTH", "#1E90FF"), 0, 1);
        
        //-- 
        
        strength = new PieChart();
        strength.setLabelLineLength(5);
        
        VBox stBox = new VBox(strength);
        stBox.getStyleClass().add("container");
        
        contentGrid.add(SMS.setBorderContainer(stBox, "Departments"), 1, 1);
        
        
        
        setCenter(contentGrid);
        
        dds.start();
        dds.restart();
    }
    
    
    public class DepartmentDetailsWork extends Task<Integer> {       
        @Override 
        protected Integer call() throws Exception {
            Platform.runLater(() -> {
                
                ObservableList<Department> departmentList = AdminQuery.getDepartments();
                
                ObservableList<PieChart.Data>  data = FXCollections.observableArrayList();
                
                for(Department dept : departmentList){
                    int sz = SMS.dbHandler.getEmployeeList(true, dept.getID()).size();
                   data.add(new PieChart.Data(dept.getDepartmentName()+" - "+sz, sz));
                }
                
                departments.setData(data);
                
            });
            
            return 1;
        }
    }

    
    public class DepartmentDetailsService extends Service<Integer> {

        @Override
        protected Task createTask() {
            return new DepartmentDetailsWork();
        }
    }
   
    
    
}
