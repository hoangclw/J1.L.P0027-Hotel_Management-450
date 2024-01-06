package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import constants.ConsoleMessage;
import constants.Message;
import constants.Regex;
import utils.ConsoleColors;
import utils.FileHandler;
import utils.Inputter;

public class HotelManagement {

    public static ArrayList<HotelModel> hotelList = new ArrayList<>();

    private ArrayList<HotelModel> searchList = new ArrayList<>();

    ConsoleMessage cm = new ConsoleMessage();

    private boolean isSearchCompleted = false;

    public HotelManagement() {
        // hotelList.add(new HotelModel("H01", "Seraton", 10,
        // "189 Ung Van Khiem, Ward 25, Binh Thanh District, Ho Chi Minh City",
        // "0911796099",
        // "4 stars"));
        // hotelList.add(new HotelModel("H02", "VinStar", 5,
        // "200 Ung Van Khiem, Ward 25, Binh Thanh District, Ho Chi Minh City",
        // "0918940111",
        // "5 stars"));
        // hotelList.add(new HotelModel("H03", "OutString", 7,
        // "300 D1, Ward 24, Binh Thanh District, Ho Chi Minh City", "0988940222", "6
        // stars"));
        // hotelList.add(new HotelModel("H04", "Betigar", 8,
        // "189 Duong Quang Ham, Ward 5, Go Vap District, Ho Chi Minh City",
        // "0977940100",
        // "3 stars"));
    }

    public void addNewHotel() {
        boolean isContinue = false;
        boolean isExisted = false;
        String hotel_id = null;
        do {
            do {
                isExisted = false; // reset isExisted
                hotel_id = Inputter.getString(cm.INPUT_HOTEL_ID, cm.HOTEL_ID_MUST_BE_H_AND_2_DIGITS,
                        Regex.HOTEL_ID).toUpperCase();
                for (HotelModel hotel : hotelList) {
                    if (hotel.getHotel_id().equals(hotel_id)) {
                        isExisted = true;
                        System.out.println(ConsoleColors.RED + cm.HOTEL_ID_IS_EXISTED
                                + ConsoleColors.RESET);
                        break;
                    }
                }
            } while (isExisted);

            String hotel_Name = Inputter.getString(cm.INPUT_HOTEL_NAME, cm.HOTEL_NAME_IS_REQUIRED);
            String hotel_Room_Available = Inputter.getString(cm.INPUT_HOTEL_ROOM_AVAILABLE,
                    cm.HOTEL_ROOM_AVAILABLE_IS_REQUIRED);
            String hotel_Address = Inputter.getString(cm.INPUT_HOTEL_ADDRESS,
                    cm.HOTEL_ADDRESS_IS_REQUIRED);
            String hotel_Phone = Inputter.getString(cm.INPUT_HOTEL_PHONE,
                    cm.HOTEL_PHONE_MUST_BE_10_DIGITS,
                    Regex.HOTEL_PHONE); // check regex
            String hotel_Rating = Inputter.getString(cm.INPUT_HOTEL_RATING,
                    cm.HOTEL_RATING_MUST_BE_NUMBER_AND_STAR,
                    Regex.HOTEL_RATING);

            HotelModel hotel = new HotelModel(hotel_id, hotel_Name, hotel_Room_Available,
                    hotel_Address,
                    hotel_Phone, hotel_Rating);
            hotelList.add(hotel);
            System.out.println(cm.ADD_NEW_HOTEL_SUCCESSFULLY);
        } while (getUserChoice());
    }

    public int searchHotelIndexId(String keyId) {
        for (int i = 0; i < hotelList.size(); i++) {
            if (hotelList.get(i).getHotel_id().equals(keyId)) {
                return i;
            }
        }
        return -1;
    }

    public HotelModel searchHotelByID(String keyId) {
        int pos = this.searchHotelIndexId(keyId);
        return pos == -1 ? null : hotelList.get(pos);
    }

    public ArrayList<HotelModel> searchHotelListByID(String keyId) {
        searchList.clear(); // reset searchList
        for (HotelModel hotelModel : hotelList) {
            if (hotelModel.getHotel_id().contains(keyId)) {
                searchList.add(hotelModel);
            }
        }
        Comparator orderById = new Comparator<HotelModel>() {
            @Override
            public int compare(HotelModel o1, HotelModel o2) {
                return o2.getHotel_id().compareTo(o1.getHotel_id()); // descending
            }
        };
        Collections.sort(searchList, orderById);
        return searchList;
    }

