
package schooladministration;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ofentse
 */
public class ExtraCurriculaActivity {
    SimpleStringProperty id, name, coach, type;

    public ExtraCurriculaActivity(String id, String name, String coach, String type) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.coach = new SimpleStringProperty(coach);
        this.type = new SimpleStringProperty(type);
    }

    public ExtraCurriculaActivity() {
        this.id = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.coach = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getCoach() {
        return coach.get();
    }

    public String getType() {
        return type.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setCoach(String coach) {
        this.coach.set(coach);
    }

    public void setType(String type) {
        this.type.set(type);
    }
    
    
    
    
    
}
