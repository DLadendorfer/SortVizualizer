// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu;

import aero.sort.vizualizer.ui.components.menu.concrete.*;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import org.jetbrains.annotations.NotNull;
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

    private final @NotNull JPanel actionInvocationPanel;
    private final @NotNull JPanel sidebarPanel;

    public MenuPanel() {
        actionInvocationPanel = createActionInvocationPanel();
        sidebarPanel = createSidebarPanel();

        initialize();
    }

    private void initialize() {
        logger.debug("Initializing StatusBar");

        createPanel();
    }

    private void createPanel() {
        setLayout(new BorderLayout());
        add(actionInvocationPanel, BorderLayout.NORTH);
        var scrollPane = new JScrollPane(sidebarPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private @NotNull JPanel createSidebarPanel() {
        var panel = new JPanel(new GridBagLayout());
        var constraints = FluentConstraints.of(createGridBagConstraints());

        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setAlignmentY(Component.TOP_ALIGNMENT);
        createSidebarPanel(panel, constraints);
        panel.add(new JPanel(), constraints.incrementY()
                                           .weightY(420.69f)
                                           .get());// do not delete this or else the ui will break

        return panel;
    }

    private static void createSidebarPanel(@NotNull JPanel panel, @NotNull FluentConstraints constraints) {
        // panels
        var addSorterPanel = new AddSorterPanel(constraints);
        var visualizationPanel = new VisualizationPanel(constraints);
        var sortSetPanel = new SortSetPanel(constraints);
        var debugPanel = new DebugPanel(constraints);
        debugPanel.setVisible(false);

        // add panels at the correct position
        panel.add(addSorterPanel, constraints.width(1)
                                             .get());
        panel.add(visualizationPanel, constraints.resetX()
                                                 .incrementY()
                                                 .width(1)
                                                 .get());
        panel.add(sortSetPanel, constraints.resetX()
                                           .incrementY()
                                           .padY(0)
                                           .width(1)
                                           .get());
        panel.add(debugPanel, constraints.resetX()
                                         .incrementY()
                                         .width(1)
                                         .get());
    }

    private @NotNull GridBagConstraints createGridBagConstraints() {
        var constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(1, 7, 1, 3);
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        return constraints;
    }

    private @NotNull JPanel createActionInvocationPanel() {
        return new InvocationPanel();
    }
}
