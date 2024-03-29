// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.plain

import java.awt.Color
import java.util.*

/**
 * Custom plain style. The user can choose the preferred color.
 *
 * @author Daniel Ladendorfer
 */
class CustomPlain(color: Color) : AbstractPlainStyle() {
    private val color: Color

    init {
        Objects.requireNonNull(color, "The specified color must not be null")
        this.color = color
    }

    override fun getPlainColor(): Color = color
}