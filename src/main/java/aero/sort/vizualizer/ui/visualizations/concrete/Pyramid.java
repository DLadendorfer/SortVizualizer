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
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Pyramid visualizer.
 *
 * @author Daniel Ladendorfer
 */
public class Pyramid extends AbstractVisualizer {
    public static final int ARC = 10;
    private static final int MARGIN = 7;
    private static final int BAR_OFFSET = 3;

    public Pyramid(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        super(renderPanel, style, steps);
    }

    public @NotNull JPanel renderInternal(@NotNull StepResult step) {
        int maxValue = Arrays.stream(step.ints())
                             .max(Comparator.naturalOrder())
                             .orElse(1);
        int minValue = Arrays.stream(step.ints())
                             .min(Comparator.naturalOrder())
                             .orElse(1);

        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (g instanceof Graphics2D g2) {
                    int widthRatio = getPanelDimension().width / maxValue;
                    int barHeight = getPanelDimension().height / (step.ints().length) - BAR_OFFSET;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    for (int index = 0; index < step.ints().length; index++) {
                        var context = new StyleContext(g2, step.ints().length, index, step.ints()[index], maxValue,
                                minValue);
                        drawBar(context, widthRatio, barHeight, step);
                    }
                }
            }
        };
    }

    private void drawBar(@NotNull StyleContext context, int widthRatio, int barHeight, @NotNull StepResult step) {
        boolean markedIndex = Arrays.stream(step.marked())
                                    .anyMatch(m -> m == context.index());
        context.g2()
               .setColor(style.getColor(context));
        int x = (getPanelDimension().width - context.value() * widthRatio) / 2;
        int y = MARGIN + context.index() * BAR_OFFSET + (context.index() * barHeight);
        int width = context.value() * widthRatio;
        context.g2()
               .fillRoundRect(x, y, width, barHeight, ARC, ARC);

        if (markedIndex) {
            drawMarker(context, width, x, y, barHeight);
        }
    }

    private static void drawMarker(@NotNull StyleContext context, int barWidth, int x, int y, int height) {
        var markOptions = DataRegistry.fetch(VisualizationOptions.class)
                                      .marker();

        context.g2()
               .setColor(markOptions.markColor());
        context.g2()
               .setStroke(new BasicStroke(3));
        var markType = markOptions.markType();
        if (Objects.requireNonNull(markType) == MarkType.FILL) {
            context.g2()
                   .fillRoundRect(x, y, barWidth, height, ARC, ARC);
        } else if (markType == MarkType.OUTLINE) {
            context.g2()
                   .drawRoundRect(x, y, barWidth, height, ARC, ARC);
        }
    }
}
