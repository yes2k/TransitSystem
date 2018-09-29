package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.UserGiftScreen;

import FareSystem.Card;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * the class for user gift screen
 */
public class UserGiftSceneController extends GeneralControllerScreen implements Initializable {

    @FXML
    Label userName;

    @FXML
    Label userGiftBalance;

    @FXML
    Button backButton;

    @FXML
    Button giftButton;

    @FXML
    TextField recipientEmailTextField;

    @FXML
    TextField moneyTextField;

    @FXML
    TextField cardID;

    @FXML
    TextField amount;

    @FXML
    Label giftSuccessLabel;

    @FXML
    Label addValueSuccessLabel;

    private CardHolder cardHolder;

    @Override
    /**
     * initializes this screen
     */
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    /**
     * handles the back button
     * @param e the event that has occurred
     */
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    /**
     * handles gift button
     * @param e the event that has occurred
     */
    public void handleGiftButton(ActionEvent e){
        this.giftSuccessLabel.setText("");
        String recipient = this.recipientEmailTextField.getText();
        CardHolder cardHolderRecipient = this.getTransitSystem().getCardHolders().findCardHolder(recipient);
        if (cardHolderRecipient != null){
            try {
                int amount = Integer.parseInt(this.moneyTextField.getText());
                cardHolderRecipient.receiveMoney(amount);
                this.userGiftBalance.setText(Integer.toString(this.cardHolder.getBalance()));
                this.moneyTextField.clear();
                this.recipientEmailTextField.clear();
                this.getTransitSystem().getProgramLog().addToLog(this.cardHolder.getEmail() + " gifted $" +
                        Integer.toString(amount) + " to " + cardHolderRecipient.getEmail());
            } catch (NumberFormatException exception){
                this.giftSuccessLabel.setText("Whole numbers only please.");
            }
        } else {
            this.giftSuccessLabel.setText("This email is not valid.");
        }
    }

    @FXML
    /**
     * handles add value button
     * @param e the event that has occurred
     */
    public void handleAddValueButton(ActionEvent e){
        this.addValueSuccessLabel.setText("");
        try {
            int valueBeingAdded = Integer.parseInt(this.amount.getText());
            if (valueBeingAdded <= this.cardHolder.getBalance()) {
                Card cardBeingModified = this.getTransitSystem().getCardHolders().findCard(Integer.parseInt(this.cardID.getText()));
                if (cardBeingModified != null) {
                    cardBeingModified.addValue(valueBeingAdded);
                    this.cardHolder.setBalance(this.cardHolder.getBalance() - valueBeingAdded);
                    this.userGiftBalance.setText(Integer.toString(this.cardHolder.getBalance()));
                    this.amount.clear();
                    this.cardID.clear();
                    this.getTransitSystem().getProgramLog().addToLog(this.cardHolder.getEmail() + " added $" +
                            Integer.toString(valueBeingAdded) + " to card" + this.cardID.getText());

                } else {
                    this.addValueSuccessLabel.setText("Card doesn't exist.");
                }
            } else {
                this.addValueSuccessLabel.setText("Insufficient funds");
                this.amount.clear();
            }
        } catch (NumberFormatException nfe){
            this.addValueSuccessLabel.setText("Whole numbers only please.");
        }
    }

    /**
     * sets up the controller
     */
    public void setUpController(){}

    /**
     * sets up the controller
     * @param obj a cardholder
     * @throws ClassCastException is thrown if you send a not cardholder
     */
    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        userGiftBalance.setText(Integer.toString(this.cardHolder.getBalance()));
        userName.setText(this.cardHolder.getEmail());
    }
}
