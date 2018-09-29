package FareSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import TransitUsers.CardHolder;
import TransitUsers.Trip;

public class Card implements Serializable {


    private static final long serialVersionUID = 264026;

    private int cardId;
    private double balance;
    private ArrayList<Trip> allTrips = new ArrayList<>();
    private CardHolder owner;
    private boolean suspended;
    private Calendar lastEffectiveTap;
    private double amountSinceLastEffectiveTap;
    private boolean firstTap;
    private TapManager tm;

    /**
     * constructs a new Card
     * @param owner this Card's cardHolder
     */
    public Card(CardHolder owner, TapManager tm) {
        this.balance = 19;
        this.owner = owner;
        this.cardId = this.owner.getTs().getCardIDCounter().getIdCounter();
        this.owner.getTs().getCardIDCounter().updateCounter();
        this.suspended = false;
        this.amountSinceLastEffectiveTap = 0;
        this.lastEffectiveTap = null;
        this.firstTap = true;
        this.tm = tm;
    }

    /**
     * return this Card's id
     * @return this Card's id
     */
    public int getCardID() {
        return cardId;
    }

    /**
     * returns this Card's balance
     * @return this Card's balance
     */
    public double getBalance() {
        return balance;
    }


    /**
     * returns this Card's owner
     * @return this Card's CardHolder
     */
    public CardHolder getOwner(){
        return this.owner;
    }

    /**
     * returns the last CardMachine this Card tapped
     * @return the last CardMachine this Card tapped
     */
    public CardMachine getLastCardMachineTapped(){
        ArrayList<CardMachine> tempCMArray = new ArrayList <>();
        if (!this.allTrips.isEmpty()) {
            for (int i = 0; i <= this.allTrips.size() - 1; i++) {
                if (this.allTrips.get(i).getStart() != null) {
                    tempCMArray.add(this.allTrips.get(i).getStart());
                }
                if (this.allTrips.get(i).getEnd() != null) {
                    tempCMArray.add(this.allTrips.get(i).getEnd());
                }
            }
            return tempCMArray.get(tempCMArray.size() - 1);
        }
        return null;
    }

    /**
     * Adds one of three dollar amounts - 10, 20, 50 - to the card's
     * balance.
     *
     * @param value the value proposed to be added
     *
     */
    public boolean addValue(double value) {
        if (isSuspended()) {
            System.out.println("This card is suspended");
            return false;
        } else {
            this.balance += value;
            return true;
        }
    }

    /**
     * deducts the fare from the Card's balance and suspends the Card if the balance goes negative
     * @param fare the amount to be deducted
     */
    public void deductValue(double fare){
        this.balance -= fare;
    }

    public boolean tapCard(CardMachine cm){
        return tm.tapCard(this, cm);
    }

    /**
     * returns a String representation of this Card
     * @return String representation of this Card
     */
    public String toString(){
        if (this.suspended) {
            return "SUSPENDED: Card ID: " + this.cardId + ", Balance: " + this.balance;
        } else {
            return "Card ID: " + this.cardId + ", Balance: " + this.balance;
        }
    }

    /**
     * adds a Trip to this Card's allTrips and calls the Card's owner's addTrip to add the Trip to their allTrips
     * @param t the trip to be added
     */
    public void addTrip(Trip t){
        this.allTrips.add(t);
        this.owner.addTrip(t);
    }

    /**
     * supspends the Card
     */
    public void suspendCard() {
        this.suspended = true;
    }

    public void unSuspendCard(){
        this.suspended = false;
    }

    /**
     * Checks if the card is suspended
     * @return if the card is suspended
     */
    public boolean isSuspended() {
        return suspended;
    }

    /**
     * returns the lasttime the Card was effectively tapped
     * @return the lasttime the Card was effectively tapped
     */
    public Calendar getLastEffectiveTap() {
        return lastEffectiveTap;
    }

    /**
     * sets a new lastEffectiveTap
     * @param lastEffectiveTap the Date of the lastEffectiveTap
     */
    public void setLastEffectiveTap(Calendar lastEffectiveTap) {
        this.lastEffectiveTap = lastEffectiveTap;
    }

    /**
     * returns the amount of fare calculated since the lastEffectiveTap
     * @return the amount of fare calculated since the lastEffectiveTap
     */
    public double getAmountSinceLastEffectiveTap() {
        return this.amountSinceLastEffectiveTap;
    }

    /**
     * sets the amount to the amountSinceLastEffectiveTap
     * @param amountSinceLastEffectiveTap the amount to be set
     */
    public void setAmountSinceLastEffectiveTap(double amountSinceLastEffectiveTap) {
        this.amountSinceLastEffectiveTap = amountSinceLastEffectiveTap;
    }


    /**
     * adds the amount to the amountSinceLastEffectiveTap
     * @param amountToAdd the amount to be added
     */
    public void addAmountSinceLastEffectiveTap(double amountToAdd) {
        if(this.amountSinceLastEffectiveTap + amountToAdd >=6){
            this.amountSinceLastEffectiveTap = 6;
        }else{
            this.amountSinceLastEffectiveTap = this.amountSinceLastEffectiveTap + amountToAdd;
        }
    }

    /**
     * returns true if the tap is within the two hours time period since the lastEffectiveTape
     * @return true if the tap is within the two hours time period since the lastEffectiveTape
     */
    public boolean isWithinTimeLimit(){
        Calendar d = Calendar.getInstance();
        if (this.lastEffectiveTap != null)
            return (Math.abs(this.lastEffectiveTap.getTimeInMillis() - d.getTimeInMillis()) < 7200000);
        return false;
    }

    /**
     * resets the lastEffectiveTap
     */
    public void resetLastEffective(){
        setLastEffectiveTap(Calendar.getInstance());
        setAmountSinceLastEffectiveTap(0);
    }

    public boolean isFirstTap() {
        return firstTap;
    }

    public void setFirstTap(boolean firstTap) {
        this.firstTap = firstTap;
    }

    public ArrayList<Trip> getAllTrips() {
        return allTrips;
    }
}
