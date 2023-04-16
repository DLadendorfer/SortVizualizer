// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations.concrete;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Bars extends AbstractVisualizer {
    private static final int MARGIN = 7;
    private static final int BAR_OFFSET = 3;

    public Bars(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
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

                int heightRatio = getPanelDimension().height / maxValue;
                int barWidth = getPanelDimension().width / (step.ints().length) - BAR_OFFSET;

                if (g instanceof Graphics2D g2) {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    for (int index = 0; index < step.ints().length; index++) {
                        drawBar(index, maxValue, minValue, heightRatio, barWidth, g2, step);
                    }
                }
            }
        };
    }

    private void drawBar(int index, int maxValue, int minValue, int heightRatio, int barWidth, Graphics2D g2, StepResult step) {
        int value = step.ints()[index];
        g2.setColor(Arrays.stream(step.marked()).anyMatch(m -> m == index) ? Theme.RED : style.getColor(g2, step.ints().length, index, value, maxValue, minValue));
        int x = MARGIN + index * BAR_OFFSET + (index * barWidth);
        int y = getPanelDimension().height - value * heightRatio;
        int height = value * heightRatio;
        g2.fillRoundRect(x, y, barWidth, height, 10, 10);
    }
}
