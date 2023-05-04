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
     * @param styleContext the style context
     * @return an instance of Color
     */
    Color getColor(StyleContext styleContext);

}
