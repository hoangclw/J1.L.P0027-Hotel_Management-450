package views;

import constants.Message;
import constants.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import styles.BorderHandler;
import styles.ColorHandler;
import styles.FontHandler;
import controllers.HotelManagementController;

public class HotelManagementView extends JFrame implements ActionListener {

    // Define all the components and variables at the top
    private JPanel jPanel_container = new JPanel(); // Main container panel
    private JPanel jPanel_left = new JPanel(); // Left panel
    private JPanel jPanel_left_button = new JPanel(new GridLayout(8, 1)); // Left button panel
    private JPanel jPanel_right = new JPanel(); // Right panel

    private JLabel jLabel_title = new JLabel("Hotel Management"); // Title label

    // Buttons
    private JButton jButton_add = new JButton("Add");
    private JButton jButton_exist = new JButton("Exits");
    private JButton jButton_update = new JButton("Update");
    private JButton jButton_delete = new JButton("Delete");
    private JButton jButton_search = new JButton("Search");
    private JButton jButton_search_id = new JButton("Search ID");
    private JButton jButton_search_name = new JButton("Search Name");
    private JButton jButton_show = new JButton("Show");
    private JButton jButton_saveToFile = new JButton("Save to File");
    private JButton jButton_quit = new JButton("Quit");

    public static JTextArea jTextArea_result; // Text area for results

    private Dimension dimension = new Dimension(50, 30); // Dimension for buttons

    private JFrame jFrame_search; // Frame for search

    private static String btnValue = "";
    private HotelManagementController hotelManagementController;

