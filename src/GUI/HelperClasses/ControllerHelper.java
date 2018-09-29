package GUI.HelperClasses;

import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is a helper class that makes switching screens more efficient and easily done in the
 * controller files.
 */
public class ControllerHelper {

    /**
     * This method returns a new root, given an FXML file, and loads the root's controllers
     * with the required setup methods.
     *
     * @param fxmlResource the path of the fxml file we need to switch to.
     * @param model the transitSystem we want to assign to the destination controller
     * @return returns the parent screen
     */
    private Parent changeScreen(String fxmlResource, TransitSystem model){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            GeneralControllerScreen controller = loader.getController();
            controller.setTransitSystem(model);
            controller.setUpController();
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method returns a new root, given an FXML file, and loads the root's controllers
     * with the required setup methods, including an object.
     *
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param object the object we want to assign to the new controller
     * @return returns the parent screen
     */
    private Parent changeScreen(String fxmlResource, TransitSystem model, Object object) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource(fxmlResource)));
            Parent root = loader.load();
            GeneralControllerScreen controller = loader.getController();
            controller.setTransitSystem(model);
            controller.setUpController(object);
            return root;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This method opens a new window, passing over a TransitSystem model.
     *
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param event the event which has occurred
     */
    public void openSameWindow(String fxmlResource, TransitSystem model, ActionEvent event) {
        Parent root = changeScreen(fxmlResource, model);
        setSameWindow(root, event);
    }

    /**
     * This method switches scenes, passing over a TransitSystem model and an Object.
     *
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param event the event which has occurred
     * @param object the object we want to assign to the new controller
     */
    public void openSameWindow(String fxmlResource, TransitSystem model, ActionEvent event, Object object) {
        Parent root = changeScreen(fxmlResource, model, object);
        setSameWindow(root, event);
    }

    /**
     * This method opens a new window, passing over a TransitSystem model and an Object.
     *
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param title the screen's title
     * @param object the object we want to assign to the new controller
     */
    public void openNewWindow(String fxmlResource, TransitSystem model, String title, Object object) {
        Parent root = changeScreen(fxmlResource, model, object);
        setNewWindow(root, title);
    }

    /**
     * This method opens a new window, only passing over a TransitSystem model.
     *
     * @param fxmlResource the path of the fxml file we need to switch to
     * @param model the transitSystem we want to assign to the destination controller
     * @param title the screen's title
     */
    public void openNewWindow(String fxmlResource, TransitSystem model, String title) {
        Parent root = changeScreen(fxmlResource, model);
        setNewWindow(root, title);
    }

    /**
     * This method creates a new Stage and sets the given root to the stage.
     *
     * @param root the scene to be sat
     * @param title the title of the new screen
     */
    private void setNewWindow(Parent root, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * This method sets the stage of the given event to the given root.
     *
     * @param root the scene to be sat
     * @param event the event that has occured
     */
    private void setSameWindow(Parent root, ActionEvent event) {
        Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
        window.setScene(new Scene(root));
        window.show();
    }
}
