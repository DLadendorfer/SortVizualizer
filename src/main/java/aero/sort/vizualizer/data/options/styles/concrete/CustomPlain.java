// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle;
import aero.sort.vizualizer.ui.constants.Theme;

import java.awt.*;

/**
 * Custom plain style. The user can choose the preferred color.
 *
 * @author Daniel Ladendorfer
 */
public class CustomPlain extends AbstractPlainStyle {
    private Color color = Theme.YELLOW;
    @Override
    protected Color getPlainColor() {
        return color;
    }
}
