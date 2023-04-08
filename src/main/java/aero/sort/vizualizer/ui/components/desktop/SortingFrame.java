package aero.sort.vizualizer.ui.components.desktop;

import aero.sort.vizualizer.algorithms.ISortingAlgorithm;
import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.algorithms.concrete.BubbleSort;
import aero.sort.vizualizer.algorithms.concrete.InsertionSort;
import aero.sort.vizualizer.algorithms.concrete.QuickSort;
import aero.sort.vizualizer.algorithms.concrete.SelectionSort;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.concrete.None;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.IVisualizer;
import aero.sort.vizualizer.ui.visualizations.concrete.Bars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Internal frame that visualizes the sorting process of a sort algorithm.
 *
 * @author Daniel Ladendorfer
 */
public class SortingFrame extends JInternalFrame {

    private final SortOptions options;
    private final JPanel renderPanel;
    private final Logger logger;

    public SortingFrame(SortOptions options) {
        this.options = options;
        logger = LoggerFactory.getLogger("%s:%s:%s:%s".formatted(options.algorithm(), options.visualization(), options.style(), UUID.randomUUID()));
        setTitle("%s - %s".formatted(options.algorithm(), options.visualization()));
        setBounds(10, 10, 600, 600);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        try {
            setFrameIcon(new ImageIcon(ImageIO.read(Objects.requireNonNull(MainFrame.class.getClassLoader().getResourceAsStream("icons/sort.png")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        renderPanel = new JPanel();
        renderPanel.setBackground(Theme.BACKGROUND);

        renderPanel.setPreferredSize(new Dimension(600, 600));

        setVisible(true);
        add(renderPanel);
    }

    public void sort(Integer[] ints) {
        ISortingAlgorithm algorithm = switch (options.algorithm()) {
            case Bubblesort -> new BubbleSort(options);
            case Insertionsort -> new InsertionSort(options);
            case Selectionsort -> new SelectionSort(options);
            case Quicksort -> new QuickSort(options);
        };

        render(algorithm.sort(ints));
    }

    private void render(LinkedList<StepResult> steps) {
        IStyle style = switch (options.style()) {
            case None -> new None();
        };
        IVisualizer visualizer  = switch (options.visualization()) {
            case Bars -> new Bars(renderPanel, style, steps);
        };

        visualizer.render();
    }
}