    public void checkExistsHotel() {
        do {
            String hotel_id = Inputter.getString(cm.INPUT_HOTEL_ID,
                    cm.HOTEL_ID_MUST_BE_H_AND_2_DIGITS,
                    Regex.HOTEL_ID);
            HotelModel hotelModel = this.searchHotelByID(hotel_id);
            if (hotelModel == null) {
                System.out.println(cm.HOTEL_ID_IS_NOT_EXISTED);
            } else {
                System.out.println(
                        ConsoleColors.RED + cm.HOTEL_ID_IS_EXISTED + ConsoleColors.RESET);
            }

        } while (getUserChoice());
    }

    // after updating, the program return to the main screen
    public void updateHotel() {
        String hotel_id = Inputter.getString(cm.INPUT_HOTEL_ID, cm.HOTEL_ID_MUST_BE_H_AND_2_DIGITS,
                Regex.HOTEL_ID);
        HotelModel hotel = this.searchHotelByID(hotel_id);
        if (hotel == null) {
            System.out.println(cm.HOTEL_ID_IS_NOT_EXISTED);
        } else {
            System.out.println("Before updating: ");
            hotel.showInfo();
            // các field dữ liệu update có thể rỗng
            String hotel_Name = Inputter.getString(cm.INPUT_HOTEL_NAME);
            String hotel_Room_Available = Inputter.getString(cm.INPUT_HOTEL_ROOM_AVAILABLE);
            String hotel_Address = Inputter.getString(cm.INPUT_HOTEL_ADDRESS);
            String hotel_Phone = Inputter.getString(cm.INPUT_HOTEL_PHONE);
            String hotel_Rating = Inputter.getString(cm.INPUT_HOTEL_RATING);

            // If new information is blank, then not change old information.
            if (hotel_Name.isEmpty()) {
                hotel.setHotel_Name(hotel.getHotel_Name());
            } else {
                hotel.setHotel_Name(hotel_Name);
            }

            if (hotel_Room_Available.isEmpty()) {
                hotel.setHotel_Room_Available(hotel.getHotel_Room_Available());
            } else {
                hotel.setHotel_Room_Available(hotel_Room_Available);
            }

            if (hotel_Address.isEmpty()) {
                hotel.setHotel_Address(hotel.getHotel_Address());
            } else {
                hotel.setHotel_Address(hotel_Address);
            }

            if (hotel_Phone.isEmpty()) {
                hotel.setHotel_Phone(hotel.getHotel_Phone());
            } else {
                hotel.setHotel_Phone(hotel_Phone);
            }

            if (hotel_Rating.isEmpty()) {
                hotel.setHotel_Rating(hotel.getHotel_Rating());
            } else {
                hotel.setHotel_Rating(hotel_Rating);
            }
            System.out.println("After updating: ");
            hotel.showInfo();
            System.out.println(cm.UPDATE_HOTEL_SUCCESSFULLY);
        }
    }

    // after deleting, the program return to the main screen
    public void deleteHotel() {

        String hotel_id = Inputter.getString(cm.INPUT_HOTEL_ID, cm.HOTEL_ID_MUST_BE_H_AND_2_DIGITS,
                Regex.HOTEL_ID);
        // tìm vị trí của hotel cần xóa
        HotelModel hotelModel = this.searchHotelByID(hotel_id);
        if (hotelModel == null) {
            System.out.println(cm.DELETE_HOTEL_FAILED);
        } else {
            hotelModel.showInfo();
            if (!getUserConfirmation()) {
                return;
            }
            hotelList.remove(hotelModel);
            System.out.println(cm.DELETE_HOTEL_SUCCESSFULLY);
        }

    }

    public void searchHotel() {
        do {
            System.out.println("-------------Search Hotel--------------");
            int choice = Inputter.getAnInteger("1. Search hotel by id\n2. Search hotel by name\n",
                    "Please input 1,2", 1, 2);
            switch (choice) {
                // search by id
                case 1:
                    String hotel_id = Inputter.getString(cm.INPUT_HOTEL_ID, cm.HOTEL_NAME_IS_REQUIRED);
                    searchList = this.searchHotelListByID(hotel_id);
                    if (searchList.isEmpty()) {
                    } else {
                        for (HotelModel item : searchList) {
                            item.showInfo();
                        }
                    }
                    break;
                // search by name
                case 2:
                    String hotel_Name = Inputter.getString(cm.INPUT_HOTEL_NAME, cm.HOTEL_NAME_IS_REQUIRED);
                    for (HotelModel item : hotelList) {
                        if (item.getHotel_Name().equalsIgnoreCase(hotel_Name)) {
                            item.showInfo();
                            break;
                            // sử dụng break ở đây để thoát khỏi vòng lặp gần nhất
                            // nếu dùng return thì sẽ thoát luôn cả cái hàm này,
                            // dẫn đến việc lặp lại thao tác Do you want to continue? bị ngắt
                        }
                    }
                    break;
            }
        } while (getUserChoice());
    }

