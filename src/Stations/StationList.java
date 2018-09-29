package Stations;

import FareSystem.CardMachine;

import java.io.Serializable;
import java.util.ArrayList;

public class StationList implements Serializable {

    private static final long serialVersionUID = 2912745;

    private ArrayList<Station> allStations;

    public StationList(){
        this.allStations = new ArrayList<>();
    }

    public boolean addStation(Station newStation){
        for (Station existingStation : allStations ) {
            if (existingStation.equals(newStation)){
                return false;
            }
        }
        this.allStations.add(newStation);
        return true;
    }

    /***
     * This method attempts to add a station to the existing list.
     *
     * If the Station name exists already, it will return false.
     * Otherwise, it will add a new Station
     *
     * @param name the name of the new Station being added.
     * @return boolean to determine success of method
     */
    public boolean addBusStation(String name, int xCoordinate, int yCoordinate) {
        for (Station existingStation : allStations ) {
            if (existingStation.getName().equals(name)){
                return false;
            }
        }
        BusStation newBusStation = new BusStation(xCoordinate, yCoordinate, name);
        this.allStations.add(newBusStation);
        return true;
    }

    /***
     * This method attempts to add a station to the existing list.
     *
     * If the Station name exists already, it will return false.
     * Otherwise, it will add a new Stationdd
     *
     * @param name the name of the new Station being added.
     * @return boolean to determine success of method
     */
    public boolean addSubwayStation(String name, int xCoordinate, int yCoordinate) {
        for (Station existingStation : allStations ) {
            if (existingStation.getName().equals(name)){
                return false;
            }
        }
        SubwayStation newSubwayStation = new SubwayStation(xCoordinate, yCoordinate, name);
        this.allStations.add(newSubwayStation);
        return true;
    }

    /***
     * This method attempts to find and return an existing station, based on a given String name.
     *
     * If the Station name does not exist, it will return null. Otherwise, it will
     * return the Station object requested.
     *
     * @param name the name of the Station being looked for
     * @return the Station requested if it exists
     */
    public Station findStation(String name){
        for (Station existingStation : allStations ) {
            if (existingStation.getName().equals(name)){
                return existingStation;
            }
        }
        return null;
    }

    /**
     * Returns the entrance CardMachine with the given ID
     * @param cmID ID of the CardMachine
     * @return returns the entrance CardMachine with the ID that was passed to the method
     */
    public CardMachine findEntrance(int cmID){
        for(Station s: this.allStations){
            for(CardMachine cm: s.getEntrances()){
                if (cm.getId() == cmID){
                    return cm;
                }
            }
        }
        return null;
    }

    /**
     * Returns the exit CardMachine with the given ID
     * @param cmID ID if the CarMachine
     * @return returns the exit CardMachine with the ID that was passed to the method
     */
    public CardMachine findExit(int cmID){
        for(Station s: this.allStations){
            for(CardMachine cm: s.getExits()){
                if (cm.getId() == cmID){
                    return cm;
                }
            }
        }
        return null;
    }
}
