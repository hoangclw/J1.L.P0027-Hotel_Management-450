package utils;

import java.io.*;
import java.util.ArrayList;
import models.HotelModel;

public class FileHandler {
    public static boolean serialize(ArrayList<HotelModel> hotelList, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hotelList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("Serialization failed: " + e.getMessage());
            return false;
        }
    }

    public static ArrayList<HotelModel> deserialize(String filename) {
        ArrayList<HotelModel> hotelList = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hotelList = (ArrayList<HotelModel>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized data is loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization failed: " + e.getMessage());
        }
        return hotelList;
    }
}
