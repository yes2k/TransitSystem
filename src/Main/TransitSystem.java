package Main;

import AdminUsers.AdminUserList;
import Data.CardIDCounter;
import Data.Logger;
import Data.TransitData;
import FareSystem.FareManager;
import FareSystem.TapManager;
import TransitUsers.*;
import TransitUsers.CardHolderList;
import Stations.StationList;

import java.io.*;

public class TransitSystem implements Serializable{

    private static final long serialVersionUID = 646745;
    /**
     * a FareManager Specific to this TransitSystem
     */
    private FareManager tm = new FareManager(2, 0.5, 6, 7200000);
    private TapManager tapManager = new TapManager();

    private AdminUserList adminUsers;
    private CardHolderList cardHolders;
    private StationList stations;
    private TransitData transitData;
    private Logger programlog;


    /**
     * Constructs a new TransitSystem
     */
    public TransitSystem(){
        this.adminUsers = new AdminUserList();
        this.cardHolders = new CardHolderList();
        this.stations = new StationList();
        this.transitData = new TransitData(this);
        this.programlog = new Logger();
    }

    public Logger getProgramLog() {
        return programlog;
    }

    public AdminUserList getAdminUsers() {
        return adminUsers;
    }

    public CardHolderList getCardHolders() {
        return cardHolders;
    }

    public StationList getStations() {
        return stations;
    }


    /**
     * this method returns this TransitSystem's FareManager
     * @return this TransitSystem's FareManager
     */
    public FareManager getFareManager(){
        return this.tm;
    }

    /**
     * this method returns this TransitSystem's TransitData
     * @return this TransitSystem's TransitData
     */
    public TransitData getTransitData() {
        return transitData;
    }

    /**
     * Adds a Trip to this TransitSystem's trips
     * @param t  to be added to this objects trips
     */
    public void addTrip(Trip t){
        this.transitData.getAllTrips().add(t);
    }

    public TapManager getTapManager(){
        return this.tapManager;
    }

    public CardIDCounter getCardIDCounter(){
        return this.transitData.getCardIDCounter();
    }

}
