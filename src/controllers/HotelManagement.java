package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

import constants.Message;
import constants.Regex;
import models.HotelModel;
import utils.ConsoleColors;
import utils.Inputter;
import utils.StringTools;

public class HotelManagement {

    public static ArrayList<HotelModel> hotelList = new ArrayList<HotelModel>();

    private ArrayList<HotelModel> userActionList = new ArrayList<HotelModel>();

    private ArrayList<HotelModel> searchList = new ArrayList<HotelModel>();

    public HotelManagement() {
    }

    public void addNewHotel() {
        boolean isExisted;
        String hotel_id;
        do {
            do {
                isExisted = false; // reset isExisted
                hotel_id = Inputter
                        .getString(Message.INPUT_HOTEL_ID, Message.HOTEL_ID_MUST_BE_H_AND_2_DIGITS, Regex.HOTEL_ID)
                        .toUpperCase();
                for (HotelModel hotel : userActionList) {
                    if (hotel.getHotel_id().equals(hotel_id)) {
                        isExisted = true;
                        System.out.println(Message.HOTEL_ID_IS_EXISTED + "\n" + Message.ADD_NEW_HOTEL_FAILED);
                        break;
                    }
                }
            } while (isExisted);

            String hotel_Name = Inputter.getString(Message.INPUT_HOTEL_NAME, Message.HOTEL_NAME_MUST_BE_LETTER,
                    Regex.HOTEL_NAME);
            // String hotel_Room_Available =
            // String.valueOf(Inputter.getAnInteger(Message.INPUT_HOTEL_ROOM_AVAILABLE,
            // Message.HOTEL_ROOM_AVAILABLE_MUST_BE_NUMBER, 0, Integer.MAX_VALUE));
            String hotel_Room_Available = Inputter.getString(Message.INPUT_HOTEL_ROOM_AVAILABLE,
                    Message.HOTEL_ROOM_AVAILABLE_MUST_BE_NUMBER, Regex.HOTEL_ROOM_AVAILABLE);
            String hotel_Address = StringTools.formatString(Inputter.getString(Message.INPUT_HOTEL_ADDRESS,
                    Message.HOTEL_ADDRESS_MUST_NOT_CONTAIN_SPECIAL_CHARACTER, Regex.HOTEL_ADDRESS));
            String hotel_Phone = Inputter.getString(Message.INPUT_HOTEL_PHONE, Message.HOTEL_PHONE_MUST_BE_10_DIGITS,
                    Regex.HOTEL_PHONE); // check regex
            String hotel_Rating = Inputter.getString(Message.INPUT_HOTEL_RATING,
                    Message.HOTEL_RATING_MUST_BE_NUMBER_AND_STAR, Regex.HOTEL_RATING);

            HotelModel hotel = new HotelModel(hotel_id, hotel_Name, hotel_Room_Available, hotel_Address, hotel_Phone,
                    hotel_Rating);
            // add to userActionList
            // hotelList.add(hotel);
            userActionList.add(hotel);
            System.out.println(Message.ADD_NEW_HOTEL_SUCCESSFULLY);
        } while (getUserChoice());
    }

    // search trong hotelList gốc
    public int searchHotelIndexId(ArrayList<HotelModel> hotel, String keyId) {
        for (int i = 0; i < hotel.size(); i++) {
            if (hotel.get(i).getHotel_id().equals(keyId)) {
                return i;
            }
        }
        return -1;
    }

    public HotelModel searchHotelByID(ArrayList<HotelModel> hotel, String keyId) {
        int pos = this.searchHotelIndexId(hotel, keyId);
        return pos == -1 ? null : hotel.get(pos);
    }

    // tìm trong 2 mảng hotelList và searchList
    public ArrayList<HotelModel> searchHotelListByID(String keyId) {
        searchList.clear(); // reset searchList
        // tìm trong hotelList
        for (HotelModel hotel : hotelList) {
            if (hotel.getHotel_id().contains(keyId)) {
                searchList.add(hotel);
            }
        }
        Comparator<HotelModel> orderById = new Comparator<HotelModel>() {
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
            String hotel_id = Inputter.getString(Message.INPUT_HOTEL_ID, Message.HOTEL_ID_MUST_BE_H_AND_2_DIGITS,
                    Regex.HOTEL_ID);
            HotelModel hotelModel = this.searchHotelByID(hotelList, hotel_id); // search trong hotelList gốc
            if (hotelModel == null) {
                System.out.println(Message.NO_HOTEL_FOUND);
            } else {
                System.out.println(Message.EXIST_HOTEL);
            }
        } while (getUserChoice());
    }

    // after updating, the program return to the main screen
    public void updateHotel() {
        String hotel_id = Inputter.getString(Message.INPUT_HOTEL_ID, Message.HOTEL_ID_MUST_BE_H_AND_2_DIGITS,
                Regex.HOTEL_ID);
        HotelModel hotel = this.searchHotelByID(userActionList, hotel_id);
        if (hotel == null) {
            System.out.println(Message.HOTEL_DOES_NOT_EXIST);
        } else {
            // các field dữ liệu update có thể rỗng
            String hotel_Name = Inputter.getString(Message.INPUT_HOTEL_NAME);
            String hotel_Room_Available = Inputter.getString(Message.INPUT_HOTEL_ROOM_AVAILABLE);
            String hotel_Address = Inputter.getString(Message.INPUT_HOTEL_ADDRESS);
            String hotel_Phone = Inputter.getString(Message.INPUT_HOTEL_PHONE);
            String hotel_Rating = Inputter.getString(Message.INPUT_HOTEL_RATING);

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
        }
    }

