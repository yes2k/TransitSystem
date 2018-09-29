package Main;


import Data.TransitSystemSerializer;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.ArrayList;


public class ButterflyTransity extends Application {

    private TransitSystem transitSystem;

    public ButterflyTransity() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TransitSystemStarter tsStart = new TransitSystemStarter();
        this.transitSystem = tsStart.getTs();
        String mainScreen = "/GUI/HomeScreen/MainScene.fxml";
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(mainScreen)));
        Parent root = loader.load();
        GeneralControllerScreen controller = loader.getController();
        controller.setTransitSystem(this.transitSystem);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent event1) -> {
            try{
                TransitSystemSerializer transitSerializer = new TransitSystemSerializer();
                transitSerializer.saveToFile("./serializedTransitSystem.ser", this.transitSystem);
                recordEvents(this.transitSystem.getProgramLog().getEvents());
            }catch(Exception e){
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch(args);
    }

  public void recordEvents(ArrayList<String> events) {

    try (PrintWriter out = new PrintWriter("./log.txt")) {
      for (int i = 0; i < events.size(); i++) {
        out.println(events.get(i));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
        }
}


