// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient

import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Sunset].
 *
 * @author Daniel Ladendorfer
 */
internal class SunsetTest : AbstractGradientStyleTest() {
    override fun getExpectedPrimaryColor(): Color = Theme.RED
    override fun getExpectedSecondaryColor(): Color = Theme.YELLOW
    override fun getGradientStyle(): AbstractGradientStyle = Sunset()
}