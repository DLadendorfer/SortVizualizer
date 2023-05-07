// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.AbstractPlainStyle
import aero.sort.vizualizer.data.options.styles.AbstractPlainStyleTest
import aero.sort.vizualizer.data.options.styles.concrete.plain.Yellow
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Yellow].
 *
 * @author Daniel Ladendorfer
 */
internal class YellowTest : AbstractPlainStyleTest() {
    override fun getExpectedColor(): Color = Theme.YELLOW
    override fun getPlainStyle(): AbstractPlainStyle = Yellow()
}