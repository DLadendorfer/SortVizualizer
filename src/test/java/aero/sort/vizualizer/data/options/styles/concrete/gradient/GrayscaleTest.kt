// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient

import aero.sort.vizualizer.data.options.styles.concrete.gradient.AbstractGradientStyle
import aero.sort.vizualizer.data.options.styles.concrete.gradient.AbstractGradientStyleTest
import aero.sort.vizualizer.data.options.styles.concrete.gradient.Grayscale
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Grayscale].
 *
 * @author Daniel Ladendorfer
 */
internal class GrayscaleTest : AbstractGradientStyleTest() {
    override fun getExpectedPrimaryColor(): Color = Theme.GRAY
    override fun getExpectedSecondaryColor(): Color = Theme.WHITE
    override fun getGradientStyle(): AbstractGradientStyle = Grayscale()
}