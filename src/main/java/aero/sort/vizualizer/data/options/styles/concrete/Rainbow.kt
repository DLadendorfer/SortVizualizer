// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.IStyle
import aero.sort.vizualizer.data.options.styles.StyleContext
import java.awt.Color

/**
 * Rainbow style
 *
 * @author Daniel Ladendorfer
 */
class Rainbow : IStyle {
    override fun getColor(context: StyleContext): Color =
        Color.getHSBColor(context.value.toFloat() / context.max, 0.7f, 0.7f)
}