    public HotelManagementView() {
        this.hotelManagementController = new HotelManagementController();
        // Set up the main frame
        setTitle("Hotel Management");
        setSize(Size.FRAME_WIDTH, Size.FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize the view
        initHotelManagementView();
    }

    private void initHotelManagementView() {
        // Set up all the panels and user actions
        setupLeftPanel();
        setupRightPanel();
        setupSearchPanel();
        setupContainerPanel();
        userAction();
    }

    private void setupLeftPanel() {
        // Set up the left panel with title and buttons
        jLabel_title.setText("Hotel Management");
        jLabel_title.setBorder(BorderHandler.TITLE_BORDER);
        jLabel_title.setFont(FontHandler.TITLE_FONT);
        jLabel_title.setForeground(ColorHandler.TEXT_COLOR);
        jLabel_title.setBackground(ColorHandler.PRIMARY_COLOR);

        // Set the preferred size for all buttons

        jButton_add.setFont(FontHandler.BUTTON_FONT);
        jButton_exist.setFont(FontHandler.BUTTON_FONT);
        jButton_update.setFont(FontHandler.BUTTON_FONT);
        jButton_delete.setFont(FontHandler.BUTTON_FONT);
        jButton_search.setFont(FontHandler.BUTTON_FONT);
        jButton_search_id.setFont(FontHandler.BUTTON_FONT);
        jButton_search_name.setFont(FontHandler.BUTTON_FONT);
        jButton_show.setFont(FontHandler.BUTTON_FONT);
        jButton_saveToFile.setFont(FontHandler.BUTTON_FONT);
        jButton_quit.setFont(FontHandler.BUTTON_FONT);

        jButton_add.setPreferredSize(dimension);
        jButton_exist.setPreferredSize(dimension);
        jButton_update.setPreferredSize(dimension);
        jButton_delete.setPreferredSize(dimension);
        jButton_search_name.setPreferredSize(dimension);
        jButton_show.setPreferredSize(dimension);
        jButton_saveToFile.setPreferredSize(dimension);
        jButton_quit.setPreferredSize(dimension);

        jButton_add.setBackground(ColorHandler.TEXT_COLOR);
        jButton_exist.setBackground(ColorHandler.TEXT_COLOR);
        jButton_update.setBackground(ColorHandler.TEXT_COLOR);
        jButton_delete.setBackground(ColorHandler.TEXT_COLOR);
        jButton_search.setBackground(ColorHandler.TEXT_COLOR);
        jButton_search_id.setBackground(ColorHandler.TEXT_COLOR);
        jButton_search_name.setBackground(ColorHandler.TEXT_COLOR);
        jButton_show.setBackground(ColorHandler.TEXT_COLOR);
        jButton_saveToFile.setBackground(ColorHandler.TEXT_COLOR);
        jButton_quit.setBackground(ColorHandler.TEXT_COLOR);

        jButton_add.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_exist.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_update.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_delete.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_search.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_search_id.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_search_name.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_show.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_saveToFile.setForeground(ColorHandler.PRIMARY_COLOR);
        jButton_quit.setForeground(ColorHandler.PRIMARY_COLOR);

        // Add all buttons to the left button panel
        jPanel_left_button.add(jButton_add);
        jPanel_left_button.add(jButton_exist);
        jPanel_left_button.add(jButton_update);
        jPanel_left_button.add(jButton_delete);
        jPanel_left_button.add(jButton_search);
        jPanel_left_button.add(jButton_show);
        jPanel_left_button.add(jButton_saveToFile);
        jPanel_left_button.add(jButton_quit);

        // Set the layout for the left panel and add the title and button panel to it
        jPanel_left.setLayout(new BorderLayout());
        jPanel_left.add(jLabel_title, BorderLayout.NORTH);
        jPanel_left.add(jPanel_left_button, BorderLayout.CENTER);
    }

    private void setupRightPanel() {
        // Set up the right panel with a text area for results
        jTextArea_result = new JTextArea(3, 30);
        jTextArea_result.setFont(FontHandler.TEXT_FIELD_FONT);
        jTextArea_result.setBackground(ColorHandler.PRIMARY_COLOR);
        jTextArea_result.setForeground(ColorHandler.TEXT_COLOR);

        jPanel_right.setLayout(new BorderLayout());
        jPanel_right.setBorder(BorderHandler.TEXT_FIELD_BORDER);
        jPanel_right.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_right.add(jTextArea_result, BorderLayout.CENTER);
    }

    private void setupContainerPanel() {
        // Set up the main container panel with the left and right panels
        jPanel_container.setBackground(ColorHandler.PRIMARY_COLOR);
        jPanel_container.setLayout(new BorderLayout());
        jPanel_container.add(jPanel_left, BorderLayout.WEST);
        jPanel_container.add(jPanel_right, BorderLayout.CENTER);
        add(jPanel_container);
    }

    private void setupSearchPanel() {
        // Set up the search frame with two buttons for searching by ID and name
        jFrame_search = new JFrame();
        jFrame_search.setTitle("Search");
        jFrame_search.setSize(300, 300);
        jFrame_search.setLocationRelativeTo(null);
        jFrame_search.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel jPanel_search = new JPanel();
        jPanel_search.setLayout(new GridLayout(1, 2));
        jPanel_search.add(jButton_search_id);
        jPanel_search.add(jButton_search_name);

        jFrame_search.add(jPanel_search);
        jFrame_search.setVisible(false);
    }

    private void userAction() {
        // Set up all the action listeners for the buttons
        jButton_add.addActionListener(this);
        jButton_exist.addActionListener(this);
        jButton_update.addActionListener(this);
        jButton_delete.addActionListener(this);
        jButton_search.addActionListener(this);
        jButton_search_id.addActionListener(this);
        jButton_search_name.addActionListener(this);
        jButton_show.addActionListener(this);
        jButton_saveToFile.addActionListener(this);
        jButton_quit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // consoleArea.setText(null);
        JButton clickedButton = (JButton) e.getSource();
        String btnValue = clickedButton.getText();
        // Call the controller method with the button value
        hotelManagementController.clicksOnMenu(btnValue);
    }

    public static String getBtnValue() {
        if (!btnValue.isEmpty()) {
            return btnValue;
        } else {
            return "";
        }
    }

    // Action classes for each button
    private class AddAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message.createInputDialog("Input Add Data: ");
        }
    }

    private class CheckExistsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = Message.createInputDialog("Input Check Exists Data: ");
            System.out.println("User input: " + str);
        }
    }

    private class UpdateAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = Message.createInputDialog("Input Update Data: ");
            System.out.println("User input: " + str);
        }
    }

    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message.createInputDialog("Input Delete Data: ");
        }
    }

    private class SearchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jFrame_search.setVisible(true);
            String buttonValue = e.getActionCommand();
            if (buttonValue.equals("Search ID")) {
                Message.createInputDialog("Input Search ID: ");
            } else if (buttonValue.equals("Search Name")) {
                Message.createInputDialog("Input Search Name: ");
            }
        }
    }

    private class ShowAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message.createInputDialog("Input Show Data: ");
        }
    }

    private class SaveToFileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message.createInputDialog("Input Save to File Data: ");
        }
    }

    private class QuitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message.createInputDialog("Input Quit Data: ");
        }
    }

}