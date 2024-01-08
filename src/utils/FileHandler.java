package utils;

import java.io.*;
import java.util.ArrayList;

import controllers.HotelManagement;
import models.HotelModel;

public class FileHandler {
    // method: serialize for writing
    public static boolean serialize(String fName) {
        try {
            FileOutputStream fOut = new FileOutputStream(fName);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            for(HotelModel item: HotelManagement.hotelList){
                out.writeObject(item);
            }
            out.close();
            fOut.close();
            System.out.println("Serialized data is saved in " + fName);
            return true;
        } catch (IOException e) {
            System.out.println("Serialization failed: " + e.getMessage());
            return false;
        }
    }

    // method: deserialize for reading
    public static ArrayList<HotelModel> deserialize(String fName) {
        ArrayList<HotelModel> hotelList = new ArrayList<>();
        try {
            FileInputStream fIn = new FileInputStream(fName);
            ObjectInputStream in = new ObjectInputStream(fIn);
            hotelList = (ArrayList<HotelModel>) in.readObject();
            in.close();
            fIn.close();
            System.out.println("Deserialized data is loaded from " + fName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization failed: " + e.getMessage());
        }
        return hotelList;
    }
}