    public void displayHotelList() {
        if (hotelList.isEmpty()) {
            System.out.println("Hotel list is empty");
            return;
        }
        Comparator orderByName = new Comparator<HotelModel>() {
            @Override
            public int compare(HotelModel o1, HotelModel o2) {
                return o2.getHotel_Name().compareTo(o1.getHotel_Name());
            }
        };
        Collections.sort(hotelList, orderByName);
        String str = String.format(
                ConsoleColors.RED + "   %5s,%15s,%5s,%80s,%11s,   %10s" + ConsoleColors.RESET,
                ConsoleColors.BLACK_BACKGROUND + "ID", "Name", "Room", "Address", "Phone",
                "Rating" + ConsoleColors.RESET);
        System.out.println(str);
        for (HotelModel item : hotelList) {
            item.showInfo();
        }
        // return str + "\n" + hotelList.toString();
    }

    /*
     * public boolean loadFromFile(String url) {
     * try {
     * BufferedReader br = new BufferedReader(new FileReader(url));
     * String line = br.readLine();
     * while (line != null) {
     * StringTokenizer stk = new StringTokenizer(line, "-");
     * String hotel_id = stk.nextToken().trim();
     * String hotel_Name = stk.nextToken().trim();
     * String hotel_Room_Available = stk.nextToken().trim();
     * String hotel_Address = stk.nextToken().trim();
     * String hotel_Phone = stk.nextToken().trim();
     * String hotel_Rating = stk.nextToken().trim();
     * HotelModel hotel = new HotelModel(hotel_id, hotel_Name, hotel_Room_Available,
     * hotel_Address, hotel_Phone, hotel_Rating);
     * hotelList.add(hotel);
     * line = br.readLine();
     * }
     * return true;
     * } catch (Exception e) {
     * System.out.println("Error read file" + e.getMessage());
     * return false;
     * }
     * }
     * 
     * public boolean saveToFile(String url) {
     * if (hotelList.isEmpty()) {
     * System.out.println("Hotel list is empty");
     * return false;
     * }
     * try {
     * File file = new File(url);
     * if (!file.exists()) {
     * file.createNewFile();
     * }
     * FileWriter fw = new FileWriter(file);
     * BufferedWriter bw = new BufferedWriter(fw);
     * for (HotelModel hotelModel : hotelList) {
     * bw.write(hotelModel.getHotel_id() + "-" + hotelModel.getHotel_Name() + "-"
     * + hotelModel.getHotel_Room_Available() + "-"
     * + hotelModel.getHotel_Address() + "-" + hotelModel.getHotel_Phone()
     * + "-" + hotelModel.getHotel_Rating());
     * bw.newLine();
     * }
     * bw.close();
     * fw.close();
     * System.out.println("Save to file successfully");
     * return true;
     * } catch (Exception e) {
     * System.out.println("Error save to file" + e.getMessage());
     * return false;
     * }
     * }
     */

    public boolean loadFromFile(String url) {
        hotelList = FileHandler.deserialize(url);
        return hotelList != null;
    }

    public boolean saveToFile(String url) {
        return FileHandler.serialize(hotelList, url);
    }

    public void quit() {
        System.exit(0);
    }

    public boolean getUserChoice() {
        return Inputter.getYesNo(cm.DO_YOU_WANT_TO_CONTINUE, cm.PLESE_INPUT_Y_OR_N)
                .toLowerCase()
                .equals("y");
    }

    public boolean getUserConfirmation() {
        return Inputter.getYesNo(cm.DO_YOU_WANT_TO_DELETE, cm.PLESE_INPUT_Y_OR_N)
                .toLowerCase()
                .equals("y");
    }

    public void exitProgram() {
        System.out.println("Good bye");
        Message.createConfirmationMsg("Do you want to continue?");
    }

}
