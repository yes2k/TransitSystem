package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.ManageUserInfoScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * the class for manage user screen
 */
public class ManageUserInfoSceneController extends GeneralControllerScreen {

    @FXML
    Label userName;

    @FXML
    Button backButton;

    @FXML
    Button changeNameButton;

    @FXML
    TextField nameTextField;

    @FXML
    Button changePasswordButton;

    @FXML
    TextField oldPasswordTextField;

    @FXML
    TextField newPasswordTextField;

    @FXML
    Label outComeLabel;

    private CardHolder cardHolder;


    /**
     * handles the back button
     * @param e the event that has occurred
     */
    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest, this.getTransitSystem(), e, this.cardHolder);
    }

    /**
     * handles change name button
     * @param e the event that has occurred
     */
    public void handleChangeNameButton(ActionEvent e){
        if(!nameTextField.getText().isEmpty()){
            this.getTransitSystemInteractions().changeName(this.getTransitSystem(), this.cardHolder, this.nameTextField.getText());
            outComeLabel.setTextFill(Color.GREEN);
            outComeLabel.setText("Name Changed!");
        }
    }

    /**
     * handles change password button
     * @param e the event that has occurred
     */
    public void handleChangePasswordButton(ActionEvent e){
        if(cardHolder.isPassCorrect(oldPasswordTextField.getText())){
            if(!newPasswordTextField.getText().isEmpty()){
                this.getTransitSystemInteractions().changePassword(this.getTransitSystem(), this.cardHolder, this.newPasswordTextField.getText());
                outComeLabel.setTextFill(Color.GREEN);
                outComeLabel.setText("Password Changed!");
            }else{
                outComeLabel.setTextFill(Color.RED);
                outComeLabel.setText("Invalid Password");
            }
        }else{
            outComeLabel.setTextFill(Color.RED);
            outComeLabel.setText("Old password doesn't match current password");
        }
    }

    /**
     * sets up the controller with a cardHolder
     * @param obj a cardholder
     * @throws ClassCastException is thrown if you send not a cardholder
     */
    public void setUpController(Object obj) throws ClassCastException{
        CardHolder ch = (CardHolder) obj;
        this.cardHolder = ch;
        this.userName.setText(this.cardHolder.getEmail());
    }
}