    // after deleting, the program return to the main screen
    public void deleteHotel() {
        String hotel_id = Inputter.getString(Message.INPUT_HOTEL_ID, Message.HOTEL_ID_MUST_BE_H_AND_2_DIGITS,
                Regex.HOTEL_ID);
        // tìm vị trí của hotel cần xóa
        // tìm trong cái mảng gốc
        HotelModel hotel = this.searchHotelByID(hotelList, hotel_id);
        if (hotel == null) {
            System.out.println(Message.DELETE_HOTEL_FAILED);
        } else {
            hotel.showInfo();
            if (!getUserConfirmation()) {
                return;
            }
            userActionList.remove(hotel);
            System.out.println(Message.DELETE_HOTEL_SUCCESSFULLY);
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
                    String hotelId = Inputter.getString(Message.INPUT_HOTEL_ID, Message.HOTEL_NAME_IS_REQUIRED);
                    searchList = this.searchHotelListByID(hotelId); // search trong hotelList (search)
                    if (searchList.isEmpty()) {
                        System.out.println(Message.NO_HOTEL_FOUND);
                    } else {
                        for (HotelModel item : searchList) {
                            item.showInfo();
                        }
                    }
                    break;
                // search by name
                case 2:
                    String hotelName = Inputter.getString(Message.INPUT_HOTEL_NAME, Message.HOTEL_NAME_IS_REQUIRED);
                    for (HotelModel item : hotelList) {
                        if (!item.getHotel_Name().equalsIgnoreCase(hotelName)) {
                            System.out.println(Message.HOTEL_NAME_IS_NOT_EXISTED);
                        } else {
                            System.out.println(Message.HOTEL_NAME_IS_EXISTED);
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
        Comparator<HotelModel> orderByName = new Comparator<HotelModel>() {
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

        System.out.println("Test UserActionList: ");
        for (HotelModel item : userActionList) {
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

    public boolean loadFromFileObj(String url) {
        // hotelList.clear();
        // hotelList = FileHandler.deserialize(url);
        // return hotelList != null;
        if (hotelList.size() > 0) {
            hotelList.clear();
        }
        try {
            File f = new File(url);
            if (!f.exists()) {
                return false;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            HotelModel hotel;
            for (HotelModel item : hotelList) {
                hotel = (HotelModel) fo.readObject();
                hotelList.add(hotel);
            }
            fo.close();
            fi.close();
            System.out.println("Deserialized data is loaded from " + url);
            return true;
        } catch (Exception e) {
            System.out.println("Error read file" + e.getMessage());
            return false;
        }

    }

    public boolean saveToFileObj(String url) {
        try {
            FileOutputStream fOut = new FileOutputStream(url);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            for (HotelModel item : hotelList) {
                out.writeObject(item);
            }
            out.close();
            fOut.close();
            System.out.println("Serialized data is saved in " + url);
            return true;
        } catch (IOException e) {
            System.out.println("Serialization failed: " + e.getMessage());
            return false;
        }
    }

    public boolean readFromFileTxt(String url) {
        try {
            File file = new File(url);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader br = new BufferedReader(new FileReader(url));
            String line = br.readLine();
            while (line != null) {
                StringTokenizer stk = new StringTokenizer(line, "-");
                String hotel_id = stk.nextToken().trim();
                String hotel_Name = stk.nextToken().trim();
                String hotel_Room_Available = stk.nextToken().trim();
                String hotel_Address = stk.nextToken().trim();
                String hotel_Phone = stk.nextToken().trim();
                String hotel_Rating = stk.nextToken().trim();
                HotelModel hotel = new HotelModel(hotel_id, hotel_Name, hotel_Room_Available, hotel_Address,
                        hotel_Phone, hotel_Rating);
                hotelList.add(hotel);

                line = br.readLine();
            }
            userActionList = new ArrayList<>(hotelList); // copy hotelList to userActionList
            System.out.println("Read from file successfully at " + url);
            return true;
        } catch (Exception e) {
            System.out.println("Error save to file" + e.getMessage());
            return false;
        }
    }

    public boolean saveToFileTxt(String url) {
        if (hotelList.isEmpty()) {
            System.out.println("Hotel list is empty");
            return false;
        }
        try {
            File file = new File(url);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // write file trong mang userActionList
            for (HotelModel hotel : userActionList) {
                bw.write(hotel.getHotel_id() + "-" + hotel.getHotel_Name() + "-"
                        + hotel.getHotel_Room_Available() + "-" + hotel.getHotel_Address() + "-"
                        + hotel.getHotel_Phone() + "-" + hotel.getHotel_Rating());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Save to file successfully at " + url);
            return true;
        } catch (Exception e) {
            System.out.println("Error save to file" + e.getMessage());
            return false;
        }
    }

    public void quit() {
        System.exit(0);
    }

    public boolean getUserChoice() {
        return Inputter.getYesNo(Message.DO_YOU_WANT_TO_CONTINUE, Message.PLEASE_INPUT_Y_OR_N)
                .toLowerCase()
                .equals("y");
    }

    public boolean getUserConfirmation() {
        return Inputter.getYesNo(Message.DO_YOU_WANT_TO_DELETE, Message.PLEASE_INPUT_Y_OR_N)
                .toLowerCase()
                .equals("y");
    }
}
