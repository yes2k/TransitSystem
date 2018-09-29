package GUI.TapScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * the screen for tapping the cards with a cardID and a cardMachineID
 */
public class TapSceneController extends GeneralControllerScreen {

    @FXML
    Button backButton;
    @FXML
    Label tapOutcomeLabel;
    @FXML
    TextField cardMachineIDInputTextField;
    @FXML
    TextField cardIDInputTextField;
    @FXML
    Button enterButton;
    @FXML
    Button exitButton;
    @FXML
    Rectangle outcomeRectangle;

    /**
     * sets up the controller
     */

    public void setUpController(){
        Color purple = Color.rgb(91, 87, 213);
        this.outcomeRectangle.setFill(purple);
    }

    /**
     * This Controller should never receive an object to set up.
     * @param object the object that should not be passed
     */
    public void setUpController(Object object){}

    @FXML
    /**
     * handles the backbutton
     *@param e the event that has occurred
     */
    private void handleBackButton(ActionEvent event) {
        String goingTo = "/GUI/HomeScreen/MainScene.fxml";
        this.getControllerHelper().openSameWindow(goingTo, this.getTransitSystem(), event);
    }

    @FXML
    /**
     * handles Exit button
     * @param e the event that has occurred
     */
    private void handleExitButton(ActionEvent event){
        String cardID = this.cardIDInputTextField.getText();
        String cmID = this.cardMachineIDInputTextField.getText();
        if (this.getTransitSystemInteractions().exitStation(this.getTransitSystem(), cardID, cmID)){
            this.outcomeRectangle.setFill(Color.GREEN);
            this.tapOutcomeLabel.setText("Success");
        } else {
            this.outcomeRectangle.setFill(Color.RED);
            this.tapOutcomeLabel.setText("Failure");
        }
    }

    @FXML
    /**
     * handles enter button
     * @param e the event that has occurred
     */
    private void handleEnterButton(ActionEvent event){
        String cardID = this.cardIDInputTextField.getText();
        String cmID = this.cardMachineIDInputTextField.getText();
        if (this.getTransitSystemInteractions().enterStation(this.getTransitSystem(), cardID, cmID)){
            this.outcomeRectangle.setFill(Color.GREEN);
            this.tapOutcomeLabel.setText("Success");
        } else {
            this.outcomeRectangle.setFill(Color.RED);
            this.tapOutcomeLabel.setText("Failure");
        }
    }
}
