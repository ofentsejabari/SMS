package inventorymanagement;
/**
 * @author MOILE
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.HSpacer;
import entry.SMS;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mysqldriver.AdminQuery;
import mysqldriver.InventoryQuery;
import schooladministration.Subject;


public class BookTab extends BorderPane{

    private final StackPane stackPane;
    private JFXListView<String> listview;
    JFXTextField bookName;
    JFXTextField publisher;
    JFXTextField author;
    JFXTextField isbn;
    JFXTextField dept,purchase_date,purchase_supplier,purchase_cost;
    JFXTextField edition,serial_number;
    ObservableList<String> listItems;
    HBox toolbar,toolbar1,toolbar2 ;
    JFXComboBox<String> combo;
    Label book_label,toolbarText,toolbarText1,toolbarText2;
    VBox vbox;
    String book_id;
    
 public BookTab(){

            getStyleClass().add("container");
            stackPane = new StackPane(); 
            setPadding(new Insets(5));
            /*
                Initialization
            */
            combo = new JFXComboBox();
            vbox = new VBox();
            book_id="";
            
            TabPane tabpane = new TabPane();
            Tab details_tab = new Tab("Book Details");
            Tab copies_tab = new Tab("Book Copies");
            toolbarText = new Label();
            toolbarText1 = new Label();
            toolbarText2 = new Label();

            listview = new JFXListView();
            listview.getStyleClass().add("jfx-custom-list");
            listview.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    if (newValue != null){
                        BookModel bookModel = InventoryQuery.getBooksByName(newValue);
                        book_id=bookModel.id;
                        bookName.setText(bookModel.name);
                        BookItem.lab.setText(bookModel.name);
                        publisher.setText(bookModel.publisher);
                        toolbarText.setText(bookModel.name);
                        toolbarText1.setText(bookModel.name);
                        toolbarText2.setText(bookModel.name);
                        author.setText(bookModel.author);
                        isbn.setText(bookModel.isbn);
                        book_label.setText(newValue);
                        Subject sub=AdminQuery.getSubjectByID(bookModel.subject);
                        dept.setText(sub.getDescription());
                        BookItem.filter=book_id;
                        /*
                            refresh book copies table
                        */
                        BookItem.bookWork.restart();
                        
                    } 
            });
            
            toolbar = new HBox(toolbarText);
            toolbar1 = new HBox(toolbarText1);
            toolbar2 = new HBox(toolbarText2);
            toolbar.setAlignment(Pos.BOTTOM_LEFT);
            toolbar1.setAlignment(Pos.BOTTOM_LEFT);
            toolbar.getStyleClass().add("secondary-toolbar");
            toolbar1.getStyleClass().add("secondary-toolbar");
            toolbar2.getStyleClass().add("secondary-toolbar");
            
            BorderPane bookView = new BorderPane();
            listItems = InventoryQuery.getBookNames();
            listview.setItems(listItems);
            

            
            combo.prefWidthProperty().bind(vbox.widthProperty());
            ObservableList<String> subjects = AdminQuery.getSubjectNameList(true, 0);
            combo.setItems(subjects);
            combo.setValue("Select Subject");
            combo.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                   listItems.clear();
                   String subjectId=AdminQuery.getSubjectByName(newValue).getSubjectID();
                   listItems = InventoryQuery.getBookNamesBySubject(subjectId);
                   listview.setItems(listItems);
                   clearFields();
            });

            HBox booktoolbar = new HBox();
            booktoolbar.getStyleClass().add("secondary-toolbar");
            book_label= new Label();
            booktoolbar.getChildren().add(book_label);
            
            /*
                book details
            */
            VBox gp = new VBox();
            bookName = new JFXTextField();
            bookName.getStyleClass().add("input-text");
            bookName.setPromptText("Book Name");
            bookName.setLabelFloat(true);
            bookName.setDisable(true);

            author = new JFXTextField();
            author.setPromptText("Book Author");
            author.getStyleClass().add("input-text");
            author.setLabelFloat(true);
            author.setDisable(true);

            publisher = new JFXTextField();
            publisher.setPromptText("Book Publisher");
            publisher.getStyleClass().add("input-text");
            publisher.setLabelFloat(true);
            publisher.setDisable(true);

            isbn = new JFXTextField();
            isbn.setPromptText("ISBN");
            isbn.getStyleClass().add("input-text");
            isbn.setLabelFloat(true);
            isbn.setDisable(true);
            
            dept = new JFXTextField();
            dept.setPromptText("Department");
            dept.getStyleClass().add("input-text");
            dept.setDisable(true);
            dept.setLabelFloat(true);
            
            
            gp.setPadding(new Insets(20, 0, 0, 10));
            gp.setSpacing(20);
            gp.getChildren().addAll(bookName,author,publisher,isbn,dept);
            
            VBox book_side= new VBox(toolbar,gp);
            book_side.prefHeightProperty().bind(this.heightProperty());
            book_side.setPadding(new Insets(6,0,0,0));
            details_tab.setContent(book_side);
            details_tab.setClosable(false);
            copies_tab.setClosable(false);
            tabpane.getTabs().addAll(details_tab,copies_tab);
            
            
            /*
                Book copies
            */
            VBox b_copies = new VBox();
            b_copies.prefHeightProperty().bind(this.heightProperty());
            b_copies.setPadding(new Insets(6,0,0,0));
            b_copies.getChildren().addAll(new BookItem());
            copies_tab.setContent(b_copies);
            
            //create the left
            /*
                the tool bar for the right
            */
            VBox bookdetails = new VBox();    
            bookdetails.getChildren().addAll(tabpane);
            bookdetails.setSpacing(10);
            bookdetails.setPadding(new Insets(0, 0, 0, 10));
            HBox rightToolBar= new HBox();
            rightToolBar.setAlignment(Pos.CENTER_LEFT);
            Label tool_label = new Label("Departments");
            /*
                add deptment button
            */
            JFXButton addDept = new JFXButton();
            addDept.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
            addDept.setOnAction((ActionEvent event) -> {
                new AddBookItemStage();
            });
            JFXButton filterDept = new JFXButton();
            filterDept.setTooltip(new Tooltip("Filter"));
            filterDept.setGraphic(SMS.getGraphics(MaterialDesignIcon.FILTER, "icon-default", 18));
            filterDept.setOnAction((ActionEvent event) -> {
                enable(combo);
            });
            tool_label.getStyleClass().add("title-label");
            rightToolBar.getChildren().addAll(tool_label,new HSpacer(),filterDept,addDept);
            rightToolBar.getStyleClass().add("secondary-toolbar");
            
            
            vbox.getChildren().addAll(rightToolBar,listview);

            VBox.setVgrow(listview, Priority.ALWAYS);
            vbox.setSpacing(5);
            bookView.setLeft(vbox);
            bookView.setCenter(bookdetails);

            JFXButton btn_add = new JFXButton("Add");
            btn_add.setGraphic(SMS.getGraphics(MaterialDesignIcon.PLUS, "icon-default", 24));
            btn_add.setOnAction((ActionEvent event) -> {
                new AddBookItemStage().show();
            });

            JFXButton btn_refresh = new JFXButton("Refresh");
            btn_refresh.setGraphic(SMS.getGraphics(MaterialDesignIcon.ROTATE_3D, "icon-default", 24));
            btn_refresh.setOnAction((ActionEvent event) -> {

            });

            btn_refresh.getStyleClass().add("jfx-tool-button");
            btn_add.getStyleClass().add("jfx-tool-button");

            stackPane.setPadding(new Insets(10));
            stackPane.getChildren().addAll(bookView);
            setCenter(stackPane);

    }
 
  public  void clearFields(){
        book_label.setText("");
        bookName.setText("");
        publisher.setText("");
        author.setText("");
        isbn.setText("");
        dept.setText("");
        toolbarText.setText("");
        toolbarText1.setText("");
        BookItem.lab.setText("");
        BookItem.filter="";
        BookItem.bookWork.restart();
  }
  public void enable(JFXComboBox e){
      if(vbox.getChildren().contains(e)){
          vbox.getChildren().remove(e);
          listItems=InventoryQuery.getBookNames();
          listview.setItems(listItems);
      }
      else{
          
          vbox.getChildren().add(1,e);
      }
  }
}
