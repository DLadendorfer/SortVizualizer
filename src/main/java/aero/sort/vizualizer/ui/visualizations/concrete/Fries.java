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
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Fries visualizer. This is a fun one. ;)
 *
 * @author Daniel Ladendorfer
 */
public class Fries extends AbstractVisualizer {
    public static final int ARC = 10;
    private static final int FRY_OFFSET = 5;
    private final Random random = new Random();
    private final Map<Integer, Color> fryColors = new TreeMap<>();

    public Fries(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        super(renderPanel, style, steps);
        prepareFryColors(steps.getFirst()
                              .ints());
    }

    private void prepareFryColors(Integer[] steps) {
        for (int step : steps) {
            fryColors.put(step, createRandomFryColor());
        }
    }

    @Override
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
                    int margin = getPanelDimension().width / 7;
                    int heightRatio = getPanelDimension().height / 3 / maxValue ;
                    int fryWidth = (getPanelDimension().width - margin * 2) / (step.ints().length) - FRY_OFFSET;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    drawBoxOval(g2, margin);
                    for (int index = 0; index < step.ints().length; index++) {
                        var context = new StyleContext(g2, step.ints().length, index, step.ints()[index], maxValue,
                                minValue);
                        drawFry(context, heightRatio, fryWidth, margin, step);
                    }
                    drawBox(g2, margin);
                }
            }
        };
    }

    private void drawBox(Graphics2D g2, int margin) {
        g2.setColor(Theme.DARK_RED);
        g2.fillRect(margin, getPanelDimension().height / 6 + getPanelDimension().height / 4,
                getPanelDimension().width - margin * 2, getPanelDimension().height / 10 * 8);

    }

    private void drawBoxOval(Graphics2D g2, int margin) {
        g2.setColor(Theme.DARK_RED.darker());
        g2.fillOval(margin, getPanelDimension().height / 6, getPanelDimension().width - margin * 2,
                getPanelDimension().height / 2);
    }

    private void drawFry(@NotNull StyleContext context, int heightRatio, int fryWidth, int margin,
                         @NotNull StepResult step) {
        boolean markedIndex = Arrays.stream(step.marked())
                                    .anyMatch(m -> m == context.index());
        context.g2()
               .setColor(fryColors.get(context.value()));
        int x = margin + context.index() * FRY_OFFSET + (context.index() * fryWidth) + 2;
        int relativeValue = context.max() - Math.abs(context.value() - context.max() / 2);
        int height = relativeValue * heightRatio;
        int y = getPanelDimension().height / 8 + getPanelDimension().height / 3 -  height;
        context.g2()
               .fillRoundRect(x, y, fryWidth, height, ARC, ARC);

        if (markedIndex) {
            drawMarker(context, fryWidth, x, y, height);
        }
    }

    private Color createRandomFryColor() {
        double p = random.nextDouble(0.0, 1.0);
        Color from = Theme.FRY.brighter();
        Color to = Theme.FRY;
        return new Color((int) (from.getRed() * p + to.getRed() * (1 - p)),
                (int) (from.getGreen() * p + to.getGreen() * (1 - p)),
                (int) (from.getBlue() * p + to.getBlue() * (1 - p)));
    }

    private static void drawMarker(@NotNull StyleContext context, int fryWidth, int x, int y, int height) {
        var markOptions = DataRegistry.fetch(VisualizationOptions.class)
                                      .marker();

        context.g2()
               .setColor(markOptions.markColor());
        context.g2()
               .setStroke(new BasicStroke(3));
        var markType = markOptions.markType();
        if (Objects.requireNonNull(markType) == MarkType.FILL) {
            context.g2()
                   .fillRoundRect(x, y, fryWidth, height, ARC, ARC);
        } else if (markType == MarkType.OUTLINE) {
            context.g2()
                   .drawRoundRect(x, y, fryWidth, height, ARC, ARC);
        }
    }
}
