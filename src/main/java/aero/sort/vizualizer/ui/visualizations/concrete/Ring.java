// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations.concrete;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.MarkType;
import aero.sort.vizualizer.data.options.VisualizationOptions;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.StyleContext;
import aero.sort.vizualizer.data.registry.DataRegistry;
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Ring visualizer.
 *
 * @author Daniel Ladendorfer
 */
public class Ring extends AbstractVisualizer {
    public Ring(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        super(renderPanel, style, steps);
    }

    @Override
    public JPanel renderInternal(StepResult step) {
        int maxValue = Arrays.stream(step.ints()).max(Comparator.naturalOrder()).orElse(1);
        int minValue = Arrays.stream(step.ints()).min(Comparator.naturalOrder()).orElse(1);

        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (g instanceof Graphics2D g2) {
                    double baseline = Math.sqrt(Math.pow(getPanelDimension().width, 2) + Math.pow(getPanelDimension().height, 2));
                    int length = (int) ((baseline - 10) / 2);

                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    for (int index = 0; index < step.ints().length; index++) {
                        var context = new StyleContext(g2, step.ints().length, index, step.ints()[index], maxValue, minValue);
                        drawArc(context, length, step);
                    }
                }
            }
        };
    }

    private void drawArc(StyleContext context, int length, StepResult step) {
        boolean markedIndex = Arrays.stream(step.marked()).anyMatch(m -> m == context.index());
        context.g2().setColor(style.getColor(context));
        int startX = getPanelDimension().width / 2 - length / 2;
        int startY = getPanelDimension().height / 2 - length / 2;
        int arcAngle = Math.round(360.0f / step.ints().length);
        int startAngle = (context.index() + 1) * arcAngle + 90 - arcAngle;

        context.g2().setStroke(new BasicStroke(5));
        context.g2().drawArc(startX, startY, length, length, startAngle, arcAngle);

        if (markedIndex) {
            drawMarker(context, startX, startY, length, startAngle, arcAngle);
        }
    }

    private void drawMarker(StyleContext context, int startX, int startY, int length, int startAngle, int arcAngle) {
        var markOptions = DataRegistry.fetch(VisualizationOptions.class).marker();

        context.g2().setColor(markOptions.markColor());
        var markType = markOptions.markType();
        if (Objects.requireNonNull(markType) != MarkType.OFF) {
            context.g2().drawArc(startX, startY, length, length, startAngle, arcAngle);
        }
    }
}
