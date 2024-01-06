package controllers;

import constants.Message;
import constants.Path;
import models.HotelManagement;
import models.Menu;
import views.HotelManagementView;

public class HotelManagementController {
    HotelManagement hm = new HotelManagement();
    Menu menu = new Menu("Hotel Management System");
    int choice = 0;

    public HotelManagementController() {
        hm.loadFromFile(Path.DATA);
        initMenu();
        System.out.println("Hotel Management Controller");
        clicksOnMenu(HotelManagementView.getBtnValue());
    }

    private void initMenu() {

        menu.addNewOption("Adding new hotel");
        menu.addNewOption("Checking exists hotel");
        menu.addNewOption("Upating Hotel information");
        menu.addNewOption("Deleting Hotel");
        menu.addNewOption("Searching Hotel");
        menu.addNewOption("Display a hotel list");
        menu.addNewOption("Save to file");
        menu.addNewOption("Others Quit");
    }

    public void clicksOnMenu(String btnValue) {
        try {
            switch (btnValue) {
                case "Add":
                    hm.addNewHotel();
                    Message.createInputDialog("Input Add Data: ");
                    break;
                case "Exits":
                    hm.checkExistsHotel();
                    break;
                case "Update":
                    hm.updateHotel();
                    break;
                case "Delete":
                    hm.deleteHotel();
                    break;
                case "Search":
                    String str = Message.createInputDialog("Input search id(Hxx)");
                    hm.searchHotel();

                    break; 
                case "Show":
                    hm.displayHotelList();
                    HotelManagementView.jTextArea_result.setText(hm.hotelList.toString());
                    break;
                case "Save to File":
                    if (hm.saveToFile(Path.DATA)) {
                        Message.createSuccessDialog("Save file successfully at " + Path.DATA);
                    } else {
                        Message.createErrorDialog("Save file failed");
                    }
                    ;
                    break;
                case "Quit":
                    hm.exitProgram();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error controller: " + e.getMessage());
        }
    }

}
