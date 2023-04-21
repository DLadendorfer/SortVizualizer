// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle;
import aero.sort.vizualizer.ui.constants.Theme;

import java.awt.*;

/**
 * App gradient style.
 *
 * @author Daniel Ladendorfer
 */
public class App extends AbstractGradientStyle {
    @Override
    protected Color getPrimaryColor() {
        return Theme.UI_ACCENT;
    }

    @Override
    protected Color getSecondaryColor() {
        return Theme.UI_ACCENT_2;
    }
}
