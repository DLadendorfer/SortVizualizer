// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.SortOptions
import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle
import java.awt.Color
import java.util.*

/**
 * Custom gradient style. The user can choose the preferred colors.
 *
 * @author Daniel Ladendorfer
 */
class CustomGradient(colors: SortOptions.Colors) : AbstractGradientStyle() {
    private val colors: SortOptions.Colors

    init {
        Objects.requireNonNull(colors, "The specified primary colors must not be null")
        this.colors = colors
    }

    override fun getPrimaryColor(): Color = colors.primary
    override fun getSecondaryColor(): Color = colors.secondary
}