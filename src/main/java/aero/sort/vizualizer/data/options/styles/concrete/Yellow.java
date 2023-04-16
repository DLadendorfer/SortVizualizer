// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle;
import aero.sort.vizualizer.ui.constants.Theme;

import java.awt.*;

/**
 * Yellow style.
 *
 * @author Daniel Ladendorfer
 */
public class Yellow extends AbstractPlainStyle {

    @Override
    protected Color getPlainColor() {
        return Theme.YELLOW;
    }
}