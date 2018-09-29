package TransitUsers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import FareSystem.CardMachine;


public class Trip implements Serializable {

    private static final long serialVersionUID = 295624;
    private CardMachine start = null;
    private CardMachine end = null;

    private Calendar starDate = null;
    private Calendar endDate = null;

    private Date customStart;
    private Date customEnd;

    private double fare;


    /**
     * constructs a new Trip
     */
    public Trip(){
        this.fare = 0;
    }

    /**
     * returns a String representation of this Trip
     * @return a String representation of this Trip
     */
    public String toString() {
        if(this.end == null){//checks if start is null
            return "Start: " + this.start.toString() + " on " + this.customStart.toString() +
                    "\n" + "End: trip did not end " + "\n" + "Fare: " + this.fare;
        }
        if(this.start == null) {//checks if end is null
           return "Start: Trip did not start " +
                    "\n" + "End: " + this.end.toString() + " on " + this.customEnd.toString() + "\n" + "Fare: " + this.fare;
        }
        return "Start: " + this.start.toString() + " on " + this.customStart.toString() +
                "\n" + "End: " + this.end.toString() + " on " + this.customEnd.toString() + "\n" + "Fare: " + this.fare;
    }

    /**
     * sets the stating point of a trip
     * @param cardMachine the CardMachine starting of the a trip
     */
    public void setStart(CardMachine cardMachine){
        this.start = cardMachine;
        Calendar d = Calendar.getInstance();
        this.starDate = d;
        this.customStart = this.starDate.getTime();
//        this.sDate.set(this.starDate.getTime().toString());
    }

    public void setStart(CardMachine cardMachine, Double fare){
        this.start = cardMachine;
        Calendar d = Calendar.getInstance();
        this.starDate = d;
        addFare(fare);
        this.customStart = this.starDate.getTime();
    }
    /**
     * sets the ending point of a trip
     * @param cardMachine the CardMachine ending of the a trip
     */

    public void setEnd(CardMachine cardMachine){
        this.end = cardMachine;
        Calendar d = Calendar.getInstance();
        this.endDate = d;
        this.customEnd = this.endDate.getTime();
    }

    public void setEnd(CardMachine cardMachine, Double fare){
        this.end = cardMachine;
        Calendar d = Calendar.getInstance();
        this.endDate = d;
        addFare(fare);
        this.customEnd = this.endDate.getTime();
    }

    /**
     * returns this trip CardMachine exit
     * @return this trip CardMachine exit
     */
    public CardMachine getEnd() { return end; }

    /**
     * returns this trip CardMachine entrance
     * @return this trip CardMachine entrance
     */
    public CardMachine getStart() { return start; }

    /**
     * returns this trip end Date
     * @return this trip end Date
     */
    public Calendar getEndDate() { return endDate; }

    /**
     * returns this trip Start Date
     * @return this trip Start Date
     */
    public Calendar getStarDate() { return starDate; }

    public void addFare(Double fare){
        this.fare += fare;
    }

    public double getFare() {
        return fare;
    }

    public Date getCustomStart() {
        return customStart;
    }

    public Date getCustomEnd() {
        return customEnd;
    }
}
