package aero.sort.vizualizer.ui.visualizations.concrete;

import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.ui.visualizations.AbstractVisualizer;

import javax.swing.*;
import java.util.LinkedList;

public class Bars extends AbstractVisualizer {
    public Bars(JPanel renderPanel, IStyle style, LinkedList<StepResult> steps) {
        super(renderPanel, style, steps);
    }

    @Override
    public void render() {

    }
}
