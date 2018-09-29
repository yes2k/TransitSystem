package GUI.UserPackage.UserLogInPackage;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * the class for the UserLoginScreen
 */
public class UserLoginScreenController extends GeneralControllerScreen implements Initializable {

    @FXML
    Button backButton;

    @FXML
    Button loginButton;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Label failedLoginMessage;

    @FXML
    Button newAccountButton;


    @Override
    /**
     * initializes this screen
     */
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * sets up the controller
     */
    public void setUpController(){}

    /**
     * sets up the controller with a CardHolder
     * @param obj an instance of a cardHolder
     */
    public void setUpController(Object obj){
        CardHolder cardHolder = (CardHolder) obj;
        String name = cardHolder.getName();
        this.failedLoginMessage.setText("Welcome " + name + "! Account created successfully. Log in.");
        this.failedLoginMessage.setTextFill(Color.BLACK);
    }

    /**
     * handles back button
     * @param e the event
     */
    public void handleBackButton(ActionEvent e){
    this.getControllerHelper().openSameWindow("/GUI/HomeScreen/MainScene.fxml", this.getTransitSystem(), e);
    }

    /**
     * handles loginbutton
     * @param e the event
     * @throws IOException if you enter weird stuff
     */
    public void handleLoginButton(ActionEvent e) throws IOException{
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        CardHolder cardHolder =  this.getTransitSystem().getCardHolders().findCardHolder(email);

        if(cardHolder == null){
            this.failedLoginMessage.setText("Incorrect Username");
            this.getTransitSystem().getProgramLog().addToLog("Incorrect User Name was inputted");
        }
        else if (!cardHolder.isPassCorrect(password)){
            failedLoginMessage.setText("Incorrect Password");
            this.getTransitSystem().getProgramLog().addToLog("Incorrect Password was inputted");
        }
        else{
            this.failedLoginMessage.setText("");
            this.getControllerHelper().openSameWindow("/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml", this.getTransitSystem(), e, cardHolder);
            this.getTransitSystem().getProgramLog().addToLog(email + " successfully logs in");
        }
    }

    /**
     * handles make account button
     * @param e the event
     */
    public void handleMakeAccountButton(ActionEvent e) {
        this.getControllerHelper().openSameWindow("/GUI/UserPackage/NewUserScreen/NewUserScreen.fxml", this.getTransitSystem(), e);
    }
}
