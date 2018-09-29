package Stations;

import java.io.Serializable;

public class SubwayStation extends Station implements Serializable {


    private static final long serialVersionUID = 234734;
    /**
     * creates a new SubwayStation, not flatRated
     * @param x this SubwayStation's first coordinate
     * @param y this SubwayStation's second coordinate
     * @param name this SubwayStation's name
     */
    public SubwayStation(int x, int y, String name) {
        super(x, y, false, name);
    }

}
