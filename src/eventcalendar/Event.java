package eventcalendar;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Event {
    SimpleStringProperty ID, employeeID, title, addDay, start,
                        end, location, description, status, date, type;

    public Event() {
        ID = new SimpleStringProperty("");
        employeeID = new SimpleStringProperty("");
        title = new SimpleStringProperty("");
        addDay = new SimpleStringProperty("");
        start = new SimpleStringProperty("");//
        end = new SimpleStringProperty("");
        location = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        status = new SimpleStringProperty("");
        date = new SimpleStringProperty("");
        type = new SimpleStringProperty("");
    }
    
    public Event(String ID, String employeeID, String title, String allDay, String start, 
            String end, String location, String description, String status, String date, String type){
        
       this.ID = new SimpleStringProperty(ID);
       this.employeeID = new SimpleStringProperty(employeeID);
       this.title = new SimpleStringProperty(title);
       this.addDay = new SimpleStringProperty(allDay);
       this.start = new SimpleStringProperty(start);
       this.end = new SimpleStringProperty(end);
       this.location = new SimpleStringProperty(location);
       this.description = new SimpleStringProperty(description);
       this.status = new SimpleStringProperty(status);
       this.date = new SimpleStringProperty(date);
       this.type = new SimpleStringProperty(type);
    }
    
    
    public String getEmployeeID(){return employeeID.get();}
    public void setEmployeeID(String id){employeeID.set(id);}
    
    public String getID(){return ID.get();}
    public void setID(String id){ID.set(id);}
    
    public String getDescription(){return description.get();}
    public void setDescription(String id){description.set(id);}
    
    public String getAllDay(){return addDay.get();}
    public void setAllDay(String lnam){addDay.set(lnam);}
    
    public String getStart(){return start.get();}
    public void setStart(String mnam){start.set(mnam);}
    
    public String getTitle(){  return title.get();}
    public void   setTitle(String id){title.set(id);}
    
    public String getEnd(){    return end.get();}
    public void   setEnd(String titl){end.set(titl);}
    
    public String getLocation(){    return location.get();}
    public void   setLocation(String fnam){location.set(fnam);}
    
    public String getDate(){    return date.get();}
    public void   setDate(String lnam){date.set(lnam);}
    
    public String getType(){    return type.get();}
    public void   setType(String lnam){type.set(lnam);}
    
    public String getStatus(){    return status.get();}
    public void   setStatus(String mnam){status.set(mnam);}
    
}
