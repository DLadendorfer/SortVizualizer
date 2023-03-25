package aero.sort.vizualizer.ui;

import aero.sort.vizualizer.ui.constants.FrameConstants;
import aero.sort.vizualizer.ui.laf.UIBindings;
import com.bulenkov.darcula.DarculaLaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;

/**
 * Main application frame.
 *
 * @author Daniel Ladendorfer
 */
public class MainFrame extends JFrame {

    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);

    static {
        UIBindings.setupLookAndFeel();
    }

    public MainFrame() {
        createFrame();
        add(new JLabel("Top"), BorderLayout.NORTH);
        var center = new JPanel();
        center.setBackground(center.getBackground().darker());
        center.add(new JLabel("Center"));
        add(center, BorderLayout.CENTER);
        add(new JLabel("Bottom"), BorderLayout.SOUTH);
    }

    private void createFrame() {
        setTitle(FrameConstants.TITLE);
        setMinimumSize(FrameConstants.MIN_DIMENSION);
        setPreferredSize(FrameConstants.PREFERRED_DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
}
