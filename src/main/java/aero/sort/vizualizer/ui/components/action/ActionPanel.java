// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.action;

import aero.sort.vizualizer.data.options.Algorithm;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.Style;
import aero.sort.vizualizer.data.options.Visualization;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.constants.Theme;
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
public class ActionPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(ActionPanel.class);

    private final JPanel actionInvocationPanel;
    private final JPanel addFramePanel;

    public ActionPanel() {
        actionInvocationPanel = createActionInvocationPanel();
        addFramePanel = createAddFramePanel();

        initialize();
    }

    private void initialize() {
        logger.debug("Initializing StatusBar");

        createPanel();
    }

    private void createPanel() {
        setLayout(new BorderLayout());
        add(actionInvocationPanel, BorderLayout.NORTH);
        add(addFramePanel, BorderLayout.CENTER);
    }

    private JPanel createAddFramePanel() {
        var panel = new JPanel(new GridBagLayout());
        var constraints = FluentConstraints.of(createGridBagConstraints());
        var algorithmComboBox = new JComboBox<>(Algorithm.values());
        var visualizationComboBox = new JComboBox<>(Visualization.values());
        var styleComboBox = new JComboBox<>(Style.values());
        var primaryColor = new JColorChooser(Theme.CYAN);
        var secondaryColor = new JColorChooser(Theme.YELLOW);
        var addButton = UiFactory.createButton("Add Sorter", () -> {
            var algorithm = (Algorithm) algorithmComboBox.getSelectedItem();
            var visualization = (Visualization) visualizationComboBox.getSelectedItem();
            var style = (Style) styleComboBox.getSelectedItem();
            var colors = new SortOptions.Colors(primaryColor.getColor(), secondaryColor.getColor());
            var options = new SortOptions(algorithm, visualization, style, colors);
            MainFrame.getInstance().createInternalFrame(options);
        });

        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        panel.add(new JSeparator(), constraints.width(2).padY(5).get());
        panel.add(new JLabel(), constraints.incrementY().padY(0).get());
        panel.add(new JLabel("Algorithm: "), constraints.width(1).incrementY().resetX().get());
        panel.add(algorithmComboBox, constraints.incrementX().get());
        panel.add(new JLabel("Visualization: "), constraints.resetX().incrementY().get());
        panel.add(visualizationComboBox, constraints.incrementX().get());
        panel.add(new JLabel("Style: "), constraints.resetX().incrementY().get());
        panel.add(styleComboBox, constraints.incrementX().get());
        panel.add(new JLabel("Primary Color: "), constraints.resetX().incrementY().get());
        panel.add(UiFactory.createColorButton(primaryColor::getColor, () -> Ui.showInfo("Choose a primary color", primaryColor.getChooserPanels()[1])), constraints.incrementX().get());
        panel.add(new JLabel("Secondary Color: "), constraints.resetX().incrementY().get());
        panel.add(UiFactory.createColorButton(secondaryColor::getColor, () -> Ui.showInfo("Choose a secondary color", secondaryColor.getChooserPanels()[1])), constraints.incrementX().get());
        panel.add(addButton, constraints.resetX().incrementY().width(2).get());
        panel.add(new JPanel(), constraints.incrementY().weightY(420.69f).get());// do not delete this or else the ui will break

        return panel;
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
        var sort = UiFactory.createButton("Sort", () -> MainFrame.getInstance().getController().sort());
        var step = UiFactory.createButton("Step", () -> logger.info("Step pressed, lmao."));
        var stop = UiFactory.createButton("Stop", () -> MainFrame.getInstance().getController().stopSort());
        sort.setSelected(true);

        return Ui.using(new JPanel()).add(sort, step, stop).get();
    }


}
