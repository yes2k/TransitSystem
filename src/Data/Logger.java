package Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Logger implements Serializable{

    private static final long serialVersionUID = 555555;

    ArrayList<String> events;

    /**
     * constructs a new Logger
     * @param events this Logger's event statements
     */
    public Logger() {
    this.events = new ArrayList<>();
    }

    /**
     * constructs a new Logger
     * @param string this Logger's event message
     */
    public void addToLog(String string){
        this.events.add(Calendar.getInstance().getTime().toString() + "  " + string);
    }

    /**
     * returns this Logger's event messages
     * @return this Logger's event messages
     */
    public ArrayList<String> getEvents() {
        return this.events;
    }
}
