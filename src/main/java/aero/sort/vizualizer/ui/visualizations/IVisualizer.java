// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations;

/**
 * Visualizers define how the concrete {@link aero.sort.vizualizer.algorithms.StepResult step results} of a sorting
 * algorithm are displayed on screen.
 *
 * @author Daniel Ladendorfer
 */
public interface IVisualizer {
    /**
     * Render all {@link aero.sort.vizualizer.algorithms.StepResult step results}.
     */
    void render();
}
