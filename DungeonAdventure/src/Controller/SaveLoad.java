package Controller;

import java.io.*;

public class SaveLoad {

    public static void saveGame(Object object, String fileName) {
        try{
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            fileOut.close();
            System.out.println("Saved to " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadGame(String fileName) {
        Object object = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return object;
    }
}
