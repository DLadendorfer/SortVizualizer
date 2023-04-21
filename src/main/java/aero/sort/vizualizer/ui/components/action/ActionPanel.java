// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.action;

import aero.sort.vizualizer.data.options.Algorithm;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.Style;
import aero.sort.vizualizer.data.options.Visualization;
import aero.sort.vizualizer.data.options.styles.StyleType;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.Ui;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
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
        var scrollPane = new JScrollPane(addFramePanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createAddFramePanel() {
        var panel = new JPanel(new GridBagLayout());
        var constraints = FluentConstraints.of(createGridBagConstraints());

        panel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        panel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        createAddSorterPanel(panel, constraints);
        panel.add(new JPanel(), constraints.incrementY().weightY(420.69f).get());// do not delete this or else the ui will break

        return panel;
    }

    private static void createAddSorterPanel(JPanel panel, FluentConstraints constraints) {
        var algorithmComboBox = new JComboBox<>(Algorithm.values());
        var visualizationComboBox = new JComboBox<>(Visualization.values());
        var styleTypeComboBox = new JComboBox<>(StyleType.values());
        var styleComboBox = new JComboBox<>(Style.values());
        var primaryColor = new JColorChooser(Theme.UI_ACCENT);
        var secondaryColor = new JColorChooser(Theme.UI_ACCENT_2);
        var showStats = new JCheckBox("", true);
        var addButton = UiFactory.createButton("Add Sorter", () -> {
            var algorithm = (Algorithm) algorithmComboBox.getSelectedItem();
            var visualization = (Visualization) visualizationComboBox.getSelectedItem();
            var style = (Style) styleComboBox.getSelectedItem();
            var colors = new SortOptions.Colors(primaryColor.getColor(), secondaryColor.getColor());
            var options = new SortOptions(algorithm, visualization, style, colors);
            MainFrame.getInstance().createInternalFrame(options);
        });
        var addSorterPanel = new JPanel(new GridBagLayout());
        var border = new TitledBorder(new MatteBorder(1, 0, 0, 0, Theme.FOREGROUND), "Add new sorter ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
        border.setTitleFont(border.getTitleFont().deriveFont(15.0f));

        addSorterPanel.setBorder(border);
        addSorterPanel.add(new JLabel(), constraints.incrementY().padY(0).get());
        addSorterPanel.add(new JLabel("Algorithm: "), constraints.width(1).incrementY().resetX().get());
        addSorterPanel.add(algorithmComboBox, constraints.incrementX().get());
        addSorterPanel.add(new JLabel("Visualization: "), constraints.resetX().incrementY().get());
        addSorterPanel.add(visualizationComboBox, constraints.incrementX().get());
        addSorterPanel.add(new JLabel("Style Type: "), constraints.resetX().incrementY().get());
        addSorterPanel.add(styleTypeComboBox, constraints.incrementX().get());
        addSorterPanel.add(new JLabel("Style: "), constraints.resetX().incrementY().get());
        addSorterPanel.add(styleComboBox, constraints.incrementX().get());
        addSorterPanel.add(new JLabel("Show statistics: "), constraints.resetX().incrementY().get());
        addSorterPanel.add(showStats, constraints.incrementX().get());
        addSorterPanel.add(new JLabel("Primary Color: "), constraints.resetX().incrementY().get());
        addSorterPanel.add(UiFactory.createColorButton(primaryColor::getColor, () -> Ui.showInfo("Choose a primary color", primaryColor.getChooserPanels()[1])), constraints.incrementX().get());
        addSorterPanel.add(new JLabel("Secondary Color: "), constraints.resetX().incrementY().get());
        addSorterPanel.add(UiFactory.createColorButton(secondaryColor::getColor, () -> Ui.showInfo("Choose a secondary color", secondaryColor.getChooserPanels()[1])), constraints.incrementX().get());
        addSorterPanel.add(addButton, constraints.resetX().incrementY().width(2).get());
        panel.add(addSorterPanel, constraints.width(1).get());

        var visualizationPanel = new JPanel(new GridBagLayout());
        var borderV = new TitledBorder(new MatteBorder(1, 0, 0, 0, Theme.FOREGROUND), "Visualization Options ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
        borderV.setTitleFont(borderV.getTitleFont().deriveFont(15.0f));


        var showShuffle = new JCheckBox("", true);
        var showSteps = new JCheckBox("", true);
        var stepTime = new JSlider(0, 3000, 200);
        var markType = new ButtonGroup();
        var fill = new JRadioButton("Fill", true);
        var outline = new JRadioButton("Outline");
        var off = new JRadioButton("Off");
        var markerColor = new JColorChooser(Theme.RED);
        markType.add(fill);
        markType.add(outline);
        markType.add(off);
        stepTime.setMajorTickSpacing(1000);
        stepTime.setPaintTicks(true);
        stepTime.setPaintLabels(true);
        visualizationPanel.setBorder(borderV);
        visualizationPanel.add(new JLabel("Show shuffle: "), constraints.resetX().incrementY().get());
        visualizationPanel.add(showShuffle, constraints.incrementX().get());
        visualizationPanel.add(new JLabel("Show steps: "), constraints.resetX().incrementY().get());
        visualizationPanel.add(showSteps, constraints.incrementX().get());
        visualizationPanel.add(new JLabel("Step duration (ms): "), constraints.resetX().incrementY().get());
        visualizationPanel.add(stepTime, constraints.incrementX().get());
        visualizationPanel.add(new JLabel("Mark Type: "), constraints.resetX().incrementY().get());
        visualizationPanel.add(fill, constraints.incrementX().padY(-10).get());
        visualizationPanel.add(outline, constraints.incrementY().get());
        visualizationPanel.add(off, constraints.incrementY().get());
        visualizationPanel.add(new JLabel("Marker Color: "), constraints.resetX().padY(0).incrementY().get());
        visualizationPanel.add(UiFactory.createColorButton(markerColor::getColor, () -> Ui.showInfo("Choose a primary color", markerColor.getChooserPanels()[1])), constraints.incrementX().get());

        panel.add(visualizationPanel, constraints.resetX().incrementY().width(1).get());

        var sortSetPanel = new JPanel(new GridBagLayout());
        var borderS = new TitledBorder(new MatteBorder(1, 0, 0, 0, Theme.FOREGROUND), "Sort Set Options ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
        borderS.setTitleFont(borderS.getTitleFont().deriveFont(15.0f));


        var identical = new JCheckBox("", true);
        var elements = new JSlider(0, 100, 20);
        var duplicates = new ButtonGroup();
        var none = new JRadioButton("None", true);
        var some = new JRadioButton("Some");
        var many = new JRadioButton("Many");
        var setType = new ButtonGroup();
        var random = new JRadioButton("Random", true);
        var sorted = new JRadioButton("Sorted");
        var almostSorted = new JRadioButton("Almost sorted");
        var completelyUnsorted = new JRadioButton("Completely unsorted");
        setType.add(random);
        setType.add(sorted);
        setType.add(almostSorted);
        setType.add(completelyUnsorted);
        elements.setMajorTickSpacing(10);
        elements.setPaintTicks(true);
        elements.setPaintLabels(true);
        sortSetPanel.setBorder(borderS);
        sortSetPanel.add(new JLabel("Identical set: "), constraints.resetX().incrementY().get());
        sortSetPanel.add(identical, constraints.incrementX().get());
        sortSetPanel.add(new JLabel("Number of elements: "), constraints.resetX().incrementY().get());
        sortSetPanel.add(elements, constraints.incrementX().get());
        sortSetPanel.add(new JLabel("Duplicates: "), constraints.resetX().incrementY().get());
        sortSetPanel.add(none, constraints.padY(-10).incrementX().get());
        sortSetPanel.add(some, constraints.incrementY().get());
        sortSetPanel.add(many, constraints.incrementY().get());
        sortSetPanel.add(new JLabel("Set Type: "), constraints.padY(0).resetX().incrementY().get());
        sortSetPanel.add(random, constraints.incrementX().padY(-10).get());
        sortSetPanel.add(sorted, constraints.incrementY().get());
        sortSetPanel.add(almostSorted, constraints.incrementY().get());
        sortSetPanel.add(completelyUnsorted, constraints.incrementY().get());

        panel.add(sortSetPanel, constraints.resetX().incrementY().padY(0).width(1).get());

        var debugPanel = new JPanel(new GridBagLayout());
        var borderD = new TitledBorder(new MatteBorder(1, 0, 0, 0, Theme.FOREGROUND), "Debugging ", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
        borderD.setTitleFont(borderD.getTitleFont().deriveFont(15.0f));

        var logLevelComboBox = new JComboBox<>(Level.values());
        var debugButton = UiFactory.createButton("Add Log Frame", () -> {});
        debugPanel.setBorder(borderD);
        debugPanel.add(new JLabel("Log Level: "), constraints.width(1).incrementY().resetX().get());
        debugPanel.add(logLevelComboBox, constraints.incrementX().get());
        debugPanel.add(debugButton, constraints.resetX().incrementY().width(2).get());
        panel.add(debugPanel, constraints.resetX().incrementY().width(1).get());
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
        var resume = UiFactory.createButton("Resume", () -> logger.info("Resume pressed, lmao."));
        var stop = UiFactory.createButton("Stop", () -> MainFrame.getInstance().getController().stopSort());
        sort.setSelected(true);

        return Ui.using(new JPanel()).add(sort, step, resume, stop).get();
    }


}
