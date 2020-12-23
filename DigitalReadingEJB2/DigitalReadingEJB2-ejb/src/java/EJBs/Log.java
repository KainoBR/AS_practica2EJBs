/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBs;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 *
 * @author Miki
 */
@Singleton
@LocalBean
public class Log {

    private List<String> logs;
    int size;

    public Log() {
        size=0;
        this.logs = new ArrayList<>();
    }
    
    @Resource
    TimerService userTimer;
    
    //la variable loop permite indicar al usuario si quiere, o no, que se 
    //repita el timer
    private boolean loop;

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setMiliseconds(int miliseconds) {
        this.miliseconds = miliseconds;
    }
    private long miliseconds;

    public void setUserTimer() {
        userTimer.createSingleActionTimer(miliseconds,
                new TimerConfig());
    }

    @Timeout
    public void userTimeout(Timer timer) {
        addLogs(" --- User time out --- ");
        size++; // para que no afecte al @Schedule
        if(loop){
             userTimer.createSingleActionTimer(miliseconds,
                new TimerConfig());
        }
    }
    
    /**
     * Timer automático que comprueba la inactividad
     *
     * @param timer
     */
    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void checkLog(Timer timer) {
        if (size == logs.size()) {
            this.addLogs("ningun usuario a utilizado el sistema por 5 segundos");
        } else {
            size= logs.size();
        }
    }

    /**
     * Vacia el List<String> de logs
     */
    public void clearLogs() {
        logs.clear();
    }

    /**
     * @return Todos los logs en formato List<String>
     */
    public List<String> getLogs() {
        return logs;
    }

    public void addLogs(String newlog) {
        logs.add(newlog);
    }

}
