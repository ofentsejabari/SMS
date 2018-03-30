package schooladministration;


import entry.SMS;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author ofentse
 */
public class GradeScheme {
    SimpleStringProperty id, symbol, lowerBound, upperBound, points;

    public GradeScheme() {
        id = new SimpleStringProperty("");
        symbol = new SimpleStringProperty("");
        lowerBound = new SimpleStringProperty("");
        upperBound = new SimpleStringProperty("");
        points = new SimpleStringProperty("");
    }
    
    public GradeScheme(String ID, String symbol, String lowerbound, String upperbound, String points){
        
       this.id = new SimpleStringProperty(ID);
       this.symbol = new SimpleStringProperty(symbol);
       this.lowerBound = new SimpleStringProperty(lowerbound);
       this.upperBound = new SimpleStringProperty(upperbound);
       this.points = new SimpleStringProperty(points);
    }
    
    public String getSymbol(){return symbol.get();}
    public void setSymbol(String id){symbol.set(id);}
    
    public String getId(){return id.get();}
    public void setId(String id){this.id.set(id);}
    
    public String getLowerBound(){return lowerBound.get();}
    public void setLowerBound(String id){lowerBound.set(id);}
    
    public String getUpperBound(){return upperBound.get();}
    public void setUpperBound(String id){upperBound.set(id);}
    
    public String getPoints(){return points.get();}
    public void setPoints(String id){points.set(id);}
    
    public static String getSymbol(double score){
        
        ObservableList<GradeScheme> grades = SMS.dbHandler.getGrades();
        for(GradeScheme grade: grades){
            
            //-- lowerBound <= score <= upperBound
            if(score >= Double.parseDouble(grade.getLowerBound()) && 
               score <= Double.parseDouble(grade.getUpperBound())){
                return grade.getSymbol();
            }
        }
        return "-";
    }
}
