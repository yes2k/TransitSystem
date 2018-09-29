package Data;

import java.io.Serializable;

public class CardIDCounter implements Serializable {
    private int idCounter = 100000;

    public void updateCounter(){
        idCounter += 1;
    }

    public int getIdCounter(){
        return this.idCounter;
    }
}
