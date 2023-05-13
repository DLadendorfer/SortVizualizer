// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient;

import aero.sort.vizualizer.data.options.styles.IStyle;
import aero.sort.vizualizer.data.options.styles.StyleContext;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Abstract implementation of gradient styles.
 *
 * @author Daniel Ladendorfer
 */
public abstract class AbstractGradientStyle implements IStyle {

    /**
     * The primary color of the style.
     *
     * @return the color
     */
    protected abstract Color getPrimaryColor();

    /**
     * The secondary color of the style.
     *
     * @return the color
     */
    protected abstract Color getSecondaryColor();

    @Override
    public @NotNull Color getColor(@NotNull StyleContext context) {

        /*
         Interpolating colours by calculating the same interpolation for each of its components (R, G, B).
         Linear interpolation: Take percentage p of the first colour and percentage 1 - p of the second
         */
        double p = (double) context.value() / context.max();
        Color from = getPrimaryColor();
        Color to = getSecondaryColor();
        return new Color((int) (from.getRed() * p + to.getRed() * (1 - p)),
                (int) (from.getGreen() * p + to.getGreen() * (1 - p)),
                (int) (from.getBlue() * p + to.getBlue() * (1 - p)));
    }
}
