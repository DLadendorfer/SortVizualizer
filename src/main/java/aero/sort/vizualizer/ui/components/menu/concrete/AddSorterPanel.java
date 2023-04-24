// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.data.options.Algorithm;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.Style;
import aero.sort.vizualizer.data.options.Visualization;
import aero.sort.vizualizer.data.options.styles.StyleType;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.menu.AbstractMenuPanel;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.Ui;
import aero.sort.vizualizer.utilities.ui.UiFactory;

import javax.swing.*;

/**
 * Panel that manages the creation of new sorter-frames.
 *
 * @author Daniel Ladendorfer
 */
public class AddSorterPanel extends AbstractMenuPanel<SortOptions> {
    private JComboBox<Algorithm> algorithmComboBox;
    private JComboBox<Visualization> visualizationComboBox;
    private JComboBox<StyleType> styleTypeComboBox;
    private JComboBox<Style> styleComboBox;
    private JColorChooser primaryColor;
    private JColorChooser secondaryColor;
    private JCheckBox showStats;


    public AddSorterPanel(FluentConstraints constraints) {
        super(constraints);
    }

    @Override
    protected String getTitle() {
        return "Add new sorter ";
    }

    @Override
    protected SortOptions getData() {
        var algorithm = (Algorithm) algorithmComboBox.getSelectedItem();
        var visualization = (Visualization) visualizationComboBox.getSelectedItem();
        var style = (Style) styleComboBox.getSelectedItem();
        var colors = new SortOptions.Colors(primaryColor.getColor(), secondaryColor.getColor());
        var stats = showStats.isSelected();
        return new SortOptions(algorithm, visualization, style, colors, stats);
    }

    @Override
    protected void createUiComponents() {
        algorithmComboBox = new JComboBox<>(Algorithm.values());
        visualizationComboBox = new JComboBox<>(Visualization.values());
        styleTypeComboBox = new JComboBox<>(StyleType.values());
        styleComboBox = new JComboBox<>(Style.values());
        primaryColor = new JColorChooser(Theme.UI_ACCENT);
        secondaryColor = new JColorChooser(Theme.UI_ACCENT_2);
        showStats = new JCheckBox("", false);
    }

    @Override
    protected void initializePanel() {
        // -- padding label
        add(new JLabel(), constraints.incrementY().padY(0).get());

        // -- algorithm selection section
        add(new JLabel("Algorithm: "), constraints.width(1).incrementY().resetX().get());
        add(algorithmComboBox, constraints.incrementX().get());

        // -- visualization selection section
        add(new JLabel("Visualization: "), constraints.resetX().incrementY().get());
        add(visualizationComboBox, constraints.incrementX().get());

        // -- style type selection section
        add(new JLabel("Style Type: "), constraints.resetX().incrementY().get());
        add(styleTypeComboBox, constraints.incrementX().get());

        // -- style selection section
        add(new JLabel("Style: "), constraints.resetX().incrementY().get());
        add(styleComboBox, constraints.incrementX().get());

        // -- show statistics selection section
        add(new JLabel("Show statistics: "), constraints.resetX().incrementY().get());
        add(showStats, constraints.incrementX().get());

        // -- color selection section
        add(new JLabel("Primary Color: "), constraints.resetX().incrementY().get());
        add(UiFactory.createColorButton(primaryColor::getColor, () -> Ui.showInfo("Choose a primary color", primaryColor.getChooserPanels()[1])), constraints.incrementX().get());

        add(new JLabel("Secondary Color: "), constraints.resetX().incrementY().get());
        add(UiFactory.createColorButton(secondaryColor::getColor, () -> Ui.showInfo("Choose a secondary color", secondaryColor.getChooserPanels()[1])), constraints.incrementX().get());

        // -- add sorter button
        add(UiFactory.createButton("Add Sorter", () -> MainFrame.getInstance().createInternalFrame(getData())), constraints.resetX().incrementY().width(2).get());
    }
}
