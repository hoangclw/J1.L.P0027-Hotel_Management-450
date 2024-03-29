package views;

import constants.Path;
import controllers.HotelManagement;
import models.Menu;

public class Main {
    public static void main(String[] args) {

        HotelManagement hm = new HotelManagement();

        // hm.loadFromFile(Path.URL);
        hm.readFromFileTxt(Path.URL_TXT);

        Menu menu = new Menu("Hotel Management System");
        menu.addNewOption("Adding new hotel");
        menu.addNewOption("Checking exists hotel");
        menu.addNewOption("Updating Hotel information");
        menu.addNewOption("Deleting Hotel");
        menu.addNewOption("Searching Hotel");
        menu.addNewOption("Display a hotel list");
        menu.addNewOption("Save to file");
        menu.addNewOption("Others Quit");

        int choice;
        do {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    hm.addNewHotel();
                    break;
                case 2:
                    hm.checkExistsHotel();
                    break;
                case 3:
                    hm.updateHotel();
                    break;
                case 4:
                    hm.deleteHotel();
                    break;
                case 5:
                    hm.searchHotel();
                    break;
                case 6:
                    hm.displayHotelList();
                    break;
                case 7:
                    hm.saveToFileTxt(Path.URL_TXT);
                    hm.saveToFile(Path.URL);
                    break;
                case 8:
                    hm.quit();
                    break;
            }
        } while (choice != 8);
    }
}
