package Data;

import Main.TransitSystem;
import TransitUsers.Trip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * TransitData class keeps track of the trips taken within a TransitSystem. It also saves the total fare collected
 */

public class TransitData implements Serializable {

    private static final long serialVersionUID = 473658;

    private ArrayList<Trip> allTrips;
    private CardIDCounter cardIDCounter;

    /**
     * Constructs a new TransitData Object
     */
    public TransitData(TransitSystem ts){
        this.allTrips = new ArrayList<>();
        this.cardIDCounter = new CardIDCounter();
    }

    public CardIDCounter getCardIDCounter(){
        return this.cardIDCounter;
    }

    public ArrayList<Trip> getAllTrips(){
        return this.allTrips;
    }


    /**
     * Returns the summation of all fares collected.
     *
     * @return the sum of all fares
     */
    public double totalFareAmount(){
        double totalFare = 0;
        for(Trip t: this.allTrips){
            totalFare += t.getFare();
        }
        return totalFare;
    }

    public double getDailyFare(int day, int month, int year){
        double fare = 0;
        for(Trip t: this.allTrips){
            if((t.getStarDate().get(Calendar.DATE) == day) && (t.getStarDate()
                    .get(Calendar.MONTH) == month) && (t.getStarDate().get(Calendar.YEAR) == year)){
                fare+= t.getFare();
            }
        }
        return fare;
    }
    public double getMonthlyFareAmount(int month, int year){
        double totalFare = 0;
        for (Trip t: this.allTrips){
            if((t.getStarDate().get(Calendar.MONTH) == month) && (t.getStarDate().get(Calendar.YEAR) == year)){
                totalFare = totalFare + t.getFare();
            }
        }
        return totalFare;
    }


    public double getYearlyFareAmount(int year){
        double totalFare = 0;
        for (Trip t: this.allTrips){
            boolean correctyear = (year == t.getStarDate().get(Calendar.YEAR));
            if(correctyear){
                totalFare = totalFare + t.getFare();
            }
        }
        return totalFare;
    }
}
