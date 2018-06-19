package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class Term {
    SimpleStringProperty termID, description, from, to, year, currentTerm;

    public Term() {
        termID = new SimpleStringProperty("");
        description = new SimpleStringProperty("");
        from = new SimpleStringProperty("");
        to = new SimpleStringProperty("");
        year = new SimpleStringProperty("");
        currentTerm = new SimpleStringProperty("");
    }
    
    public Term(String termID, String description, String from, String to, String year, String currentTerm){
        
        this.termID = new SimpleStringProperty(termID);
        this.description = new SimpleStringProperty(description);
        this.from = new SimpleStringProperty(from);
        this.to = new SimpleStringProperty(to);
        this.year = new SimpleStringProperty(year);
        this.currentTerm = new SimpleStringProperty(currentTerm);
    }

    public String getTermID(){return termID.get();}
    public void setTermID(String id){termID.set(id);}
    
    public String getDescription(){return description.get();}
    public void setDescrption(String id){description.set(id);}
    
    public String getStart(){return from.get();}
    public void setFrom(String titl){from.set(titl);}
    
    public String getEnd(){return to.get();}
    public void setTo(String fnam){to.set(fnam);}
    
    public String getYear(){return year.get();}
    public void setYear(String mnam){year.set(mnam);}
 
    public String getCurrentTerm(){return currentTerm.get();}
    public void setCurrentTerm(String mnam){currentTerm.set(mnam);}
}
