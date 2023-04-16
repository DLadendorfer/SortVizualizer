// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.desktop;

import aero.sort.vizualizer.algorithms.ISortingAlgorithm;
import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.algorithms.concrete.BubbleSort;
import aero.sort.vizualizer.algorithms.concrete.InsertionSort;
import aero.sort.vizualizer.algorithms.concrete.QuickSort;
import aero.sort.vizualizer.algorithms.concrete.SelectionSort;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.concrete.*;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.IVisualizer;
import aero.sort.vizualizer.ui.visualizations.concrete.Bars;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

/**
 * Internal frame that visualizes the sorting process of a sort algorithm.
 *
 * @author Daniel Ladendorfer
 */
public class SortingFrame extends JInternalFrame {
    public static final String ICONS_SORT_PNG = "icons/sort.png";
    // this variable is used to make sure new windows are not exactly on top of each others
    private static int createOffset = 0;
    private final SortOptions options;
    private final JPanel renderPanel;
    private final Logger logger;

    public SortingFrame(SortOptions options) {
        this.options = options;
        this.logger = LoggerFactory.getLogger("%s:%s:%s:%s".formatted(options.algorithm(), options.visualization(), options.style(), UUID.randomUUID()));
        this.renderPanel = Ui.using(new JPanel()).execute(panel -> panel.setBackground(Theme.BACKGROUND)).get();

        initializeFrame(options);
    }

    private void initializeFrame(SortOptions options) {
        add(renderPanel);
        setTitle("%s - %s - %s".formatted(options.algorithm(), options.visualization(), options.style()));

        if (!GraphicsEnvironment.isHeadless()) {
            setBounds(10 + createOffset, 10 + createOffset, MainFrame.getInstance().getDesktop().getWidth() / 2, MainFrame.getInstance().getDesktop().getHeight() / 3 * 2);
        }

        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setVisible(true);
        setIcon();
        recalculateNewCreationOffset();
    }

    private void setIcon() {
        try {
            var resource = Objects.requireNonNull(MainFrame.class.getClassLoader().getResourceAsStream(ICONS_SORT_PNG));
            var icon = new ImageIcon(ImageIO.read(resource));
            setFrameIcon(icon);
        } catch (NullPointerException | IOException e) {
            logger.warn("Failed to load frame icon: {}", e.getMessage());
        }
    }

    private static void recalculateNewCreationOffset() {
        createOffset += 20;
        if (createOffset > 200) {
            createOffset -= 195;
        }
    }

    public void sort(Integer[] ints) {
        ISortingAlgorithm algorithm = switch (options.algorithm()) {
            case Bubblesort -> new BubbleSort();
            case Insertionsort -> new InsertionSort();
            case Selectionsort -> new SelectionSort();
            case Quicksort -> new QuickSort();
        };

        render(algorithm.sort(ints));
    }

    private void render(LinkedList<StepResult> steps) {
        IStyle style = switch (options.style()) {
            case CustomGradient -> new CustomGradient(options.primaryColor(), options.secondaryColor());
            case Grayscale -> new Grayscale();
            case Auqa -> new Aqua();
            case Sunrise -> new Sunrise();
            case Sunset -> new Sunset();
            case CustomPlain -> new CustomPlain(options.primaryColor());
            case White -> new White();
            case Cyan -> new Cyan();
            case Green -> new Green();
            case Purple -> new Purple();
            case Yellow -> new Yellow();
        };
        IVisualizer visualizer = switch (options.visualization()) {
            case Bars -> new Bars(renderPanel, style, steps);
        };

        visualizer.render();
    }
}
