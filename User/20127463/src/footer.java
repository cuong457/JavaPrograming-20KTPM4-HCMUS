
import javax.swing.*;
import java.awt.*;

public class footer {
    protected JPanel FooterPanel() {
        JPanel FooterPanel = new JPanel();
        FooterPanel.setLayout(new FlowLayout());

        JLabel copyright = new JLabel("<html><p style=\"font-family:Verdana;font-size:8px;\">Copyright © 2022 by Sunrise Company</p></html>");
        FooterPanel.add(copyright);

        return FooterPanel;
    }
}
