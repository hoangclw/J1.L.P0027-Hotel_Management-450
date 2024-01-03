package constants;

import javax.swing.JOptionPane;

public class Message {

    public static void createSuccessDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createErrorDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static String createInputDialog(String msg) {
        return JOptionPane.showInputDialog(null, msg, "Input", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createConfirmationMsg(String msg) {
        int result = JOptionPane.showConfirmDialog(
                null,
                msg,
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            System.out.println("User clicked No");
            JOptionPane.getRootFrame().dispose();
        }
    }

}
