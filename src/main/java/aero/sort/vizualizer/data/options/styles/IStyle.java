// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles;

import java.awt.*;

/**
 * Interface for all Styles.
 * <p/>
 * A style is responsible to provide the color of a specific element in the sort set,
 * based on the current style context.
 *
 * @see StyleContext
 *
 * @author Daniel Ladendorfer
 */
public interface IStyle {

    /**
     * Returns a color for the value at the given index. Additionally, the max value of the set and the min value are
     * specified.
     *
     * @param styleContext the style context
     * @return an instance of Color
     */
    Color getColor(StyleContext styleContext);

}
