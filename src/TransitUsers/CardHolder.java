package TransitUsers;

import Main.TransitSystem;
import FareSystem.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class CardHolder implements Serializable {

  private boolean isBanned = false;
  private String banPass;
  private String name;
  private String email;
  private String password;
  private ArrayList<Card> cards;
  private TransitSystem ts;
  private ArrayList<Trip> trips;
  private int balance;
  private static final long serialVersionUID = 291745;


  public CardHolder(String name, String email, String password, TransitSystem ts) {
    this.password = password;
    this.name = name;
    this.email = email;
    this.cards = new ArrayList<>();
    this.trips = new ArrayList <>();
    this.ts = ts;
    this.balance = 0;
  }

  /**
   * constructs a new CardHolder
   * @param name this CardHolder's name
   * @param email this CardHolder's email
   */


  public CardHolder(String name, String email, TransitSystem ts) {
    this.name = name;
    this.email = email;
    this.cards = new ArrayList<>();
    this.trips = new ArrayList <>();
    this.ts = ts;
  }

  /**
   * return this CardHolder's cards
   * @return the Cards of this CardHolder
   */
  public ArrayList<Card> getCards() {
    return this.cards;
  }

  public void banCardHolder(String bannedPassword){
    this.isBanned = true;
    this.banPass = this.password;
    this.setPassword(bannedPassword);
    for(Card c: this.getCards()){
      c.suspendCard();
    }
  }

  public void unBanCardHolder(){
    this.isBanned = false;
    this.setPassword(this.banPass);
  }

  public boolean isBanned(){
    return this.isBanned;
  }

  /**
     * Returns TransitSystem
     * @return returns TransitSystem
     */
    public TransitSystem getTs() {
        return ts;
    }

    /**
   * adds a Card this CardHolder's cards
   * @param card the Card to be added
   */

  public void addCard(Card card) {
    this.cards.add(card);
  }

  /**
   * sets this CardHolder's name
   * @param name the name to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * returns this CardHolder's name
   * @return this CardHolder's name
   */
  public String getName() {
    return name;
  }

  /**
   * returns this CardHolder's email
   * @return this CardHolder's email
   */
  public String getEmail() {
    return email;
  }

  public int getBalance(){
      return this.balance;
  }

  /**
   * removes a Card by id
   * @param cardID the id of the Card to be removed
   */
  public void removeCard(int cardID) {
    for (int i = 0; i < this.cards.size(); i++) {
      if (cardID == this.cards.get(i).getCardID()) {
        this.cards.remove(i);
      }
    }
  }

  /**
   * returns this CardHolder's 3 recent trips
   * @return this CardHolder's 3 recent trips
   */
  public ArrayList<Trip> viewRecentTrips() {
    ArrayList<Trip> recentTrips = new ArrayList<Trip>();

    if(this.trips.size() > 0){
      recentTrips.add(this.trips.get(this.trips.size() - 1));
    }
    if(this.trips.size() > 1){
      recentTrips.add(this.trips.get(this.trips.size() - 2));
    }
    if(this.trips.size() > 2){
      recentTrips.add(this.trips.get(this.trips.size() - 3));
    }
    return recentTrips;
  }

  /**
   * returns a String representation of this CardHolder
   * @return  a String representation of this CardHolder
   */
  public String toString(){
    return this.name + ", " + this.email;
  }

  /**
   * adds a trip to this Cardholder's Trips, calls this Cardholder's TransitSystem's addTrip to add the Trip also in there
   * @param t the trip to be added
   */
  public void addTrip(Trip t){
    this.trips.add(t);
    this.ts.addTrip(t);
  }


  public ArrayList<Trip> getTrips() {
    return trips;
  }

  public ObservableList<Trip> getObservableTrip(){
    if(this.trips.size()<30){
        return FXCollections.observableArrayList(this.trips);
    }else {
        return FXCollections.observableArrayList(this.trips.subList(this.trips.size() - 30, this.trips.size() - 1));
    }
  }

  public ObservableList<Card> getObservableCards(){
    return FXCollections.observableArrayList(this.cards);
  }

  public double getDailyFare(int day, int month, int year){
      double fare = 0;
      for(Trip t: this.trips){
          if((t.getStarDate().get(Calendar.DATE) == day) && (t.getStarDate()
          .get(Calendar.MONTH) == month) && (t.getStarDate().get(Calendar.YEAR) == year)){
              fare+= t.getFare();
          }
      }
      return fare;
  }

  public void receiveMoney(int amount){
    this.balance += amount;
  }

  public void setBalance(int amount){
      this.balance = amount;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public boolean isPassCorrect(String pass){
    if (this.password != null) {
      return (this.password.equals(pass)); }
    return false;
  }
}