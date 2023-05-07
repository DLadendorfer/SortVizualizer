// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles;

import java.awt.*;

/**
 * The style context contains all info of a specific index to style.
 * The graphics context, the index value and the sort element count are included.
 * <p>
 * Additionally, the max value of the set and the min value are
 * specified.
 *
 * @param g2     the 2D graphics context
 * @param length the number of elements in the sort-set
 * @param index  the index to color
 * @param value  the value of the sort-set-entry
 * @param max    the max value of the sort-set-entry
 * @param min    the min value of the sort-set-entry
 * @author Daniel Ladendorfer
 */
public record StyleContext(
        Graphics2D g2,
        int length,
        int index,
        int value,
        int max,
        int min
) {
}
