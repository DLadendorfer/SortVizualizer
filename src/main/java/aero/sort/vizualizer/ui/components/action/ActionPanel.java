package aero.sort.vizualizer.ui.components.action;

import aero.sort.vizualizer.ui.components.status.StatusBar;
import aero.sort.vizualizer.ui.constants.Theme;
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
        panel.add(new JComboBox<>(Algorithms.values()), constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel("Visualization: "), constraints);
        constraints.gridx++;
        panel.add(new JComboBox<>(Visualization.values()), constraints);
        constraints.gridx = 0;
        constraints.gridy++;
        panel.add(new JLabel("Style: "), constraints);
        constraints.gridx++;
        panel.add(new JComboBox<>(Style.values()), constraints);
        constraints.gridy++;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        panel.add(new JButton("Add Sorter"), constraints);
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
        var panel = new JPanel();
        var sort = new JButton("Sort");
        var step = new JButton("Step");
        var stop = new JButton("Stop");
        sort.setSelected(true);
        panel.add(sort);
        panel.add(step);
        panel.add(stop);

        return panel;
    }


}
