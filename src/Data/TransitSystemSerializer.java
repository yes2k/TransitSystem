package Data;


import Main.TransitSystem;

import java.io.*;
import java.util.logging.*;

public class TransitSystemSerializer {
    private static final Handler consoleHandler = new ConsoleHandler();

    public TransitSystemSerializer() throws ClassNotFoundException, IOException {
        // Associate the handler with the logger.
        consoleHandler.setLevel(Level.ALL);
        }

    public TransitSystem readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the Map
          TransitSystem ts = (TransitSystem) input.readObject();
            input.close();
            return ts;
        } catch (IOException ex) {
            return null;
        }
    }

    public void saveToFile(String filePath, TransitSystem ts) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(ts);
        output.close();
    }
}

