package GUI.AdminScreens.AdminStatistics;
import Data.TransitData;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import Main.TransitSystem;
import TransitUsers.CardHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;


public class AdminStatisticsController extends GeneralControllerScreen {

    @FXML
    Button backButton;

    @FXML
    private AreaChart OverallRevenueAreaChart;

    @FXML
    private BarChart OverallRevenueBarChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private Button monthly;

    @FXML
    private Button Yearly;

    @FXML
    private Button thisMonth;

    @FXML
    private NumberAxis y;

    @FXML
    private Label monthlyRevenueLabel;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    private ObservableList<String> yearNames = FXCollections.observableArrayList();

    /**
     * Initializes Category names
      */
    @FXML
    private void initialize() {
        monthNames.addAll(Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"));
        yearNames.addAll(Arrays.asList("2016", "2017", "2018"));
        OverallRevenueBarChart.setVisible(true);
        OverallRevenueAreaChart.setVisible(false);
    }

    /**
     * Graphs Monthly Revenue
     */
    public void graphMonthlyRevenue() {
        OverallRevenueBarChart.getData().clear();
        OverallRevenueBarChart.setVisible(true);
        OverallRevenueAreaChart.setVisible(false);

        XYChart.Series series = new XYChart.Series<>();
        x.setCategories(monthNames);
        // Create a XYChart.Data object for each month. Add it to the series.
        TransitData td = this.getTransitSystem().getTransitData();
        for (int i = 0; i < monthNames.size(); i++) {
            double fare = td.getMonthlyFareAmount(i, Calendar.getInstance().get(Calendar.YEAR));
            series.getData().add(new XYChart.Data<>(monthNames.get(i), fare));
        }

        OverallRevenueBarChart.setLegendVisible(false);
        OverallRevenueBarChart.getData().add(series);
        double fare = 0;
        for (int i = 0; i < monthNames.size(); i++) {
            fare += td.getMonthlyFareAmount(i, Calendar.getInstance().get(Calendar.YEAR));

        }
        monthlyRevenueLabel.setText("Total Revenue for the current Year: " + Double.toString(fare));

    }

    /**
     * Graphs yearly revenue
     */
    public void graphYearlyRevenue() {
        OverallRevenueBarChart.getData().clear();
        OverallRevenueBarChart.setVisible(true);
        OverallRevenueAreaChart.setVisible(false);

        XYChart.Series series = new XYChart.Series<>();
        x.setCategories(yearNames);
        TransitData td = this.getTransitSystem().getTransitData();
        // Create a XYChart.Data object for each year. Add it to the series.
        for (int i = 0; i < yearNames.size(); i++) {
            double fare = td.getYearlyFareAmount(Integer.parseInt(yearNames.get(i)));
            series.getData().add(new XYChart.Data<>(yearNames.get(i), fare));
        }

        OverallRevenueBarChart.setLegendVisible(false);
        OverallRevenueBarChart.getData().add(series);
    }

    /**
     * Graphs Revenue for the current month
     */
    public void graphCurrentMonthRevenue() {
        OverallRevenueBarChart.getData().clear();
        OverallRevenueAreaChart.getData().clear();
        OverallRevenueBarChart.setVisible(false);
        OverallRevenueAreaChart.setVisible(true);


        TransitData td = this.getTransitSystem().getTransitData();
        XYChart.Series series = new XYChart.Series<>();
        // Create a XYChart.Data object for each day of the month. Add it to the series.
        for (int i = 1; i <= 31; i++) {
            double fare = td.getDailyFare(i, Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.YEAR));
            series.getData().add(new XYChart.Data<>(Integer.toString(i), fare));
        }

        OverallRevenueAreaChart.setLegendVisible(false);
        OverallRevenueBarChart.setLegendVisible(false);
        OverallRevenueAreaChart.getData().add(series);
        double fare = 0;
        for (int i = 1; i <= 31; i++) {
             fare += td.getDailyFare(i, Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.YEAR));

        }
        this.getTransitSystem().getProgramLog().addToLog("Total Revenue for the current Month: " + Double.toString(fare));
        monthlyRevenueLabel.setText("Total Revenue for the current Month: " + Double.toString(fare));
    }

    /**
     * Goes back to the Admin Main Screen
     * @param e The event when the button is clicked
     */
    public void handleBackButton(ActionEvent e) {
        ControllerHelper ch = new ControllerHelper();
        String goingTo = "/GUI/AdminScreens/AdminMainScene.fxml";
        ch.openSameWindow("/GUI/AdminScreens/AdminMainScene.fxml", this.getTransitSystem(), e);
    }
}
