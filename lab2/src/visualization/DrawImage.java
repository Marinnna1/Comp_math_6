package visualization;

import javax.swing.*;

public class DrawImage extends JPanel {

    public static void make() {
        JFrame fr = new JFrame();
        fr.setSize(500,500);
        fr.setVisible(true);
        fr.add(new DrawImage());

    }
}
