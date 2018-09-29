package GUI.AdminScreens;

import AdminUsers.AdminUser;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import GUI.HelperClasses.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMainController extends GeneralControllerScreen {

    private AdminUser adminUser;

    @FXML
    Button backButton;

    @FXML
    private Button changeAccountInfo;

    @FXML
    private Button manageUsers;

    @FXML
    private Button viewStatistics;


    public void setUpController(){}

    public void setUpController(Object admin){
        this.adminUser = (AdminUser) admin;
    }

    /**
     * Changes the screen
     * @param event The event when the button is clicked
     */
    @FXML
    private void handleButtonAction(ActionEvent event){
        Object eve = event.getSource();
        if (eve.equals(changeAccountInfo)){
            String tapScreen = "/GUI/AdminScreens/AdminChangeInfoScreen/AdminChangeInfoScene.fxml";
            this.getControllerHelper().openSameWindow(tapScreen, this.getTransitSystem(), event, this.adminUser);
        }
        if (eve.equals(manageUsers)){
            String manageUsers = "/GUI/AdminScreens/AdminManageUsersScreen/AdminManageUserScene.fxml";
            this.getControllerHelper().openSameWindow(manageUsers, this.getTransitSystem(), event, this.adminUser);
        }
        if (eve.equals(viewStatistics)){
            String statsScreen = "/GUI/AdminScreens/AdminStatistics/AdminStatisticsScene.fxml";
            this.getControllerHelper().openSameWindow(statsScreen, this.getTransitSystem(), event, this.adminUser);
        }
    }

    /**
     * Goes back to the login screen
     * @param e The event when the button is clicked
     */
    public void handleBackButton(ActionEvent e) {
        ControllerHelper ch = new ControllerHelper();
        String goingTo = "/GUI/AdminScreens/AdminLoginScreen/AdminLoginScene.fxml";
        ch.openSameWindow(goingTo, this.getTransitSystem(), e);
    }
}

