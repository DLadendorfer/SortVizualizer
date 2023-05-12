// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.visualizations;

import aero.sort.vizualizer.data.shared.SharedStepToken;

/**
 * Visualizers define how the concrete {@link aero.sort.vizualizer.algorithms.StepResult step results} of a sorting
 * algorithm are displayed on screen.
 *
 * @author Daniel Ladendorfer
 */
public interface IVisualizer {
    /**
     * Render all {@link aero.sort.vizualizer.algorithms.StepResult step results}.
     *
     * @param stepToken the shared step token to comply with
     */
    void render(SharedStepToken stepToken);
}
