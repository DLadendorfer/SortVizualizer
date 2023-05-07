// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.special;

import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.StyleContext;
import aero.sort.vizualizer.ui.constants.Theme;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Relative-to-Position style styles the index based on how far off it is from the correct position.
 * If it is at the correct position the color value should equal to {@link #getPrimaryColor()}.
 * If it is at the exact oposite position the color value should be equal to {@link #getSecondaryColor()}.
 *
 * @author Daniel Ladendorfer
 */
public class RelativeToPosition implements IStyle {

    private static final double PERCENTAGE = 100.0;

    /**
     * The primary color of the style.
     *
     * @return the color
     */
    protected Color getPrimaryColor() {
        return Theme.DARK_GREEN;
    }

    /**
     * The secondary color of the style.
     *
     * @return the color
     */
    protected Color getSecondaryColor() {
        return Theme.DARK_RED;
    }

    @Override
    public @NotNull Color getColor(@NotNull StyleContext context) {

        /*
         Interpolating colours by calculating the same interpolation for each of its components (R, G, B).
         Linear interpolation: Take percentage p of the first colour and percentage 1 - p of the second
         */
        double p = Math.abs((context.value() - context.index() + 1.0) / context.max());

        Color from = getSecondaryColor();
        Color to = getPrimaryColor();
        return new Color((int) (from.getRed() * p + to.getRed() * (1 - p)), (int) (from.getGreen() * p + to.getGreen() * (1 - p)), (int) (from.getBlue() * p + to.getBlue() * (1 - p)));
    }
}
