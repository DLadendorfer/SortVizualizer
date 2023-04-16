// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle;
import aero.sort.vizualizer.ui.constants.Theme;

import java.awt.*;

/**
 * BlackWhite gradient style.
 *
 * @author Daniel Ladendorfer
 */
public class Grayscale extends AbstractGradientStyle {
    @Override
    protected Color getPrimaryColor() {
        return Theme.GRAY;
    }

    @Override
    protected Color getSecondaryColor() {
        return Theme.WHITE;
    }
}
