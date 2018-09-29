package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.TripHistoryScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import TransitUsers.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * the class for view trip history controller
 */
public class ViewTripHistorySceneController extends GeneralControllerScreen{

    @FXML
    Label userName;

    @FXML
    Button backButton;

    @FXML
    TableView<Trip> table;

    @FXML
    TableColumn<Trip, String> customStart;

    @FXML
    TableColumn<Trip, String> startLocationColumn;

    @FXML
    TableColumn<Trip, String> customEnd;

    @FXML
    TableColumn<Trip, String> endLocationColumn;

    @FXML
    TableColumn<Trip, Double> fareColumn;

    private CardHolder cardHolder;

    /**
     * handles back button
     * @param event the event that has occurred
     */
    public void handleBackButton(ActionEvent event){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), event, this.cardHolder);
    }

    /**
     * initializes the screen
     */
    public void initialize() {
        if(cardHolder != null) {
            customStart.setCellValueFactory(new PropertyValueFactory<Trip, String>("customStart"));
            customEnd.setCellValueFactory(new PropertyValueFactory<Trip, String>("customEnd"));
            table.setItems(cardHolder.getObservableTrip());
        }
    }

    /**
     * sets up the controller
     * @param c a cardholder
     */
    public void setCardHolder(CardHolder c){
        cardHolder = c;
    }

    /**
     * sets up the controller
     * @param obj a cardholder
     * @throws ClassCastException is thrown if you send a not cardholder
     */
    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        cardHolder = ch;
        userName.setText(this.cardHolder.getEmail());
        initialize();
    }
}
