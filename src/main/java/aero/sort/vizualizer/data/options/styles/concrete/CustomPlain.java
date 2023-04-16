// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle;

import java.awt.*;
import java.util.Objects;

/**
 * Custom plain style. The user can choose the preferred color.
 *
 * @author Daniel Ladendorfer
 */
public class CustomPlain extends AbstractPlainStyle {
    private final Color color;

    public CustomPlain(Color color) {
        Objects.requireNonNull(color, "The specified color must not be null");
        this.color = color;
    }

    @Override
    protected Color getPlainColor() {
        return color;
    }
}
