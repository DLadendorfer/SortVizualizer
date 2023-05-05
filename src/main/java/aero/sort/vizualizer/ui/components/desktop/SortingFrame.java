// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.desktop;

import aero.sort.vizualizer.algorithms.ISortingAlgorithm;
import aero.sort.vizualizer.algorithms.StepResult;
import aero.sort.vizualizer.algorithms.concrete.*;
import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.concrete.*;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.statistics.StatisticsPanel;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.visualizations.IVisualizer;
import aero.sort.vizualizer.ui.visualizations.concrete.*;
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
    private final transient SortOptions options;
    private final JPanel renderPanel;
    private final @NotNull JPanel statsPanel;
    private final transient Logger logger;
    private final transient @NotNull ISortingAlgorithm algorithm;
    private transient @NotNull LinkedList<StepResult> previousRenderData = new LinkedList<>();

    public SortingFrame(@NotNull SortOptions options) {
        this.options = options;
        this.logger = LoggerFactory.getLogger("%s:%s:%s:%s".formatted(options.algorithm(), options.visualization(), options.style(), UUID.randomUUID()));
        this.renderPanel = Ui.using(new JPanel()).execute(panel -> panel.setBackground(Theme.BACKGROUND)).get();
        this.algorithm = getSortingAlgorithm();
        this.statsPanel = new StatisticsPanel(options, algorithm);
        initializeFrame(options);
    }

    private void initializeFrame(@NotNull SortOptions options) {
        var framePanel = new JPanel(new BorderLayout());
        framePanel.add(renderPanel, BorderLayout.CENTER);

        if (options.showStatistics()) {
            framePanel.add(statsPanel, BorderLayout.SOUTH);
        }

        add(framePanel);
        setTitle("%s - %s - %s".formatted(options.algorithm(), options.visualization(), options.style()));

        if (!GraphicsEnvironment.isHeadless()) {
            var desktop = Controllers.fetch(FrameController.class).getDesktop();
            setBounds(10 + createOffset, 10 + createOffset, desktop.getWidth() / 2, desktop.getHeight() / 3 * 2);
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

    private @NotNull ISortingAlgorithm getSortingAlgorithm() {
        return switch (options.algorithm()) {
            case BUBBLE -> new BubbleSort();
            case INSERTION -> new InsertionSort();
            case SELECTION -> new SelectionSort();
            case QUICK -> new QuickSort();
            case MERGE -> new MergeSort();
        };
    }

    private void render(@NotNull LinkedList<StepResult> steps) {
        previousRenderData = new LinkedList<>();
        previousRenderData.add(new StepResult(steps.getFirst().marked(), steps.getFirst().ints()));
        IStyle style = switch (options.style()) {
            case APP -> new App();
            case RAINBOW -> new Rainbow();
            case CUSTOM_GRADIENT -> new CustomGradient(options.colors());
            case GRAY -> new Grayscale();
            case AQUA -> new Aqua();
            case SUNRISE -> new Sunrise();
            case SUNSET -> new Sunset();
            case CUSTOM_PLAIN -> new CustomPlain(options.colors().primary());
            case WHITE -> new White();
            case CYAN -> new Cyan();
            case GREEN -> new Green();
            case PURPLE -> new Purple();
            case YELLOW -> new Yellow();
        };
        IVisualizer visualizer = switch (options.visualization()) {
            case BARS -> new Bars(renderPanel, style, steps);
            case PYRAMID -> new Pyramid(renderPanel, style, steps);
            case SCATTER_PLOT -> new ScatterPlot(renderPanel, style, steps);
            case CIRCLE -> new Circle(renderPanel, style, steps);
            case RING -> new Ring(renderPanel, style, steps);
            case DISPARITY_CIRCLE -> new DisparityCircle(renderPanel, style, steps);
            case DISPARITY_RING -> new DisparityRing(renderPanel, style, steps);
            case SQUARES -> new Squares(renderPanel, style, steps);
            case BUBBLES -> new Bubbles(renderPanel, style, steps);
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
