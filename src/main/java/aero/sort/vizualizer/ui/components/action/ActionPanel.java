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
        var constraints = createGridBagConstraints();
        var algorithmComboBox = new JComboBox<>(Algorithm.values());
        var visualizationComboBox = new JComboBox<>(Visualization.values());
        var styleComboBox = new JComboBox<>(Style.values());
        var addButton = UiFactory.createButton("Add Sorter", () -> {
            var algorithm = (Algorithm) algorithmComboBox.getSelectedItem();
            var visualization = (Visualization) visualizationComboBox.getSelectedItem();
            var style = (Style) styleComboBox.getSelectedItem();
            var options = new SortOptions(algorithm, visualization, style);
            MainFrame.getInstance().createInternalFrame(options);
        });

        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        constraints.gridwidth = 2;
        constraints.ipady = 5;
        panel.add(new JSeparator(), constraints);
        constraints.gridy++;
        constraints.ipady = 0;
        panel.add(new JLabel(), constraints);
        constraints.gridwidth = 1;
        constraints.gridy++;
        constraints.gridx = 0;
        panel.add(new JLabel("Algorithm: "), constraints);
        constraints.gridx++;
        panel.add(algorithmComboBox, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel("Visualization: "), constraints);
        constraints.gridx++;
        panel.add(visualizationComboBox, constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel("Style: "), constraints);
        constraints.gridx++;
        panel.add(styleComboBox, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        JColorChooser jColorChooser = new JColorChooser();
        jColorChooser.setColor(Theme.YELLOW);
        var chooser = jColorChooser.getChooserPanels()[1];
        var button = UiFactory.createColorButton(jColorChooser::getColor, () -> JOptionPane.showMessageDialog(null, chooser, "Choose a color", JOptionPane.PLAIN_MESSAGE));
        panel.add(new JLabel("Primary Color: "), constraints);
        constraints.gridx++;
        panel.add(button, constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(addButton, constraints);
        constraints.gridy++;
        constraints.weighty = 420.69f; // do not delete this or else the ui will break
        panel.add(new JPanel(), constraints);


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
