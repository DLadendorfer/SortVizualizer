package aero.sort.vizualizer.ui.visualizations;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.styles.IStyle;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public abstract class AbstractVisualizer implements IVisualizer {

    protected final JPanel renderPanel;
    protected final IStyle style;
    protected final LinkedList<StepResult> steps;

    public AbstractVisualizer(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {

        this.renderPanel = renderPanel;
        this.style = style;
        this.steps = steps;
    }

    protected void sleep() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected Dimension getPanelDimension() {
        return new Dimension(renderPanel.getWidth(), renderPanel.getHeight());
    }
}
