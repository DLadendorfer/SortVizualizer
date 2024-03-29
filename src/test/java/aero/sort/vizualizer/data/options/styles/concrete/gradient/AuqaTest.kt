// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.gradient

import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Aqua].
 *
 * @author Daniel Ladendorfer
 */
internal class AuqaTest : AbstractGradientStyleTest() {
    override fun getExpectedPrimaryColor(): Color = Theme.BLUE
    override fun getExpectedSecondaryColor(): Color = Theme.DEEP_BLUE
    override fun getGradientStyle(): AbstractGradientStyle = Aqua()
}