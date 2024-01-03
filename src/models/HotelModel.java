package models;

import utils.ConsoleColors;

public class HotelModel {
    private String Hotel_id;
    private String Hotel_Name;
    private String Hotel_Room_Available;
    private String Hotel_Address;
    private String Hotel_Phone;
    private String Hotel_Rating;

    public HotelModel() {
    }

    public HotelModel(String hotel_id, String hotel_Name, String hotel_Room_Available, String hotel_Address,
            String hotel_Phone, String hotel_Rating) {
        this.Hotel_id = hotel_id.toUpperCase();
        this.Hotel_Name = hotel_Name;
        this.Hotel_Room_Available = hotel_Room_Available;
        this.Hotel_Address = hotel_Address;
        this.Hotel_Phone = hotel_Phone;
        this.Hotel_Rating = hotel_Rating;
    }

    public String getHotel_id() {
        return Hotel_id;
    }

    public String getHotel_Name() {
        return Hotel_Name;
    }

    public String getHotel_Room_Available() {
        return Hotel_Room_Available;
    }

    public String getHotel_Address() {
        return Hotel_Address;
    }

    public String getHotel_Phone() {
        return Hotel_Phone;
    }

    public String getHotel_Rating() {
        return Hotel_Rating;
    }

    public void setHotel_id(String hotel_id) {
        Hotel_id = hotel_id;
    }

    public void setHotel_Name(String hotel_Name) {
        Hotel_Name = hotel_Name;
    }

    public void setHotel_Room_Available(String hotel_Room_Available) {
        Hotel_Room_Available = hotel_Room_Available;
    }

    public void setHotel_Address(String hotel_Address) {
        Hotel_Address = hotel_Address;
    }

    public void setHotel_Phone(String hotel_Phone) {
        Hotel_Phone = hotel_Phone;
    }

    public void setHotel_Rating(String hotel_Rating) {
        Hotel_Rating = hotel_Rating;
    }

    public void showInfo() {
        String str = String.format(ConsoleColors.GREEN + "%5s," + ConsoleColors.RESET + "%15s,%5s,%80s,%11s,%10s",
                Hotel_id, Hotel_Name, Hotel_Room_Available,
                Hotel_Address,
                Hotel_Phone, Hotel_Rating);
        System.out.println(str);
    }

    @Override
    public String toString() {
        return String.format("%5s-%15s-%2s-%80s-%11s-%10s\n", Hotel_id, Hotel_Name, Hotel_Room_Available,
                Hotel_Address, Hotel_Phone, Hotel_Rating);
    }

}
