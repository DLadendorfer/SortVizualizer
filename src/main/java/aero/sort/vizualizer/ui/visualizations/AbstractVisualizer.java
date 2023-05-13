// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.VisualizationOptions;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.registry.DataRegistry;
import aero.sort.vizualizer.data.shared.SharedStepToken;
import aero.sort.vizualizer.data.shared.StepInstruction;
import aero.sort.vizualizer.ui.components.status.StatusBar;
import aero.sort.vizualizer.utilities.Async;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public abstract class AbstractVisualizer implements IVisualizer {
    private static final long PAUSE_TIME = 5L;
    protected final JPanel renderPanel;
    protected final IStyle style;
    protected final LinkedList<StepResult> steps;

    protected AbstractVisualizer(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        this.renderPanel = renderPanel;
        this.style = style;
        this.steps = steps;
    }

    @Override
    public final void render(SharedStepToken stepToken) {
        int totalSteps = steps.size();
        StatusBar.updateProgress(0, totalSteps);
        while (!steps.isEmpty()) {
            var options = DataRegistry.fetch(VisualizationOptions.class);
            if (!options.showSteps()) {
                removeAllStepsButLast();
            }

            while (stepToken.getStepInstruction() == StepInstruction.PAUSE) {
                Async.sleep(PAUSE_TIME);
            }

            var render = renderInternal(steps.poll());
            setRenderPanel(render);
            StatusBar.updateProgress(totalSteps - steps.size(), totalSteps);
            Async.sleep(options.stepDuration());

            if (stepToken.getStepInstruction() == StepInstruction.STEP) {
                stepToken.setStepInstruction(StepInstruction.PAUSE);
            }
        }
    }

    private void removeAllStepsButLast() {
        var temp = steps.pollLast();
        steps.clear();
        steps.add(temp);
    }

    private void setRenderPanel(@NotNull JPanel render) {
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

    protected @NotNull Dimension getPanelDimension() {
        return new Dimension(renderPanel.getWidth(), renderPanel.getHeight());
    }
}
