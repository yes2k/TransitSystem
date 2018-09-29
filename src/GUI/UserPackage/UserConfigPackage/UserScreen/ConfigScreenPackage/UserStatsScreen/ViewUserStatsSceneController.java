package GUI.UserPackage.UserConfigPackage.UserScreen.ConfigScreenPackage.UserStatsScreen;

import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Calendar;

/**
 * the class for the view user stats scene screen
 */
public class ViewUserStatsSceneController extends GeneralControllerScreen{

    @FXML
    Label userName;

    @FXML
    Button backButton;

    @FXML
    LineChart<Number, Number> userFareGraph;

    @FXML
    NumberAxis xAxis;

    @FXML
    NumberAxis yAxis;

    @FXML
    Label totalMonthlyFareLabel;

    private CardHolder cardHolder;

    private XYChart.Series series;


    /**
     * handles the back button
      * @param e the event
     */

    public void handleBackButton(ActionEvent e){
        String dest = "/GUI/UserPackage/UserConfigPackage/UserScreen/ViewUserScene.fxml";
        this.getControllerHelper().openSameWindow(dest, this.getTransitSystem(), e, this.cardHolder);
    }

    /**
     * initializes the screen
     */
    public void initialize() {
        series = new XYChart.Series();
        if (cardHolder != null) {
            System.out.println(cardHolder);
            for(int i=1; i<=31; i++){
                series.getData().add(new XYChart.Data<>(i, cardHolder.getDailyFare(i, Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.YEAR))));
            }
        }
        userFareGraph.getData().add(series);
    }

    /**
     * sets this screen's cardholder
     * @param c
     */
    public void setCardHolder(CardHolder c){
        cardHolder = c;
    }

    /**
     * sets up the cardholder for this controller
     * @param object it assigns an object to this GeneralControllerHelper
     */
    public void setUpController(Object object){
        CardHolder c = (CardHolder) object;
        setCardHolder(c);
        initialize();
        setTotalMonthlyFareLabel();
        userName.setText(this.cardHolder.getEmail());
    }

    /**
     * sets the total monthly fare label
     */
    public void setTotalMonthlyFareLabel() {
        double fare = 0;
        for(int i=0; i<=31; i++){
            fare+= cardHolder.getDailyFare(i, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR));
        }
        totalMonthlyFareLabel.setText("Total fare spare spent this month: " + Double.toString(fare));
    }
}
