package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen;

import FareSystem.Card;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

/**
 * the class for the manage cards screen
 */
public class ManageCardsScreenController extends GeneralControllerScreen{

    @FXML
    ListView listOfCards;

    @FXML
    Button backButton;

    @FXML
    Label userLabel;

    @FXML
    Button addCardButton;

    @FXML
    Button deleteCardButton;

    @FXML
    Button suspendCardButton;

    @FXML
    Button addValueButton;

    private CardHolder cardHolder;


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
     * handles add card button
     * @param e the event that has occurred
     */
    public void handleAddCardButton(ActionEvent e){
        this.getTransitSystemInteractions().addNewCard(cardHolder);
        this.listOfCards.setItems(this.cardHolder.getObservableCards());
    }

    @FXML
    /**
     * handles delete card button
     * @param e the event that has occurred
     */
    public void handleDeleteCardButton(ActionEvent e) {
        if (this.listOfCards.getSelectionModel().getSelectedItem() != null) {

            String cardID = Integer.toString(((Card)
                    this.listOfCards.getSelectionModel().getSelectedItem()).getCardID());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deleting Card");
            alert.setHeaderText("Are you sure you want to remove this card?");
            alert.setContentText("You are deleting card: " + cardID + ".");

            ButtonType buttonTypeOne = new ButtonType("Remove anyways");
            ButtonType buttonTypeTwo = new ButtonType("Yikes, no thanks!");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeOne) {
                if (this.listOfCards.getSelectionModel().getSelectedItem() != null) {
                    this.getTransitSystemInteractions().removeCard(this.cardHolder, (Card)
                            this.listOfCards.getSelectionModel().getSelectedItem());
                    this.listOfCards.setItems(this.cardHolder.getObservableCards());
                }
            } else if (result.isPresent() && result.get() == buttonTypeTwo) {
                alert.close();
            }
        }
    }

    @FXML
    /**
     * handles suspend card button
     * @param e the event that has occurred
     */
    public void handleSuspendCardButton(ActionEvent e){
        if (this.listOfCards.getSelectionModel().getSelectedItem() != null) {

            String cardID = Integer.toString(((Card)
                    this.listOfCards.getSelectionModel().getSelectedItem()).getCardID());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Confirm Suspending Card");
            alert.setHeaderText("Are you sure you want to suspend this card?");
            alert.setContentText("You are suspending card: " + cardID + ".");

            ButtonType buttonTypeOne = new ButtonType("Suspend anyways");
            ButtonType buttonTypeTwo = new ButtonType("Yikes, no thanks!");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeOne) {
                this.getTransitSystemInteractions().suspendCard((Card)
                        this.listOfCards.getSelectionModel().getSelectedItem());
                this.listOfCards.setItems(this.cardHolder.getObservableCards());
            } else if (result.isPresent() && result.get() == buttonTypeTwo) {
                alert.close();
            }
        }
    }

    @FXML
    /**
     * handles add value button
     * @param e the event that has occurred
     */
    public void handleAddValueButton(ActionEvent e) {
        Card selectedCard = (Card) this.listOfCards.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/AddValueScreen/AddValueScreen.fxml";
            this.getControllerHelper().openNewWindow(dest, this.getTransitSystem(), "Add Balance", selectedCard);
        }
    }

    /**
     * sets up the controller
     */
    public void setUpController(){}

    /**
     * sets up the controller with a cardholder
     * @param obj a cardholder
     * @throws ClassCastException is thrown if you do not send a cardHolder
     */
    public void setUpController(Object obj) throws ClassCastException{
        this.cardHolder = (CardHolder) obj;
        userLabel.setText(this.cardHolder.getEmail());
        this.listOfCards.setItems(this.cardHolder.getObservableCards());
    }
}
