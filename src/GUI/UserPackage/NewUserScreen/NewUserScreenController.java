package GUI.UserPackage.NewUserScreen;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * the screen class for the new user screen
 */
public class NewUserScreenController extends GeneralControllerScreen implements Initializable {

    @FXML
    Label messageLabel;

    @FXML
    Button makeUserButton;

    @FXML
    Button backButton;

    @FXML
    TextField nameTextField;

    @FXML
    TextField emailTextField;

    @FXML
    TextField passTextField;



    @Override
    /**
     * initializes the screen
     */
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * handles the back button
     * @param e the event that has occurred
     */
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserLogInPackage/UserLoginScreen.fxml";
        this.getControllerHelper().openSameWindow(dest,
                this.getTransitSystem(), e);
    }

    /**
     * handles the make user button
     * @param e the event that has occurred
     */

    public void handleMakeUserButton(ActionEvent e) {
        String dest = "/GUI/UserPackage/UserLogInPackage/UserLoginScreen.fxml";
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String pass = passTextField.getText();

        if (name.equals("")) messageLabel.setText("Please Enter Your Name");
        else if (email.equals("")) messageLabel.setText("Please Enter Your email");
        else if (pass.equals("")) messageLabel.setText("Please Enter Your Password");
        else {
            Boolean success = this.getTransitSystem().getCardHolders().addCardHolder(name, email,
                    pass, this.getTransitSystem());
            if (success) {
                CardHolder thisCardHolder = this.getTransitSystem().getCardHolders().findCardHolder(email);
                this.getTransitSystem().getProgramLog().addToLog("New User " + email + " Created" );
                this.getControllerHelper().openSameWindow(dest, this.getTransitSystem(), e, thisCardHolder);
            } else {
                this.messageLabel.setText("User name already exists!");
                this.messageLabel.setTextFill(Color.RED);
            }
        }
    }
}
