package GUI.AdminScreens.AdminLoginScreen;

import AdminUsers.AdminUser;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystemInteractions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class AdminLoginSceneController extends GeneralControllerScreen{

    @FXML
    Button backButton;

    @FXML
    Label loginOutComeLabel;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button logInButton;

    /**
     * Logs the admin user in if the password and email match
     * @param event The event when the button is pushed
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //Find and check if the Transit System contains this AdminUser
        AdminUser adminUser = this.getTransitSystem().getAdminUsers().findAdminUser(emailTextField.getText());
        if (adminUser != null) {
            boolean adminLoginCorrect = this.getTransitSystemInteractions().loginAdmin(this.getTransitSystem(),
                    emailTextField.getText(), passwordField.getText());
            if (adminLoginCorrect) {
                this.getTransitSystem().getProgramLog().addToLog(adminUser.getEmail() + " Login Successful");
                ControllerHelper newControllerHelper = new ControllerHelper();
                String adminMainScreen = "/GUI/AdminScreens/AdminMainScene.fxml";
                newControllerHelper.openSameWindow(adminMainScreen, this.getTransitSystem(), event, adminUser);
            } else if (passwordField.getText().equals("")) {
                loginOutComeLabel.setTextFill(Color.web("#FF0000"));
                loginOutComeLabel.setText("Please enter a password!");
            } else if (adminUser.getPassword() != passwordField.getText()) {
                loginOutComeLabel.setTextFill(Color.web("#FF0000"));
                this.getTransitSystem().getProgramLog().addToLog(emailTextField.getText() + " Invalid Password");
                loginOutComeLabel.setText("Invalid Password!");
            }
        } else {
            this.getTransitSystem().getProgramLog().addToLog("Invalid Admin Username");
            this.loginOutComeLabel.setText("Invalid Username");
            this.loginOutComeLabel.setTextFill(Color.web("#FF0000"));
        }
    }

    /**
     * Goes back to the Main Screen
     * @param e The event when the button is clicked
     */
    public void handleBackButton(ActionEvent e){
        String mainScreen = "/GUI/HomeScreen/MainScene.fxml";
        this.getControllerHelper().openSameWindow(mainScreen, this.getTransitSystem(), e);
    }
}
