// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles;

import java.awt.*;

/**
 * Abstract implementation of plain styles.
 *
 * @author Daniel Ladendorfer
 */
public abstract class AbstractPlainStyle implements IStyle {

    /**
     * The plain color of the style.
     *
     * @return the color
     */
    protected abstract Color getPlainColor();

    @Override
    public Color getColor(StyleContext context) {
        return getPlainColor();
    }
}
