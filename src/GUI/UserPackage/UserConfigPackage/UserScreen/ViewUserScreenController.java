package GUI.UserPackage.UserConfigPackage.UserScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * the class for the vie user screen
 */
public class ViewUserScreenController extends GeneralControllerScreen implements Initializable {

    @FXML
    Button backButton;

    @FXML
    Label userName;
    @FXML
    Button manageCardsButton;

    @FXML
    Button giftMoneyButton;

    @FXML
    Button viewStatsButton;

    @FXML
    Button changeUserInfoButton;

    @FXML
    Button viewHistoryButton;

    private CardHolder cardHolder;

    @Override
    /**
     * initializes the screen
     */
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    /**
     * handles back button
     * @param e the event that has occurred
     */
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserLogInPackage/UserLoginScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e);
    }

    @FXML
    /**
     * handles manage cards button
     * @param e the event that has occurred
     */
    public void handleManageCardsButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageCardsScreen/ManageCardsScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    /**
     * handles the gift money button
     * @param e the event that has occurred
     */
    public void handleGiftMoneyButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/UserGiftScreen/UserGiftScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    /**
     * handles view stats button
     * @param e the event that has occurred
     */
    public void handleViewStatsButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/UserStatsScreen/ViewUserStatsScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }

    @FXML
    /**
     * handles change user info button
     * @param e the event that has occurred
     */
    public void handleChangeUserInfoButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage/ManageUserInfoScreen/ManageUserInfoScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e, this.cardHolder);
    }


    @FXML
    /**
     * handles view history button
     * @param e the event that has occurred
     */
    public void handleViewHistoryButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ConfigScreenPackage" +
                "/TripHistoryScreen/ViewTripHistoryScene.fxml";
        this.getControllerHelper().openSameWindow(dest, this.getTransitSystem(), e, this.cardHolder);
    }

    /**
     * sets up the controller
     */
    public void setUpController(){}

    /**
     * sets up the controller with a cardHolder
     * @param obj a cardholder for this generalControllerScreen instance
     * @throws ClassCastException throws an exception if you do not send a CardHolder
     */
    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        userName.setText(this.cardHolder.getEmail());
    }
}
