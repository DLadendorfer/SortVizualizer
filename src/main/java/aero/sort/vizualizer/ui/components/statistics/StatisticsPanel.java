// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.statistics;

import aero.sort.vizualizer.algorithms.ISortingAlgorithm;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.Ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Panel that shows characteristics and statistics of the visualized sorting algorithms.
 *
 * @author Daniel Ladendorfer
 */
public class StatisticsPanel extends JPanel {

    private final SortOptions options;
    private ISortingAlgorithm algorithm;

    public StatisticsPanel(SortOptions options, ISortingAlgorithm algorithm) {
        this.options = options;
        this.algorithm = algorithm;
        initializePanel();
    }

    private void initializePanel() {

        setLayout(new GridLayout(4, 6));
        add(new JLabel("Algorithm:"));
        add(createValueLabel(options.algorithm().name()));

        add(new JLabel("Status:"));
        add(createValueLabel("Done"));

        add(new JLabel("Comparison:"));
        add(createValueLabel(toText(algorithm.isComparisonSort())));

        add(new JLabel("Method: "));
        add(createValueLabel(algorithm.getMethod().getText()));

        add(new JLabel("Memory:"));
        add(createValueLabel(algorithm.getPerformance().memory().getText()));

        add(new JLabel(""));
        add(createValueLabel(""));

        add(new JLabel("Best:"));
        add(createValueLabel(algorithm.getPerformance().best().getText()));

        add(new JLabel("Average:"));
        add(createValueLabel(algorithm.getPerformance().average().getText()));

        add(new JLabel("Worst:"));
        add(createValueLabel(algorithm.getPerformance().worst().getText()));

        add(new JLabel("Swaps:"));
        add(Ui.using(new JLabel("78")).execute(l -> l.setForeground(Theme.UI_ACCENT)).get());

        add(new JLabel("Array-Access:"));
        add(Ui.using(new JLabel("2994")).execute(l -> l.setForeground(Theme.UI_ACCENT)).get());

        add(new JLabel("Temp-Vars:"));
        add(Ui.using(new JLabel("50")).execute(l -> l.setForeground(Theme.UI_ACCENT)).get());

        var font = new JLabel().getFont().deriveFont(10f);
        Arrays.stream(getComponents()).forEach(c -> c.setFont(font));
    }

    private String toText(boolean flag) {
        return flag ? "Yes" : "No";
    }

    private JLabel createValueLabel(String text) {
        return Ui.using(new JLabel(text)).execute(l -> l.setForeground(Theme.UI_ACCENT)).get();
    }
}
