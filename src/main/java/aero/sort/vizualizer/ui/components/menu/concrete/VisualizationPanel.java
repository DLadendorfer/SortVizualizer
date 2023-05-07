// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.data.options.MarkType;
import aero.sort.vizualizer.data.options.VisualizationOptions;
import aero.sort.vizualizer.ui.components.basic.SliderPanel;
import aero.sort.vizualizer.ui.components.menu.AbstractMenuPanel;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Menu panel with all visualization options.
 *
 * @author Daniel Ladendorfer
 */
public class VisualizationPanel extends AbstractMenuPanel<VisualizationOptions> {
    private static final int MIN_TIME = 1;
    private static final int MAX_TIME = 200;
    private static final int DEFAULT_TIME = 15;
    private JCheckBox showShuffle;
    private JCheckBox showSteps;
    private SliderPanel stepTime;
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
        stepTime = new SliderPanel(MIN_TIME, MAX_TIME, DEFAULT_TIME);
        var markType = new ButtonGroup();
        fill = new JRadioButton("Fill", true);
        outline = new JRadioButton("Outline");
        off = new JRadioButton("Off");
        markerColor = new JColorChooser(Theme.RED);
        markType.add(fill);
        markType.add(outline);
        markType.add(off);
    }

    @Override
    protected void initializePanel() {
        add(new JLabel("Show shuffle: "), constraints.resetX()
                                                     .width(1)
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
        add(UiFactory.createColorButton(markerColor::getColor, markerColor), constraints.incrementX()
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
