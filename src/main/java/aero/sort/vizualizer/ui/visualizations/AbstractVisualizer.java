// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.utilities.Async;

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

    @Override
    public final void render() {
        while (!steps.isEmpty()) {
            var render = renderInternal(steps.poll());
            setRenderPanel(render);
            Async.sleep(250L);
        }
    }

    private void setRenderPanel(JPanel render) {
        render.setBackground(renderPanel.getBackground());
        render.setPreferredSize(getPanelDimension());
        renderPanel.removeAll();
        renderPanel.add(render);
        renderPanel.revalidate();
    }

    /**
     * Renders the current step.
     *
     * @param step the step to render
     * @return the new panel to display
     */
    protected abstract JPanel renderInternal(StepResult step);

    protected Dimension getPanelDimension() {
        return new Dimension(renderPanel.getWidth(), renderPanel.getHeight());
    }
}
