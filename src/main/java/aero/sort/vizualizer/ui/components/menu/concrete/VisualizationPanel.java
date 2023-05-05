// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.data.options.MarkType;
import aero.sort.vizualizer.data.options.VisualizationOptions;
import aero.sort.vizualizer.ui.components.menu.AbstractMenuPanel;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.Ui;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Menu panel with all visualization options.
 *
 * @author Daniel Ladendorfer
 */
public class VisualizationPanel extends AbstractMenuPanel<VisualizationOptions> {
    private JCheckBox showShuffle;
    private JCheckBox showSteps;
    private JSlider stepTime;
    private JRadioButton fill;
    private JRadioButton outline;
    private JRadioButton off;
    private JColorChooser markerColor;

    public VisualizationPanel(FluentConstraints constraints) {
        super(constraints);
    }

    @Override
    protected @NotNull String getTitle() {
        return "Visualization Options ";
    }

    @Override
    protected @NotNull VisualizationOptions getData() {
        var markerOptions = new VisualizationOptions.Marker(getMarkerType(), markerColor.getColor());
        return new VisualizationOptions(showShuffle.isSelected(), showSteps.isSelected(), stepTime.getValue(), markerOptions);
    }

    @Override
    protected void createUiComponents() {
        showShuffle = new JCheckBox("", true);
        showSteps = new JCheckBox("", true);
        stepTime = new JSlider(0, 1000, 45);
        var markType = new ButtonGroup();
        fill = new JRadioButton("Fill", true);
        outline = new JRadioButton("Outline");
        off = new JRadioButton("Off");
        markerColor = new JColorChooser(Theme.RED);
        markType.add(fill);
        markType.add(outline);
        markType.add(off);
        stepTime.setMajorTickSpacing(250);
        stepTime.setPaintTicks(true);
        stepTime.setPaintLabels(true);
    }

    @Override
    protected void initializePanel() {
        add(new JLabel("Show shuffle: "), constraints.resetX()
                                                     .incrementY()
                                                     .get());
        add(showShuffle, constraints.incrementX()
                                    .get());
        add(new JLabel("Show steps: "), constraints.resetX()
                                                   .incrementY()
                                                   .get());
        add(showSteps, constraints.incrementX()
                                  .get());
        add(new JLabel("Step duration (ms): "), constraints.resetX()
                                                           .incrementY()
                                                           .get());
        add(stepTime, constraints.incrementX()
                                 .get());
        add(new JLabel("Mark Type: "), constraints.resetX()
                                                  .incrementY()
                                                  .get());
        add(fill, constraints.incrementX()
                             .padY(-10)
                             .get());
        add(outline, constraints.incrementY()
                                .get());
        add(off, constraints.incrementY()
                            .get());
        add(new JLabel("Marker Color: "), constraints.resetX()
                                                     .padY(0)
                                                     .incrementY()
                                                     .get());
        add(UiFactory.createColorButton(markerColor::getColor, () -> Ui.showInfo("Choose a primary color", markerColor.getChooserPanels()[1])), constraints.incrementX()
                                                                                                                                                           .get());

    }

    private @NotNull MarkType getMarkerType() {
        if (fill.isSelected()) {
            return MarkType.FILL;
        } else if (outline.isSelected()) {
            return MarkType.OUTLINE;
        }
        return MarkType.OFF;
    }
}
