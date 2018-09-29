package FareSystem;

import Stations.Station;

import java.io.Serializable;

public class CardMachine implements Serializable {


    private static final long serialVersionUID = 128763;

    private boolean entrance;

    private Station station;

    private int id;

    static int cardMachineID = 1000;

    /**
     * constructs a new CardMachine
     * @param entrance determines if this cardMachine is an entrance or an exit
     * @param station determines what Stations this CardMachine belongs to
     */
    public CardMachine(boolean entrance, Station station){
        this.id = cardMachineID;
        CardMachine.cardMachineID +=1;
        this.entrance = entrance;
        this.station = station;
    }

    /**
     * returns a String representation of this CardMachine
     * @return the String representation of this CardMachine
     */
    public String toString() {
        StringBuilder stringRep = new StringBuilder();
        stringRep.append("ID: " + Integer.toString(this.id) + " @ " +  this.station.toString());
        return stringRep.toString();
    }

    /**
     * returns the Station which this CardMachine belongs to
     * @return thisCardMachine's station
     */
    public Station getStation(){
        return this.station;
    }

    /**
     * returns this CardMachine's id
     * @return this CardMachine's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * returns true if this CardMachine is an entrance
     * @return true if this CardMachine is an entrance
     */
    public boolean isEntrance(){
        return this.entrance;
    }
}
