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
import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.concrete.*;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.statistics.StatisticsPanel;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.IVisualizer;
import aero.sort.vizualizer.ui.visualizations.concrete.Bars;
import aero.sort.vizualizer.utilities.Async;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

/**
 * Internal frame that visualizes the sorting process of a sort algorithm.
 *
 * @author Daniel Ladendorfer
 */
public class SortingFrame extends JInternalFrame implements ComponentListener {
    public static final String ICONS_SORT_PNG = "icons/sort.png";
    // this variable is used to make sure new windows are not exactly on top of each others
    @Justification("If the frames are not offset, it may be hard for the user to realize that a frame was created")
    private static int createOffset = 0;
    private final SortOptions options;
    private final JPanel renderPanel;
    private final JPanel statsPanel;
    private final Logger logger;
    private final ISortingAlgorithm algorithm;
    private LinkedList<StepResult> previousRenderData = new LinkedList<>();

    public SortingFrame(SortOptions options) {
        this.options = options;
        this.logger = LoggerFactory.getLogger("%s:%s:%s:%s".formatted(options.algorithm(), options.visualization(), options.style(), UUID.randomUUID()));
        this.renderPanel = Ui.using(new JPanel()).execute(panel -> panel.setBackground(Theme.BACKGROUND)).get();
        this.algorithm = getSortingAlgorithm();
        this.statsPanel = new StatisticsPanel(options, algorithm);
        initializeFrame(options);
    }

    private void initializeFrame(SortOptions options) {
        var framePanel = new JPanel(new BorderLayout());
        framePanel.add(renderPanel, BorderLayout.CENTER);

        if (options.showStatistics()) {
            framePanel.add(statsPanel, BorderLayout.SOUTH);
        }

        add(framePanel);
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
        revalidate();
        pack();
        addComponentListener(this);
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

    /**
     * Just rendering the given int array.
     *
     * @param ints the ints to render
     */
    public void render(Integer[] ints) {
        var list = new LinkedList<StepResult>();
        list.add(new StepResult(new Integer[]{}, ints));
        render(list);
    }

    public void sort(Integer[] ints) {
        render(algorithm.sort(ints));
    }

    private ISortingAlgorithm getSortingAlgorithm() {
        return switch (options.algorithm()) {
            case Bubblesort -> new BubbleSort();
            case Insertionsort -> new InsertionSort();
            case Selectionsort -> new SelectionSort();
            case Quicksort -> new QuickSort();
        };
    }

    private void render(LinkedList<StepResult> steps) {
        previousRenderData = new LinkedList<>();
        previousRenderData.add(new StepResult(steps.getFirst().marked(), steps.getFirst().ints()));
        IStyle style = switch (options.style()) {
            case App -> new App();
            case Rainbow -> new Rainbow();
            case CustomGradient -> new CustomGradient(options.colors());
            case Grayscale -> new Grayscale();
            case Auqa -> new Aqua();
            case Sunrise -> new Sunrise();
            case Sunset -> new Sunset();
            case CustomPlain -> new CustomPlain(options.colors().primary());
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

    @Override
    public void componentResized(ComponentEvent e) {
        redraw();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        redraw();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        redraw();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        redraw();
    }

    private void redraw() {
        Async.invoke(() -> render(previousRenderData));
    }
}
