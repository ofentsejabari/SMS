package eventcalendar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author JABARI
 */
public class JBCalendarDate {
    
    Calendar calendarInstance;//-- in format - yy,mm,dd,hh,mm,ss
    public static NumberFormat fomatter = new DecimalFormat("#0.00");
   
    
    public JBCalendarDate(String lcdate, String lctime){
        try{
            String lcd[] = (lcdate.replace("-", ",")).split(",");
            String lct[] = (lctime.replace(":", ",")).split(",");

            calendarInstance = Calendar.getInstance();
        
            calendarInstance.set(Integer.parseInt(lcd[0]),Integer.parseInt(lcd[1])-1,Integer.parseInt(lcd[2]),
                                 Integer.parseInt(lct[0]),Integer.parseInt(lct[1]), 0);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public JBCalendarDate(String lcdate){
        try{
            String lcd[] = (lcdate.replace("-", ",")).split(",");

            calendarInstance = Calendar.getInstance();
        
            calendarInstance.set(Integer.parseInt(lcd[0]),Integer.parseInt(lcd[1])-1,Integer.parseInt(lcd[2]),
                                 0, 0, 0);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Get the number of hours between tf1 and tf2
     * @param tf1
     * @param tf2
     * @return 
     */
    public static double getHours(JBCalendarDate tf1, JBCalendarDate tf2){
        System.out.println(tf1.calendarInstance.getTime()+"===="+ tf2.calendarInstance.getTime());
        if(tf1.calendarInstance.before(tf2.calendarInstance)){
            return Double.parseDouble(fomatter.format((((tf2.calendarInstance.getTimeInMillis()- 
                    tf1.calendarInstance.getTimeInMillis())/1000.0)/60.0)/60.0));
        }else{
            return Double.parseDouble(fomatter.format((((tf1.calendarInstance.getTimeInMillis()- 
                    tf2.calendarInstance.getTimeInMillis())/1000.0)/60.0)/60.0));
        }
    }
    
    public static String getHoursMunites(JBCalendarDate tf1, JBCalendarDate tf2){
        
        if(tf1.calendarInstance.before(tf2.calendarInstance)){
            
            long hrs = ((((tf2.calendarInstance.getTimeInMillis()- 
                    tf1.calendarInstance.getTimeInMillis())/1000)/60)/60);
            
            long minutes = ((((tf2.calendarInstance.getTimeInMillis()- 
                    tf1.calendarInstance.getTimeInMillis())/1000)/60) % 60);
            if(hrs == 0){
                return  minutes+" Minutes";
            }else{
                return hrs+" Hour(s) - "+ minutes+" Minutes";
            }
        }else{
            long hrs = ((((tf1.calendarInstance.getTimeInMillis()- 
                    tf2.calendarInstance.getTimeInMillis())/1000)/60)/60);
            
            long minutes = ((((tf1.calendarInstance.getTimeInMillis()- 
                    tf2.calendarInstance.getTimeInMillis())/1000)/60) % 60);
            
            if(hrs == 0){
                return  minutes+" Minutes";
            }else{
                return hrs+" Hour(s) - "+ minutes+" Minutes";
            }
        }
    }
    
    /**
     * Return the number of days between fromDate - toDate or toDate - FromDate
     * @param fromDate
     * @param toDate
     * @return 
     */
    public static int getDays(JBCalendarDate fromDate, JBCalendarDate toDate){
        
        if(fromDate.calendarInstance.before(toDate.calendarInstance)){
            return (int) (((((toDate.calendarInstance.getTimeInMillis()- 
                    fromDate.calendarInstance.getTimeInMillis())/1000)/60)/60)/24);
        }else{
            return (int) (((((fromDate.calendarInstance.getTimeInMillis()- 
                    toDate.calendarInstance.getTimeInMillis())/1000)/60)/60)/24);
        }
    }
    
    public static boolean inBetween(JBCalendarDate start, JBCalendarDate end){
        
        long startTime = ((((start.calendarInstance.getTimeInMillis())/1000)/60)/60);
        long endTime = ((((end.calendarInstance.getTimeInMillis())/1000)/60)/60);
        Calendar today = Calendar.getInstance();
        
        System.out.println(start.calendarInstance.getTimeInMillis() +"---"
                +Calendar.getInstance().getTimeInMillis()+"---"
                + end.calendarInstance.getTimeInMillis());
        
        System.out.println(startTime +"---"+today+"---"+ endTime);
        
        return  ((start.calendarInstance.getTimeInMillis() <= today.getTimeInMillis()) 
                && (today.getTimeInMillis() <= end.calendarInstance.getTimeInMillis()));
    }
    
    
    public static String getDaysHours(JBCalendarDate fromDate, JBCalendarDate toDate){
        
        if(fromDate.calendarInstance.before(toDate.calendarInstance)){
            return  (((((toDate.calendarInstance.getTimeInMillis()- 
                    fromDate.calendarInstance.getTimeInMillis())/1000)/60)/60)/24)+" Day(s) - "+
                    (((((toDate.calendarInstance.getTimeInMillis()- 
                    fromDate.calendarInstance.getTimeInMillis())/1000)/60)/60)%24.0)+" Hour(s)";
        }else{
            return (((((fromDate.calendarInstance.getTimeInMillis()- 
                    toDate.calendarInstance.getTimeInMillis())/1000)/60)/60)/24)+" Day(s) - "+
                    (((((fromDate.calendarInstance.getTimeInMillis()- 
                    toDate.calendarInstance.getTimeInMillis())/1000)/60)/60)%24.0)+" Hour(s)";
        }
    }
    
    public static ObservableList<String> getDatesBetween(JBCalendarDate fromDate, JBCalendarDate toDate, boolean includeWeekend, boolean includeHoliday){
        
        ObservableList<String> dates = FXCollections.observableArrayList();
        
        if(fromDate.calendarInstance.after(toDate.calendarInstance)){
            //-- swap date
            JBCalendarDate temp = fromDate;
            fromDate = toDate;
            toDate = temp;
        }
        
        int days = getDays(toDate, fromDate);
        
        //System.out.println(days);
        for(int i = 0 ; i <= days; i++){
//            System.out.println(fromDate.calendarInstance.getTime());
            
            String fomattedDate = fromDate.calendarInstance.get(Calendar.YEAR)+"-"+
                                 (fromDate.calendarInstance.get(Calendar.MONTH)+1)+"-"+
                                  fromDate.calendarInstance.get(Calendar.DAY_OF_MONTH);
            
            if(includeHoliday && includeWeekend){
                dates.add(fomattedDate);
                
            }else if((includeHoliday) && !(includeWeekend)){//-- true && false
                if(!isWeekend(fromDate)){
                    dates.add(fomattedDate);
                }
            }else if(!(includeHoliday) && (includeWeekend)){//-- false && true
                if(!isHoliday(fomattedDate)){
                    dates.add(fomattedDate);
                }
            }else{
                //-- Not a holiday and not a weekend
                if(!isHoliday(fomattedDate) && !isWeekend(fromDate)){
                    dates.add(fomattedDate);
                }
            }
            
            fromDate.calendarInstance.add(Calendar.DAY_OF_WEEK, 1);
            
        }
        
        return dates;
    }
    
    public static boolean isWeekend(JBCalendarDate date){
        
        if(date.calendarInstance.getTime().toString().split(" ")[0].equalsIgnoreCase("sat") ||
            date.calendarInstance.getTime().toString().split(" ")[0].equalsIgnoreCase("sun")){
            
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isHoliday(String date){//-- 2017-04-24

        return (false);
        
    }
    
    public boolean before(JBCalendarDate tf1){
        
        if(calendarInstance.before(tf1.calendarInstance)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean after(JBCalendarDate tf1){
        
        if(calendarInstance.after(tf1.calendarInstance)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean isTheSameDate(JBCalendarDate tf1){
        
        return  (calendarInstance.compareTo(tf1.calendarInstance) == 0);
    }
    
    
    public static ObservableList<String> timeRange(){
        return FXCollections.observableArrayList("06:00", "06:30",
                "07:00", "07:30",
                "08:00", "08:30",
                "09:00", "09:30",
                "10:00", "10:30",
                "11:00", "11:30",
                "12:00", "12:30",
                "13:00", "13:30",
                "14:00", "14:30",
                "15:00", "15:30",
                "16:00", "16:30",
                "17:00", "17:30",
                "18:00", "18:30",
                "19:00", "19:30",
                "20:00", "20:30",
                "21:00", "21:30",
                "22:00", "22:30",
                "23:00", "23:30",
                "00:00", "00:30",
                "01:00", "01:30",
                "02:00", "02:30",
                "03:00", "03:30",
                "04:00", "04:30",
                "05:00", "05:30");
    }
    
}
