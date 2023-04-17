// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete;

import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle;

import java.awt.*;
import java.util.Objects;

/**
 * Custom gradient style. The user can choose the preferred colors.
 *
 * @author Daniel Ladendorfer
 */
public class CustomGradient extends AbstractGradientStyle {
    private final SortOptions.Colors colors;

    public CustomGradient(SortOptions.Colors colors) {
        Objects.requireNonNull(colors, "The specified primary colors must not be null");
        this.colors = colors;
    }

    @Override
    protected Color getPrimaryColor() {
        return colors.primary();
    }

    @Override
    protected Color getSecondaryColor() {
        return colors.secondary();
    }
}
