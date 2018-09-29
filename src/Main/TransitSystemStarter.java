package Main;

import Data.TransitSystemSerializer;
import FareSystem.Card;
import Stations.BusStation;
import Stations.SubwayStation;
import TransitUsers.CardHolder;

import java.io.File;
import java.io.IOException;

public class TransitSystemStarter {

    private TransitSystem ts;

    private static String filepath = "./serializedTransitSystem.ser";

    public TransitSystemStarter() throws IOException, ClassNotFoundException {

        TransitSystemSerializer saveFileHelper = new TransitSystemSerializer();

        ts = initializeTransitSystem();

        // Save the log to file
        saveFileHelper.saveToFile(filepath, ts);

    }

    public TransitSystem getTs() {
        return ts;
    }


    private static TransitSystem initializeTransitSystem() throws IOException, ClassNotFoundException {
        File serFile = new File(filepath);

        if (!(serFile.exists())) {

            TransitSystem newTS = new TransitSystem();

            newTS.getCardHolders().addCardHolder("HAL", "HAL@mail.com", "hi", newTS);
            newTS.getCardHolders().addCardHolder("Dave", "Dave@mail.com", "hi", newTS);
            newTS.getAdminUsers().addAdminUser("Sai", "Sai@gmail.com", "gg");

            CardHolder hal = newTS.getCardHolders().findCardHolder("HAL@mail.com");
            Card newCard = new Card(hal, newTS.getTapManager());
            hal.addCard(newCard);

            CardHolder dave = newTS.getCardHolders().findCardHolder("Dave@mail.com");
            Card newCard1 = new Card(dave, newTS.getTapManager());
            dave.addCard(newCard1);

            SubwayStation ss1 = new SubwayStation(0, 2, "ossington");
            ss1.addEntrace();
            ss1.addExits();
            SubwayStation ss2 = new SubwayStation(1, 2, "christie");
            ss2.addEntrace();
            ss2.addExits();
            SubwayStation ss3 = new SubwayStation(2, 2, "bathurst");
            ss3.addEntrace();
            ss3.addExits();
            SubwayStation ss4 = new SubwayStation(3, 2, "spadina");
            ss4.addEntrace();
            ss4.addExits();
            SubwayStation ss5 = new SubwayStation(4, 2, "st. george");
            ss5.addEntrace();
            ss5.addExits();

            BusStation bs1 = new BusStation(2, 0, "a");
            bs1.addEntrace();
            bs1.addExits();
            BusStation bs2 = new BusStation(2, 2, "b");
            bs2.addEntrace();
            bs2.addExits();
            BusStation bs3 = new BusStation(2, 3, "c");
            bs3.addEntrace();
            bs3.addExits();
            BusStation bs4 = new BusStation(2, 4, "d");
            bs4.addEntrace();
            bs4.addExits();

            newTS.getStations().addStation(ss1);
            newTS.getStations().addStation(ss2);
            newTS.getStations().addStation(ss3);
            newTS.getStations().addStation(ss4);
            newTS.getStations().addStation(ss5);

            newTS.getStations().addStation(bs1);
            newTS.getStations().addStation(bs2);
            newTS.getStations().addStation(bs3);
            newTS.getStations().addStation(bs4);
            return newTS;
        } else {
            TransitSystemSerializer tsLog = new TransitSystemSerializer();
            TransitSystem newTS = tsLog.readFromFile(filepath);
            return newTS;
        }
    }

}
