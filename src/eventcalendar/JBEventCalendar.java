
package eventcalendar;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import entry.SMS;
import entry.ToolTip;
import java.util.Calendar;
import java.util.Locale;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author JABARI
 */
public class JBEventCalendar extends BorderPane{
    private  GridPane CALENDAR_GRID;
    protected Calendar CURRENTLY_SELECETED_MONTH;
    
    /**
      * <P>The object used for executing a static SQL statement
      * and returning the results it produces.
      * <P>
      */
    //public static Statement STATEMENT = null;
//    private final EventsViewerUI upcomingEventstUI;
    //public boolean showFullView = false;
    private String MONTH_OF_YEAR;
    
    
    public JBEventCalendar() {
        
        
        CURRENTLY_SELECETED_MONTH = Calendar.getInstance();
        CURRENTLY_SELECETED_MONTH.set(Calendar.DAY_OF_MONTH, 1);
        
        getStyleClass().add("event-calendar");
        
        
        CALENDAR_GRID = new GridPane();
        CALENDAR_GRID.setAlignment(Pos.TOP_CENTER);
        CALENDAR_GRID.getStyleClass().add("calendar-grid");
        CALENDAR_GRID.setHgap(0);
        CALENDAR_GRID.setVgap(0);
        
        CALENDAR_GRID.setPrefHeight(600);
        
        rollDateTo(CURRENTLY_SELECETED_MONTH);
        
        
//        upcomingEventstUI = new EventsViewerUI();
        
//        if(showFullView){
////            setCenter(Borders.wrap(upcomingEventstUI).lineBorder().innerPadding(0).color(Color.TRANSPARENT).buildAll());
////            setLeft(Borders.wrap(CALENDAR_GRID).lineBorder().innerPadding(0).color(Color.TRANSPARENT).buildAll());
//        }else{
            setLeft(CALENDAR_GRID);
        //}
        
    }
    
    
    
    
    private void rollDateTo(Calendar calendar) {
        
        CALENDAR_GRID.getChildren().clear();
        
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
         MONTH_OF_YEAR = calendar.getDisplayName(Calendar.MONTH, 
                Calendar.LONG_FORMAT, Locale.ENGLISH);
        
        //-- Go to the first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
        Label monthOfYear = new Label();
        monthOfYear.getStyleClass().add("month-label");
        monthOfYear.setText(MONTH_OF_YEAR+" "+ getYear());
        
        HBox toolbar = new HBox();
        toolbar.getStyleClass().add("toolbar");
        toolbar.setAlignment(Pos.CENTER);
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        
        Region sp1 = new Region();
        HBox.setHgrow(sp1, Priority.ALWAYS);
        
        JFXButton monthForward = new JFXButton("", SMS.getGraphics(MaterialDesignIcon.CHEVRON_RIGHT, "text-indigo", 24));
        monthForward.getStyleClass().add("nav-button");
        monthForward.setPrefSize(30, 30);
        monthForward.setOnAction((ActionEvent event) -> {
            
//            elws.restart();
            rollForward();
            
        });
        
        JFXButton monthBack = new JFXButton("", SMS.getGraphics(MaterialDesignIcon.CHEVRON_LEFT, "text-indigo", 24));
        monthBack.getStyleClass().add("nav-button");
        monthBack.setPrefSize(30, 30);
        
        monthBack.setOnAction((ActionEvent event) -> {
            
//            elws.restart();
            rollBack();
        });
        
        toolbar.getChildren().addAll(monthBack, sp, monthOfYear, sp1, monthForward);
        
        JFXButton gotoToday = new JFXButton("Go To Today");
        gotoToday.getStyleClass().add("jfx-warning-raised");
        gotoToday.setTooltip(new ToolTip("Go to today"));
        gotoToday.setOnAction((ActionEvent event) -> {
//            elws.restart();
            today();
            
        });
        
        HBox top = new HBox(gotoToday);
        top.setAlignment(Pos.CENTER_RIGHT);
        top.getStyleClass().add("calendar-header");
        top.setSpacing(10);
        
        //if(!showFullView){
        CALENDAR_GRID.add(top, 0, 0, 7, 1);
        //}
        
        CALENDAR_GRID.add(toolbar, 0, 1, 7, 1);
        
        HBox sun = new HBox(new Label("Sun"));
        sun.getStyleClass().add("dates");
        
        CALENDAR_GRID.add(sun, 0, 2);
        
        HBox mon = new HBox(new Label("Mon"));
        mon.getStyleClass().add("dates");
        CALENDAR_GRID.add(mon, 1, 2);
        
        HBox tue = new HBox(new Label("Tue"));
        tue.getStyleClass().add("dates");
        CALENDAR_GRID.add(tue, 2, 2);
        
        HBox wed = new HBox(new Label("Wed"));
        wed.getStyleClass().add("dates");
        CALENDAR_GRID.add(wed, 3, 2);
        
        HBox thu = new HBox(new Label("Thu"));
        thu.getStyleClass().add("dates");
        CALENDAR_GRID.add(thu, 4, 2);
        
        HBox fri = new HBox(new Label("Fri"));
        fri.getStyleClass().add("dates");
        CALENDAR_GRID.add(fri, 5, 2);
        
        HBox sat = new HBox(new Label("Sat"));
        sat.getStyleClass().add("dates");
        CALENDAR_GRID.add(sat, 6, 2);
        
        if(Calendar.getInstance().getTime().toString().split(" ")[0].equalsIgnoreCase("Sun")){ sun.getStyleClass().add("now");}
        if(Calendar.getInstance().getTime().toString().split(" ")[0].equalsIgnoreCase("Mon")){ mon.getStyleClass().add("now");}
        if(Calendar.getInstance().getTime().toString().split(" ")[0].equalsIgnoreCase("Tue")){ tue.getStyleClass().add("now");}
        if(Calendar.getInstance().getTime().toString().split(" ")[0].equalsIgnoreCase("Wed")){ wed.getStyleClass().add("now");}
        if(Calendar.getInstance().getTime().toString().split(" ")[0].equalsIgnoreCase("Thu")){ thu.getStyleClass().add("now");}
        if(Calendar.getInstance().getTime().toString().split(" ")[0].equalsIgnoreCase("Fri")){ fri.getStyleClass().add("now");}
        if(Calendar.getInstance().getTime().toString().split(" ")[0].equalsIgnoreCase("Sat")){ sat.getStyleClass().add("now");}
        
        ToggleGroup tg = new ToggleGroup();
        
        int startColumn = findFirstDay(calendar);
        
        int start = 1;
        for(int row = 3; row < 9; row++){
           
            for(int column = startColumn ; column < 7;column++){
                
                ToggleButton day  = new ToggleButton(""+calendar.getTime().getDate());
                day.setPrefSize(40, 40);
                day.setTooltip(new ToolTip("Double click to schedule an event", 200, 40));
                day.setToggleGroup(tg);
                
                JBCalendarDate dat = new JBCalendarDate(getYear()+"-"+
                            getMonthIndex(MONTH_OF_YEAR.substring(0, 3))+"-"+day.getText());
                day.setOnAction((ActionEvent event) -> {
                    
                });
                
                day.setOnMouseClicked((MouseEvent event) -> {
                    if(event.getClickCount() == 2){
//                        new UpdateEventSchedule(day, dat, null);
                    }
                });
                
                HBox graphic = new HBox();
                //graphic.prefWidthProperty().bind(day.widthProperty().subtract(0));
                graphic.setAlignment(Pos.CENTER_RIGHT);
                graphic.setPadding(new Insets(0));
                
                Button eventCount  = new Button("");
                
                RadialGradient gradient1 = new RadialGradient(0, 0, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
                                new Stop(0, Color.web("#adadad")),
                                new Stop(1, Color.web("#adadad"))
                        });
                Label cnt = new Label();
                eventCount.setGraphic(new StackPane(new Circle(0, 0, 8, gradient1), cnt));
                eventCount.setPrefSize(15, 5);
                eventCount.getStyleClass().add("event-button");
                
                day.setContentDisplay(ContentDisplay.TOP);
                day.setGraphic(graphic);
                day.setGraphicTextGap(1);

                if(isToday(calendar, Calendar.getInstance())){
                    day.getStyleClass().add("today");
                    tg.selectToggle(day);
                    
                }else if(getDayIndex(calendar) == 0 || getDayIndex(calendar) == 6){
                    day.getStyleClass().add("weekend-button");
                    
                }else{
                    day.getStyleClass().add("day-button");
                    
                }
                
                new Thread(() -> {
                    
                    Platform.runLater(() -> {
                        
                        ObservableList<Event> events = DBQuery.getAllAgenda();
                        int count = 0;
                        for(Event event: events){
                            if(event.getStart().split(" ")[0].equals(getDateFromCalendar(dat.calendarInstance))){
                                if(event.getType().equalsIgnoreCase("public")){
                                    count++;
                                }else{
//                                    if(event.getEmployeeID().equalsIgnoreCase(LoginWindow.ACTIVE_USER.getUserID())){
//                                        count++;
//                                    }
                                }
                            }
                        }
                        if(count > 0){
                            
                            cnt.setText(""+count);
                            eventCount.setOnAction((ActionEvent event) -> {
//                                if(showFullView){
//                                    new ScheduledEvents( day, dat);
//                                }else{
//                                    new ScheduledEvents(day, dat);
//                                }
                            });
                            
                            eventCount.setTooltip(new ToolTip(count+" event(s) scheduled for this day.", 200, 40));
                            graphic.getChildren().add(eventCount);
                        }
                    });
                    
                }).start();
                
                String mn = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.ENGLISH)+" "+ getYear();
                
                if(mn.equalsIgnoreCase(monthOfYear.getText()) && (start <= lastDayOfMonth)){
                    
                    if(start <= lastDayOfMonth){
                        CALENDAR_GRID.add(day, column, row);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        start++;
                    }
                    
                }
            }
            startColumn = 0;
        }
        tg.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if (newValue == null) {
                tg.selectToggle(oldValue);
            }
        });
        
        CURRENTLY_SELECETED_MONTH.add(Calendar.DAY_OF_MONTH, -1);
        
    }
    
    

    public static int findFirstDay(Calendar calendar){
    
        for(int column = 0; column <= 7; column++){
            if(calendar.get(Calendar.DAY_OF_MONTH) == 1){//-- First day of month

                if(getDayIndex(calendar) == column){
                    return column;
                }
            }
        }
        return 0;
    }
    
    public  void refresh(){
         
        rollDateTo(CURRENTLY_SELECETED_MONTH);
    }

    public  void today(){
        
        CURRENTLY_SELECETED_MONTH = Calendar.getInstance();
        CURRENTLY_SELECETED_MONTH.set(Calendar.DAY_OF_MONTH, 1);
            
        rollDateTo(CURRENTLY_SELECETED_MONTH);
    }
    public  void rollForward(){
       
        CURRENTLY_SELECETED_MONTH.add(Calendar.MONTH, 1);
        CURRENTLY_SELECETED_MONTH.set(Calendar.DAY_OF_MONTH, 1);
         
        rollDateTo(CURRENTLY_SELECETED_MONTH);
    }
    
    public  void rollBack(){
        
        System.out.println(CURRENTLY_SELECETED_MONTH.getTime());
        
        CURRENTLY_SELECETED_MONTH.add(Calendar.MONTH, -1);
        CURRENTLY_SELECETED_MONTH.set(Calendar.DAY_OF_MONTH, 1);
         
        rollDateTo(CURRENTLY_SELECETED_MONTH);
    }
    
    public static String  getDateFromCalendar(Calendar calendar){
        String []date = calendar.getTime().toString().split(" ");
        return date[5]+"-"+getMonthIndex(date[1])+"-"+date[2];
        
    }
    
    public static String  getTimeFromCalendar(Calendar calendar){
        return calendar.getTime().toString().split(" ")[3];
        
    }
    
    public static boolean isToday(Calendar calendar1, Calendar calendar2){
        
        return (getDateFromCalendar(calendar1).equalsIgnoreCase(getDateFromCalendar(calendar2)));
    }
    
    public  String getYear(){
        return (CURRENTLY_SELECETED_MONTH.getTime().toString()).split(" ")[5];
    }

    public  String getDay(){
        return (CURRENTLY_SELECETED_MONTH.getTime().toString()).split(" ")[0];
    }
    
    public static int getDayIndex(Calendar calendar){
        
        String day = (calendar.getTime().toString()).split(" ")[0];
        
        if(day.equalsIgnoreCase("Sun")){return 0;}
        else if(day.equalsIgnoreCase("Mon")){return 1;}
        else if(day.equalsIgnoreCase("Tue")){return 2;}
        else if(day.equalsIgnoreCase("Wed")){return 3;}
        else if(day.equalsIgnoreCase("Thu")){return 4;}
        else if(day.equalsIgnoreCase("Fri")){return 5;}
        else {return 6;}//sat
        
    }
    
    public static int getMonthIndex(String month){
        //System.out.println(month);
        
        if(month.equalsIgnoreCase("jan")){return 1;}
        else if(month.equalsIgnoreCase("feb")){return 2;}
        else if(month.equalsIgnoreCase("mar")){return 3;}
        else if(month.equalsIgnoreCase("apr")){return 4;}
        else if(month.equalsIgnoreCase("may")){return 5;}
        else if(month.equalsIgnoreCase("jun")){return 6;}
        else if(month.equalsIgnoreCase("jul")){return 7;}
        else if(month.equalsIgnoreCase("aug")){return 8;}
        else if(month.equalsIgnoreCase("sep")){return 9;}
        else if(month.equalsIgnoreCase("oct")){return 10;}
        else if(month.equalsIgnoreCase("novr")){return 11;}
        else {return 12;}
        
    }
 
}
