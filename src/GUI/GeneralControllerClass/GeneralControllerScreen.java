package GUI.GeneralControllerClass;

import GUI.HelperClasses.ControllerHelper;
import Main.TransitSystem;
import Main.TransitSystemInteractions;

/**
 * General Controller Class implements the preliminary necessities of every other screen class in the software
 */

public class GeneralControllerScreen {
    private TransitSystem ts;
    private TransitSystemInteractions transitSystemInteractions;
    private ControllerHelper controllerHelper;

    /**
     * Constructs a new instance of GeneralControllerScreen
     */
    public GeneralControllerScreen(){
        this.controllerHelper = new ControllerHelper();
        this.transitSystemInteractions = new TransitSystemInteractions();
    }

    /**
     * sets the TransitSystem for the screen to work with it.
     * @param ts the TransitSystem of this screen
     */
    public void setTransitSystem(TransitSystem ts) {
        this.ts = ts;
    }

    /**
     * return the transitsystem of this GeneralControllerScreen instance
     * @return TransitSystem of this screen
     */
    public TransitSystem getTransitSystem() {
        return this.ts;
    }

    /**
     * return the transitsystemINteractions of this GeneralControllerScreen instance
     * @return TransitSystemInteractions of this screen
     */
    public TransitSystemInteractions getTransitSystemInteractions() {
        return transitSystemInteractions;
    }

    /**
     * return the ControllerHelper of this GeneralControllerScreen instance
     * @return ControllerHelper
     */
    public ControllerHelper getControllerHelper(){
        return this.controllerHelper;
    }

    /**
     * sets up this controller before it is shows.
     * @param object it assigns an object to this GeneralControllerHelper
     */
    public void setUpController(Object object){}

    /**
     * sets up this controller before it is shows.
     */
    public void setUpController(){}
}
