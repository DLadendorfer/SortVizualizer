// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle;

import java.awt.*;
import java.util.Objects;

/**
 * Custom gradient style. The user can choose the preferred colors.
 *
 * @author Daniel Ladendorfer
 */
public class CustomGradient extends AbstractGradientStyle {
    private final Color primaryColor;
    private final Color secondaryColor;

    public CustomGradient(Color primaryColor, Color secondaryColor) {
        Objects.requireNonNull(primaryColor, "The specified primary color must not be null");
        Objects.requireNonNull(secondaryColor, "The specified secondary color must not be null");
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    @Override
    protected Color getPrimaryColor() {
        return primaryColor;
    }

    @Override
    protected Color getSecondaryColor() {
        return secondaryColor;
    }
}
