// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

/**
 * All options of visualizing a sorting algorithm.
 *
 * @param algorithm     the algorithm to apply
 * @param visualization the visualization type
 * @param style         the render style
 *
 * @author Daniel Ladendorfer
 */
public record SortOptions(Algorithm algorithm, Visualization visualization, Style style) {
}
