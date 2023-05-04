// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options;

import java.awt.*;

/**
 * All options of visualizing the algorithm "animations".
 *
 * @param showShuffle  whether the shuffle process should be rendered
 * @param showSteps    whether the steps process should be rendered
 * @param stepDuration the duration of rendered steps
 * @param marker       the marker options
 * @author Daniel Ladendorfer
 */
public record VisualizationOptions(boolean showShuffle, boolean showSteps, long stepDuration,
                                   Marker marker) implements IOptions {

    /**
     * The marker options.
     *
     * @param markType  type of marker
     * @param markColor the color of the marker
     * @author Daniel Ladendorfer
     */
    public record Marker(MarkType markType, Color markColor) {

    }
}
