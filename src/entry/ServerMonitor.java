package entry;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import static entry.Footer.DB_CONN_ICON;
import static entry.SMS.dbHandler;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import mysqldriver.MySQLHander;
import javafx.scene.text.Text;
/**
 *
 * @author jabari
 */
public class ServerMonitor {
    private static ScheduledExecutorService scheduler;

    Text img1 = SMS.getGraphics(MaterialDesignIcon.LAN_CONNECT, "text-white", 20);
    Text img2 = SMS.getGraphics(MaterialDesignIcon.LAN_CONNECT, "text-success", 20);
    
    public ServerMonitor() {
        scheduler = Executors.newScheduledThreadPool(5);
    }
    
    public void stop(){
        scheduler.shutdown();
    }
    
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void startServerMonitor(){
        
        scheduler.scheduleAtFixedRate(() -> {
              Platform.runLater(() -> {
                  DB_CONN_ICON.setGraphic(img1);
              });
        }, 0, 2, TimeUnit.SECONDS); 
        
        scheduler.scheduleAtFixedRate(() -> {
              Platform.runLater(() -> {
                  DB_CONN_ICON.setGraphic(img2);
              });
        }, 1, 2, TimeUnit.SECONDS); 
        
        
        //-- check database server status -- 
        scheduler.scheduleAtFixedRate(() -> {
            new Thread(() -> {
                dbHandler = new MySQLHander();
                
                    if(dbHandler.CONNECTION != null){
                        Platform.runLater(() -> {
                            img1 = SMS.getGraphics(MaterialDesignIcon.LAN_CONNECT, "text-success", 20);
                        });
                    }else{
                        Platform.runLater(() -> {
                            SMS.getGraphics(MaterialDesignIcon.LAN_CONNECT, "text-error", 20);
                        });
                    }
                
            }).start();
        }, 0, 60, TimeUnit.SECONDS);  
    }
    
    public static String getCurrentTime(){
         Date date = new Date();
         return String.format("%tc",date);
    }
}
