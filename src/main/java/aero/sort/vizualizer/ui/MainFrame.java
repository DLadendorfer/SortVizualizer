package aero.sort.vizualizer.ui;

import aero.sort.vizualizer.ui.components.status.StatusBar;
import aero.sort.vizualizer.ui.constants.FrameConstants;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.laf.UIBindings;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.InternalFrameUI;
import java.awt.*;
import java.util.UUID;

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
        var actionPanel = new JPanel();
        var button = new JButton("Sort");
        button.addActionListener(e -> logger.debug("Sort pressed. " + UUID.randomUUID().toString()));

        actionPanel.add(button);
        actionPanel.add(new JLabel("Actions"));
        add( actionPanel, BorderLayout.WEST);
        var center = new JPanel();
        center.setBackground(Theme.BLACK);
        var desktop = new JDesktopPane();
        desktop.setBackground(Theme.BLACK);
        desktop.add(createInternalFrame());
        desktop.add(createInternalFrame());
        desktop.add(createInternalFrame());
        desktop.setVisible(true);
        add(desktop, BorderLayout.CENTER);
        add(new StatusBar(), BorderLayout.SOUTH);
        pack();
    }

    @NotNull
    private static JInternalFrame createInternalFrame() {
        JInternalFrame intern = new JInternalFrame("Test") {
            @Override
            public void setUI(InternalFrameUI ui) {
                super.setUI(ui); // this gets called internally when updating the ui and makes the northPane reappear
//                BasicInternalFrameUI frameUI = (BasicInternalFrameUI) getUI(); // so...
//                if (frameUI != null) {
//                    frameUI.setNorthPane(null); // lets get rid of it
//                }
            }
        };
        intern.setBounds(10, 10, 600, 600);
        intern.setResizable(true);
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
        intern.setVisible(true);

        // add panel to internal frame
        intern.add(p);
        // set the size of frame
        return intern;
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
