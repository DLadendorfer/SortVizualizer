// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.IStyle
import java.awt.Color
import java.awt.Graphics2D

/**
 * Rainbow style
 *
 * @author Daniel Ladendorfer
 */
class Rainbow : IStyle {
    override fun getColor(g2: Graphics2D, length: Int, index: Int, value: Int, max: Int, min: Int): Color =
        Color.getHSBColor(value.toFloat() / max, 0.7f, 0.7f)
}