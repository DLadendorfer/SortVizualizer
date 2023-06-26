// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.constants

import java.awt.Dimension

/**
 * Constants for the swing frame.
 *
 * @author Daniel Ladendorfer
 */
object FrameConstants {
    const val TITLE = "Sorting algorithm visualizer"

    private const val MIN_FRAME_WIDTH = 640
    private const val MIN_FRAME_HEIGHT = 480

    private const val PREFERRED_FRAME_WIDTH = 1600
    private const val PREFERRED_FRAME_HEIGHT = 876

    @JvmField
    val MIN_DIMENSION = Dimension(MIN_FRAME_WIDTH, MIN_FRAME_HEIGHT)

    @JvmField
    val PREFERRED_DIMENSION = Dimension(PREFERRED_FRAME_WIDTH, PREFERRED_FRAME_HEIGHT)
}