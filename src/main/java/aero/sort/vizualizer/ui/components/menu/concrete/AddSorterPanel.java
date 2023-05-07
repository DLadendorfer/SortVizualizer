// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.data.options.Algorithm;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.Style;
import aero.sort.vizualizer.data.options.Visualization;
import aero.sort.vizualizer.ui.components.basic.StyleComboBox;
import aero.sort.vizualizer.ui.components.basic.StyleTypeComboBox;
import aero.sort.vizualizer.ui.components.menu.AbstractMenuPanel;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

/**
 * Panel that manages the creation of new sorter-frames.
 *
 * @author Daniel Ladendorfer
 */
public class AddSorterPanel extends AbstractMenuPanel<SortOptions> {
    private JLabel primaryLabel;
    private JLabel secondaryLabel;
    private JComboBox<Algorithm> algorithmComboBox;
    private JComboBox<Visualization> visualizationComboBox;
    private StyleTypeComboBox styleTypeComboBox;
    private StyleComboBox styleComboBox;
    private JColorChooser primaryColor;
    private JColorChooser secondaryColor;
    private JCheckBox showStats;
    private JButton primaryColorButton;
    private JButton secondaryColorButton;

    public AddSorterPanel(FluentConstraints constraints) {
        super(constraints);
    }

    @Override
    protected @NotNull String getTitle() {
        return "Add new sorter ";
    }

    @Override
    protected @NotNull SortOptions getData() {
        var algorithm = Objects.requireNonNull((Algorithm) algorithmComboBox.getSelectedItem());
        var visualization = Objects.requireNonNull((Visualization) visualizationComboBox.getSelectedItem());
        var style = (Style) styleComboBox.getSelectedItem();
        var colors = new SortOptions.Colors(primaryColor.getColor(), secondaryColor.getColor());
        var stats = showStats.isSelected();
        return new SortOptions(algorithm, visualization, style, colors, stats);
    }

    @Override
    protected void createUiComponents() {
        primaryLabel = new JLabel("Primary Color: ");
        secondaryLabel = new JLabel("Secondary Color: ");
        algorithmComboBox = new JComboBox<>(Algorithm.values());
        visualizationComboBox = new JComboBox<>(Visualization.values());
        primaryColor = new JColorChooser(Theme.UI_ACCENT);
        secondaryColor = new JColorChooser(Theme.UI_ACCENT_2);
        primaryColorButton = UiFactory.createColorButton(primaryColor::getColor, primaryColor);
        secondaryColorButton = UiFactory.createColorButton(secondaryColor::getColor, secondaryColor);
        styleComboBox = new StyleComboBox(primaryColorButton, secondaryColorButton, primaryLabel, secondaryLabel);
        styleTypeComboBox = new StyleTypeComboBox(styleComboBox);
        showStats = UiFactory.createCheck(false);
    }

    @Override
    protected void initializePanel() {
        // -- padding label
        add(new JLabel(), constraints.incrementY()
                                     .padY(0)
                                     .get());

        // -- algorithm selection section
        add(new JLabel("Algorithm: "), constraints.width(1)
                                                  .incrementY()
                                                  .resetX()
                                                  .get());
        add(algorithmComboBox, constraints.incrementX()
                                          .get());

        // -- visualization selection section
        add(new JLabel("Visualization: "), constraints.resetX()
                                                      .incrementY()
                                                      .get());
        add(visualizationComboBox, constraints.incrementX()
                                              .get());

        // -- style type selection section
        add(new JLabel("Style Type: "), constraints.resetX()
                                                   .incrementY()
                                                   .get());
        add(styleTypeComboBox, constraints.incrementX()
                                          .get());

        // -- style selection section
        add(new JLabel("Style: "), constraints.resetX()
                                              .incrementY()
                                              .get());
        add(styleComboBox, constraints.incrementX()
                                      .get());

        // -- show statistics selection section
        add(new JLabel("Show statistics: "), constraints.resetX()
                                                        .incrementY()
                                                        .get());
        add(showStats, constraints.incrementX()
                                  .get());

        // -- color selection section
        add(primaryLabel, constraints.resetX()
                                     .incrementY()
                                     .get());
        add(primaryColorButton, constraints.incrementX()
                                           .get());

        add(secondaryLabel, constraints.resetX()
                                       .incrementY()
                                       .get());
        add(secondaryColorButton, constraints.incrementX()
                                             .get());

        // -- add sorter button
        add(UiFactory.createButton("Add Sorter", this::createInternalFrame), constraints.resetX()
                                                                                        .incrementY()
                                                                                        .width(2)
                                                                                        .get());
    }

    private void createInternalFrame() {
        var controller = Controllers.fetch(FrameController.class);
        controller.createInternalFrame(getData());
    }
}
