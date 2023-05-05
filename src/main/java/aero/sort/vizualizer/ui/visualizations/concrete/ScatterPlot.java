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
 * Scatter Plot visualizer.
 *
 * @author Daniel Ladendorfer
 */
public class ScatterPlot extends AbstractVisualizer {

    public ScatterPlot(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        super(renderPanel, style, steps);
    }

    public JPanel renderInternal(StepResult step) {
        int maxValue = Arrays.stream(step.ints()).max(Comparator.naturalOrder()).orElse(1);
        int minValue = Arrays.stream(step.ints()).min(Comparator.naturalOrder()).orElse(1);

        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (g instanceof Graphics2D g2) {
                    int height = (getPanelDimension().height - 10) / step.ints().length;
                    int width = getPanelDimension().width / step.ints().length;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    for (int index = 0; index < step.ints().length; index++) {
                        var context = new StyleContext(g2, step.ints().length, index, step.ints()[index], maxValue, minValue);
                        drawCircle(context, width, height, step);
                    }
                }
            }
        };
    }

    private void drawCircle(StyleContext context, int width, int height, StepResult step) {
        boolean markedIndex = Arrays.stream(step.marked()).anyMatch(m -> m == context.index());
        context.g2().setColor(style.getColor(context));

        int x = width * context.value() - width;
        int y = getPanelDimension().height - height * (context.index() + 1) - 10;

        context.g2().fillOval(x, y, width, height);

        if (markedIndex) {
            drawMarker(context, width, height, x, y);
        }
    }

    private static void drawMarker(StyleContext context, int width, int height, int x, int y) {
        var markOptions = DataRegistry.fetch(VisualizationOptions.class).marker();

        context.g2().setColor(markOptions.markColor());
        context.g2().setStroke(new BasicStroke(3));
        var markType = markOptions.markType();
        if (Objects.requireNonNull(markType) == MarkType.FILL) {
            context.g2().fillOval(x, y, width, height);
        } else if (markType == MarkType.OUTLINE) {
            context.g2().drawOval(x, y, width, height);
        }
    }
}
