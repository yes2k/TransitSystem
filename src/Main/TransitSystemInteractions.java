package Main;

import AdminUsers.AdminUser;
import FareSystem.Card;
import FareSystem.CardMachine;
import TransitUsers.CardHolder;
import TransitUsers.Trip;
import java.util.ArrayList;


public class TransitSystemInteractions {

    public TransitSystemInteractions(){
    }

    /**
     * Attempts to tap a Card onto an entry CardMachine.
     *
     * This function attempts to search for the Card and CardMachine
     * in the TransitSystem using find methods. If the Card or the CardMachine
     * cannot be found, a statement is outputted to the screen.
     *
     * @param cID the Card ID being tapped
     * @param cmID the CardMachine ID that the Card is tapping on
     * @param ts the TransitSystem
     * @return true if the user can enter, false otherwise
     */
    public boolean enterStation(TransitSystem ts, String cID, String cmID) {
        int cardID=0;
        int entrance=0;
        try{
            cardID= Integer.parseInt(cID);
            entrance = Integer.parseInt(cmID);
        }catch (Exception e){

        }
        Card thisCard = ts.getCardHolders().findCard(cardID);
        CardMachine thisCM = ts.getStations().findEntrance(entrance);
        if (thisCard == null){
            ts.getProgramLog().addToLog("This card is invalid.");
            return false;
        } else if (thisCM == null){
            ts.getProgramLog().addToLog("This card machine is invalid.");
            return false;
        } else {
            if(thisCard.tapCard(thisCM)){
                ts.getProgramLog().addToLog(thisCard.toString() + " enters " + thisCM.toString());
                return true;
            } else {
                ts.getProgramLog().addToLog("Tap was not successful.");
                return false;
            }
        }
    }


    /**
     * Attempts to tap a Card onto an exit CardMachine.
     *
     * This function attempts to search for the Card and CardMachine
     * in the TransitSystem using find methods. If the Card or the CardMachine
     * cannot be found, a statement is outputted to the screen.
     *
     * @param cID the Card ID being tapped
     * @param cmID the CardMachine ID that the Card is tapping on
     * @param ts the TransitSystem
     */
    public boolean exitStation(TransitSystem ts, String cID, String cmID) {
        int cardID=0;
        int exit=0;
        try{
             cardID= Integer.parseInt(cID);
             exit = Integer.parseInt(cmID);
        }catch (Exception e){

        }
        Card thisCard = ts.getCardHolders().findCard(cardID);
        CardMachine thisCM = ts.getStations().findExit(exit);
        if (thisCard == null){
            ts.getProgramLog().addToLog("This card is invalid.");
            return false;
        } else if (thisCM == null){
            ts.getProgramLog().addToLog("This card machine is invalid.");
            return false;
        } else {
            if(thisCard.tapCard(thisCM)){
                ts.getProgramLog().addToLog(thisCard.toString() + " exits " + thisCM.toString());
                return true;
            } else {
                ts.getProgramLog().addToLog("Tap was not successful.");
                return false;
            }
        }
    }



    /***
     * This function adds a new Card to the passed in CardHolder.
     *
     * There does not need to be a check for the CardHolder, this is assumed
     * to have been done in a prior check.
     *
     * @param cardHolder the passed in CardHolder.
     */
    public void addNewCard(CardHolder cardHolder){
        Card newCard = new Card(cardHolder, cardHolder.getTs().getTapManager());
        cardHolder.addCard(newCard);
    }

    /**
     * This method removes a Card from a CardHolder given the two objects.
     *
     * It does not do checks for existence, operating on the assumption they have been done prior.
     *
     * @param cardHolder the CardHolder who is having the Card removed
     * @param card the Card object being removed from the CardHolder
     */
    public void removeCard(CardHolder cardHolder, Card card){
        cardHolder.removeCard(card.getCardID());
    }

    /**
     * Suspend a given Card object.
     *
     * @param card the Card being suspended.
     */
    public void suspendCard(Card card) {
        card.suspendCard();
        }


    /**
     * UnSuspends a given Card object.
     * @param card the Card being unsuspended.
     */
    public void unSuspendCard(Card card) {
        card.unSuspendCard();
    }

    /**
     * This method adds a specific value to a given Card.
     *
     * This method is more flexible, and can add any amount.
     *
     * @param amount the amount being added
     */
    public void addToBalance(Card card, int amount){
        card.addValue(amount);
    }

    /**
     * Modifies a CardHolder's name, changing it to an inputted name.
     *
     * This function attempts to search for the CardHolder in the TransitSystem
     * using a find method. If the User does not exist, a statement is outputted
     * to screen.
     *
     * @param ch the CardHolder
     * @param ts the TransitSystem
     * @param newName the name requested for change
     */
    public void changeName(TransitSystem ts, CardHolder ch, String newName){
        if(ch != null){
            ch.setName(newName);
            ts.getProgramLog().addToLog("Name for user " + ch.toString() + " changed successfully");
        } else {
            ts.getProgramLog().addToLog("Card holder could not be found in system.");
        }
    }

    public void changePassword(TransitSystem ts, CardHolder ch, String newPassword){
        if(ch != null){
            ch.setPassword(newPassword);
            ts.getProgramLog().addToLog("Name for user " + ch.toString() + " changed successfully");
        } else {
            ts.getProgramLog().addToLog("Card holder could not be found in system.");
        }
    }

    /**
     * Outputs to screen the last three trips this CardHolder took.
     *
     * This function attempts to search for the CardHolder in the TransitSystem
     * using a find method. If the CardHolder does not exist, a statement is outputted
     * to screen.
     *
     * @param ch the CardHolder
     * @param ts the TransitSystem
     */
    public void viewRecentTrips(TransitSystem ts, String ch){
        CardHolder cardHolder = ts.getCardHolders().findCardHolder(ch);
        if(cardHolder != null){
            ArrayList<Trip> trips = cardHolder.viewRecentTrips();
            for (Trip t: trips ) {
                ts.getProgramLog().addToLog(t.toString());
            }
        } else {
            ts.getProgramLog().addToLog("Card holder could not be found in system.");
        }
    }

    /**
     * Checks the login information of an Admin User
     * @param ts the Transit System being used
     * @param email the login email to check
     * @param password the login password to check
     * @return a boolean indicateing whether the Admin login info is correct.
     *
     */
    public boolean loginAdmin(TransitSystem ts, String email, String password){
        AdminUser au = ts.getAdminUsers().findAdminUser(email);
        return (au!= null && au.getPassword().equals(password));
    }
}