package entry;

import javafx.beans.binding.NumberBinding;
import javafx.collections.ListChangeListener;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author jabari
 */
public class CustomTableView<s> extends StackPane{ 
        private TableView<s> table;
        @SuppressWarnings("rawtypes") 
        public CustomTableView(){ 
            this.table = new TableView<s>(); 
            final GridPane grid = new GridPane(); 
            this.table.getColumns().addListener(new ListChangeListener(){ 
                @Override public void onChanged(javafx.collections.ListChangeListener.Change arg0) {
                    grid.getColumnConstraints().clear(); 
                    ColumnConstraints[] arr1 = new ColumnConstraints[CustomTableView.this.table.getColumns().size()]; 
                    StackPane[] arr2 = new StackPane[CustomTableView.this.table.getColumns().size()]; 
                    int i=0; 
                    for(TableColumn column : CustomTableView.this.table.getColumns()){ 
                        CustomTableColumn col = (CustomTableColumn)column; 
                        ColumnConstraints consta = new ColumnConstraints(); 
                        consta.setPercentWidth(col.getPercentWidth()); 
                        StackPane sp = new StackPane(); 
                        if(i==0){ 
                            NumberBinding diff = sp.widthProperty().subtract(3.75); 
                            column.prefWidthProperty().bind(diff); 
                        }else{ column.prefWidthProperty().bind(sp.widthProperty()); } 
                        arr1[i] = consta; arr2[i] = sp; i++; 
                    } 
                    grid.getColumnConstraints().addAll(arr1); 
                    grid.addRow(0, arr2); 
                } 
            }); 
            getChildren().addAll(grid,table); 
        } 
        public TableView<s> getTableView(){ 
            return this.table; 
        } 
    }
