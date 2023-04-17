// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.IStyle;

import java.awt.*;

/**
 * Rainbow style
 *
 * @author Daniel Ladendorfer
 */
public class Rainbow implements IStyle {
    @Override
    public Color getColor(Graphics2D g2, int length, int index, int value, int max, int min) {
        return Color.getHSBColor((float) value / max, 0.7f, 0.7f);
    }
}
