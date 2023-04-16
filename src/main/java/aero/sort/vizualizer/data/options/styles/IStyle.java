// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles;

import java.awt.*;

/**
 * Interface for all Styles.
 *
 * @author Daniel Ladendorfer
 */
public interface IStyle {

    /**
     * Returns a color for the value at the given index. Additionally, the max value of the set and the min value are
     * specified.
     *
     * @param index the index to color
     * @param value the value of the sort-set-entry
     * @param max the max value of the sort-set-entry
     * @param min the min value of the sort-set-entry
     *
     * @return an instance of Color
     */
    Color getColor(int index, int value, int max, int min);

}
