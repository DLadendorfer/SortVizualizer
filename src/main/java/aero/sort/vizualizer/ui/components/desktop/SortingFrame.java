package aero.sort.vizualizer.ui.components.desktop;

import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.constants.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.InternalFrameUI;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Internal frame that visualizes the sorting process of a sort algorithm.
 *
 * @author Daniel Ladendorfer
 */
public class SortingFrame extends JInternalFrame {

    private final SortOptions options;

    public SortingFrame(SortOptions options) {
        this.options = options;

        setTitle("%s - %s".formatted(options.algorithm(), options.visualization()));
        setBounds(10, 10, 600, 600);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        try {
            setFrameIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(MainFrame.class.getClassLoader().getResourceAsStream("icons/sort.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // create a Button

        // create a label to display text
        var l = new JLabel("This is a JInternal Frame  ");

        // create a panel
        JPanel p = new JPanel();
        p.setBackground(Theme.BACKGROUND);

        // add label and button to panel
        p.add(l);
        p.setPreferredSize(new Dimension(600, 600));


        // set visibility internal frame
        setVisible(true);

        // add panel to internal frame
        add(p);
    }
}
