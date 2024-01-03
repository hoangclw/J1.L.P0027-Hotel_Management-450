package styles;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class BorderHandler {
    public static final EmptyBorder TITLE_BORDER = new EmptyBorder(10, 10, 10, 10);
    public static final EmptyBorder BUTTON_BORDER = new EmptyBorder(10, 10, 10, 10);
    public static final EmptyBorder TEXT_FIELD_BORDER = new EmptyBorder(10, 10, 10, 10);
    public static final Border FACTORY_BORDER = BorderFactory.createLineBorder(ColorHandler.OTHER_OPTIONS, 1);
}
