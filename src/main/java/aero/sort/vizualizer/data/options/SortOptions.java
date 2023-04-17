// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.ui.constants.Theme;

import java.awt.*;

/**
 * All options of visualizing a sorting algorithm.
 *
 * @param algorithm     the algorithm to apply
 * @param visualization the visualization type
 * @param style         the render style
 * @param colors        the colors to use
 * @author Daniel Ladendorfer
 */
public record SortOptions(Algorithm algorithm, Visualization visualization, Style style, Colors colors) {
    @Justification("Some visualization styles have locked colors, thus making the colors parameter insignificant for them")
    private static final Colors DEFAULT_COLORS = new Colors(Theme.DEEPBLUE, Theme.CYAN);

    /**
     * Options of visualizing a sorting algorithm. The colors are insignificant.
     *
     * @param algorithm     the algorithm to apply
     * @param visualization the visualization type
     * @param style         the render style
     * @author Daniel Ladendorfer
     */
    @Justification("Some visualization styles have locked colors, thus making the colors parameter insignificant for them")
    public SortOptions(Algorithm algorithm, Visualization visualization, Style style) {
        this(algorithm, visualization, style, DEFAULT_COLORS);
    }

    /**
     * The color options of a sorter.
     *
     * @param primary   the primary color
     * @param secondary the secondary color
     */
    public record Colors(Color primary, Color secondary) {
    }
}
