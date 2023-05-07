// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.SortOptions
import aero.sort.vizualizer.data.options.styles.AbstractGradientStyle
import aero.sort.vizualizer.data.options.styles.AbstractGradientStyleTest
import aero.sort.vizualizer.data.options.styles.concrete.gradient.CustomGradient
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [CustomGradient].
 *
 * @author Daniel Ladendorfer
 */
internal class CustomGradientTest : AbstractGradientStyleTest() {
    override fun getExpectedPrimaryColor(): Color = Theme.YELLOW
    override fun getExpectedSecondaryColor(): Color = Theme.CYAN
    override fun getGradientStyle(): AbstractGradientStyle = CustomGradient(
        SortOptions.Colors(
            Theme.YELLOW, Theme.CYAN
        )
    )
}