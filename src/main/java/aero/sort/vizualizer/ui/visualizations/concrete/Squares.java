// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations.concrete;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.data.options.MarkType;
import aero.sort.vizualizer.data.options.VisualizationOptions;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.StyleContext;
import aero.sort.vizualizer.data.registry.DataRegistry;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Bubbles visualizer.
 *
 * @author Daniel Ladendorfer
 */
public class Squares extends AbstractVisualizer {
    private static final int ARC = 3;
    private static final int SQUARE_OFFSET = 3;
    private static final int DRAW_VALUE_THRESHOLD = 20;

    public Squares(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        super(renderPanel, style, steps);
    }

    public @NotNull JPanel renderInternal(@NotNull StepResult step) {
        int maxValue = Arrays.stream(step.ints()).max(Comparator.naturalOrder()).orElse(1);
        int minValue = Arrays.stream(step.ints()).min(Comparator.naturalOrder()).orElse(1);

        return new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (g instanceof Graphics2D g2) {
                    int squareWidth = Math.max(getPanelDimension().height, getPanelDimension().width) / step.ints().length - SQUARE_OFFSET;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    for (int index = 0; index < step.ints().length; index++) {
                        var context = new StyleContext(g2, step.ints().length, index, step.ints()[index], maxValue, minValue);
                        drawSquare(context, squareWidth, step);
                    }
                }
            }
        };
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Justification("width == height for squares")
    private void drawSquare(@NotNull StyleContext context, int squareWidth, @NotNull StepResult step) {
        boolean markedIndex = Arrays.stream(step.marked()).anyMatch(m -> m == context.index());
        context.g2().setColor(style.getColor(context));

        int x;
        int y;

        if (getPanelDimension().width > getPanelDimension().height) {
            x = context.index() * squareWidth + context.index() * SQUARE_OFFSET + SQUARE_OFFSET / 2;
            y = getPanelDimension().height / 2 - squareWidth / 2;
        } else {
            y = context.index() * squareWidth + context.index() * SQUARE_OFFSET + SQUARE_OFFSET / 2;
            x = getPanelDimension().width / 2 - squareWidth / 2;
        }

        context.g2().fillRoundRect(x, y, squareWidth, squareWidth, ARC, ARC);

        if (markedIndex) {
            drawMarker(context, squareWidth, x, y);
        }

        if (squareWidth >= DRAW_VALUE_THRESHOLD) {
            drawValue(context, squareWidth, x, y);
        }
    }

    private void drawValue(@NotNull StyleContext context, int squareWidth, int x, int y) {
        var text = String.valueOf(context.value());
        Font font = renderPanel.getFont().deriveFont(Font.BOLD, 12f);
        Graphics2D g2 = context.g2();
        g2.setColor(Theme.BACKGROUND);

        FontMetrics metrics = g2.getFontMetrics(font);
        int diameter = (int) (metrics.getHeight() * 1.2f);
        g2.fillOval(x + (squareWidth - diameter) / 2, y + (squareWidth - diameter) / 2, diameter, diameter);
        x = x + (squareWidth - metrics.stringWidth(text)) / 2;
        y = y + ((squareWidth - metrics.getHeight()) / 2) + metrics.getAscent();
        g2.setFont(font);
        g2.setColor(Theme.FOREGROUND);
        g2.drawString(text, x, y);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Justification("width == height for squares")
    private static void drawMarker(@NotNull StyleContext context, int squareWidth, int x, int y) {
        var markOptions = DataRegistry.fetch(VisualizationOptions.class).marker();

        context.g2().setColor(markOptions.markColor());
        context.g2().setStroke(new BasicStroke(3));
        var markType = markOptions.markType();
        if (Objects.requireNonNull(markType) == MarkType.FILL) {
            context.g2().fillRoundRect(x, y, squareWidth, squareWidth, ARC, ARC);
        } else if (markType == MarkType.OUTLINE) {
            context.g2().drawRoundRect(x, y, squareWidth, squareWidth, ARC, ARC);
        }
    }
}
