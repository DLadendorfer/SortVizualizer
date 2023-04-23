// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu;

import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.menu.concrete.*;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.Ui;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Action panel that contains all button actions.
 *
 * @author Daniel Ladendorfer
 */
public class MenuPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(MenuPanel.class);

    private final JPanel actionInvocationPanel;
    private final JPanel menuPanel;

    public MenuPanel() {
        actionInvocationPanel = createActionInvocationPanel();
        menuPanel = createMenuPanels();

        initialize();
    }

    private void initialize() {
        logger.debug("Initializing StatusBar");

        createPanel();
    }

    private void createPanel() {
        setLayout(new BorderLayout());
        add(actionInvocationPanel, BorderLayout.NORTH);
        var scrollPane = new JScrollPane(menuPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createMenuPanels() {
        var panel = new JPanel(new GridBagLayout());
        var constraints = FluentConstraints.of(createGridBagConstraints());

        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        createMenuPanels(panel, constraints);
        panel.add(new JPanel(), constraints.incrementY().weightY(420.69f).get());// do not delete this or else the ui will break

        return panel;
    }

    private static void createMenuPanels(JPanel panel, FluentConstraints constraints) {
        panel.add(new AddSorterPanel(constraints), constraints.width(1).get());
        panel.add(new VisualizationPanel(constraints),constraints.resetX().incrementY().width(1).get());
        panel.add(new SortSetPanel(constraints), constraints.resetX().incrementY().padY(0).width(1).get());
        panel.add(new DebugPanel(constraints),  constraints.resetX().incrementY().width(1).get());
    }

    private GridBagConstraints createGridBagConstraints() {
        var constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(1, 7, 1, 3);
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        return constraints;
    }

    private JPanel createActionInvocationPanel() {
        return new InvocationPanel();
    }
}
