package aero.sort.vizualizer.ui.visualizations.concrete;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;

public class Bars extends AbstractVisualizer {
    public Bars(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        super(renderPanel, style, steps);
    }

    @Override
    public void render() {
        StepResult poll = steps.poll();

        Optional<Integer> max = Arrays.stream(poll.ints()).max(Comparator.naturalOrder());
        Optional<Integer> min = Arrays.stream(poll.ints()).min(Comparator.naturalOrder());

        var render = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                int heightRatio = getPanelDimension().height / max.orElse(1);
                int widthRatio = getPanelDimension().width / (poll.ints().length) - 3;
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);


                for (int i = 0; i < poll.ints().length; i++) {
                    int value = poll.ints()[i];
                    int finalI = i;
                    g2.setColor(Arrays.stream(poll.marked()).anyMatch(m -> m == finalI) ? Theme.RED : Theme.YELLOW);
                    int x = 7 + i * 3 + (i * widthRatio);
                    int y = getPanelDimension().height - value * heightRatio;
                    int width = widthRatio;
                    int height = value * heightRatio;
                    g2.fillRoundRect(x, y, width, height, 10, 10);
                }
//                g2.setStroke(STROKE);
//                for (Shape shape : shapeList) {
//                    g2.setColor(FILL_COLOR);
//                    g2.fill(shape);
//                    g2.setColor(BORDER_COLOR);
//                    g2.draw(shape);
//                }
            }
        };
        render.setBackground(renderPanel.getBackground());
        render.setPreferredSize(getPanelDimension());
        renderPanel.removeAll();
        renderPanel.add(render);
        renderPanel.revalidate();
    }
}
