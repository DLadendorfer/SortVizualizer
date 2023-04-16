// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle;
import aero.sort.vizualizer.ui.constants.Theme;

import java.awt.*;

/**
 * Aqua gradient style.
 *
 * @author Daniel Ladendorfer
 */
public class Aqua extends AbstractGradientStyle {
    @Override
    protected Color getPrimaryColor() {
        return Theme.BLUE;
    }

    @Override
    protected Color getSecondaryColor() {
        return Theme.DEEPBLUE;
    }
}
