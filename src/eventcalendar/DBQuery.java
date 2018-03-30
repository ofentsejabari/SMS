package eventcalendar;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static mysqldriver.MySQLHander.STATEMENT;

/**
 *
 * @author JABARI
 */
public class DBQuery {

    public static ObservableList<Event> getAllAgenda(){
        ObservableList<Event> agenda = FXCollections.observableArrayList();
        try{
            String query = "SELECT `id`, `employeeID`, `title`, `allDay`, `start`, `end`, `location`,"
                        + "`description`, `status`, `date`, `eventType`"
                        + " FROM `agenda` ORDER BY `start` ASC";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            while(result.next()){
                agenda.add(new Event(result.getString("id"),result.getString("employeeID"),
                        result.getString("title"), result.getString("allDay"),
                        result.getString("start"),result.getString("end"),
                        result.getString("location"), result.getString("description"),
                        result.getString("status"), result.getString("date"), result.getString("eventType")));
            }
            return agenda;
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return agenda;
        }
    }
    
    
    public static Event getAgendaByID(String id){
        
        try{
            String query = "SELECT `id`, `employeeID`, `title`, `allDay`, `start`, `end`, `location`,"
                        + "`description`, `status`, `date`, `eventType`"
                        + " FROM `agenda` WHERE `id` = '"+id+"'";
           
            ResultSet result = STATEMENT.executeQuery(query);
            
            if(result.next()){
                return new Event(result.getString("id"),result.getString("employeeID"),
                        result.getString("title"), result.getString("allDay"),
                        result.getString("start"),result.getString("end"),
                        result.getString("location"), result.getString("description"),
                        result.getString("status"), result.getString("date"), result.getString("eventType"));
            }
            return new Event();
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return new Event();
        }
    }
    
    public static boolean updateAgenda(Event agenda, boolean update){
        
        try{
            String query;
            if(!update){
                query = "INSERT INTO `agenda` (`id`, `employeeID`, `title`, `allDay`, `start`, `end`, `location`,"
                        + "`description`, `status`, `date`, `eventType`)"
                        + " VALUES ('0', '"+agenda.getEmployeeID()+"', '"+agenda.getTitle()+"',"
                        + " '"+agenda.getAllDay()+"', '"+agenda.getStart()+"', '"+agenda.getEnd()+"',"
                        + " '"+agenda.getLocation()+"', '"+agenda.getDescription()+"', '"+agenda.getStatus()+"',"
                        + " NOW(), '"+agenda.getType()+"')";
                
                return STATEMENT.executeUpdate(query) > 0;
                
            }else{
                query = "UPDATE `agenda` SET `description`='"+agenda.getDescription()+"',"
                        + " `title`='"+agenda.getTitle()+"', `allDay`='"+agenda.getAllDay()+"',"
                        + " `start`='"+agenda.getStart()+"', `end`='"+agenda.getEnd()+"',"
                        + " `location`='"+agenda.getLocation()+"', `eventType` = '"+agenda.getType()+"'"
                        + " WHERE `id`= '"+agenda.getID()+"'";
                
                return STATEMENT.executeUpdate(query) > 0;
            }
        } 
        catch(Exception ex){
             System.out.println(ex.getMessage());
             return false;
        }
    }
    
    public static boolean deleteAgenda(String id){
        
        try{
            String query = "DELETE FROM `agenda` WHERE `id` = '"+id+"'";
            
            return STATEMENT.executeUpdate(query) > 0;
            
        }catch(Exception ex){
             return false;
        }
    }
}
