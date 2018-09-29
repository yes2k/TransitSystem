package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageCardsScreen.AddValueScreen;

import FareSystem.Card;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * the class for add value screen
 */
public class AddValueScreenController extends GeneralControllerScreen implements Initializable{

    @FXML
    Button addFive;
    @FXML
    Button addTen;
    @FXML
    Button addFifteen;
    @FXML
    Button addTwenty;

    @FXML
    Button addValueButton;

    @FXML
    Label cardIDMessageLabel;

    @FXML
    TextField valueAmountTextField;

    private Card selectedCard;

    @Override
    /**
     * initializes the screen
     */
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    /**
     * handles add five button
     *
     */
    public void handleAddFive(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 5);
        this.getTransitSystem().getProgramLog().addToLog("$5 added to cardID: " + selectedCard.getCardID());
        Stage stage = (Stage) addFive.getScene().getWindow();
        stage.close();
    }

    @FXML
    /**
     * handles add ten button
     */
    public void handleAddTen(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 10);
        this.getTransitSystem().getProgramLog().addToLog("$10 added to cardID: " + selectedCard.getCardID());

        Stage stage = (Stage) addTen.getScene().getWindow();
        stage.close();
    }

    @FXML
    /**
     * handles add fifteen button
     */
    public void handleAddFifteen(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 15);
        this.getTransitSystem().getProgramLog().addToLog("$15 added to cardID: " + selectedCard.getCardID());
        Stage stage = (Stage) addFifteen.getScene().getWindow();
        stage.close();
    }

    @FXML
    /**
     * handles add twenty button
     */
    public void handleAddTwenty(){
        this.getTransitSystemInteractions().addToBalance(this.selectedCard, 20);
        this.getTransitSystem().getProgramLog().addToLog("$20 added to cardID: " + selectedCard.getCardID());
        Stage stage = (Stage) addTwenty.getScene().getWindow();
        stage.close();
    }

    @FXML
    /**
     * handles add value button
     */
    public void handleAddValueButton() {

        if (valueAmountTextField.getText() != null) {


            try {
                int amount = Integer.parseInt(this.valueAmountTextField.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Confirm Value");
                alert.setHeaderText("Are you sure you want to add money to this card?");
                alert.setContentText("You are adding: " + this.valueAmountTextField.getText() + "to this Card");

                ButtonType buttonTypeOne = new ButtonType("Add away!");
                ButtonType buttonTypeTwo = new ButtonType("Yikes, no thanks!");

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == buttonTypeOne) {
                    this.getTransitSystem().getProgramLog().addToLog("$" + amount + " added to cardID: " + selectedCard.getCardID());
                    this.getTransitSystemInteractions().addToBalance(this.selectedCard, amount);
                } else {
                    alert.close();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrect Value");
                alert.setHeaderText("Not able to add this value.");
                alert.setContentText("Currently, we are only able to add whole values to your card. No decimals please!");
                ButtonType buttonOne = new ButtonType("Okay");
                alert.getButtonTypes().setAll(buttonOne);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonOne) {
                    alert.close();
                }
            }
        }
    }

    /**
     * sets up the controller
     */
    public void setUpController(){

    }

    /**
     * set up the controller with a card object
     * @param obj a card
     * @throws ClassCastException is thrown if you send a not card
     */
    public void setUpController(Object obj) throws ClassCastException{
        this.selectedCard = (Card) obj;
    }
}
