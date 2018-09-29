package Stations;

import FareSystem.CardMachine;

import java.io.Serializable;
import java.util.ArrayList;

public class Station implements Serializable {

    private static int idCount = 000001;
    private static final long serialVersionUID = 875687;
    /** location for this station */

    private int x;
    private int y;

    private boolean flatRate;

    private int id;

    /**
     * lists of this Station's entrance and exits
     */
    private int cardMachineCount;
    public ArrayList<CardMachine> entrances;
    public ArrayList<CardMachine> exits;

    private String name;


    /**
     * constructs an instance of Station
     * @param flatRate whether this Station has a flatRate or not
     */
    public Station(boolean flatRate){}{
        this.id = idCount;
        idCount++;
        this.flatRate = flatRate;
    }

    /**
     * onstructs an instance of Station
     * @param x this Station's first coordinate
     * @param y this Station's second coordinate
     * @param flatRate if this station has a flatRate
     * @param name the name of this Station
     */
    public Station(int x, int y, boolean flatRate, String name){
        this.cardMachineCount = 0;
        this.id =idCount;
        idCount++;
        this.x = x;
        this.y = y;
        this.flatRate = flatRate;
        this.name = name;
        this.entrances = new ArrayList <>();
        this.exits = new ArrayList <>();
    }

    public ArrayList<CardMachine> getEntrances(){
        return this.entrances;
    }

    public ArrayList<CardMachine> getExits(){
        return this.exits;
    }

    /**
     * retursn this Station's id
     * @return this station's id
     */
    public int getId(){ return this.id;}

    /**
     * retursn the name of this Station
     * @return String name of the Station,
     */
    public String getName() {return this.name;}

    /**
     * returns this Station first coordinate
     * @return this station's first coordinate
     */
    public int getX(){return this.x;}

    /**
     * returns this Station second coordinate
     * @return this station's second coordinate
     */
    public int getY(){return this.y;}

    /**
     * returns true if this Station is flatrated
     * @return true if this Station is flatrated
     */
    public boolean isFlatRate(){
        return this.flatRate;
    };

    /**
     * adds a new entrance CardMachine to the station
     */
    public void addEntrace(){
        this.entrances.add(new CardMachine(true, this));
        this.cardMachineCount++;
    }

    /**
     * adds a new exit CardMachine to this Station
     */
    public void addExits(){
        this.exits.add(new CardMachine(false, this));
        this.cardMachineCount++;
    }

    /**
     * the String representation of this Station
     * @return the String representation of this Station
     */
    public String toString() {
        return this.name;
    }
}
